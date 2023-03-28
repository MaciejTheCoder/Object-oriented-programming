/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package javaproject.Project;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.GridLayout;
import javaproject.Project.World;
import javaproject.animals.*;
import javaproject.plants.*;

/**
 *
 * @author Maciej
 */
public class Panel extends JPanel {

    public JButton buttons[][];
    public static final int MARGIN = 50;
    public static final int INS = 0;

    public Panel(int x, int y, World world,Frame frame) {

        setLayout(new GridLayout(y, x));
        int k = 0;
        //searching for mazimum optimal size of a button(board must fit in a frame)
        while (k * x < Static.PANEL_W && k * y < Static.PANEL_H) {
            k++;
        }
        k--;
        setBounds(MARGIN, MARGIN, k * x, k * y);

        buttons = new JButton[x][y];
        //creating an array of a buttons
        for (int i = 0; i < y; i++) {
            for (int j = 0; j < x; j++) {
                buttons[j][i] = new JButton("");
                buttons[j][i].setMargin(new Insets(INS, INS, INS, INS));//setting insets
                buttons[j][i].setFont(new Font("Arial", Font.PLAIN, k * 5 / 7));//(5/7)is a size of font to size of a button ratio
                add(buttons[j][i]);
                final int pos_x=j;
                final int pos_y=i;
                buttons[j][i].addActionListener(new ActionListener() {//if active button is clicked you can add new organism to this place
                    public void actionPerformed(ActionEvent e) {
                        Point p = MouseInfo.getPointerInfo().getLocation();
                        JFrame list = new JFrame();//new frame for choosing a organism
                        list.setBounds(p.x, p.y, Static.LIST_W, Static.LIST_H);
                        JButton bw = new JButton("Wolf");
                        JButton bo = new JButton("Sheep");
                        JButton bl = new JButton("Fox");
                        JButton bz = new JButton("Turtle");
                        JButton ba = new JButton("Antelope");
                        JButton bc = new JButton("CyberSheep");
                        JButton bt = new JButton("Grass");
                        JButton bm = new JButton("Sow thistle");
                        JButton bg = new JButton("Guarana");
                        JButton bj = new JButton("Belladona");
                        JButton bb = new JButton("Sosnowsky's Hogweed");
                        list.add(bw);
                        bw.addActionListener(new ActionListener() {
                            public void actionPerformed(ActionEvent e) {
                                world.Add(new Wolf(world,pos_x,pos_y));
                                list.setVisible(false);
                                frame.requestFocus();
                                world.drawboard();
                            }
                        });
                        list.add(bo);
                        bo.addActionListener(new ActionListener() {
                            public void actionPerformed(ActionEvent e) {
                                world.Add(new Sheep(world,pos_x,pos_y));
                                list.setVisible(false);
                                frame.requestFocus();
                                world.drawboard();
                            }
                        });
                        list.add(bl);
                        bl.addActionListener(new ActionListener() {
                            public void actionPerformed(ActionEvent e) {
                                world.Add(new Fox(world,pos_x,pos_y));
                                list.setVisible(false);
                                frame.requestFocus();
                                world.drawboard();
                            }
                        });
                        list.add(bz);
                        bz.addActionListener(new ActionListener() {
                            public void actionPerformed(ActionEvent e) {
                                world.Add(new Turtle(world,pos_x,pos_y));
                                list.setVisible(false);
                                frame.requestFocus();
                                world.drawboard();
                            }
                        });
                        list.add(ba);
                        ba.addActionListener(new ActionListener() {
                            public void actionPerformed(ActionEvent e) {
                                world.Add(new Antelope(world,pos_x,pos_y));
                                list.setVisible(false);
                                frame.requestFocus();
                                world.drawboard();
                            }
                        });
                        list.add(bc);
                        ba.addActionListener(new ActionListener() {
                            public void actionPerformed(ActionEvent e) {
                                world.Add(new CyberSheep(world,pos_x,pos_y));
                                list.setVisible(false);
                                frame.requestFocus();
                                world.drawboard();
                            }
                        });
                        list.add(bt);
                        bt.addActionListener(new ActionListener() {
                            public void actionPerformed(ActionEvent e) {
                                world.Add(new Grass(world,pos_x,pos_y));
                                list.setVisible(false);
                                frame.requestFocus();
                                world.drawboard();
                            }
                        });
                        list.add(bm);
                        bm.addActionListener(new ActionListener() {
                            public void actionPerformed(ActionEvent e) {
                                world.Add(new Sow_thistle(world,pos_x,pos_y));
                                list.setVisible(false);
                                frame.requestFocus();
                                world.drawboard();
                            }
                        });
                        list.add(bg);
                        bg.addActionListener(new ActionListener() {
                            public void actionPerformed(ActionEvent e) {
                                world.Add(new Guarana(world,pos_x,pos_y));
                                list.setVisible(false);
                                frame.requestFocus();
                                world.drawboard();
                            }
                        });
                        list.add(bj);
                        bj.addActionListener(new ActionListener() {
                            public void actionPerformed(ActionEvent e) {
                                world.Add(new Belladona(world,pos_x,pos_y));
                                list.setVisible(false);
                                frame.requestFocus();
                                world.drawboard();
                            }
                        });
                        list.add(bb);
                        bb.addActionListener(new ActionListener() {
                            public void actionPerformed(ActionEvent e) {
                                world.Add(new Sosnowskys_hogweed(world,pos_x,pos_y));
                                list.setVisible(false);
                                frame.requestFocus();
                                world.drawboard();
                            }
                        });
                        list.setVisible(true);
                        list.setLayout(new GridLayout(2, 5));
                    }
                });
            }
        }
        setVisible(true);
    }

}
