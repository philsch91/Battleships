package sample;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

import org.apache.logging.log4j.*;

class PlayerTest {

    private static final Logger logger = LogManager.getLogger(PlayerTest.class);
    private static DBController dbController;
    private HumanPlayer player;

    @Test
    void testPlayer__PlayerInit() {
        logger.info("playerTest");
        HumanPlayer player = new HumanPlayer();
        assertEquals(player, player);
    }

    @DisplayName("Test #1")
    @Test
    void testAttack_NotPossible(){
        HumanPlayer player = new HumanPlayer();
        player.saveAttack(1, 1);
        boolean result = player.attackPossible(1,1);
        assertFalse(result);
    }

    @Test
    void testAttackPossible__AttackNotPossible(){
        HumanPlayer player = new HumanPlayer();
        player.saveAttack(1,1);
        assertFalse(player.attackPossible(1,1));
    }

    @Test
    void testAttackPossible__AttackPossible(){
        HumanPlayer player = new HumanPlayer();
        player.saveAttack(1,2);
        assertTrue(player.attackPossible(1,1));
    }

    @Test
    void testReset__ResetList(){
        HumanPlayer player = new HumanPlayer();
        player.saveAttack(1,1);
        assertFalse(player.attackPossible(1,1));
        player.reset();
        assertTrue(player.attackPossible(1,1));
    }
}