package cloud.springboot.config.mybatis;

import org.springframework.jdbc.datasource.lookup.AbstractRoutingDataSource;
import org.springframework.lang.Nullable;

public class MyRoutingDataSource extends AbstractRoutingDataSource {
    /**
     * @Author
     * @Description //根据Key获取数据源的信息，上层抽象函数的钩子
     * @Date 2019/4/30 9:39
     * @Param []
     * @return java.lang.Object
     **/
    @Nullable
    @Override
    protected Object determineCurrentLookupKey() {
        return DBContextHolder.get();
    }
}