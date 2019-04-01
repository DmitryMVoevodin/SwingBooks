package com.netcracker.swingmodels;

import javax.swing.*;
import javax.swing.border.Border;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Reference extends JDialog {

    public Reference() {

        setTitle("About application");
        setIconImage((new ImageIcon("resources/img/referenceMenuIcon.png")).getImage());
        setSize(400, 300);
        setLocationRelativeTo(null);
        setResizable(false);
        setModal(true);

        JPanel panel1 = new JPanel();
        JPanel panel2 = new JPanel();
        panel1.setLayout(new GridLayout());
        panel2.setLayout(new FlowLayout());

        String textStr = "  This application belongs to Dmitry Voevodin.\n\n" +
                "  If you want to create a new table,\n  please, choose the corresponding option in the menu-bar.\n\n" +
                "  If you want to import some table,\n  please, find a file (*.library) using the import option in the menu-bar.\n\n" +
                "  You can create a new table or import some other table,\n  but you have to choose between these alternatives -\n" +
                "  all this session is devoted only to one of this variants.";
        JTextArea text = new JTextArea(textStr);
        text.setEditable(false);
        text.setBackground(Color.WHITE);
        panel1.setBackground(Color.WHITE);
        panel1.add(text);
        add(panel1);

        JButton acceptButton = new JButton("OK");
        panel2.setBackground(Color.WHITE);
        panel2.add(acceptButton);
        add(panel2, BorderLayout.SOUTH);

        acceptButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                dispose();
            }
        });

        setVisible(true);

    }

}
