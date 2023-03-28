/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package javaproject.Project;

import java.awt.GridLayout;
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import javaproject.Project.Direction;
import javaproject.Project.World;

/**
 *
 * @author Maciej
 */
public class Frame extends JFrame {

    public Panel panel;
    public Log log;

    public Frame(int size_x, int size_y, World world) {
        super("Maciej Majewski S184945");
        setSize(Static.FRAME_W, Static.FRAME_H);
        panel = new Panel(size_x, size_y, world, this);
        add(panel);
        log = new Log();
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        JLabel l1 = new JLabel("Choose direction");
        l1.setBounds(Static.PANEL_W + 100, 840, 120, 30);
        JButton b1 = new JButton("Next Round");
        b1.setBounds(Static.PANEL_W + 100, 800, 120, 30);
        JLabel l2 = new JLabel("Special Ability: Ready");
        l2.setForeground(Color.GREEN);
        l2.setBounds(100, 20, 120, 30);
        JLabel l3 = new JLabel("Human is Alive");
        l3.setBounds(100, 0, 120, 30);
        add(l3);
        add(l2);
        add(log);
        b1.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                log.text.setText("");
                remove(l1);
                if (world.GetIfAlive() == true) {
                    if (world.GetSinceLastSuperAbility() >= 5) {
                        l2.setText("Special Ability: Ready");
                        l2.setForeground(Color.green);
                    } else {
                        l2.setText("Special Ability: Used");
                        l2.setForeground(Color.RED);
                    }
                }
                if (world.GetIfAlive() == false) {
                    remove(l2);
                    l2.setForeground(Color.RED);
                    l3.setText("HUMAN IS DEAD!");
                    world.NextRound();
                } else if (world.kierunek != null) {
                    world.NextRound();
                } else {
                    add(l1);
                }
                requestFocus();
            }
        });

        this.addKeyListener(new KeyListener() {
            @Override
            public void keyTyped(KeyEvent e) {
            }

            @Override
            public void keyReleased(KeyEvent e) {
            }

            @Override
            public void keyPressed(KeyEvent e) {
                int code = e.getKeyCode();
                remove(l1);
                if (world.GetIfAlive() == true) {
                    if (world.GetSinceLastSuperAbility() >= 5) {
                        l2.setText("Special Ability: Ready");
                        l2.setForeground(Color.green);
                    } else {
                        l2.setText("Special Ability: Used");
                        l2.setForeground(Color.RED);
                    }
                    switch (code) {
                        case KeyEvent.VK_S:
                            world.kierunek = Direction.DOWN;
                            world.NextRound();
                            break;
                        case KeyEvent.VK_W:
                            world.kierunek = Direction.UP;
                            world.NextRound();
                            break;
                        case KeyEvent.VK_A:
                            world.kierunek = Direction.LEFT;
                            world.NextRound();
                            break;
                        case KeyEvent.VK_D:
                            world.kierunek = Direction.RIGHT;
                            world.NextRound();
                            break;
                        case KeyEvent.VK_X:
                            if (world.GetSinceLastSuperAbility() >= 5) {
                                world.SetLastAbility();
                                l2.setText("Special Ability: Used");
                                l2.setForeground(Color.RED);
                            }
                        default:
                            add(l1);
                            break;
                    }
                } else {
                    add(l1);
                }
                requestFocus();
            }
        });

        setFocusable(true);
        JButton b2 = new JButton("Load");
        b2.setBounds(1400, 800, 80, 30);
        b2.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                JFrame name = new JFrame();
                JTextField textfield = new JTextField(30);
                name.add(textfield);
                name.setSize(200, 200);
                name.setVisible(true);
                textfield.addActionListener(new ActionListener() {
                    public void actionPerformed(ActionEvent e) {
                        world.Load(textfield.getText());
                        name.setVisible(false);
                    }
                });
            }
        });
        JButton b3 = new JButton("Save");
        b3.setBounds(1500, 800, 80, 30);
        b3.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                JFrame name = new JFrame();
                JTextField textfield = new JTextField(30);
                name.add(textfield);
                name.setSize(200, 200);
                name.setVisible(true);
                textfield.addActionListener(new ActionListener() {
                    public void actionPerformed(ActionEvent e) {
                        world.Save(textfield.getText());
                        name.setVisible(false);
                    }
                });
            }
        });

        add(b1);

        add(b2);

        add(b3);

        setLayout(null);
        setVisible(true);

    }

}
