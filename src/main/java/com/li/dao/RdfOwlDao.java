package com.li.dao;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.stream.Stream;
import org.semanticweb.owlapi.apibinding.OWLManager;
import org.semanticweb.owlapi.io.StringDocumentSource;
import org.semanticweb.owlapi.model.AddAxiom;
import org.semanticweb.owlapi.model.IRI;
import org.semanticweb.owlapi.model.OWLAxiom;
import org.semanticweb.owlapi.model.OWLClass;
import org.semanticweb.owlapi.model.OWLClassExpression;
import org.semanticweb.owlapi.model.OWLDataFactory;
import org.semanticweb.owlapi.model.OWLDataPropertyAssertionAxiom;
import org.semanticweb.owlapi.model.OWLDataPropertyExpression;
import org.semanticweb.owlapi.model.OWLEntity;
import org.semanticweb.owlapi.model.OWLIndividual;
import org.semanticweb.owlapi.model.OWLIndividualAxiom;
import org.semanticweb.owlapi.model.OWLLiteral;
import org.semanticweb.owlapi.model.OWLNamedIndividual;
import org.semanticweb.owlapi.model.OWLObjectProperty;
import org.semanticweb.owlapi.model.OWLObjectPropertyAssertionAxiom;
import org.semanticweb.owlapi.model.OWLOntology;
import org.semanticweb.owlapi.model.OWLOntologyCreationException;
import org.semanticweb.owlapi.model.OWLOntologyIRIMapper;
import org.semanticweb.owlapi.model.OWLOntologyManager;
import org.semanticweb.owlapi.model.OWLSubClassOfAxiom;
import org.semanticweb.owlapi.model.RemoveAxiom;
import org.semanticweb.owlapi.util.AutoIRIMapper;
import org.semanticweb.owlapi.util.OWLEntityRemover;
import org.semanticweb.owlapi.util.PriorityCollection;
import org.springframework.stereotype.Component;
import uk.ac.manchester.cs.owl.owlapi.OWLClassAssertionAxiomImpl;
import uk.ac.manchester.cs.owl.owlapi.OWLClassImpl;
import uk.ac.manchester.cs.owl.owlapi.OWLDataPropertyAssertionAxiomImpl;
import uk.ac.manchester.cs.owl.owlapi.OWLDataPropertyImpl;
import uk.ac.manchester.cs.owl.owlapi.OWLLiteralImplString;
import uk.ac.manchester.cs.owl.owlapi.OWLNamedIndividualImpl;
import uk.ac.manchester.cs.owl.owlapi.OWLObjectPropertyAssertionAxiomImpl;
import uk.ac.manchester.cs.owl.owlapi.OWLObjectPropertyImpl;
@Component("rdfOwlDao")
public class RdfOwlDao {

  //文件路径
  public static final String FILE_PATH = "d://dongwuku.rdf";
  //个体属性
  public static final String INDIVIDUAL_PROPERTY = "1";
  //个体关系
  public static final String INDIVIDUAL_LINK = "2";
  //OWLManager为owi api的管理类，
  OWLDataFactory df = OWLManager.getOWLDataFactory();
  //本地引用
  //每一个本地都有一个唯一标识  前缀和后缀拼在一起，正好和xml本体是一致的

  IRI DONGWU = IRI
      .create("http://www.semanticweb.org/administrator/ontologies/2019/3/untitled-ontology-8");

  /**
   * UFT-8模式读取文件
   */
  public String readTxtFile(String filePath) {
    try {
      String encoding = "UTF-8";//文件编码格式
      File file = new File(filePath);//文件
      StringBuffer string = new StringBuffer();
      if (file.isFile() && file.exists()) {//文件是否存在
        InputStreamReader read = new InputStreamReader(new FileInputStream(file), encoding);//输入流等信息
        BufferedReader bufferedReader = new BufferedReader(read);//读取流
        String lineTxt = null;
        while ((lineTxt = bufferedReader.readLine()) != null) {//一行一行读取 当读取完毕会返回null结束
          string.append(lineTxt).append("\r\n");//拼接字符串
        }
        bufferedReader.close();
        read.close();//关闭
      } else {
      }
      return string.toString();//返回结果
    } catch (Exception e) {
      e.printStackTrace();
      return null;
    }

  }

