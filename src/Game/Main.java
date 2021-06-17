package Game;

import Buildings.Archer;
import Buildings.Building;
import Entities.Entity;
import Graphics.Frame;
import java.awt.*;
import java.util.concurrent.TimeUnit;

public class Main {

    public static Point[][] coordinates = new Point[8][8];
    public static int[][] occupied = new int[8][8];
    public static int[][] repeat = new int[8][8];
    public static long[][] preAttackTime = new long[8][8];

    //establishing levels
    public static Level level1 = new Level(new int[]{30,5});
    public static Level level2 = new Level(new int[]{10,5});
    public static Level[] levels = {level1,level2};

    public static int lives = 3;
    public static int gold = 100;
    public static int currentLevel = 0;

    public static Entity[] warriors;
    public static Entity[] vikings;
    public static Entity[][] entities;
    public static Building[] buildings;

    public static long currentTime;

    public static void main(String[] args) {

        //filling buildings index with all the buildings
        buildings = new Building[1];
        Building archer = new Archer(50,1);
        buildings[0] = archer;

        Frame frame = new Frame();

        levels[currentLevel].Load();

        //game loop
        while (true) {

            //sets values for all coordinates in the center of each square.
            for (int x = 0; x < coordinates.length; x++) {
                for (int y = 0; y < coordinates[x].length; y++) {

                    coordinates[x][y] = new Point(((x+1)*100-50),((y+1)*100-50));

                }

            }

            //keeping current time up to date
            currentTime = System.currentTimeMillis();

            //refreshing the screen
            frame.sketch.repaint();
            //using sleep function to set fps to 100
            try {
                TimeUnit.MILLISECONDS.sleep(10);
            } catch (Exception e) {System.out.println("could not perform sleep function");}

        }

    }

}

