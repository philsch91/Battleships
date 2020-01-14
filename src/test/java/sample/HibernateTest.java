package sample;

import org.hibernate.Session;
import org.junit.BeforeClass;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import org.apache.logging.log4j.Logger;
import org.apache.logging.log4j.LogManager;
import sample.entity.TestResultEntity;
import sample.util.HibernateUtil;

import java.sql.SQLException;

public class HibernateTest {

    private static final Logger logger = LogManager.getLogger(HibernateTest.class);

    @BeforeClass
    public static void init() {
        //init
    }

    @BeforeEach
    public void setUp() {
        //setUp
    }

    @Test
    public void testTestResultSave_Possible() {
        Session session = HibernateUtil.getSessionFactory().openSession();
        session.beginTransaction();

        TestResultEntity result = new TestResultEntity();
        result.setId(1234);
        result.setApplication(HibernateTest.class.getSimpleName());
        result.setName("Test");
        result.setResult(1);

        session.save(result);
        session.getTransaction().commit();
        HibernateUtil.shutdown();
    }
}
