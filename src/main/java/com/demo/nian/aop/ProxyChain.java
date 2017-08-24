package com.demo.nian.aop;

import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.List;

import org.springframework.cglib.proxy.MethodProxy;

public class ProxyChain {
	private final Class<?> targetClass;  
    private final Object targetObject;//目标对象  
    private final Method targetMethod;//目标方法  
    private final MethodProxy methodProxy; //代理方法  
    private final Object[] methodParams;   
    private List<Proxy> proxyList = new ArrayList<Proxy>(); //代理对象列表  
    private int proxyIndex = 0;  
  
    public ProxyChain(Class<?> targetClass, Object targetObject, Method targetMethod, MethodProxy methodProxy,  
            Object[] methodParams, List<Proxy> proxyList) {  
        super();  
        this.targetClass = targetClass;  
        this.targetObject = targetObject;  
        this.targetMethod = targetMethod;  
        this.methodProxy = methodProxy;  
        this.methodParams = methodParams;  
        this.proxyList = proxyList;  
    }  
  
    public Class<?> getTargetClass() {  
        return targetClass;  
    }  
  
    public Method getTargetMethod() {  
        return targetMethod;  
    }  
  
    public Object[] getMethodParams() {  
        return methodParams;  
    }  
  
    public Object doProxyChain() throws Throwable {  
        Object methodResult;  
        if (proxyIndex < proxyList.size()) {  
            methodResult = proxyList.get(proxyIndex++).doProxy(this);  
        } else {  
            methodResult = methodProxy.invokeSuper(targetObject, methodParams);  
        }  
        return methodResult;  
    }  
}
