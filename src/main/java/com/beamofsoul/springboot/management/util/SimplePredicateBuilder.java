package com.beamofsoul.springboot.management.util;

import java.util.Map;

import com.alibaba.fastjson.JSONObject;
import com.querydsl.core.types.dsl.BooleanExpression;
import com.querydsl.core.types.dsl.PathBuilder;

import lombok.NonNull;

/**
 * @ClassName PredicateUtils
 * @Description 解析与封装QueryDSL断言的工具类
 * @author MingshuJian
 * @Date 2017年2月6日 下午3:54:40
 * @version 1.0.0
 * 
 * PS: 此工具类需要重构，包含的方法与功能过于简单、不完整且不高效
 */
public class SimplePredicateBuilder {

	private JSONObject content;
	private PathBuilder<?> entityPath;
	private BooleanExpression root;
	public static final String PREDICATE_CONTENT_KEY = "predicateContent";
	
	/**  
	 * 创建一个新的实例 SimplePredicateBuilder.    
	 */
	public <T> SimplePredicateBuilder(Class<T> clazz) {
		entityPath = new PathBuilder<T>(clazz, clazz.getSimpleName());
	}
	
	public <T> BooleanExpression build(@NonNull Map<String, Object> map) {
		if (map.containsKey(PREDICATE_CONTENT_KEY)) {
			String unformattedContent = map.get(PREDICATE_CONTENT_KEY).toString();
			content = JSONUtils.formatAndParseObject(unformattedContent);
			for (String key : content.keySet()) {
				addEqPredicate(key, content.get(key));
			}
		}
		return root;
	}
	
	private void addEqPredicate(String attr, Object value) {
		if (root == null) {
			root = entityPath.get(attr).eq(value);
		} else {
			root.and(entityPath.get(attr).eq(value));
		}
	}
}
