package cloud.springboot.config.aop;

import cloud.springboot.annotions.DbSource;
import cloud.springboot.config.mybatis.DBContextHolder;
import cloud.springboot.enums.DBTypeEnum;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.stereotype.Component;

import java.lang.reflect.Method;

@Aspect
@Component
public class DataSourceAop {
    /**
     * 另一种写法：if...else...  判断哪些需要读从数据库，其余的走主数据库
     */
    @Before("execution(* cloud.springboot.service.impl.*.*(..))")
    public void before(JoinPoint jp){
        MethodSignature methodSignature = (MethodSignature) jp.getSignature();
        Method method = methodSignature.getMethod();
        boolean methodlag = method.isAnnotationPresent(DbSource.class);//可以根据此标记判断方法是否有注解，将数据源切换的注解使用到方法上
        System.out.println("拦截到了" + jp.getSignature().getName() +"方法...");
        Class<?> targetClass = jp.getTarget().getClass();
        boolean flag = targetClass.isAnnotationPresent(DbSource.class);
        //包含数据源注解，数据源为注解中的类
        if(flag){
            //获取注解的value
            DbSource annotation = targetClass.getAnnotation(DbSource.class);
            String value = annotation.value();
            DBContextHolder.read(value);
        }else {
            //不包含注解，查询方法默认走 默认读数据源
            //if (StringUtils.startsWithAny(method.getName(), "get", "select", "find")) {
                DBContextHolder.read(DBTypeEnum.READ_TEST1.toString());
            //}else {
                //DBContextHolder.write();
           // }
        }
    }
}