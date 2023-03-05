package xyz.holomek.utils;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.HashMap;
import java.util.Map;
import java.util.TreeMap;


/*
Author: @Maxmilianos
 */
public class UtilValue implements Comparator<Object> {
	
    private Map<Object, Integer> base;

    private Boolean low;
    
    public UtilValue(Map<Object, Integer> base, Boolean lowToUp) {
        this.base = base;
        this.low = lowToUp;
    }

    public int compare(Object a, Object b) {
    	if (low) {
            if (base.get(a) < base.get(b)) {
                return -1;
            } else {
                return 1;
            }
    	} else {
            if (base.get(a) >= base.get(b)) {
                return -1;
            } else {
                return 1;
            }
    	}
    }

    // metoda pro ziskani mapy s ciselnym razením od nejvyssiho po nejmensi
    public static HashMap<Integer, Object> convertToHighestLowest(HashMap<Object, Integer> defaultMap) {
    	HashMap<Object, Integer> map = new HashMap<Object, Integer>();
    	UtilValue bvc = new UtilValue(map, false);
        TreeMap<Object, Integer> sorted_map = new TreeMap<Object, Integer>(bvc);
    	for (Object o : defaultMap.keySet()) {
    		map.put(o, defaultMap.get(o));
    	}
    	sorted_map.putAll(map);
        HashMap<Integer, Object> ids = new HashMap<Integer, Object>();
        for (Object si : sorted_map.keySet()) {
        	ids.put(ids.size(), si);
        }
		return ids;
	}

    // metoda pro ziskani mapy s ciselnym razením od nejmensiho pro nejvetsi
    public static HashMap<Integer, Object> convertToLowestHighest(HashMap<Object, Integer> defaultMap) {
    	HashMap<Object, Integer> map = new HashMap<Object, Integer>();
    	UtilValue bvc = new UtilValue(map, true);
        TreeMap<Object, Integer> sorted_map = new TreeMap<Object, Integer>(bvc);
    	for (Object o : defaultMap.keySet()) {
    		map.put(o, defaultMap.get(o));
    	}
    	sorted_map.putAll(map);
        HashMap<Integer, Object> ids = new HashMap<Integer, Object>();
        for (Object si : sorted_map.keySet()) {
        	ids.put(ids.size(), si);
        }
		return ids;
	}
}