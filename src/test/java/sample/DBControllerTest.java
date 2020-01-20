package sample;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import org.apache.logging.log4j.Logger;
import org.apache.logging.log4j.LogManager;

import java.sql.SQLException;

public class DBControllerTest {

    private static final Logger logger = LogManager.getLogger(Log4jConsoleTest.class);

    @BeforeEach
    public static void init() {
        //init
    }

    @BeforeEach
    public void setUp() {
        //setUp
    }

    @Test
    public void testDBControllerInstantiation_Possible() {
        try {
            DBController db = DBController.getInstance();
        } catch (SQLException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
    }
}
