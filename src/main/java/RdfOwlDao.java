import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
import org.semanticweb.owlapi.apibinding.OWLManager;
import org.semanticweb.owlapi.io.StringDocumentSource;
import org.semanticweb.owlapi.io.StringDocumentTarget;
import org.semanticweb.owlapi.model.AddAxiom;
import org.semanticweb.owlapi.model.IRI;
import org.semanticweb.owlapi.model.OWLClass;
import org.semanticweb.owlapi.model.OWLDataFactory;
import org.semanticweb.owlapi.model.OWLDataPropertyAssertionAxiom;
import org.semanticweb.owlapi.model.OWLDataPropertyExpression;
import org.semanticweb.owlapi.model.OWLIndividual;
import org.semanticweb.owlapi.model.OWLIndividualAxiom;
import org.semanticweb.owlapi.model.OWLObjectProperty;
import org.semanticweb.owlapi.model.OWLObjectPropertyAssertionAxiom;
import org.semanticweb.owlapi.model.OWLOntology;
import org.semanticweb.owlapi.model.OWLOntologyCreationException;
import org.semanticweb.owlapi.model.OWLOntologyIRIMapper;
import org.semanticweb.owlapi.model.OWLOntologyManager;
import org.semanticweb.owlapi.model.OWLSubClassOfAxiom;
import org.semanticweb.owlapi.model.RemoveAxiom;
import org.semanticweb.owlapi.util.AutoIRIMapper;
import org.semanticweb.owlapi.util.PriorityCollection;
import uk.ac.manchester.cs.owl.owlapi.OWLClassImpl;
import uk.ac.manchester.cs.owl.owlapi.OWLDataPropertyAssertionAxiomImpl;
import uk.ac.manchester.cs.owl.owlapi.OWLDataPropertyImpl;
import uk.ac.manchester.cs.owl.owlapi.OWLLiteralImplString;
import uk.ac.manchester.cs.owl.owlapi.OWLNamedIndividualImpl;
import uk.ac.manchester.cs.owl.owlapi.OWLObjectPropertyAssertionAxiomImpl;
import uk.ac.manchester.cs.owl.owlapi.OWLObjectPropertyImpl;

public class RdfOwlDao {

  //文件路径
  public static final String FILE_PATH="d://dongwuku.rdf";
   //个体属性
  public static final String INDIVIDUAL_PROPERTY="1";
  //个体关系
  public static final String INDIVIDUAL_LINK="2";

   OWLDataFactory df = OWLManager.getOWLDataFactory();
  //本地引用
   IRI DONGWU = IRI.create("http://www.semanticweb.org/administrator/ontologies/2019/3/",
      "untitled-ontology-8");

  /**
   * UFT-8模式读取文件
   * @param filePath
   * @return
   */
  public  String readTxtFile(String filePath) {
    try {
      String encoding = "UTF-8";
      File file = new File(filePath);
      StringBuffer string = new StringBuffer();
      if (file.isFile() && file.exists()) {
        InputStreamReader read = new InputStreamReader(new FileInputStream(file), encoding);
        BufferedReader bufferedReader = new BufferedReader(read);
        String lineTxt = null;
        Set<String> set = new HashSet<String>();
        while ((lineTxt = bufferedReader.readLine()) != null) {
          string.append(lineTxt).append("\r\n");
        }
        read.close();
      } else {
      }
      return string.toString();
    } catch (Exception e) {
      e.printStackTrace();
      return null;
    }

  }

  /**
   * 获取本体管理类
   * @return
   */
  public  OWLOntologyManager create() {
    OWLOntologyManager m = OWLManager.createOWLOntologyManager();
    PriorityCollection<OWLOntologyIRIMapper> iriMappers = m.getIRIMappers();
    iriMappers.add(new AutoIRIMapper(new File("materializedOntologies"), true));
    return m;
  }

  /**
   * 加载动物信息
   * @param m
   * @return
   * @throws OWLOntologyCreationException
   */
  private  OWLOntology loadDongwu(OWLOntologyManager m)
      throws OWLOntologyCreationException {
    String content = readTxtFile(FILE_PATH);
    return m.loadOntologyFromOntologyDocument(new StringDocumentSource(content));
  }

