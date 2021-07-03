package Game;

import Buildings.*;
import Entities.Entity;
import Graphics.Frame;
import java.awt.*;
import java.util.Arrays;
import java.util.concurrent.TimeUnit;

public class Main {

    public static BuildingInfo[][] buildingInfo = new BuildingInfo[8][8];

    //establishing levels
    public static Level level1 = new Level(new int[]{5,0,0});
    public static Level level2 = new Level(new int[]{8,1,0});
    public static Level level3 = new Level(new int[]{5,5,0});
    public static Level level4 = new Level(new int[]{0,8,0});
    public static Level level5 = new Level(new int[]{10,0,3});
    public static Level level6 = new Level(new int[]{10,10,0});
    public static Level[] levels = {level1,level2,level3,level4,level5,level6};

    public static int lives = 3;
    public static int gold = 500;
    public static int currentLevel = 0;
    public static int livingEnemies;
    public static long currentTime;
    public static boolean menu;
    public static boolean upgrading;

    public static Entity[] warriors;
    public static Entity[] vikings;
    public static Entity[] golems;
    public static Entity[][] entities;
    public static Building[] buildings;

    public static UpgradeMenu upgradeMenu = new UpgradeMenu();
    public static Shop shop = new Shop(1, false, new Dimension(600,350));

    public static void main(String[] args) {

        Frame frame = new Frame();

        //filling buildings index with all the buildings
        Building archer = new Archer(30,1, "archer");
        Building sniper = new Sniper(100, 2, "sniper");
        Building mage = new Mage(60, 3, "mage");
        Building cannon = new Cannon(200, 4, "cannon");
        buildings = new Building[]{archer, sniper, mage, cannon};

        //upgrade stuffs

        //filling buildingInfo with defaults
        for (int x = 0; x < buildingInfo.length; x++) {
            for (int y = 0; y < buildingInfo[x].length; y++) {
                buildingInfo[x][y] = new BuildingInfo(new Point(), 0,0, 0, new int[2], new Upgrade[2]);
            }
        }

        //sets values for all coordinates in the center of each square.
        for (int x = 0; x < buildingInfo.length; x++) {
            for (int y = 0; y < buildingInfo[x].length; y++) {

                buildingInfo[x][y].coordinates = new Point(((x+1)*100-50),((y+1)*100-50));

            }
        }

        //establishing values for shop coordinates
        shop.Establish();

        //loading initial level
        levels[currentLevel].Load();
        livingEnemies = Arrays.stream(levels[currentLevel].enemies).sum();

        //menu
        menu = true;

        //game loop
        while (true) {

            if (livingEnemies <= 0) {

                currentLevel++;
                frame.sketch.repaint();
                levels[currentLevel].Load();
                livingEnemies = Arrays.stream(levels[currentLevel].enemies).sum();

            }

            //keeping current time up to date
            currentTime = System.currentTimeMillis();

            //refreshing the screen
            frame.sketch.repaint();

            //using sleep function to set fps
            try {
                TimeUnit.MILLISECONDS.sleep(15);
            } catch (Exception e) {System.out.println("could not perform sleep function");}

        }

    }

}

