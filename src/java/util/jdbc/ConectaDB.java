package util.jdbc;

import java.sql.*;

public class ConectaDB  {

	public static Connection getConnection() throws SQLException {
		Connection cn = null;
		try {
			Class.forName("com.mysql.jdbc.Driver").newInstance();
			cn  = DriverManager.getConnection(
		"jdbc:mysql://localhost:3306/dbferreteria", "root", "mauricio");
        //         "jdbc:mysql://localhost:3306/ticsolut_dbferreteria?useInformationSchema=true&noAcccessToProcedureBodies=true", "ticsolut_root", "u9[uR)TPT[lm");
//?useInformationSchema=true&
		} catch (ClassNotFoundException ex) {
			System.out.println("  ================= HIBPER-GAMA =====================");
			System.out.println(ex);
		} catch (InstantiationException ex) {
			System.out.println("  ================= HIBPER-GAMA =====================");
			System.out.println(ex);
		} catch (IllegalAccessException ex) {
			System.out.println("  ================= HIBPER-GAMA =====================");
			System.out.println(ex);
		}
                cn.setAutoCommit(false);
		return cn;
	}

	public ConectaDB() {
		database = "dbferreteria";
	}

	public ConectaDB(String database) {
		this.database = database;
	}

	private final String database;
}
