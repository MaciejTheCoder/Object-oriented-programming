/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package javaproject.plants;

import java.util.Random;
import javaproject.Project.Plant;
import javaproject.Project.World;
import java.awt.Color;

/**
 *
 * @author Maciej
 */
public class Sow_thistle extends Plant {

    public Sow_thistle(World _world, int _pos_x, int _pos_y) {
        this.pos_x = _pos_x;
        this.pos_y = _pos_y;
        this.world = _world;
        this.power = 0;
        this.initiative = 0;
        this.symbol = "&";
        chance = 10;
        color = Color.YELLOW;
        sign = "Sow thistle";
    }

    @Override
    public void Add(World world, int posx, int posy) {
        world.Add(new Sow_thistle(world, posx, posy));
    }

    @Override
    public void Action() {
        Random r = new Random();
        for (int i = 0; i < 3; i++) {//it tries 3 times to reproduce
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
    }
}
