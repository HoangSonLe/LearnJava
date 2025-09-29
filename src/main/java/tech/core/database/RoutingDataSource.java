package tech.core.database;

import org.springframework.jdbc.datasource.lookup.AbstractRoutingDataSource;

import javax.sql.DataSource;
import java.util.Map;

public class RoutingDataSource extends AbstractRoutingDataSource {
    static enum DataBaseType {
        READ, WRITE
    }

    private static final ThreadLocal<DataBaseType> type = new ThreadLocal<>();

    public RoutingDataSource(DataSource write, DataSource read) {
        super.setDefaultTargetDataSource(read);
        super.setTargetDataSources(Map.of(
                DataBaseType.WRITE, write,
                DataBaseType.READ, read
        ));
        super.afterPropertiesSet();
    }

    static void switchToWrite() {
        type.set(DataBaseType.WRITE);
    }

    static void switchToRead() {
        type.set(DataBaseType.READ);
    }

    static void clear() {
        type.remove();
    }

    @Override
    protected Object determineCurrentLookupKey() {
        DataBaseType dbType = type.get();
        if (dbType == null) {
            return DataBaseType.READ; // Mặc định là READ
        }
        return type.get();
    }
}
