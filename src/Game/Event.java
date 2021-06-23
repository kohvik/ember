package Game;

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
        mousePosition.x = (mouseEvent.getX() / 100);
        mousePosition.y = (mouseEvent.getY() / 100);

        Main.shop.Purchase(mousePosition);
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

        if (recentKey == 'o') {
            Main.shop.isOpen = !Main.shop.isOpen;

        }
        else if (Main.shop.isOpen) {
            Main.shop.Select(recentKey);
        }
    }

    @Override
    public void keyReleased(KeyEvent keyEvent) {

    }
}
