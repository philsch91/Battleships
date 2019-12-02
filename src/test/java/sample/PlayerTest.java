package sample;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

import org.apache.logging.log4j.*;

class PlayerTest {

    private static final Logger logger = LogManager.getLogger(PlayerTest.class);
    private static DBController dbController;
    private HumanPlayer player;

    @BeforeEach
    void setUp() {
        //logger.info("beforeEach");
    }

    @Test
    void playerTest() {
        logger.info("playerTest");
        HumanPlayer player = new HumanPlayer();

        assertEquals(player, player);
    }

    @DisplayName("Test #1")
    @Test
    void testAttackPossible_NotPossible(){
        HumanPlayer player = new HumanPlayer();
        player.SaveAttack(1, 1);

        boolean result = player.attackPossible(1,1);
        assertFalse(result);
    }

    @Test
    void playerSetShipTest(){
        HumanPlayer player = new HumanPlayer();
        //player.area.setShip(1,1,Direction.RIGHT,)
    }
}