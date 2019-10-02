package com.joymain.ng.util;

import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

import org.apache.commons.lang.StringUtils;

import com.google.common.collect.Lists;
import com.google.common.collect.Maps;

public class OrderBy {
	public enum OrderByDirection {
	    ASC, DESC;
	}
	public String columnOrProperty;
    public OrderByDirection direction = OrderByDirection.ASC;
	public OrderBy(String columnOrProperty, OrderByDirection direction) {
		this.columnOrProperty = columnOrProperty;
		this.direction = direction;
	}
	
	public OrderBy(String columnOrProperty) {
        this(columnOrProperty, OrderByDirection.ASC);
    }
    
	public static List<OrderBy> parseToList(Map<String, String> orderParams){
		List<OrderBy> orderBys = Lists.newArrayList();
		for (Entry<String, String> entry :orderParams.entrySet()){
			String key = entry.getKey();
			String value = entry.getValue();
			if (StringUtils.isBlank((String) value)) {
				continue;
			}
			
			OrderBy orderBy = new OrderBy(key,OrderByDirection.valueOf(value));
			orderBys.add(orderBy);
		}
		return orderBys;
	}
	public static Map<String, OrderBy> parseToMap(Map<String, String> orderParams){
		Map<String, OrderBy> orderBys = Maps.newHashMap();
		for (Entry<String, String> entry :orderParams.entrySet()){
			String key = entry.getKey();
			String value = entry.getValue();
			if (StringUtils.isBlank((String) value)) {
				continue;
			}
			
			OrderBy orderBy = new OrderBy(key,OrderByDirection.valueOf(value));
			orderBys.put(key, orderBy);
		}
		return orderBys;
		
		
	}
    
}
