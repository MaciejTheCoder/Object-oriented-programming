/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package javaproject.Project;

import javax.swing.*;
import java.awt.event.*;
import javaproject.*;

/**
 *
 * @author Maciej
 */
public class Menu implements ActionListener {

    JTextField tf1, tf2, tf3;
    JButton b1, b2;
    JLabel l3;
    JFrame f;
    public static final int L_MARGIN = 40;
    public static final int TEXT_MARGIN = 20;

    public Menu() {
        f = new JFrame();
        f.setLocation(Static.MENU_LX, Static.MENU_LY);
        f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        JLabel l1, l2;
        l3 = new JLabel("");
        l3.setBounds(L_MARGIN, 150, 150, 50);
        l1 = new JLabel("Set X-size:");
        l1.setBounds(L_MARGIN, 20, 100, 30);
        l2 = new JLabel("Set Y-size:");
        l2.setBounds(L_MARGIN, 70, 100, 30);
        tf1 = new JTextField();
        tf1.setBounds(L_MARGIN, 50, 50, 20);
        tf2 = new JTextField();
        tf2.setBounds(L_MARGIN, 100, 50, 20);
        b1 = new JButton("Set");
        b1.setBounds(L_MARGIN-10, 200, 150, 50);
        b1.addActionListener(this);
        f.add(l1);
        f.add(tf1);
        f.add(l2);
        f.add(tf2);
        f.add(b1);
        f.add(l3);
        f.setSize(Static.MENU_H, Static.MENU_W);
        f.setLayout(null);
        f.setVisible(true);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        String s1 = tf1.getText();
        String s2 = tf2.getText();
        int a = 0;
        int b = 0;
        try {//checks if integer
            a = Integer.parseInt(s1);
            b = Integer.parseInt(s2);
            if (a > 0 && b > 0) {
                f.setVisible(false);
                new Game(a, b);//starts new game
            } else {
                l3.setText("Invalid input!");
            }
        } catch (NumberFormatException p) {
            l3.setText("Invalid input!");
        }
    }
}
