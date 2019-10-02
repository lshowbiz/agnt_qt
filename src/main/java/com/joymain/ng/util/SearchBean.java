package com.joymain.ng.util;

import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

import org.apache.commons.lang.StringUtils;

import com.google.common.collect.Lists;
import com.google.common.collect.Maps;

public class SearchBean {
	public enum Operator {
		EQ, NEQ, LIKE, GT, LT, GTE, LTE, IN, NIN
	}

	public String fieldName;
	public Object value;
	public Operator operator;

	public SearchBean(String fieldName, Object value, Operator operator) {
		this.fieldName = fieldName;
		this.value = value;
		this.operator = operator;
	}

	public static List<SearchBean> parseToList(Map<String, Object> searchParams) {
		List<SearchBean> searchBeans = Lists.newArrayList();
		for (Entry<String, Object> entry : searchParams.entrySet()) {
			// 过滤掉空值
			String key = entry.getKey();
			Object value = entry.getValue();
			if (StringUtils.isBlank((String) value)) {
				continue;
			}

			// 拆分operator与filedAttribute
			String[] names = StringUtils.split(key, "_");
			if (names.length != 2) {
				throw new IllegalArgumentException(key
						+ " is not a valid search filter name");
			}
			String filedName = names[0];
			Operator operator = Operator.valueOf(names[1]);

			// 创建searchFilter
			SearchBean filter = new SearchBean(filedName, value, operator);
			searchBeans.add(filter);
		}
		return searchBeans;
	}

	/**
	 * searchParams中key的格式为OPERATOR_FIELDNAME
	 */
	public static Map<String, SearchBean> parseToMap(
			Map<String, Object> searchParams) {
		Map<String, SearchBean> filters = Maps.newHashMap();

		for (Entry<String, Object> entry : searchParams.entrySet()) {
			// 过滤掉空值
			String key = entry.getKey();
			Object value = entry.getValue();
			if (StringUtils.isBlank((String) value)) {
				continue;
			}

			// 拆分operator与filedAttribute
			String[] names = StringUtils.split(key, "_");
			if (names.length != 2) {
				throw new IllegalArgumentException(key
						+ " is not a valid search filter name");
			}
			String filedName = names[1];
			Operator operator = Operator.valueOf(names[0]);

			// 创建searchFilter
			SearchBean filter = new SearchBean(filedName, value, operator);
			filters.put(key, filter);
		}

		return filters;
	}
}
