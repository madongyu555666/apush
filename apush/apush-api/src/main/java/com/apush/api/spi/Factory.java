package com.apush.api.spi;

import java.util.function.Supplier;


/**
 * 创建工厂的接口
 * 主要用于编译级错误检查，加上该注解，当你写的接口不符合函数式接口定义的时候，编译器会报错。
 * Supplier----		
 * //创建Supplier容器，声明为TestSupplier类型，此时并不会调用对象的构造方法，即不会创建对象 		Supplier<Factory> sup= Factory::new;
 * //调用get()方法，此时会调用对象的构造方法，即获得到真正对象
 * //每次get都会调用构造方法，即获取的对象不同
 * @author madongyu-ds
 *
 * @param <T>
 */
@FunctionalInterface
public interface  Factory<T> extends Supplier<T>{

}
