/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package javaproject.plants;

import java.awt.Color;
import java.util.Random;
import javaproject.Project.*;

/**
 *
 * @author Maciej
 */
public class Sosnowskys_hogweed extends Plant {

    public Sosnowskys_hogweed(World _world, int _pos_x, int _pos_y) {
        this.pos_x = _pos_x;
        this.pos_y = _pos_y;
        this.world = _world;
        this.power = 10;
        this.initiative = 0;
        this.symbol = "s";
        chance = 2;
        color = Color.WHITE;
        sign = "Sosnowskys hogweed";
    }

    @Override
    public void Add(World world, int posx, int posy) {
        world.Add(new Sosnowskys_hogweed(world, posx, posy));
    }

    @Override
    public void Action() {
        Random r = new Random();
        Burn();
        if (r.nextInt(100) < chance) {
            int tab[][];
            tab = new int[4][2];
            int count = 0;
            if ((pos_x + 1) < world.GetWidth() && world.board[pos_x + 1][pos_y].occupied == false) {
                tab[count][0] = pos_x + 1;
                tab[count][1] = pos_y;
                count++;
            }
            if ((pos_x - 1) > -1 && world.board[pos_x - 1][pos_y].occupied == false) {
                tab[count][0] = pos_x - 1;
                tab[count][1] = pos_y;
                count++;
            }
            if ((pos_y - 1) > -1 && world.board[pos_x][pos_y - 1].occupied == false) {
                tab[count][0] = pos_x;
                tab[count][1] = pos_y - 1;
                count++;
            }
            if ((pos_y + 1) < world.GetHeight() && world.board[pos_x][pos_y + 1].occupied == false) {
                tab[count][0] = pos_x;
                tab[count][1] = pos_y + 1;
                count++;
            }
            if (count > 0) {
                int rand = r.nextInt(count);
                Add(world, tab[rand][0], tab[rand][1]);
            }
        }
    }

    void Burn() {
        for (int i = -1; i < 2; i++) {
            for (int j = -1; j < 2; j++) {
                if (pos_x + i < world.GetWidth() && pos_x + i >= 0 && pos_y + j < world.GetHeight() && pos_y + j >= 0) {
                    if (!(i == 0 && j == 0)) {
                        if (world.board[pos_x + i][pos_y + j].occupied == true) {
                            if (world.board[pos_x + i][pos_y + j].organism instanceof Animal) {
                                world.board[pos_x + i][pos_y + j].occupied = false;
                                for (int k = 0; k < world.organisms.size(); k++) {
                                    if (world.organisms.get(k).GetPos_x() == pos_x + i && world.organisms.get(k).GetPos_y() == pos_y + j) {
                                        if (world.organisms.get(k).GetSign()=="CyberSheep") {
                                            world.Log(world.organisms.get(k).GetSign() + " eats " + this.GetSign());
                                            world.organisms.remove(this);
                                        }
                                        else {
                                        world.Log(this.GetSign() + " kills " + world.organisms.get(k).GetSign());
                                        world.organisms.remove(k);
                                        break;
                                        }
                                    }
                                }
                            }
                        }
                    }
                }
            }
        }
    }
}
