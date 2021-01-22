package br.com.takuara.utils;

import java.lang.reflect.Field;

public class ReflectionUtils {

    public static Double getDouble(Field field, Object entity){
        return getDouble(field, entity, null);
    }
    public static Double getDouble(Field field, Object entity, Double defValue){
        try {
            field.setAccessible(true);
            return (Double) field.get(entity);
        } catch (IllegalAccessException e) {
            return defValue;
        }
    }


}
