package Game;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.util.Objects;

public class Loader {

    public static BufferedImage loadAsset(String imageName) {

        try {

            return ImageIO.read(Objects.requireNonNull(Loader.class.getResourceAsStream(imageName)));

        } catch (Exception e) {System.out.println("missing " + imageName.substring(1));}

        return null;

    }

    public static void loadSaveFile() {

    }

    public static void SaveGame() {

    }

}