package Game;

import Entities.*;

import java.util.Arrays;

public class Level {

    int[] enemies;


    public Level(int[] enemies) {

        this.enemies = enemies;

    }

    public void load() {
        int lastEnemyPosition = -500;

        Main.warriors = new Entity[this.enemies[0]];
        Main.vikings = new Entity[this.enemies[1]];
        Main.golems = new Entity[this.enemies[2]];

        //loading warriors
        Main.warriors = new Entity[this.enemies[0]];
        for (int i = 0; i < Main.warriors.length; i++) {
            Main.warriors[i] = new Warrior(Warrior.maxHP, 1, lastEnemyPosition, true);
            lastEnemyPosition -= 50;
        }

        //loading vikings
        for (int i = 0; i < Main.vikings.length; i++) {
            Main.vikings[i] = new Viking(Viking.maxHP, 2, lastEnemyPosition, true);
            lastEnemyPosition -= 50;
        }

        //loading golems
        for (int i = 0; i < Main.golems.length; i++) {
            Main.golems[i] = new Golem(Golem.maxHP, 1, lastEnemyPosition, true);
            lastEnemyPosition -= 50;
        }

        //filling entities array with entities
        Main.entities = new Entity[][]{Main.warriors, Main.vikings, Main.golems};

    }

}
