package sample;

import org.junit.BeforeClass;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

import org.apache.logging.log4j.Logger;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Level;

import java.net.URL;
import java.net.URLClassLoader;

public class Log4jConsoleTest {

    private static final Logger logger = LogManager.getLogger(Log4jConsoleTest.class);

    @BeforeClass
    public static void init() {
        /*
        ClassLoader cl = ClassLoader.getSystemClassLoader();
        URL[] urls = ((URLClassLoader)cl).getURLs();

        for(URL url : urls){
            System.out.println(url.getFile());
        } */
    }

    @BeforeEach
    public void setUp() {
        //setUp
    }

    @Test
    public void classPathTest() {
        System.out.println("classPathTest");
        String classPath = System.getProperty("java.class.path");
        String[] classPathVars = classPath.split(":");
        for (String str : classPathVars){
            System.out.println(classPath);
        }
    }

    @Test
    public void log4jConsoleTest() {
        if (logger.isTraceEnabled()) {
            logger.trace("Test: TRACE level message.");
        }
        if (logger.isDebugEnabled()) {
            logger.debug("Test: DEBUG level message.");
        }
        if (logger.isInfoEnabled()) {
            logger.info("Test: INFO level message.");
        }
        if (logger.isEnabled(Level.WARN)) {
            logger.warn("Test: WARN level message.");
        }
        if (logger.isEnabled(Level.ERROR)) {
            logger.error("Test: ERROR level message.");
        }
        if (logger.isEnabled(Level.FATAL)) {
            logger.fatal("Test: FATAL level message.");
        }
    }
}
