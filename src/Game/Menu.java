package Game;

import java.awt.*;

public class Menu {

    public void drawMainMenu(Graphics2D graphics) {
        graphics.setColor(new Color(0, 57, 35));
        graphics.fillRect(0, 0, 820, 840);
        graphics.setColor(Color.WHITE);
        graphics.drawString("welcome to ember!", 350, 300);
        graphics.drawString("m. load game from save file", 330, 500);
        graphics.drawString("n. begin new game", 330, 530);
    }

    public void drawLoadingMessage(Graphics2D graphics) {
        graphics.setColor(new Color(134, 137, 13));
        graphics.fillRect(0, 0, 820, 840);
        graphics.setColor(Color.WHITE);
        graphics.drawString("loaded save file!", 350, 300);
        graphics.drawString("press any key to continue...", 335, 500);
    }

    public void drawInGameMenu(Graphics2D graphics) {
        graphics.setColor(new Color(69, 69, 69));
        graphics.fillRect(410 - 150,300, 300,  150 + 20);
        graphics.setColor(Color.white);
        graphics.drawString("menu", 410 - 20, 320);

        graphics.drawString("p. save game", 410 - 60, 400);
        graphics.drawString("e. exit to main menu", 410 - 60, 430);
    }

    public void drawWonMessage(Graphics2D graphics) {
        graphics.setColor(new Color(134, 137, 13));
        graphics.fillRect(410 - 150,300, 300,  150 + 20);
        graphics.setColor(Color.white);
        graphics.drawString("you win!", 410 - 60, 380);
    }

}
