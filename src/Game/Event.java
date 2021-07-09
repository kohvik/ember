package Game;

import Buildings.Upgrade;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.util.concurrent.TimeUnit;

public class Event implements MouseListener, KeyListener {

    public Point mousePosition;
    public char recentKey;

    public Event(Point mousePosition, char recentKey) {

        this.mousePosition = mousePosition;
        this.recentKey = recentKey;

    }

    //mouse listener events

    @Override
    public void mouseClicked(MouseEvent mouseEvent) {

        mousePosition.x = (mouseEvent.getX() / 102);
        mousePosition.y = (mouseEvent.getY() / 105);

        if (SwingUtilities.isLeftMouseButton(mouseEvent)) {
            Main.shop.Purchase(mousePosition);
        }
        if (SwingUtilities.isRightMouseButton(mouseEvent)) {
            Main.upgrading = !Main.upgrading;
            Main.upgradeMenu.setUpgradeLocation(mousePosition.x, mousePosition.y);
        }

    }

    @Override
    public void mousePressed(MouseEvent mouseEvent) {

    }

    @Override
    public void mouseReleased(MouseEvent mouseEvent) {

    }

    @Override
    public void mouseEntered(MouseEvent mouseEvent) {

    }

    @Override
    public void mouseExited(MouseEvent mouseEvent) {

    }

    //keyboard listener events

    @Override
    public void keyTyped(KeyEvent keyEvent) {

    }

    @Override
    public void keyPressed(KeyEvent keyEvent) {

        recentKey = keyEvent.getKeyChar();
        if (Main.menuScene == 1) {
            if (recentKey == 'n') {
                for (int x = 0; x < Main.buildingInfo.length; x++) {
                    for (int y = 0; y < Main.buildingInfo[x].length; y++) {
                        Main.buildingInfo[x][y].setDefault();
                    }
                }
                Main.gold = 100;
                Main.currentLevel = 0;
                Main.lives = 3;
                Main.menuScene = 0;
                Main.levels[Main.currentLevel].Load();
            }
            if (recentKey == 'm') {
                if (Loader.loadSaveFile()) {
                    Main.menuScene = 2;
                }
            }
        }
        else if (Main.menuScene == 2) {
            Main.menuScene = 0;
        }
        else if (recentKey == 'm') {
            //ternary to keep code clean
            Main.menuScene = (Main.menuScene == 3? 0:3);
        }
        else if (recentKey == 'o') {
            Main.shop.isOpen = !Main.shop.isOpen;
        }
        else if (recentKey == 'e') {
            Main.menuScene = 1;
        }
        else if (Main.shop.isOpen) {
            Main.shop.Select(recentKey);
        }
        else if (Main.upgrading) {
            Main.upgradeMenu.Purchase(recentKey);
        }
        if (recentKey == 's') {
            if (Main.menuScene == 3) {
                Loader.saveGame();
                Main.menuScene = 0;
            }
        }
    }

    @Override
    public void keyReleased(KeyEvent keyEvent) {

    }
}
