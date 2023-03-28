/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package javaproject.Project;

import javax.swing.*;
import java.awt.*;

/**
 *
 * @author Maciej
 */
public class Log extends JPanel {

    public JTextArea text;

    public Log() {
        setBounds(1000, 50, 400, 705);
        text = new JTextArea();
        text.setFont(new Font("Arial", Font.PLAIN, 16));
        text.setBackground(new Color(238, 238, 238));
        add(text);
    }
}
