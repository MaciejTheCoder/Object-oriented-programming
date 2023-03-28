/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package javaproject.Project;

import java.util.Random;

/**
 *
 * @author Maciej
 */
public abstract class Plant extends Organism {

    public static int chance;//each plant has a certain chance of spreading in %

    @Override
    public void Action() {
        Random r = new Random();
        if (r.nextInt(100) < chance) {
            int tab[][];
            tab = new int[4][2];
            int count = 0;
            //checking possible positions
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
                //draw a new position
                Add(world, tab[rand][0], tab[rand][1]);
            }
        }
    }

}
