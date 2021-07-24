package Game.Player;

import Entities.Entity;
import Game.Main;

import java.awt.*;

public class Lightning extends PlayerUpgrade{

    public Lightning(int cost, int id, int level, String name, String type, boolean equipped, int attackSpeed){
        super(cost, id, level, name, type, equipped, attackSpeed);
    }

    public void doAttack() {

        Entity entity = Main.player.getClosestEntity();

        if (entity != null) {
            Entity secondEntity = entity.getNearestNeighbour();

            Main.player.beams.add(new Beam((int)Main.player.positionX + 20, (int)Main.player.positionY + 20, entity.position + 20, 415,
                    10, 50, 4, new Color(219, 218, 98)));
            Main.player.beams.add(new Beam(entity.position + 20, 415, secondEntity.position + 20, 415,
                    10, 0, 3, new Color(219, 218, 98)));

            Main.player.getClosestEntity().getNearestNeighbour().health -= 50;
        }
    }

}