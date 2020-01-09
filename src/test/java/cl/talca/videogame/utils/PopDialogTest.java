package cl.talca.videogame.utils;

import javax.swing.*;

public class PopDialogTest {

    public static void main(final String[] args) {
        final JFrame parent = new JFrame();
        JButton button = new JButton();

        String name = JOptionPane.showInputDialog(parent,
                "What is your name?", null);
        String password = JOptionPane.showInputDialog(parent,
                "What is your password?", null);
        /*
        button.setText("Click me to show dialog!");
        parent.add(button);
        parent.pack();
        parent.setVisible(true);

        button.addActionListener(new java.awt.event.ActionListener() {
            @Override
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                String name = JOptionPane.showInputDialog(parent,
                        "What is your name?", null);
            }
        });*/
    }
}

