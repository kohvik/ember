package Game;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.FileWriter;
import java.util.Objects;
import java.util.Scanner;

public class Loader {

    public static BufferedImage loadAsset(String imageName) {

        try {

            return ImageIO.read(Objects.requireNonNull(Loader.class.getResourceAsStream(imageName)));

        } catch (Exception e) {System.out.println("missing " + imageName.substring(1));}

        return null;

    }

    public static void loadLevelFile() {

    }

    public static boolean loadSaveFile() {

        try {
            Scanner scanner = new Scanner(new File("resources/save.txt"));

            int parse = Integer.parseInt(scanner.nextLine());
            Main.gold = parse;
            parse = Integer.parseInt(scanner.nextLine());
            Main.lives = parse;
            parse = Integer.parseInt(scanner.nextLine());
            Main.currentLevel = parse;

            for (int a = 0; a < Main.buildingInfo.length; a++) {
                for (int b = 0; b < Main.buildingInfo[a].length; b++) {

                    Main.buildingInfo[a][b].occupied = scanner.nextInt();
                    Main.buildingInfo[a][b].upgradeLevel[0] = scanner.nextInt();
                    Main.buildingInfo[a][b].upgradeLevel[1] = scanner.nextInt();
                    Main.buildingInfo[a][b].upgrades[0].loadUpgrade(scanner);
                    Main.buildingInfo[a][b].upgrades[1].loadUpgrade(scanner);

                }
            }

            Main.levels[Main.currentLevel].load();

            scanner.close();

            return true;

        } catch (Exception e) {System.out.println("failed to load save file!"); return false;}

    }

    public static void saveGame() {

        try {
            File saveFile = new File("resources/save.txt");
            saveFile.createNewFile();

            FileWriter writer = new FileWriter(saveFile);

            writer.write(Main.gold + "\n");
            writer.write(Main.lives + "\n");
            writer.write(Main.currentLevel + "\n");

            for (int a = 0; a < Main.buildingInfo.length; a++) {
                for (int b = 0; b < Main.buildingInfo[a].length; b++) {
                    writer.write(Main.buildingInfo[a][b].occupied + " ");
                    writer.write(Main.buildingInfo[a][b].upgradeLevel[0] + " ");
                    writer.write(Main.buildingInfo[a][b].upgradeLevel[1] + " ");
                    Main.buildingInfo[a][b].upgrades[0].saveUpgrade(writer);
                    Main.buildingInfo[a][b].upgrades[1].saveUpgrade(writer);
                }
                writer.write("\n");
            }

            writer.close();

        } catch (Exception e) {System.out.println("failed to save file!");}
    }

}