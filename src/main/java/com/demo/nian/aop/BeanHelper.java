package com.demo.nian.aop;

import java.util.HashMap;
import java.util.Map;
import java.util.Set;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class BeanHelper {
	
	//用于存放bean类及bean实例之间的映射关系
    private static final Map<Class<?>,Object> BEAN_MAP = new HashMap<Class<?>,Object>();
    static{
        Set<Class<?>> beanClassSet = ClassHelper.getBeanClassSet();
        for(Class<?> cls : beanClassSet){
            Object obj = ReflectionUtil.newInstance(cls);
            BEAN_MAP.put(cls, obj);
        }
    }

    /*
     * 获取bean映射
     */
    public static Map<Class<?>,Object> getBeanMap(){

        return BEAN_MAP;
    }
    /*
     * 获取bean实例
     */
    public static <T> T getBean(Class<T> cls){
        if(!BEAN_MAP.containsKey(cls)){
            throw new RuntimeException("can not get bean by class");
        }
        return (T)BEAN_MAP.get(cls);
    }

    public static void setBean(Class<?> cls,Object obj){
        BEAN_MAP.put(cls, obj);
    }
}
