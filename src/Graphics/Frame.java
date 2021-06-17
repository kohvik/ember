package Graphics;

import javax.swing.*;
import java.awt.*;
import Game.Event;

public class Frame extends JFrame {

    public Sketch sketch = new Sketch();
    public Event event = new Event(new Point());

    public Frame() {

        // code responsible for creating and defining properties of the window
        setVisible(true);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setSize(820,840);
        setResizable(false);
        setTitle("ember");

        add(sketch);
        addMouseListener(event);

    }
}
