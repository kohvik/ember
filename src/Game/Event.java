package Game;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class Event implements MouseListener, KeyListener, MouseMotionListener {

    public Point playerPosition = new Point(0,0);
    public Point mousePosition;

    public char recentKey;
    public char recentReleased;

    public Event(Point mousePosition, char recentKey, char recentReleased) {

        this.mousePosition = mousePosition;
        this.recentKey = recentKey;
        this.recentReleased = recentReleased;

    }

    //mouse listener events

    @Override
    public void mouseClicked(MouseEvent mouseEvent) {

    }

    @Override
    public void mousePressed(MouseEvent mouseEvent) {
        mousePosition.x = mouseEvent.getX();
        mousePosition.y = mouseEvent.getY();

        if (SwingUtilities.isLeftMouseButton(mouseEvent)) {
            Main.player.attacking[0] = true;
            Main.player.getMousePosition(mousePosition);
        }

        if (SwingUtilities.isRightMouseButton(mouseEvent)) {
            Main.player.attacking[1] = true;
            Main.player.getMousePosition(mousePosition);
        }
    }

    @Override
    public void mouseReleased(MouseEvent mouseEvent) {
        mousePosition.x = mouseEvent.getX();
        mousePosition.y = mouseEvent.getY();
        if (SwingUtilities.isLeftMouseButton(mouseEvent)) {
            Main.player.attacking[0] = false;
        }
        if (SwingUtilities.isRightMouseButton(mouseEvent)) {
            Main.player.attacking[1] = false;
        }
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

        if (recentKey == 'w') {
            Main.player.isUp = true;
        }
        if (recentKey == 'a') {
            Main.player.isLeft = true;
        }
        if (recentKey == 's') {
            Main.player.isDown = true;
        }
        if (recentKey == 'd') {
            Main.player.isRight = true;
        }

        if (recentKey == ' ') {
            playerPosition.x = (int)(Main.player.positionX + 20)/102;
            playerPosition.y = (int)(Main.player.positionY + 40)/105;
            Main.buildingShop.purchase(playerPosition);
        }
        if (recentKey == 'v') {
            playerPosition.x = (int)(Main.player.positionX + 20)/102;
            playerPosition.y = (int)(Main.player.positionY + 40)/105;
            Main.upgradeMenu.setUpgradeLocation(playerPosition.x, playerPosition.y);
            Main.upgrading = !Main.upgrading;
        }

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
                Main.levels[Main.currentLevel].load();
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
            Main.buildingShop.isOpen = !Main.buildingShop.isOpen;
        }
        else if (recentKey == 't') {
            Main.playerShop.isOpen = !Main.playerShop.isOpen;
        }
        else if (recentKey == 'e') {
            if (Main.menuScene == 3) {
                Main.menuScene = 1;
            }
        }
        else if (Main.buildingShop.isOpen) {
            Main.buildingShop.select(recentKey);
        }
        else if (Main.playerShop.isOpen) {
            Main.playerShop.purchase(recentKey);
        }
        else if (Main.upgrading) {
            Main.upgradeMenu.purchase(recentKey);
        }
        if (recentKey == 'p') {
            if (Main.menuScene == 3) {
                Loader.saveGame();
                Main.menuScene = 0;
            }
        }
    }

    @Override
    public void keyReleased(KeyEvent keyEvent) {

        recentReleased = keyEvent.getKeyChar();

        if (recentReleased == 'w') {
            Main.player.isUp = false;
        }
        if (recentReleased == 'a') {
            Main.player.isLeft = false;
        }
        if (recentReleased == 's') {
            Main.player.isDown = false;
        }
        if (recentReleased == 'd') {
            Main.player.isRight = false;
        }
    }

    @Override
    public void mouseDragged(MouseEvent mouseEvent) {
        Main.player.getMousePosition(mouseEvent.getPoint());
    }

    @Override
    public void mouseMoved(MouseEvent mouseEvent) {

    }
}
