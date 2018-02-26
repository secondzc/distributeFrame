package com.tongyuan.distributeFrame.util;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * Created by zhangcy on 2018/2/26
 */
public class CollectionUtil {
    public static <E> Set<E> list2Set(List<E> list){
        return new HashSet<E>(list);
    }
}
