package com.kcs.utils;

import java.sql.Connection;
import java.sql.DatabaseMetaData;
import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * Created by Askew on 3/7/2017.
 */
public class JdbcUtils {

    public static boolean isTableExist(Connection connection, String tableName) {
        boolean isTableExist = false;
        try {
            DatabaseMetaData metaData = connection.getMetaData();
            ResultSet resultSet = metaData.getTables(null, null, tableName, null);
            isTableExist = resultSet.next();

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return isTableExist;

    }
}
