package com.tongyuan.distributeFrame.base;

import com.github.pagehelper.Page;

import java.io.Serializable;
import java.util.List;

/**
 * Created by zhangcy on 2018/2/12
 */
public class Parameter implements Serializable{
    private String className;
    private String methodName;
    private Object[] params;
    private Object result;

    public Parameter(String className, String methodName, Object[] params) {
        this.className = className;
        this.methodName = methodName;
        this.params = params;
    }

    public Parameter() {
    }

    public Parameter(Object result) {

        this.result = result;
    }

    public String getClassName() {
        return className;
    }

    public void setClassName(String className) {
        this.className = className;
    }

    public String getMethodName() {
        return methodName;
    }

    public void setMethodName(String methodName) {
        this.methodName = methodName;
    }

    public Object[] getParams() {
        return params;
    }

    public void setParams(Object[] params) {
        this.params = params;
    }

    public Object getResult() {
        return result;
    }

    public void setResult(Object result) {
        this.result = result;
    }

    public List<?> getResultList(){
        if(result instanceof List<?>){
            return (List<?>) result;
        }
        return null;
    }

    public Page<?> getResultPage(){
        if(result instanceof  Page<?>){
            return (Page<?>) result;
        }
        return null;
    }
}
