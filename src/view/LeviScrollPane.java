package view;

import javax.swing.*;
import java.awt.*;

public class LeviScrollPane extends JScrollPane {
    public LeviScrollPane(){
        super(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS, JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);
        setMinimumSize(new Dimension(200,200));
    }
}
