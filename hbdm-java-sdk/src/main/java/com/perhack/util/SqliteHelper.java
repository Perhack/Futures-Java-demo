package com.perhack.util;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * sqlite帮助类，直接创建该类示例，并调用相应的借口即可对sqlite数据库进行操作
 * 
 * 本类基于 sqlite jdbc v56
 * 
 * @author perhack
 */
public class SqliteHelper {
    final static Logger logger = LoggerFactory.getLogger(SqliteHelper.class);
    
    private Connection connection;
    private Statement statement;
    private ResultSet resultSet;
    private String dbFilePath;
    
    /**
     * 构造函数
     * @param dbFilePath sqlite db 文件路径
     * @throws ClassNotFoundException
     * @throws SQLException
     */
    public SqliteHelper(String dbFilePath) throws ClassNotFoundException, SQLException {
        this.dbFilePath = dbFilePath;
        connection = getConnection(dbFilePath);
    }
    
    /**
     * 获取数据库连接
     * @param dbFilePath db文件路径
     * @return 数据库连接
     * @throws ClassNotFoundException
     * @throws SQLException
     */
    public Connection getConnection(String dbFilePath) throws ClassNotFoundException, SQLException {
        Connection conn = null;
        Class.forName("org.sqlite.JDBC");
        conn = DriverManager.getConnection("jdbc:sqlite:" + dbFilePath);
        return conn;
    }
    
    /**
     * 执行sql查询
     * @param sql sql select 语句
     * @param rse 结果集处理类对象
     * @return 查询结果
     * @throws SQLException
     * @throws ClassNotFoundException
     */
    public <T> T executeQuery(String sql, ResultSetExtractor<T> rse) throws SQLException, ClassNotFoundException {
        try {
            resultSet = getStatement().executeQuery(sql);
            T rs = rse.extractData(resultSet);
            return rs;
        } finally {
            destroyed();
        }
    }
    
    /**
     * 执行select查询，返回结果列表
     * 
     * @param sql sql select 语句
     * @param rm 结果集的行数据处理类对象
     * @return
     * @throws SQLException
     * @throws ClassNotFoundException 
     */
    public <T> List<T> executeQuery(String sql, RowMapper<T> rm) throws SQLException, ClassNotFoundException {
        List<T> rsList = new ArrayList<T>();
        try {
            resultSet = getStatement().executeQuery(sql);
            while (resultSet.next()) {
                rsList.add(rm.mapRow(resultSet, resultSet.getRow()));
            }
        } finally {
            destroyed();
        }
        return rsList;
    }
    
    /**
     * 执行数据库更新sql语句
     * @param sql
     * @return 更新行数
     * @throws SQLException
     * @throws ClassNotFoundException
     */
    public int executeUpdate(String sql) {
    	int c = 0;
    	try {
            
			try {
				c = getStatement().executeUpdate(sql);
			} catch (ClassNotFoundException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
            
        } finally {
            destroyed();
            
        }
        return c;
    }

    /**
     * 执行多个sql更新语句
     * @param sqls
     * @throws SQLException
     * @throws ClassNotFoundException
     */
    public void executeUpdate(String...sqls) throws SQLException, ClassNotFoundException {
        try {
            for (String sql : sqls) {
                getStatement().executeUpdate(sql);
            }
        } finally {
            destroyed();
        }
    }
    
    /**
     * 执行数据库更新 sql List
     * @param sqls sql列表
     * @throws SQLException
     * @throws ClassNotFoundException
     */
    public void executeUpdate(List<String> sqls) throws SQLException, ClassNotFoundException {
        try {
            for (String sql : sqls) {
                getStatement().executeUpdate(sql);
            }
        } finally {
            destroyed();
        }
    }
    
	public synchronized void executeBatch(String sqls){
		Statement statement = null;
		int result = 0;
		Connection conn = getConnection();
		try {
			conn.setAutoCommit(false);
			statement = conn.createStatement();
			
			String[] sqlSegs = sqls.split(";");
			for(String sql : sqlSegs){
			    if (sql != null){
			    	statement.addBatch(sql);
			    }
			}
			statement.executeBatch();
			statement.close();
			conn.commit();
			System.out.println("commit");
		} catch (Exception e) {
			e.printStackTrace();
			System.out.println("执行失败:" + sqls);
			try {
				conn.rollback();
			} catch (SQLException e1) {
				e1.printStackTrace();
			}
		}
	}
	
    private Connection getConnection(){
        if (null == connection)
			try {
				connection = getConnection(dbFilePath);
			} catch (ClassNotFoundException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
        return connection;
    }
    
    private Statement getStatement() throws SQLException, ClassNotFoundException {
        if (null == statement) statement = getConnection().createStatement();
        return statement;
    }
    
    /**
     * 数据库资源关闭和释放
     */
    public void destroyed() {
        try {
            if (null != connection) {
                connection.close();
                connection = null;
            }
            
            if (null != statement) {
                statement.close();
                statement = null;
            }
            
            if (null != resultSet) {
                resultSet.close();
                resultSet = null;
            }
        } catch (SQLException e) {
            logger.error("Sqlite数据库关闭时异常", e);
        }
    }
}
