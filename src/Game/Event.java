package Game;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

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

        if (Main.menu) {
            Main.menu = false;
        }
        else {
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

        if (Main.menu) {
            Main.menu = false;
        }

        recentKey = keyEvent.getKeyChar();

        if (recentKey == 'o') {
            Main.shop.isOpen = !Main.shop.isOpen;

        }
        else if (Main.shop.isOpen) {
            Main.shop.Select(recentKey);
        }
        else if (Main.upgrading) {
            Main.upgradeMenu.Purchase(recentKey);
        }
    }

    @Override
    public void keyReleased(KeyEvent keyEvent) {

    }
}
