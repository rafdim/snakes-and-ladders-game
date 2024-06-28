import javax.swing.*;
import java.awt.*;

public class Text extends JLabel{
    Text(String text) {
        JLabel turn = new JLabel(text);
        this.setBackground(Color.lightGray);
        this.setForeground(Color.black);
        this.setFont(new Font("Calibri", Font.BOLD, 25));
        this.setHorizontalAlignment(JLabel.LEFT);
    }
}
