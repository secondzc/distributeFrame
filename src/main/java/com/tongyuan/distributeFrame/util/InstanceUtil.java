package com.tongyuan.distributeFrame.util;


import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.HashSet;

/**
 * Created by zhangcy on 2018/2/13
 */
public class InstanceUtil {

    /**根据传入的类实例和方法名、参数名执行方法**/
    public static final Object invokeMethod(Object owner,String methodName,Object... args){
        Class<?> ownerClass = owner.getClass();
        Class<?>[] argsClass = new Class<?>[args.length];
        for(int i=0;i<args.length;i++){
            argsClass[i] = args[i].getClass();
        }
        Method method = null;
        try {
            method = ownerClass.getMethod(methodName,argsClass);
        } catch (NoSuchMethodException e) {
            e.printStackTrace();
        }
        Object result = null;
        try {
            result = method.invoke(owner,args);
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (InvocationTargetException e) {
            e.printStackTrace();
        }
        return result;
    }

    /**
     * Constructs an empty HashSet.
     */
    public static final <E> HashSet<E> newHashSet() {
        return new HashSet<E>();
    }
}
