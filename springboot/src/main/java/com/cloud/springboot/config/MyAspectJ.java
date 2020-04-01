package com.cloud.springboot.config;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.AfterThrowing;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.springframework.context.annotation.Configuration;

import java.util.Arrays;

@Configuration
@Aspect
public class MyAspectJ {

     /*
	    标识这个方法是个前置通知,  切点表达式表示执行任意类的任意方法.
	    第一个 * 代表匹配任意修饰符及任意返回值,
	    第二个 * 代表任意类的对象,
	    第三个 * 代表任意方法,
	    参数列表中的 ..  匹配任意数量的参数
	    AspectJ 支持 5 种类型的通知注解
        1）@Before:  前置通知:在方法执行之前执行的通知
        2）@After: 后置通知, 在方法执行之后执行 , 即方法返回结果或者抛出异常的时候, 下面的后置通知记录了方法的终止.
        3）@AfterRunning: 返回通知, 在方法返回结果之后执行
                ps:无论方法是正常返回还是抛出异常, 后置通知都会执行. 如果只想在方法返回的时候记录日志, 应使用返回通知代替后置通知.

        4）@AfterThrowing: 异常通知, 在方法抛出异常之后
        5） @Around: 环绕通知, 围绕着方法执行（即方法前后都有执行）
               环绕通知是所有通知类型中功能最为强大的, 能够全面地控制连接点. 甚至可以控制是否执行连接点.
        */

    /**
     * @param joinPoint
     * @Before 前置通知
     */
    @Before(value = "execution(* com.cloud.springboot.service.*.*(..))")
    public void before(JoinPoint joinPoint) {
        String MethodName = joinPoint.getSignature().getName();
        Object[] args = joinPoint.getArgs();
        System.out.println("before MethodName:" + MethodName + ",argNames:" + Arrays.toString(args));
    }

    /**
     * @param joinPoint
     * @After后置通知
     */
    @After(value = "execution(* com.cloud.springboot.service.*.*(..))")
    public void after(JoinPoint joinPoint) {
        String MethodName = joinPoint.getSignature().getName();
        Object[] args = joinPoint.getArgs();
        System.out.println("after MethodName:" + MethodName + ",argNames:" + Arrays.toString(args));
    }

    /**
     * @param joinPoint
     * @param result
     * @AfterReturning返回通知
     */
    @AfterReturning(value = "execution(* com.cloud.springboot.service.*.*(..))", returning = "result")
    public void result(JoinPoint joinPoint, Object result) {
        String MethodName = joinPoint.getSignature().getName();
        Object[] args = joinPoint.getArgs();
        System.out.println("AfterReturning MethodName:" + MethodName + ",argNames:" + Arrays.toString(args) + ",result:" + result);
    }

    //@AfterThrowing: 异常通知
    @AfterThrowing(value = "execution(* com.cloud.springboot.service.*.*(..))", throwing = "e")
    public void afterReturningMethod(JoinPoint joinPoint, Exception e) {
        String methodName = joinPoint.getSignature().getName();
        System.out.println("The method name:" + methodName + " ends and result=" + e);
    }

}