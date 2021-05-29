package com.suchness.realscene.common.utils;

import com.suchness.realscene.common.bean.dto.pipe.out.BuildingType;
import org.springframework.util.CollectionUtils;

import java.lang.reflect.Constructor;
import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.List;

/**
 * @Author wangchan
 * @Date 16:27 2020/8/28
 * @Description 映射sql查询的返回值
**/

public class ResultUtils {
    /**
     * 通过反射获取实体类的每个字段类型
     *
     * @return java.util.List<java.lang.Class                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                               <                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                               ?>>
     * @Date 9:57 2020/8/28
     * @Param [cls]
     **/
    public static List<Class<?>> getAllFieldsList(final Class<?> cls) {
        final List<Class<?>> allFields = new ArrayList<>();
        Class<?> currentClass = cls;
        while (currentClass != null) {
            final Field[] declaredFields = currentClass.getDeclaredFields();
            for (final Field field : declaredFields) {
                allFields.add(field.getType());
            }
            currentClass = currentClass.getSuperclass();
        }
        return allFields;
    }


    /**
     * 查询返回值转化为实体类
     *
     * @return java.util.List<T>
     * @Date 9:57 2020/8/28
     * @Param [list, clazz]
     **/
    public static <T> List<T> castEntity(List<Object[]> list, Class<T> clazz) throws Exception {
        List<T> returnList = new ArrayList<>();
        if (CollectionUtils.isEmpty(list)) {
            return returnList;
        }
        List<Class<?>> allFieldsList = getAllFieldsList(BuildingType.class);
        Class[] mod = allFieldsList.toArray(new Class[list.get(0).length]);
        for (Object[] co : list) {
            //初始化
            Constructor<T> constructor = clazz.getConstructor(mod);
            returnList.add(constructor.newInstance(co));
        }
        return returnList;
    }
}