  /**
   * 获取本体管理类
   */
  public OWLOntologyManager create() {
    //创建owl api的管理类
    OWLOntologyManager m = OWLManager.createOWLOntologyManager();
    //获取mapper映射
    PriorityCollection<OWLOntologyIRIMapper> iriMappers = m.getIRIMappers();
    //这行我也不懂，嘿嘿，官网就是这么个例子
    iriMappers.add(new AutoIRIMapper(new File("materializedOntologies"), true));
    return m;
  }

  /**
   * 加载动物信息
   */
  private OWLOntology loadDongwu(OWLOntologyManager m)
      throws OWLOntologyCreationException {
    String content = readTxtFile(FILE_PATH);//读取动物信息 rdf内容
    return m.loadOntologyFromOntologyDocument(
        new StringDocumentSource(content));//将rdf内容进行加载管理，这样 wolapi就能知道里面有什么内容
  }

  /**
   * 获取个体信息内容
   */
  public Map<String, List<String>> getIndividualInfo(String name) {
    Map<String, List<String>> map = new HashMap<String, List<String>>();
    try {
      OWLOntologyManager m = create();//获取管理类
      OWLOntology o = loadDongwu(m);//加载动物文件内容
      //查询数据
      OWLIndividual matthew = df.getOWLNamedIndividual(DONGWU + "#", name);//获得一个个体信息
      Set<OWLIndividualAxiom> qses = o.getAxioms(matthew);//查询这个个体信息
      for (OWLIndividualAxiom ax : qses) {//遍历查询的集合
        String typeName = ax.getAxiomType().getName();
        String type = "";
        String value = "";
        if ("ClassAssertion".equalsIgnoreCase(typeName)) {
          OWLClassExpression owlClass = ((OWLClassAssertionAxiomImpl) ax).getClassExpression();
          value = ((OWLClassImpl) owlClass).getIRI().getRemainder().get();
          type = "CLASS_";
        } else if ("ObjectPropertyAssertion".equalsIgnoreCase(typeName)) {
          //关系信息
          type = ((OWLObjectPropertyImpl) (((OWLObjectPropertyAssertionAxiomImpl) ax)
              .getProperty())).getIRI().getRemainder().get();//获取关系类型 比如 kind siblings similarity 等
          value = ((OWLNamedIndividualImpl) (((OWLObjectPropertyAssertionAxiomImpl) ax)
              .getObject())).getIRI().getRemainder().get();//获取对应的值
        } else if ("DataPropertyAssertion".equalsIgnoreCase(typeName)) {
          //属性信息
          type = ((OWLDataPropertyImpl) ((OWLDataPropertyAssertionAxiomImpl) ax).getProperty())
              .getIRI().getRemainder().get();//image intro 等
          value = ((OWLLiteralImplString) ((OWLDataPropertyAssertionAxiomImpl) ax).getObject())
              .getLiteral();//获取对应的值
        }
        if (!"".equalsIgnoreCase(type)) {//类型不为空
          if (!map.containsKey(type)) {//判断类型是否保存，如果没保存，那么保存类型
            map.put(type, new ArrayList<String>());
          }
          //获取类型添加数据
          map.get(type).add(value);
        }
      }
    } catch (Exception e) {
      e.printStackTrace();
    }
    return map;
  }

