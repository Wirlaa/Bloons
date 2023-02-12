package agh;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class PlayerTest {

    @Test
    void IsBuyingTowerWorking() {
        Player player = new Player("player");
        player.buyTower(TowerType.BASIC);
        assertEquals(90, player.getMoney());
    }

    @Test
    void IsSellingTowerWorking() {
        Player player = new Player("player");
        player.sellTower(TowerType.BASIC);
        assertEquals(105, player.getMoney());
    }

}