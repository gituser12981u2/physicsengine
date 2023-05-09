package com.physicsengine.test;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

public strictfp class test {
    {
        System.out.println("Started");
    }

    test() {
        System.out.println("Const");
    }

    private transient int non;
    private volatile int volt;

    static final double PI;
    static {
        PI = Math.PI;
        System.out.println("Static intializer called");
    }

    public static void main(String[] args) throws NoSuchMethodException, SecurityException {
        scn: {
            break scn;
        }
        {
        }
        int i = 0;
        outer: for (; i < 10; i++) {
            inner: for (int j = 0; j < 10;) {
                if (true)
                    ;
                truth: for (; true;) {
                    break truth;
                }
                break inner;
            }
            continue outer;
        }
        {
            {
                col: {
                    break col;
                }
            }
            float fl = StrictMath.signum(i);
        }
        String s = new String("Hello");
        Method m = s.getClass().getMethod("length");
        int length;
        try {
            length = (Integer) m.invoke(s);
            int \u0061 = 1;
            System.out.println(\u0061);
            // System.out.println(length);
        } catch (IllegalAccessException | IllegalArgumentException | InvocationTargetException e) {
            e.printStackTrace();
        }

        int x = 010;

        if (true | false & !true) {
            double y = 0x1.0p0;
            System.out.println(y);
        } // true;
        assert x > 0 : "x must be positive";

        if (Character.isJavaIdentifierStart(0))
            ;

        System.setSecurityManager(null);
        System.out.println(x);
        System.out.println(x >>> 1);

    }
}
