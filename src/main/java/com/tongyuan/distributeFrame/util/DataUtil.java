package com.tongyuan.distributeFrame.util;

import java.util.Collection;
import java.util.Map;

/**
 * Created by zhangcy on 2018/2/12
 */
public class DataUtil {
    /**
     * 判断对象是否为NotEmpty(!null或元素>0)<br>
     * 实用于对如下对象做判断:String Collection及其子类 Map及其子类
     *
     * @param pObj
     *            待检查对象
     * @return boolean 返回的布尔值
     */
    public static final boolean isNotEmpty(Object pObj) {
        if (pObj == null || "".equals(pObj))
            return false;
        if (pObj instanceof String) {
            if (((String) pObj).trim().length() == 0) {
                return false;
            }
        } else if (pObj instanceof Collection<?>) {
            if (((Collection<?>) pObj).size() == 0) {
                return false;
            }
        } else if (pObj instanceof Map<?, ?>) {
            if (((Map<?, ?>) pObj).size() == 0) {
                return false;
            }
        }
        return true;
    }

    public static final boolean isNotEmpty(Object... objects){
        for(int i=0;i<objects.length;i++){
            if(!isNotEmpty(objects[i])){
                return false;
            }
        }
        return true;
    }
}
