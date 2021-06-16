package Game;

import java.awt.*;
import java.awt.event.*;

public class Event implements MouseListener {

    public Point mousePosition;

    public Event(Point mousePosition) {

        this.mousePosition = mousePosition;

    }

    @Override
    public void mouseClicked(MouseEvent mouseEvent) {
        mousePosition.x = (mouseEvent.getX() / 100);
        mousePosition.y = (mouseEvent.getY() / 100);

        Shop.Purchase(mousePosition);
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
}
