package cl.talca.videogame;

import java.awt.Dimension;
import java.awt.EventQueue;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.event.ActionEvent;
import java.awt.event.KeyEvent;
import javax.swing.AbstractAction;
import javax.swing.Action;
import javax.swing.ActionMap;
import javax.swing.InputMap;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.KeyStroke;
import javax.swing.UIManager;
import javax.swing.UnsupportedLookAndFeelException;

public class Test {

    public static void main(String[] args) {
        new Test();
    }

    public Test() {
        EventQueue.invokeLater(new Runnable() {

            public void run() {
                try {
                    UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
                } catch (Exception ex) {
                    ex.printStackTrace();
                }

                JFrame frame = new JFrame("Testing");
                frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
                frame.add(new TestPane());
                frame.pack();
                frame.setLocationRelativeTo(null);
                frame.setVisible(true);
            }
        });
    }

    public class TestPane extends JPanel {

        private int xPos;

        public TestPane() {
            Action leftAction = new AbstractAction() {

                public void actionPerformed(ActionEvent e) {
                    xPos -= 2;
                    if (xPos < 0) {
                        xPos = 0;
                    }
                    repaint();
                }
            };
            Action rightAction = new AbstractAction() {

                public void actionPerformed(ActionEvent e) {
                    xPos += 2;
                    if (xPos + 10 > getWidth()) {
                        xPos = getWidth() - 10;
                    }
                    repaint();
                }
            };

            //"WHEN_IN_FOCUSED_WINDOW" es el que hace una accion al mantener presionado, por lo que entiendo
            bindKeyStroke(WHEN_IN_FOCUSED_WINDOW, "move.left", KeyStroke.getKeyStroke(KeyEvent.VK_LEFT, 0), leftAction);
            bindKeyStroke(WHEN_IN_FOCUSED_WINDOW, "move.left", KeyStroke.getKeyStroke(KeyEvent.VK_KP_LEFT, 0), leftAction);
            bindKeyStroke(WHEN_IN_FOCUSED_WINDOW, "move.left", KeyStroke.getKeyStroke(KeyEvent.VK_4, 0), leftAction);
            bindKeyStroke(WHEN_IN_FOCUSED_WINDOW, "move.left", KeyStroke.getKeyStroke(KeyEvent.VK_A, 0), leftAction);

            bindKeyStroke(WHEN_IN_FOCUSED_WINDOW, "move.right", KeyStroke.getKeyStroke(KeyEvent.VK_RIGHT, 0), rightAction);
            bindKeyStroke(WHEN_IN_FOCUSED_WINDOW, "move.right", KeyStroke.getKeyStroke(KeyEvent.VK_KP_RIGHT, 0), rightAction);
            bindKeyStroke(WHEN_IN_FOCUSED_WINDOW, "move.right", KeyStroke.getKeyStroke(KeyEvent.VK_6, 0), rightAction);
            bindKeyStroke(WHEN_IN_FOCUSED_WINDOW, "move.right", KeyStroke.getKeyStroke(KeyEvent.VK_D, 0), rightAction);
        }

        protected void bindKeyStroke(int condition, String name, KeyStroke keyStroke, Action action) {
            InputMap im = getInputMap(condition);
            ActionMap am = getActionMap();

            im.put(keyStroke, name);
            am.put(name, action);
        }

        @Override
        public Dimension getPreferredSize() {
            return new Dimension(200, 200);
        }

        protected void paintComponent(Graphics g) {
            super.paintComponent(g);
            Graphics2D g2d = (Graphics2D) g.create();
            int yPos = (getHeight() - 10) / 2;
            g2d.drawRect(xPos, yPos, 10, 10);
            g2d.dispose();
        }

    }

}
