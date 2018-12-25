package com.pengpj.example1;

import com.pengpj.example1.demo.Cat;
import com.pengpj.example1.demo.IAnimal;
import net.bytebuddy.ByteBuddy;
import net.bytebuddy.dynamic.loading.ClassLoadingStrategy;
import net.bytebuddy.implementation.FixedValue;
import net.bytebuddy.matcher.ElementMatchers;

/**
 * 创建一个子类
 *
 * @author pengpj
 * @date 2018/12/25
 */
public class SubClassExample001 {

    public static void main(String[] args) throws IllegalAccessException, InstantiationException {
        /*
        - 创建 Cat 子类 : subclass
            - 此时这些类型不会加载到 JVM 中，而是一个临时变量
        - 设置加载的 ClassLoader 与 加载策略
            - 创建一个新的包装 ClassLoader
        - class 加载到 JVM 中，返回一个 Java Class 实例，表示现在加载的动态类
        - 实例化
        - 调用方法执行
         */
        Class<?> catSubClassRaw = new ByteBuddy()
                .subclass(Cat.class)
                .make()
                .load(SubClassExample001.class.getClassLoader(), ClassLoadingStrategy.Default.WRAPPER)
                .getLoaded();
        Object catSubClassRawObj = catSubClassRaw.newInstance();
        if (catSubClassRawObj instanceof IAnimal) {
            System.out.println(((IAnimal) catSubClassRawObj).shout());
        }


        /*
        在上面的基础上，对类的方法进行拦截

        - 对方法的匹配策略 ： 方法名
        - 通过提供的实现，替代被拦截方法的实现
            - 返回固定值

         */
        Class<?> catSubClassInterceptor = new ByteBuddy()
                .subclass(Cat.class)
                .method(ElementMatchers.named("shout"))
                .intercept(FixedValue.value("小鱼干~"))
                .make()
                .load(SubClassExample001.class.getClassLoader(), ClassLoadingStrategy.Default.WRAPPER)
                .getLoaded();
        Object catSubClassInterceptorOjb = catSubClassInterceptor.newInstance();
        if (catSubClassInterceptorOjb instanceof IAnimal) {
            System.out.println(((IAnimal) catSubClassInterceptorOjb).shout());
        }
    }

}
