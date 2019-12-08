package sample;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.apache.logging.log4j.core.appender.db.DbAppenderLoggingException;
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
    private Connection conn = null;

    private static final Logger logger = LogManager.getLogger(DBController.class);

    public DBController() throws ClassNotFoundException, SQLException{
        Class.forName(JDBC_DRIVER);
        DBController.logger.info("Connecting to database...");
        this.conn = DriverManager.getConnection(DB_URL,USER,PASS);
        DBController.logger.info("connection successful");
    }

    public static DBController getInstance() throws SQLException, ClassNotFoundException {
        if(DBController.instance == null){
            DBController.instance = new DBController();
        }

        return DBController.instance;
    }

    private boolean executeStatement(String statement){

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
