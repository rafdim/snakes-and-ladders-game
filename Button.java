import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;

public class Button extends JButton {
    Button() {
        JButton button = new JButton();
        button.setBackground(Color.darkGray);
        button.setForeground(Color.white);
        button.setFont(new Font("Calibri",Font.BOLD,10));
        button.setFocusable(false);
        button.setText("Roll Dice");
    }
}
