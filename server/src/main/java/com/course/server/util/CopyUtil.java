package com.course.server.util;

import org.springframework.beans.BeanUtils;
import org.springframework.util.CollectionUtils;

import java.util.ArrayList;
import java.util.List;

public class CopyUtil {

    public static <T> List<T> copyList(List source, Class<T> clazz) {
        List<T> target = new ArrayList<>();
        if (!CollectionUtils.isEmpty(source)){
            if (!CollectionUtils.isEmpty(source)){
                for (Object c: source) {
                    T obj = copy(c, clazz);
                    target.add(obj);
                }
            }
        }
        return target;
    }

    public static <T> T copy(Object source, Class<T> clazz) {
        if (source == null) {
            return null;
        }
        T obj = null;
        try {
            obj = clazz.newInstance();
        } catch (Exception e) {
            e.printStackTrace();
        }
        BeanUtils.copyProperties(source, obj);
        return obj;
    }
}
