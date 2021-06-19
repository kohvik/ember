package Game;

import Entities.Entity;
import Entities.Warrior;
import Entities.Viking;

import java.util.Arrays;

public class Level {

    int[] enemies;

    public Level(int[] enemies) {

        this.enemies = enemies;

    }

    public void Load() {
        Main.warriors = new Entity[this.enemies[0]];
        Main.vikings = new Entity[this.enemies[1]];

        //loading warriors
        for (int i = 0; i < Main.warriors.length; i++) {
            Main.warriors[i] = new Warrior(100, 1, (-i * 50), true);
        }

        //loading vikings
        for (int i = 0; i < Main.vikings.length; i++) {
            Main.vikings[i] = new Viking(100, 2, (-i * 50) + (Main.warriors[Main.warriors.length - 1].position) - 50, true);
        }

        //filling entities array with entities
        Main.entities = new Entity[][]{Main.warriors, Main.vikings};

    }

}
