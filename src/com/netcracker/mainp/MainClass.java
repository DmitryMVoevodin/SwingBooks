package com.netcracker.mainp;

import com.netcracker.swingmodels.MainFrame;

import javax.swing.*;

public class MainClass {

    public static void main(String[] args) {
        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                new MainFrame();
            }
        });
    }

}
