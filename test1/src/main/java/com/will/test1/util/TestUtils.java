package com.will.test1.util;

import java.util.Iterator;
import java.util.Map;
import java.util.Set;

public class TestUtils {

    static void dumpMap(Map<String,Integer> map){
        Set keySet = map.entrySet();
        Iterator iter = keySet.iterator();
        while(iter.hasNext()){
            Map.Entry entry = (Map.Entry)iter.next();
            String key = (String)entry.getKey();
            Integer value = (Integer) entry.getValue();

        }

    }
}
