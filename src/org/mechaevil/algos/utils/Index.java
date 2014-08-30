package org.mechaevil.algos.utils;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Index {

	private Map<String, Integer> map = new HashMap<String, Integer>();
	private List<String> list = new ArrayList<String>();

	public int indexOf(String key) {
		Integer id = map.get(key);
		if (id == null) {
			map.put(key, id = map.size());
			list.add(key);
		}
		return id;
	}
	
	public String at(int index) {
		return list.get(index);
	}

}
