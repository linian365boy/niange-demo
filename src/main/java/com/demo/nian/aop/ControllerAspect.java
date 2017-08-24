package com.demo.nian.aop;

import java.lang.reflect.Method;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;

@Aspect(value=Controller.class)  
public class ControllerAspect extends AspectProxy {
	private static final Logger LOGGER = LoggerFactory.getLogger(ControllerAspect.class);  
    private long begin;  
  
    @Override  
    public void before(Class<?> cls, Method method, Object[] params) throws Throwable {  
        LOGGER.info("-----begin-----");  
        LOGGER.info(String.format("class: %s", cls.getName()));  
        LOGGER.info(String.format("method: %s", method.getName()));  
        begin = System.currentTimeMillis();  
    }  
  
    @Override  
    public void after(Class<?> cls, Method method, Object[] params) throws Throwable {  
        LOGGER.info(String.format("time:%dms", System.currentTimeMillis() - begin));  
        LOGGER.info("-----end-----");  
    }  
}
