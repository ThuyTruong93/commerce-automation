
package katalon.fw.lib

import java.sql.ResultSet
import java.sql.ResultSetMetaData

import katalon.fw.db.PostgreSql
import katalon.fw.db.RSet
import katalon.utility.FileHelper

public class BaseTable <T> {

	PostgreSql postgreSql = Page.nav(PostgreSql);
	RSet rset = Page.nav(RSet)
	String dbName = ""
	String table = ""

	def T executeSelect(String dbName, String table, String condition, String column) {
		String query =  String.format("SELECT * FROM %s WHERE %s", table, condition)

		postgreSql.openConnection(dbName)
		ResultSet rs = postgreSql.executeQuery(query)
		ResultSetMetaData rsmd = rs.getMetaData()
		def value = rset.getSingleCellValue(rs,0,column)

		return value
	}

	def T executeSelect(String condition, String column) {
		String query =  String.format("SELECT * FROM %s WHERE %s", table, condition)

		postgreSql.openConnection(dbName)
		ResultSet rs = postgreSql.executeQuery(query)
		ResultSetMetaData rsmd = rs.getMetaData()
		def value = rset.getSingleCellValue(rs,0,column)

		return value
	}

	def T execute(String queryFile) {
		String queryStatment = Page.nav(FileHelper).readFile(queryFile)

		postgreSql.openConnection(dbName)
		postgreSql.execute(queryStatment)

		return this;
	}

	def T execute(String queryFile, String... param) {
		String queryFileContent = Page.nav(FileHelper).readFile(queryFile)
		String queryStatment = String.format(queryFileContent, param)

		postgreSql.openConnection(dbName)
		postgreSql.execute(queryStatment)

		return this;
	}

	def T closeConnection() {
		postgreSql.closeConnection()
	}
}
