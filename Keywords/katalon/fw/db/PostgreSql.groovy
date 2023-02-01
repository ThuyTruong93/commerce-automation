package katalon.fw.db

import java.sql.Connection
import java.sql.DriverManager
import java.sql.ResultSet
import java.sql.SQLException
import java.sql.Statement

import com.kms.katalon.util.CryptoUtil

import internal.GlobalVariable

public class PostgreSql {

	private Connection conn = null;

	public PostgreSql openConnection() {
		if (conn == null ) {
			String decryptedPwd =  CryptoUtil.decode(CryptoUtil.getDefault(GlobalVariable.dbpwd))
			conn =  DriverManager.getConnection(connectionUrl(),GlobalVariable.dbuser,decryptedPwd )
		}
		return this
	}

	public closeConnection() {
		if(conn != null && !conn.isClosed()){
			conn.close()
		}
	}

	/**
	 * Execute a SQL query on DB server name and return a result set
	 * @param conn         - It is a Connection
	 * @param queryString  - A single query and should return only one result set
	 * @return a RestultSet
	 */
	public ResultSet executeQuery(String queryStr) throws SQLException{
		Statement stm = conn.createStatement(ResultSet.TYPE_SCROLL_SENSITIVE,
				ResultSet.CONCUR_UPDATABLE)
		ResultSet rs = stm.executeQuery(queryStr)
		return rs
	}

	/**
	 * Execute a DDL statement
	 * @param conn          - A Connection
	 * @param queryString   - An SQL Data Manipulation Language (DML) statement, such as INSERT, UPDATE or
	 * DELETE or an SQL statement that returns nothing     *
	 */
	public void executeUpdate(String queryStr) throws SQLException{
		try {
			Statement stm = conn.createStatement(ResultSet.TYPE_SCROLL_SENSITIVE,
					ResultSet.CONCUR_UPDATABLE)
			stm.executeUpdate(queryStr)
			conn.commit();
			//stm.close();
			println "Database updated successfully ";
		}
		catch (SQLException e) {
			println e.getMessage();
		}
	}

	/**
	 * Executes the given SQL statement, which may return multiple results or an update count
	 * In some (uncommon) situations, a single SQL statement may return
	 * multiple result sets and/or update counts.  Normally you can ignore
	 * this unless you are (1) executing a stored procedure that you know may
	 * return multiple results or (2) you are dynamically executing an
	 * unknown SQL string.
	 * @param conn           - A Connection
	 * @param queryString    - Any sql statement
	 * @return true or false
	 */
	public boolean execute(String queryStr) throws SQLException {
		Statement stm = conn.createStatement(ResultSet.TYPE_SCROLL_SENSITIVE,
				ResultSet.CONCUR_UPDATABLE)
		boolean result = stm.execute(queryStr)
		conn.commit()
		//stm.close();
	}

	private String connectionUrl() {
		return "jdbc:postgresql://"+ GlobalVariable.dbserver + ":" + GlobalVariable.port + "/" + GlobalVariable.dbname
	}
}
