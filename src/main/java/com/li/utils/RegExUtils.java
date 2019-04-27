package com.li.utils;

import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

//正则表达式工具类
public class RegExUtils {
    public static List<String> getMatchResult(String content, String reg){
        List<String> result=new ArrayList<String>();
        Pattern p= Pattern.compile(reg);
        Matcher m=p.matcher(content);
        while (m.find()) {
            int matchcount = m.groupCount();
            for (int i = 1; i <= matchcount; i++) {
                result.add(m.group(i));
            }
        }
        return result;
    }
}
