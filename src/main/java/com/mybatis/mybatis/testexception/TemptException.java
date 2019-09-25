package com.mybatis.mybatis.testexception;

import org.apache.ibatis.io.ResolverUtil;

public class TemptException {

    public void  test () {

     /*   for(int i = 0; i<100;i++)
        {
            int sum = i;
            if (i == 10) {
                try {
                    throw new ExceptionTest();
                } catch (ExceptionTest e) {//捕获的是子类的对象，故这里定义也要定义为，不存在多态
                    e.printStackTrace();
                }
            }
        }*/

        for(int i = 0; i<100;i++)
        {
            int sum = i;
            if (i == 10) {

                    throw new ExceptionTest("这里测试一下自定义一个异常类");
            }
        }


}
}
