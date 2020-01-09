package cl.talca.videogame.utils;

import java.awt.EventQueue;
import java.awt.image.BufferedImage;
import java.io.IOException;
import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.UIManager;
import javax.swing.UnsupportedLookAndFeelException;

public class BackgroundTest {

    public static void main(String[] args) {
        new BackgroundTest();
    }

    public BackgroundTest() {
        EventQueue.invokeLater(new Runnable() {
            @Override
            public void run() {
                try {
                    UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
                } catch (ClassNotFoundException | InstantiationException | IllegalAccessException | UnsupportedLookAndFeelException ex) {
                }

                try {
                    // Load the background image
                    BufferedImage img = ImageIO.read(getClass().getClassLoader().getResource("img/background.png"));

                    // Create the frame...
                    JFrame frame = new JFrame("Testing");
                    frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

                    // Set the frames content pane to use a JLabel
                    // whose icon property has been set to use the image
                    // we just loaded
                    frame.setContentPane(new JLabel(new ImageIcon(img)));

                    frame.pack();
                    frame.setLocationRelativeTo(null);
                    frame.setVisible(true);

                } catch (IOException exp) {
                    exp.printStackTrace();
                }
            }
        });
    }
}