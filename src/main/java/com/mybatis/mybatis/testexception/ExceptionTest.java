package com.mybatis.mybatis.testexception;

import com.sun.org.apache.xml.internal.resolver.readers.ExtendedXMLCatalogReader;

public class ExceptionTest  extends RuntimeException {  //这里继承了非运行时异常的时候，抛出异常必须被捕获，这里继承的是运行时异常
    static final long serialVersionUID = -3387516993124229948L;
        //实现啥功能，继承成一个类或者实现接口，再实现里面的方法。
    public ExceptionTest(String message){
        super(message);
    }
    public void printStackTrace(){
        System.out.println("myException is");
    }
}
