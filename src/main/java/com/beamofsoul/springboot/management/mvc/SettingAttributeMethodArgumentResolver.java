package com.beamofsoul.springboot.management.mvc;

import java.util.stream.Collectors;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.lang3.StringUtils;
import org.springframework.core.MethodParameter;
import org.springframework.web.bind.support.WebDataBinderFactory;
import org.springframework.web.context.request.NativeWebRequest;
import org.springframework.web.method.support.HandlerMethodArgumentResolver;
import org.springframework.web.method.support.ModelAndViewContainer;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.beamofsoul.springboot.management.control.CustomHttpServletRequestWrapper;
import com.beamofsoul.springboot.management.util.HttpServletRequestUtils;

/**
 * @ClassName SettingAttributeMethodArgumentResolver
 * @Description 自定义注解Attribute作为方法参数时的解析器
 * @author MingshuJian
 * @Date 2017年6月12日 下午2:52:08
 * @version 1.0.0
 */
public class SettingAttributeMethodArgumentResolver implements HandlerMethodArgumentResolver{

	@Override
	public boolean supportsParameter(MethodParameter parameter) {
		return parameter.hasParameterAnnotation(Attribute.class);
	}

	@Override
	public Object resolveArgument(MethodParameter parameter, ModelAndViewContainer mavContainer,
			NativeWebRequest webRequest, WebDataBinderFactory binderFactory) throws Exception {
		HttpServletRequest request = webRequest.getNativeRequest(HttpServletRequest.class);
		if (request instanceof CustomHttpServletRequestWrapper) {
			String requestBody = new String(((CustomHttpServletRequestWrapper) request).getRequestBody(), HttpServletRequestUtils.DEFAULT_CHARSET);
			JSONObject attributes = JSONObject.parseObject(requestBody);
			
			try {
				Attribute parameterAnnotation = parameter.getParameterAnnotation(Attribute.class);
				String key = parameterAnnotation.value();
				if (StringUtils.isBlank(key)) key = parameter.getParameterName();
				if (attributes.containsKey(key)) {
					Class<?> parameterType = parameter.getParameterType();
					if (parameterType.equals(String.class)) return attributes.get(key).toString();
					else if (parameterType.equals(Long.class)) return Long.valueOf(attributes.get(key).toString());
					else if (parameterType.equals(Integer.class)) return Integer.valueOf(attributes.get(key).toString());
					else if (parameterType.equals(Double.class)) return Double.valueOf(attributes.get(key).toString());
					else if (parameterType.equals(Float.class)) return Float.valueOf(attributes.get(key).toString());
					else if (parameterType.equals(Boolean.class)) return Boolean.valueOf(attributes.get(key).toString());
					else if (parameterType.equals(Byte.class)) return Byte.valueOf(attributes.get(key).toString());
					else if (parameterType.equals(Short.class)) return Short.valueOf(attributes.get(key).toString());
					else if (parameterType.equals(JSONObject.class)) return attributes.getJSONObject(key);
					else if (parameterType.equals(JSONArray.class)) return attributes.getJSONArray(key);
					else if (parameterType.equals(Object.class)) return attributes.get(key);
					
					else if (parameterType.equals(Long[].class)) return attributes.getJSONArray(key).stream().map(e -> Long.valueOf((String) e)).collect(Collectors.toList()).toArray(new Long[]{});
				}
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		return null;
	}
}