  /**
   * 获取个体信息内容
   * @param name
   * @return
   */
  public  Map<String,List<String>> getIndividualInfo(String name){
    Map<String,List<String>> map=new HashMap<String,List<String>>();
    try{
      OWLOntologyManager m = create();
      OWLOntology o = loadDongwu(m);
      //查询数据
      OWLIndividual matthew = df.getOWLNamedIndividual(DONGWU + "#", name);
      Set<OWLIndividualAxiom> qses = o.getAxioms(matthew);
      for (OWLIndividualAxiom ax : qses) {
        String typeName = ax.getAxiomType().getName();
        String type = "";
        String value = "";

        if ("ObjectPropertyAssertion".equalsIgnoreCase(typeName)) {
          type = ((OWLObjectPropertyImpl) (((OWLObjectPropertyAssertionAxiomImpl) ax)
              .getProperty())).getIRI().getRemainder().get();
          value = ((OWLNamedIndividualImpl) (((OWLObjectPropertyAssertionAxiomImpl) ax)
              .getObject())).getIRI().getRemainder().get();
        } else if("DataPropertyAssertion".equalsIgnoreCase(typeName)){
          type = ((OWLDataPropertyImpl) ((OWLDataPropertyAssertionAxiomImpl) ax).getProperty())
              .getIRI().getRemainder().get();
          value = ((OWLLiteralImplString) ((OWLDataPropertyAssertionAxiomImpl) ax).getObject())
              .getLiteral();
        }
        if(!"".equalsIgnoreCase(type)){
          if(!map.containsKey(type)){//判断类型是否保存，如果没保存，那么保存类型
            map.put(type,new ArrayList<String>());
          }
          //获取类型添加数据
          map.get(type).add(value);
        }
      }
    }catch (Exception e){
      e.printStackTrace();
    }
    return map;
  }

  /**
   * 新增属性信息
   * @param individualName 个体名称
   * @param type 类型
   * @param fieldName 字段名称
   * @param value 值
   */
  public  void addIndividualInfo(String individualName,String type,String fieldName,String value){
    try{
      OWLOntologyManager m = create();
      OWLOntology o = loadDongwu(m);
      //个体信息
      OWLIndividual individual = df.getOWLNamedIndividual(DONGWU + "#", individualName);
      if(INDIVIDUAL_PROPERTY.equals(type)){
        //个体属性
        //得到属性信息
        OWLDataPropertyExpression owlDataProperty = df.getOWLDataProperty(DONGWU + "#", fieldName);
        //得到属性值
        OWLDataPropertyAssertionAxiom assertion = df
            .getOWLDataPropertyAssertionAxiom(owlDataProperty, individual, value);
        //添加操作
        AddAxiom addAxiomChange = new AddAxiom(o, assertion);
        //执行变更
        m.applyChange(addAxiomChange);
      }else if(INDIVIDUAL_LINK.equals(type)){
        //个体关系
        OWLObjectProperty linkProperty = df.getOWLObjectProperty(DONGWU + "#", fieldName);
        //字段信息
        OWLIndividual linkIndividual = df.getOWLNamedIndividual(DONGWU + "#", value);
        OWLObjectPropertyAssertionAxiom assertion = df
            .getOWLObjectPropertyAssertionAxiom(linkProperty, individual, linkIndividual);
        // Finally, add the axiom to our ontology and save
        AddAxiom addAxiomChange = new AddAxiom(o, assertion);
        m.applyChange(addAxiomChange);
      }
      File output = new File(FILE_PATH);
      IRI documentIRI2 = IRI.create(output);
      m.saveOntology(o, documentIRI2);
    }catch (Exception e){
      e.printStackTrace();
    }
  }

