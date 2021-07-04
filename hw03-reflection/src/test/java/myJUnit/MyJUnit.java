package myJUnit;

import ru.gostohov.annotations.After;
import ru.gostohov.annotations.Before;
import ru.gostohov.annotations.Test;
import java.lang.reflect.Method;

public class MyJUnit {

    public static <T> void runTestForClass(Class<T> type) {
        int methodCount = 0;
        int expCount = 0 ;
        int sucCount = 0 ;

        MyJUnit myJUnit = new MyJUnit();

        try {
            Object obj = type.getDeclaredConstructor().newInstance();
            Class<? extends Object> clazz = obj.getClass();
            Method[] methods = clazz.getMethods();

            System.out.println("=========== Test start ======================");
            for (Method method : methods) {
                try {
                    if(method.isAnnotationPresent(Test.class)) {
                        Object newObj = type.getDeclaredConstructor().newInstance();
                        myJUnit.handleBefore(methods, newObj);
                        methodCount++;
                        method.invoke(newObj);
                        sucCount++;
                        myJUnit.handleAfter(methods, newObj);
                    }
                } catch (Exception e) {
                    expCount++;
                    System.out.println(method.getName()+" is Abnormal");
                    System.out.println("Types of: " + e.getCause().getClass());
                    System.out.println("the reason: " + e.getCause().getMessage());
                    System.out.println("---------------");
                    myJUnit.handleAfter(methods, obj);
                }
            }


            System.out.println("====== Test end =======");
            System.out.println("Test method number:"+methodCount+", Which is abnormal:"+expCount + ", And Normal is:"+sucCount);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void handleBefore(Method[] methods, Object obj) {
        for (Method method : methods) {
            try {
                if(method.isAnnotationPresent(Before.class)) {
                    method.invoke(obj);
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    private void handleAfter(Method[] methods, Object obj) {
        for (Method method : methods) {
            try {
                if(method.isAnnotationPresent(After.class)) {
                    method.invoke(obj);
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

}
