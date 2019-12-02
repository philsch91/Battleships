package sample;

import org.h2.*;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

public class DBController {

    private static final String JDBC_DRIVER = "org.h2.Driver";
    //private static final String DB_URL = "jdbc:h2:~/test";
    private static final String DB_URL = "jdbc:h2:./database";

    private static final String USER = "sa";
    private static final String PASS = "";

    private static DBController instance;

    private DBController(){
        String sql = "CREATE TABLE TESTS " +
                "(id INTEGER not NULL, " +
                " name VARCHAR(255), " +
                " application VARCHAR(255), " +
                " result INTEGER, " +
                " PRIMARY KEY (id))";

        this.executeStatement(sql);
    }

    public static DBController getInstance(){
        if(DBController.instance == null){
            DBController.instance = new DBController();
        }

        return DBController.instance;
    }

    private boolean executeStatement(String statement){
        Connection conn = null;
        Statement stmt = null;
        try {
            // STEP 1: Register JDBC driver
            Class.forName(JDBC_DRIVER);

            //STEP 2: Open a connection
            System.out.println("Connecting to database...");
            conn = DriverManager.getConnection(DB_URL,USER,PASS);

            //STEP 3: Execute a query
            System.out.println("Creating table in given database...");
            stmt = conn.createStatement();

            stmt.executeUpdate(statement);

            System.out.println("Created table in given database...");

            // STEP 4: Clean-up environment
            stmt.close();
            conn.close();
        } catch(SQLException se) {
            //Handle errors for JDBC
            se.printStackTrace();
        } catch(Exception e) {
            //Handle errors for Class.forName
            e.printStackTrace();
        } finally {
            //finally block used to close resources
            try{
                if(stmt != null){
                    stmt.close();
                }
            } catch(SQLException se2) {
                // nothing we can do
            }

            try {
                if(conn != null){
                    conn.close();
                }
            } catch(SQLException se){
                se.printStackTrace();
            } //end finally try
        } //end try

        return true;
    }

    public boolean insertTestResult(String testname, int result){
        String sql = "INSERT INTO TESTS (application, name, result) " +
                " VALUES ('Battleships','" + testname + "','" + result + "')";

        return true;
    }
}
