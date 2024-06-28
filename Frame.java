import javax.swing.ImageIcon;
import javax.swing.JFrame;
import java.awt.*;

public class Frame extends JFrame{
    Frame() {
        this.setTitle("Snake and Ladder");
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setResizable(false);
        this.setExtendedState(JFrame.MAXIMIZED_BOTH);
        this.setVisible(true);
        this.setLocationRelativeTo(null);
        this.setLayout(new BorderLayout());
    }
}
