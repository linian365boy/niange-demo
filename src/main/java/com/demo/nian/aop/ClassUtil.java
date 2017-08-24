package com.demo.nian.aop;

import java.io.File;
import java.io.FileFilter;
import java.io.IOException;
import java.net.JarURLConnection;
import java.net.URL;
import java.util.Enumeration;
import java.util.HashSet;
import java.util.Set;
import java.util.jar.JarEntry;
import java.util.jar.JarFile;

import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class ClassUtil {
	private static final Logger LOGGER = LoggerFactory.getLogger(ClassUtil.class);

    /*
     * 获取类加载器
     */
    public static ClassLoader getClassLoader(){

        return Thread.currentThread().getContextClassLoader();
    }
    /*
     * 加载类
     */
    public static Class<?> loadClass(String className,boolean isInitialized){
        Class<?> cls;
        try {
            cls = Class.forName(className, isInitialized, getClassLoader());
            //Thread.currentThread().getContextClassLoader().loadClass(className);
        } catch (ClassNotFoundException e) {
            LOGGER.error("load class failure",e);
            throw new RuntimeException(e);
        }
        return cls;
    }
    /*
     * 获取指定包名下的所有类
     */
    public static Set<Class<?>> getClassSet(String packageName){

        Set<Class<?>> classSet = new HashSet<Class<?>>();
        try {
            //获取指定包下的资源
            Enumeration<URL> urls = getClassLoader().getResources(packageName.replace(".","/"));
            while(urls.hasMoreElements()){
                URL url = urls.nextElement();
                if(url != null){
                    //获取协议的名称（file或者jar）
                    String protocol = url.getProtocol();
                    if(protocol.equals("file")){
                        //获取url的路径并解码，空格的url编码值就是%20
                        String packagePath = url.getPath().replaceAll("%20"," ");
                        addClass(classSet,packagePath,packageName);
                    }else if(protocol.equals("jar")){
                        JarURLConnection jarURLConnection = (JarURLConnection)url.openConnection();
                        if(jarURLConnection != null){
                            //如果协议是jar，获取一个jarFile
                            JarFile jarFile = jarURLConnection.getJarFile();
                            if(jarFile != null){
                                //从jarFile中得到一个枚举类
                                Enumeration<JarEntry> jarEntries = jarFile.entries();
                                while(jarEntries.hasMoreElements()){
                                    //获取一个实体
                                    JarEntry jarEntry = jarEntries.nextElement();
                                    String jarEntryName = jarEntry.getName();
                                    //如果以.class结尾，则截取类的全限定名
                                    if(jarEntryName.endsWith(".calss")){
                                        String className = jarEntryName.substring(0, jarEntryName.lastIndexOf(".")).replaceAll("/", ".");
                                        //将类实例化，并加入classSet中
                                        doAddClass(classSet,className);
                                    }
                                }
                            }

                        }
                    }
                }
            }
        } catch (IOException e) {
            LOGGER.error("get class set failure", e);
            throw new RuntimeException(e);
        }
        return classSet;
    }
    private static void doAddClass(Set<Class<?>> classSet, String className) {
        //获取类的实体
        Class<?> cls = loadClass(className, false);
        //加入classSet集合中
        classSet.add(cls);

    }
    private static void addClass(Set<Class<?>> classSet, String packagePath, String packageName) {

        File[] files = new File(packagePath).listFiles(new FileFilter() {
            //过滤
            @Override
            public boolean accept(File file) {
                return file.isFile()&&file.getName().endsWith(".class")||file.isDirectory();
            }
        });
        for(File file:files){
            String fileName = file.getName();
            if(file.isFile()){
                //截取类名
                String className = fileName.substring(0,fileName.lastIndexOf("."));
                if(StringUtils.isNotEmpty(packageName)){
                    //获取类的全限定名
                    className = packageName+"."+className;
                }
                //取得类的实例并加入classSet
                doAddClass(classSet, className);
            }else{
                //如果是目录，则递归调用函数
                String subPackagePath = fileName;
                if(StringUtils.isNotEmpty(packagePath)){
                    subPackagePath = packagePath+"/"+subPackagePath;
                }
                String subPackageName = fileName;
                if(StringUtils.isNotEmpty(packageName)){
                    subPackagePath = packageName+"."+subPackagePath;
                }
                addClass(classSet, subPackagePath, subPackageName);
            }
        }
    }
}
