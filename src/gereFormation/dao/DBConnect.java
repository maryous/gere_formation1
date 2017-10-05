package gereFormation.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DBConnect {
	
	private static final String DRIVER = "com.mysql.jdbc.Driver";
	private static final String URL = "jdbc:mysql://localhost/gere_formation?noAccessToProcedureBodies=true";
	private static final String USER = "root";
	private static final String PWD = "aase89";
	private static Connection conn = null;
        /**
         * 
         * @return RunTimeException() if any pb
         */
        public static Connection getConnection () 
        {
            if (conn==null) {

                     try
                     {
                         Class.forName(DRIVER).newInstance();

                       // 
                         conn=  DriverManager.getConnection(URL,USER, PWD);
                     }
                       catch(SQLException sqlE)
                       {
                           System.out.println("Sql Erreur " + sqlE.getMessage());
                           throw new RuntimeException();
                       }
                       catch(Exception e)
                       {
                          e.printStackTrace();
                          throw new RuntimeException(e);
                       }
            }

            return conn;

}
}
