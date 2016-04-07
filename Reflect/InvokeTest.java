package com.it.train.reject;

import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

/**
 * Created by 55 on 2016/4/6.
 */
public class InvokeTest {

    private int age;

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public InvokeTest() {
    }

    public InvokeTest(int age) {
        this.age = age;
    }

    public InvokeTest copy(InvokeTest object) throws NoSuchMethodException, InvocationTargetException, IllegalAccessException {
        Class invokeTestInstance = object.getClass();
        Field[] fields = invokeTestInstance.getDeclaredFields();

        for(Field field:fields){
            String fieldName = field.getName();
            String getMethodName = "get" + fieldName.substring(0,1).toUpperCase() + fieldName.substring(1);
            String setMethodName = "set" + fieldName.substring(0,1).toUpperCase() + fieldName.substring(1);
            System.out.println(field.getType());
            Method getMethod = invokeTestInstance.getMethod(getMethodName, new Class[]{});
            Method setMethod = invokeTestInstance.getMethod(setMethodName, field.getType());

            Object value = getMethod.invoke(object, new Object[]{});
            setMethod.invoke(object, value);
        }

        //invokeTestInstance.getConstructor();

        return  object;
    }


    public void add(){
        System.out.println("add");
    }

    public void add(Integer a){
        System.out.println(a.intValue() + age);
    }

    public static void main(String[] args) throws Exception {
        InvokeTest invokeTest = new InvokeTest(23);
        InvokeTest newinvokeTest = new InvokeTest();
        newinvokeTest = invokeTest.copy(invokeTest);
        System.out.print(newinvokeTest.getAge());
		System.out.println(invokeTest.getClass().getConstructor(int.class).newInstance(11).getAge());
    }
}
