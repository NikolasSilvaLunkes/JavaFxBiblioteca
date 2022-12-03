package project.nikolas.javaFx.javaFxProj1.DaoClasses;

 
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class MysqlSingleton  {
	
	private static Connection singletonConnection;
	
	
	public static Connection getConnection() {
		if (singletonConnection == null) {
			return conectar();
		} else {
			return singletonConnection;
		}
	}

	public static Connection conectar() {
		try {
			Class.forName("org.postgresql.Driver");
//			singletonConnection = DriverManager.getConnection("jdbc:mysql://192.168.0.102:3306/farmacia", "root", "123123123");
			singletonConnection = DriverManager.getConnection("jdbc:postgresql://localhost:5432/bibliotecajava", "postgres", "0606");
//			singletonConnectionSQLServer = DriverManager.getConnection("jdbc:sqlserver://;serverName=192.168.0.102\\\\instance1;encrypt=true;integratedSecurity=true;", "sa", "123123123");			
			return singletonConnection;
		} catch (SQLException | ClassNotFoundException e) {
			System.out.println("----------------");
			System.out.println(e);
			System.out.println("----------------");
			return null;
		}
	}
 
	public void desconectar(Connection conn) {
		try {
			singletonConnection.close();
		} catch (SQLException e) {
		}
	}

}