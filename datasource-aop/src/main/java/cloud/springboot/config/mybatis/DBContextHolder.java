package cloud.springboot.config.mybatis;

import cloud.springboot.enums.DBTypeEnum;

import java.util.concurrent.atomic.AtomicInteger;

public class DBContextHolder {
    private static final ThreadLocal<DBTypeEnum> contextHolder = new ThreadLocal<>();
    private static final AtomicInteger counter = new AtomicInteger(-1);

    public static void set(DBTypeEnum dbType) {
        contextHolder.set(dbType);
    }

    public static DBTypeEnum get() {
        return contextHolder.get();
    }

    public static void read(String value) {
        if (DBTypeEnum.READ_TEST2.toString().equals(value)) {
            set(DBTypeEnum.READ_TEST2);
            System.out.println("切换到读2" + DBTypeEnum.READ_TEST2.toString());
            return;
        }
        //默认使用1数据源
        set(DBTypeEnum.READ_TEST1);
        System.out.println("切换到读1" + DBTypeEnum.READ_TEST1.toString());
        return;
    }
}