  /**
   * 删除
   * @param individualName
   * @param type
   * @param fieldName
   * @param value
   */
  public  void removeIndividualInfo(String individualName,String type,String fieldName,String value){
    try{
      OWLOntologyManager m = create();
      OWLOntology o = loadDongwu(m);
      //个体信息
      OWLIndividual individual = df.getOWLNamedIndividual(DONGWU + "#", individualName);
      if(INDIVIDUAL_PROPERTY.equals(type)){
        //个体属性
        //得到属性信息
        OWLDataPropertyExpression owlDataProperty = df.getOWLDataProperty(DONGWU + "#", fieldName);
        //得到属性值
        OWLDataPropertyAssertionAxiom assertion = df
            .getOWLDataPropertyAssertionAxiom(owlDataProperty, individual, value);
        //删除操作
        RemoveAxiom removeAxiomChange = new RemoveAxiom(o, assertion);
        //执行变更
        m.applyChange(removeAxiomChange);
      }else if(INDIVIDUAL_LINK.equals(type)){
        //个体关系
        OWLObjectProperty linkProperty = df.getOWLObjectProperty(DONGWU + "#", fieldName);
        //字段信息
        OWLIndividual linkIndividual = df.getOWLNamedIndividual(DONGWU + "#", value);
        OWLObjectPropertyAssertionAxiom assertion = df
            .getOWLObjectPropertyAssertionAxiom(linkProperty, individual, linkIndividual);
        //删除操作
        RemoveAxiom removeAxiomChange = new RemoveAxiom(o, assertion);
        //执行变更
        m.applyChange(removeAxiomChange);
      }
      File output = new File(FILE_PATH);
      IRI documentIRI2 = IRI.create(output);
      m.saveOntology(o, documentIRI2);
    }catch (Exception e){
      e.printStackTrace();
    }
  }

  /**
   * 删除字段所有属性
   * @param individualName
   * @param type
   * @param fieldName
   */
  public  void removeIndividualInfo(String individualName,String type,String fieldName){
    try{
      Map<String,List<String>> typeValues= getIndividualInfo(individualName);
      List<String> deleteValueList=typeValues.get(fieldName);
      for(String value:deleteValueList){
        removeIndividualInfo( individualName, type, fieldName,value);
      }
    }catch (Exception e){
      e.printStackTrace();
    }
  }

  /**
   * 修改操作， 如果旧值为空，表示把字段对应的所有值清空，并添加新值
   * @param individualName 个体实体名称
   * @param type 操作字段类型
   * @param fieldName 操作字段名称
   * @param oldValue 旧值
   * @param newValue 新值
   */
  public  void updateIndividualInfo(String individualName,String type,String fieldName,String oldValue,String newValue){

    if(oldValue!=null&&oldValue.trim().length()>0){
      removeIndividualInfo(individualName,type,fieldName,oldValue);
    }else{
      removeIndividualInfo(individualName,type,fieldName);
    }
    addIndividualInfo(individualName,type,fieldName,newValue);
  }

  /**
   * 查询级联关系
   * @param name
   * @return
   */
  public  List<String> queryLink(String name){
    List<String> resultList=new ArrayList<String>();
    try{
      OWLOntologyManager m = create();
      OWLOntology o = loadDongwu(m);
      OWLClass dfOWLClass = df.getOWLClass(DONGWU + "#", name);
      Set<OWLSubClassOfAxiom> owlSubClassOfAxioms = o.getSubClassAxiomsForSuperClass(dfOWLClass);
      for (OWLSubClassOfAxiom oo : owlSubClassOfAxioms) {
        resultList.add(((OWLClassImpl) oo.getSubClass()).getIRI().getRemainder().get());
      }
    }catch (Exception e){
      e.printStackTrace();
    }
    return resultList;

  }


  public static void main(String [] str){
    RdfOwlDao rdfOwlDao=new RdfOwlDao();
    //查询
    System.out.println(rdfOwlDao.getIndividualInfo("黑长尾雉"));
    //增加
  //  rdfOwlDao.addIndividualInfo("黑鹇",RdfOwlDao.INDIVIDUAL_LINK,"similarity","测试");
    //删除\
  //  rdfOwlDao.removeIndividualInfo("黑鹇",RdfOwlDao.INDIVIDUAL_LINK,"similarity","测试");
    //修改
   // rdfOwlDao.updateIndividualInfo("黑鹇",RdfOwlDao.INDIVIDUAL_PROPERTY,"image",null,"测试");
    //查询级联关系
  // System.out.println(rdfOwlDao.queryLink("鸟类"));
  }

}
