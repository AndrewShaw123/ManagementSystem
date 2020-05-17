package com.andrew.util;

import com.alibaba.druid.pool.DruidDataSourceFactory;

import javax.sql.DataSource;
import java.io.IOException;
import java.io.InputStream;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.Properties;

/**
 * JdbcUtils Class
 *
 * @author andrew
 * @date 2019/7/18
 */
public class JdbcUtils {

    private static DataSource dataSource;

    static{

        try {
            Properties properties=new Properties();
            InputStream is= JdbcUtils.class.getClassLoader().getResourceAsStream("db.properties");
            properties.load(is);
            dataSource= DruidDataSourceFactory.createDataSource(properties);
        } catch (IOException e) {
            e.printStackTrace();
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    public static Connection getConnection() throws SQLException {
        return dataSource.getConnection();
    }


}
