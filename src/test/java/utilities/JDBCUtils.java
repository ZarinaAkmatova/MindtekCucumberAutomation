package utilities;

import java.sql.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class JDBCUtils {
    /*
    .establishConnection();-->returns void;
    .runQuery("select * from employees");-->returns List of Maps;
    .close();--> returns void;
     */
    private static Connection connection;
    private static Statement statement;

    public static void establishConnection() throws SQLException {
         connection = DriverManager.getConnection(
                ConfigReader.getProperty("DBURL"),
                ConfigReader.getProperty("DBUsername"),
                ConfigReader.getProperty("DBPassword")

        );
         statement=connection.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY);
    }

    public static List<Map<String,Object>> runQuery(String query) throws SQLException {
        ResultSet resultSet=statement.executeQuery(query);
        ResultSetMetaData rsMetaData=resultSet.getMetaData();

        List<Map<String,Object>>tableData= new ArrayList<>();

        while(resultSet.next()){
            Map<String,Object> rowMap= new HashMap<>();
            for(int order =1; order<=rsMetaData.getColumnCount(); order++){
                rowMap.put(rsMetaData.getColumnName(order),resultSet.getString(rsMetaData.getColumnName(order)));
            }
            tableData.add(rowMap);
        }
        return tableData;
    }

    public static void close() throws SQLException {
        if(connection!=null){
            connection.close();
        }
        if(statement!=null){
            statement.close();
        }

    }
}
