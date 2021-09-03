package Database;

import javax.swing.*;

import Entity.News;
import Entity.User;

import java.sql.*;

public class CreateStatement {
    /** Table name **/
    private static final String NEWS_TABLE = "news";

    /** Table name **/
    private static final String CONTENT_TABLE = "content";

    /** Table name **/
    private static final String WEB_SOURCE = "websource";

    /** Table name **/
    private static final String FORMAT = "webFormat";

    /** Table name **/
    private static final String CATEGORY = "category";

    /** Table name **/
    private static final String USER = "user";

    private static Connection connection = null;

    /** Statement **/
    private static  Statement statement = null;

    public CreateStatement() {
        connection = DbConnector.connectDb();
    }

    /**
     * Create table NEWS
     */
    public void createTableNews() {
        if(!checkExistTable(connection,NEWS_TABLE)) {
            try {
                statement = connection.createStatement();
                String sql = "CREATE TABLE " + NEWS_TABLE +
                        "(id BIGINT not NULL AUTO_INCREMENT, " +
                        " title VARCHAR(500)," +
                        " link TEXT," +
                        " category_id SMALLINT ," +
                        " websource_id SMALLINT ," +
                        " image TEXT ," +
                        " description TEXT," +
                        " href TEXT," +
                        " status smallint ," +
                        " number_of_access BIGINT, " +
                        " created_at TIMESTAMP null DEFAULT NULL," +
                        " updated_at TIMESTAMP null DEFAULT NULL," +
                        "PRIMARY KEY (id))";
                statement.executeUpdate(sql);
                System.out.println("Created table " + NEWS_TABLE + "....");
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }

    /**
     * Create table CONTENT
     */
    public void createTableContent() {
        if(!checkExistTable(connection,CONTENT_TABLE)) {
            try {
                statement = connection.createStatement();
                String sql = "CREATE TABLE " + CONTENT_TABLE +
                        "(id BIGINT not NULL AUTO_INCREMENT , " +
                        " time_content varchar(50)," +
                        " category_content TEXT," +
                        " content  TEXT," +
                        " news_id BIGINT NOT NULL ," +
                        " created_at TIMESTAMP null DEFAULT NULL ," +
                        " updated_at TIMESTAMP null DEFAULT NULL ," +
                        "PRIMARY KEY (id))";
                statement.executeUpdate(sql);
                System.out.println("Created table " + CONTENT_TABLE);
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }

    /**
     * Create table WEBSOURCE
     */
    public void createTableWebSource() {
        if(!checkExistTable(connection,WEB_SOURCE)) {
            try {
                statement = connection.createStatement();
                String sql = "CREATE TABLE " + WEB_SOURCE +
                        "(id BIGINT not NULL AUTO_INCREMENT , " +
                        " url varchar(50) not null ," +
                        " name TEXT," +
                        " status smallint," +
                        " created_at TIMESTAMP ," +
                        " updated_at TIMESTAMP ," +
                        "PRIMARY KEY (id))";
                statement.executeUpdate(sql);
                System.out.println("Created table " + WEB_SOURCE);
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }

    /**
     * Create table WEBSOURCE
     */
    public void createTableWebFormat() {
        if(!checkExistTable(connection,FORMAT)) {
            try {
                statement = connection.createStatement();
                String sql = "CREATE TABLE " + FORMAT +
                        "(id BIGINT not NULL AUTO_INCREMENT ," +
                        " websource_id Bigint not null," +
                        " category_id INT ," +
                        " category_menu TEXT," +
                        " category_parent TEXT," +
                        " category_format TEXT," +
                        " format TEXT not null ," +
                        " format_content_detail TEXT not null," +
                        " created_at TIMESTAMP ," +
                        " updated_at TIMESTAMP ," +
                        "PRIMARY KEY (id))";
                statement.executeUpdate(sql);
                System.out.println("Created table " + FORMAT);
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }
    /**
     * Create table WEBSOURCE
     */
    public void createTableCategory() {
//        if(!checkExistTable(connection,CATEGORY)) {
            try {
                statement = connection.createStatement();
                String sql = "CREATE TABLE " + CATEGORY +
                        "(id BIGINT not NULL AUTO_INCREMENT ," +
                        " name TEXT not null," +
                        " category_name TEXT not null, " +
                        " display smallint ," +
                        " created_at TIMESTAMP ," +
                        " updated_at TIMESTAMP ," +
                        "PRIMARY KEY (id))";
                statement.executeUpdate(sql);
                System.out.println("Created table " + CATEGORY);
            } catch (SQLException e) {
                e.printStackTrace();
            }
//        }
    }

    /**
     * Create table WEBSOURCE
     */
    public void createTableUser() {
//        if(!checkExistTable(connection,USER)) {
            try {
                statement = connection.createStatement();
                String sql = "CREATE TABLE " + USER +
                        "  (id int(11) unsigned NOT NULL AUTO_INCREMENT," +
                        "  username varchar(30) COLLATE utf8_unicode_ci DEFAULT NULL," +
                        "  password varchar(60) COLLATE utf8_unicode_ci DEFAULT NULL," +
                        "  email varchar(255) COLLATE utf8_unicode_ci DEFAULT NULL," +
                        "  fullname varchar(255) COLLATE utf8_unicode_ci DEFAULT NULL," +
                        "  level tinyint(1) DEFAULT NULL," +
                        " created_at TIMESTAMP ," +
                        " updated_at TIMESTAMP ," +
                        "  PRIMARY KEY (id)" +
                        ");";
                statement.executeUpdate(sql);
                System.out.println("Created table " + USER);
            } catch (SQLException e) {
                e.printStackTrace();
            }
//        }
    }
    /**
     * Delete table with table name
     * @param tableName table name
     */
    public void deleteTable(String tableName){
        if(checkExistTable(connection,tableName)) {
            try{
                statement = connection.createStatement();
                String sql = "DROP TABLE `webdata`.`"+ tableName +"`";
                statement.executeUpdate(sql);
                System.out.println("Dropped table " + tableName);
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }

    /**
     * Function check table have exist
     * @param connection Connection
     * @param tableName table name
     * @return
     */
    public static boolean checkExistTable(Connection connection ,String tableName){
        try{
            DatabaseMetaData metaData = connection.getMetaData();
            ResultSet table = metaData.getTables(null, null,tableName,null);
            return table.next();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }

}