  /**
   * 新增属性信息
   *
   * @param individualName 个体名称
   * @param type 类型
   * @param fieldName 字段名称
   * @param value 值
   */
  public void addIndividualInfo(String individualName, String type, String fieldName,
      String value) {
    try {
      OWLOntologyManager m = create();
      OWLOntology o = loadDongwu(m);
      //个体信息
      OWLIndividual individual = df.getOWLNamedIndividual(DONGWU + "#", individualName);
      if (INDIVIDUAL_PROPERTY.equals(type)) {//属性
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
      } else if (INDIVIDUAL_LINK.equals(type)) {//关系
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
      //将本地信息保存到文件
      File output = new File(FILE_PATH);
      IRI documentIRI2 = IRI.create(output);
      m.saveOntology(o, documentIRI2);
    } catch (Exception e) {
      e.printStackTrace();
    }
  }

  /**
   * 删除
   */
  public void removeIndividualInfo(String individualName, String type, String fieldName,
      String value) {
    try {
      OWLOntologyManager m = create();
      OWLOntology o = loadDongwu(m);
      //个体信息
      OWLIndividual individual = df.getOWLNamedIndividual(DONGWU + "#", individualName);
      if (INDIVIDUAL_PROPERTY.equals(type)) {
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
      } else if (INDIVIDUAL_LINK.equals(type)) {
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
    } catch (Exception e) {
      e.printStackTrace();
    }
  }

  /**
   * 删除字段所有属性
   */
  public void removeIndividualInfo(String individualName, String type, String fieldName) {
    try {
      Map<String, List<String>> typeValues = getIndividualInfo(individualName);
      List<String> deleteValueList = typeValues.get(fieldName);
      for (String value : deleteValueList) {
        removeIndividualInfo(individualName, type, fieldName, value);
      }
    } catch (Exception e) {
      e.printStackTrace();
    }
  }

  /**
   * 修改操作， 如果旧值为空，表示把字段对应的所有值清空，并添加新值
   *
   * @param individualName 个体实体名称
   * @param type 操作字段类型
   * @param fieldName 操作字段名称
   * @param oldValue 旧值
   * @param newValue 新值
   */
  public void updateIndividualInfo(String individualName, String type, String fieldName,
      String oldValue, String newValue) {

    if (oldValue != null && oldValue.trim().length() > 0) {
      removeIndividualInfo(individualName, type, fieldName, oldValue);
    } else {
      removeIndividualInfo(individualName, type, fieldName);
    }
    addIndividualInfo(individualName, type, fieldName, newValue);
  }

  /**
   * 查询级联关系
   */
  public List<String> queryLink(String name) {
    List<String> resultList = new ArrayList<String>();
    try {
      OWLOntologyManager m = create();
      OWLOntology o = loadDongwu(m);
      OWLClass dfOWLClass = df.getOWLClass(DONGWU + "#", name);
      //查询父子结构数据
      Set<OWLSubClassOfAxiom> owlSubClassOfAxioms = o.getSubClassAxiomsForSuperClass(dfOWLClass);
      for (OWLSubClassOfAxiom oo : owlSubClassOfAxioms) {
        resultList.add(((OWLClassImpl) oo.getSubClass()).getIRI().getRemainder().get());
      }
    } catch (Exception e) {
      e.printStackTrace();
    }
    return resultList;

  }

  /**
   * 查询科对应的动物
   */
  public List<String> queryIndividualsType(String name) {
    List<String> resultList = new ArrayList<String>();
    try {
      OWLOntologyManager m = create();
      OWLOntology o = loadDongwu(m);
      Object[] oo = o.individualsInSignature().toArray();
      for (Object obj : oo) {
        String nameTemp = ((OWLNamedIndividualImpl) obj).getIRI().getRemainder().get();
        Map<String, List<String>> mapListTemp = getIndividualInfo(nameTemp);
        if (mapListTemp.containsKey("CLASS_")) {
          if (mapListTemp.get("CLASS_").contains(name)) {
            resultList.add(nameTemp);
          }
        }
      }
    } catch (Exception e) {
      e.printStackTrace();
    }
    return resultList;
  }

  /**
   * 添加个体
   */
  public void addIndividualInfo(String individualName, String type) {
    try {
      OWLOntologyManager m = create();
      OWLOntology o = loadDongwu(m);
      OWLClass typeClass = df.getOWLClass(DONGWU + "#", type);
      OWLIndividual linkIndividual = df.getOWLNamedIndividual(DONGWU + "#", individualName);
      OWLAxiom ax = df.getOWLClassAssertionAxiom(typeClass, linkIndividual);
      AddAxiom addAxiom = new AddAxiom(o, ax);
      m.applyChange(addAxiom);
      File output = new File(FILE_PATH);
      IRI documentIRI2 = IRI.create(output);
      m.saveOntology(o, documentIRI2);
    } catch (Exception e) {
      e.printStackTrace();
    }
  }

  /**
   * 删除个体
   */
  public void removeIndividualInfo(String individualName) {
    try {
      OWLOntologyManager m = create();
      OWLOntology o = loadDongwu(m);
      OWLEntityRemover remover = new OWLEntityRemover(Collections.singleton(o));

      Object[] oo = o.individualsInSignature().toArray();
      for (Object obj : oo) {
        String nameTemp = ((OWLNamedIndividualImpl) obj).getIRI().getRemainder().get();
        if (individualName.equalsIgnoreCase(nameTemp)) {
          ((OWLNamedIndividualImpl) obj).accept(remover);
        }
      }
      m.applyChanges(remover.getChanges());
      File output = new File(FILE_PATH);
      IRI documentIRI2 = IRI.create(output);
      m.saveOntology(o, documentIRI2);
    } catch (Exception e) {
      e.printStackTrace();
    }
  }

  public void addClass(String type, String subType) {
    try {
      OWLOntologyManager m = create();
      OWLOntology o = loadDongwu(m);
      OWLClass clsType = df.getOWLClass(IRI.create(DONGWU + "#", type));
      OWLClass clsSubType = df.getOWLClass(IRI.create(DONGWU + "#", subType));
      OWLAxiom axiom = df.getOWLSubClassOfAxiom(clsSubType, clsType);
      AddAxiom addAxiom = new AddAxiom(o, axiom);
      m.applyChange(addAxiom);
      File output = new File(FILE_PATH);
      IRI documentIRI2 = IRI.create(output);
      m.saveOntology(o, documentIRI2);
    } catch (Exception e) {
      e.printStackTrace();
    }
  }

  public void removeClass(String type, String subType) {
    try {
      OWLOntologyManager m = create();
      OWLOntology o = loadDongwu(m);
      OWLClass clsType = df.getOWLClass(IRI.create(DONGWU + "#", type));
      OWLClass clsSubType = df.getOWLClass(IRI.create(DONGWU + "#", subType));
      OWLAxiom axiom = df.getOWLSubClassOfAxiom(clsSubType, clsType);
      RemoveAxiom removeAxiom = new RemoveAxiom(o, axiom);
      m.applyChange(removeAxiom);
      File output = new File(FILE_PATH);
      IRI documentIRI2 = IRI.create(output);
      m.saveOntology(o, documentIRI2);
    } catch (Exception e) {
      e.printStackTrace();
    }
  }

 /* public static void main(String[] str) {
    RdfOwlDao rdfOwlDao = new RdfOwlDao();
    //查询
    //System.out.println(rdfOwlDao.getIndividualInfo("稚科"));
   // System.out.println(rdfOwlDao.getIndividualInfo("矮脚鸡"));
    //增加
    //  rdfOwlDao.addIndividualInfo("黑鹇",RdfOwlDao.INDIVIDUAL_LINK,"similarity","测试");
    //删除\
    //rdfOwlDao.removeIndividualInfo("黑鹇",RdfOwlDao.INDIVIDUAL_LINK,"similarity","测试");
    //修改
    // rdfOwlDao.updateIndividualInfo("黑鹇",RdfOwlDao.INDIVIDUAL_PROPERTY,"image",null,"测试11");
    //查询级联关系
    //System.out.println(rdfOwlDao.queryLink("鸟类"));
    //查询稚科下面的动物
     //System.out.println(rdfOwlDao.queryIndividualsType("矮脚鸡"));;
    //添加测试个体挂在稚科下面
      // rdfOwlDao.addIndividualInfo("测试","稚科");
       //rdfOwlDao.addIndividualInfo("测试",RdfOwlDao.INDIVIDUAL_PROPERTY,"image","测试图片");
      //rdfOwlDao.addIndividualInfo("测试",RdfOwlDao.INDIVIDUAL_PROPERTY,"intro","测试wenzi ");
    //删除个体
   //  rdfOwlDao.removeIndividualInfo("测试");
    //添加类关系
    //rdfOwlDao.addClass("动物", "两栖动物");
    //删除类关系
   // rdfOwlDao.removeClass("动物", "两栖动物");

  }
*/
}
