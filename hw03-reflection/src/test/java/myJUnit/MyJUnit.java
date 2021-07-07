package myJUnit;

import ru.gostohov.annotations.After;
import ru.gostohov.annotations.Before;
import ru.gostohov.annotations.Test;
import java.lang.reflect.Method;
import java.util.ArrayList;

public class MyJUnit {

    public static <T> void runTestForClass(Class<T> type) {
        int methodCount = 0;
        int expCount = 0 ;
        int sucCount = 0 ;

        MyJUnit myJUnit = new MyJUnit();

        try {
            Object obj = type.getDeclaredConstructor().newInstance();
            Method[] methods = type.getMethods();
            ArrayList<Method> beforeMethods = new ArrayList<Method>();
            ArrayList<Method> testMethods = new ArrayList<Method>();
            ArrayList<Method> afterMethods = new ArrayList<Method>();

            for (Method method : methods) {
                if (method.isAnnotationPresent(Before.class)) {
                    beforeMethods.add(method);
                } else if (method.isAnnotationPresent(Test.class)) {
                    testMethods.add(method);
                } else if (method.isAnnotationPresent(After.class)) {
                    afterMethods.add(method);
                }
            }

            System.out.println("=========== Test start ======================");
            for (Method testMethod : testMethods) {
                Object newObj = type.getDeclaredConstructor().newInstance();
                try {
                    myJUnit.invokeMethods(beforeMethods, obj);
                    methodCount++;
                    testMethod.invoke(newObj);
                    sucCount++;
                } catch (Exception e) {
                    expCount++;
                    System.out.println(testMethod.getName()+" is Abnormal");
                    System.out.println("Types of: " + e.getCause().getClass());
                    System.out.println("the reason: " + e.getCause().getMessage());
                    System.out.println("---------------");
                }
                myJUnit.invokeMethods(afterMethods, newObj);
            }
            System.out.println("====== Test end =======");
            System.out.println("Test method number:"+methodCount+", Which is abnormal:"+expCount + ", And Normal is:"+sucCount);

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void invokeMethods(ArrayList<Method> methods, Object obj) {
        for (Method method : methods) {
            try {
                method.invoke(obj);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }
}
