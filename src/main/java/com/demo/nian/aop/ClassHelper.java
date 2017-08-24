package com.demo.nian.aop;

import java.lang.annotation.Annotation;
import java.util.HashSet;
import java.util.Set;

import org.springframework.stereotype.Controller;
import org.springframework.stereotype.Service;

public class ClassHelper {
	private static final Set<Class<?>> CLASS_SET;
    static{
        //自己定义的应用包常量，你也可以定义在配置文件中
        String basePackage = ConfigConstant.BASE_PACKAGE;
        CLASS_SET = ClassUtil.getClassSet(basePackage);
    }
    /*
     * 获取应用包下的所有类
     */
    public static Set<Class<?>> getClassSet(){
        return CLASS_SET;
    }
    /*
     * 获取应用包下的所有service类
     */
    public static Set<Class<?>> getServiceClassSet(){
        Set<Class<?>> classSet = new HashSet<Class<?>>();
        for(Class<?> cls: CLASS_SET){
            if(cls.isAnnotationPresent(Service.class)){
                classSet.add(cls);
            }
        }
        return classSet;
    }
    /*
     * 获取应用包下的所有Controller
     */
    public static Set<Class<?>> getControllerClassSet(){
        Set<Class<?>> classSet = new HashSet<Class<?>>();
        for(Class<?> cls: CLASS_SET){
            if(cls.isAnnotationPresent(Controller.class)){
                classSet.add(cls);
            }
        }
        return classSet;
    }
    /*
     * 获取应用包名下的所有Bean类
     */
    public static Set<Class<?>> getBeanClassSet(){
        Set<Class<?>> beanclassSet = new HashSet<Class<?>>();
        //认为所有的service类和controller类是我们需要管理的bean
        beanclassSet.addAll(getServiceClassSet());
        beanclassSet.addAll(getControllerClassSet());
        return beanclassSet;
    }
    /*
     * 获取应用包名下某父类（或接口）的所有子类（或实现类）
     */
    public static Set<Class<?>> getClassSetBySuper(Class<?> superClass){

        Set<Class<?>> classSet = new HashSet<Class<?>>();
        for(Class<?> cls : CLASS_SET){
            if(superClass.isAssignableFrom(cls)&&!superClass.equals(cls)){
                classSet.add(cls);
            }
        }
        return classSet;
    }
    /*
     * 获取应用包名下所有带有某注解的所有类
     */
    public static Set<Class<?>> getClassSetByAnnotation(Class<? extends Annotation> annotationClass){

        Set<Class<?>> classSet = new HashSet<Class<?>>();
        for(Class<?> cls : CLASS_SET){
            if(cls.isAnnotationPresent(annotationClass)){
                classSet.add(cls);
            }
        }
        return classSet;
    }
}
