/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package javaproject.animals;

import java.awt.Color;
import java.util.Random;
import javaproject.Project.Organism;
import javaproject.Project.Plant;
import javaproject.Project.World;
import javaproject.Project.Animal;

/**
 *
 * @author Maciej
 */
public class Antelope extends Animal {

    public Antelope(World _world, int _pos_x, int _pos_y) {
        this.pos_x = _pos_x;
        this.pos_y = _pos_y;
        this.world = _world;
        this.power = 4;
        this.initiative = 4;
        this.symbol = "A";
        color=Color.YELLOW;
        sign = "Antelope";
    }

    @Override
    public void Add(World world, int posx, int posy) {
        world.Add(new Antelope(world, posx, posy));
    }

    @Override
    public boolean Collision(Organism collider) {//has a chance to run away
        int arr[][] = new int[8][2];
        Random r = new Random();
        int count = 0;
        for (int i = -1; i < 2; i++) {
            for (int j = -1; j < 2; j++) {
                if (pos_x + i < world.GetWidth() && pos_x + i >= 0 && pos_y + j < world.GetHeight() && pos_y + j >= 0) {
                    if (!(i == 0 && j == 0)) {
                        if (world.board[pos_x + i][pos_y + j].occupied == false || world.board[pos_x + i][pos_y + j].organism instanceof Plant) {
                            arr[count][0] = pos_x + i;
                            arr[count][1] = pos_y + j;
                            count++;
                        }
                    }
                }
            }
        }
        if (count > 0) {
            int rand = r.nextInt(count);
            int newx = arr[rand][0];
            int newy = arr[rand][1];
            world.board[newx][newy].organism = world.board[pos_x][pos_y].organism;
            world.board[newx][newy].occupied = true;
            pos_x = newx;
            pos_y = newy;
            return false;
        } else {
            return true;
        }
    }

    @Override
    public void Action() {
        int arr[][] = new int[4][2];
        int count = 0;
        Random r = new Random();
        int random = r.nextInt(4);
        for (int i = 0; i < 2; i++) {//moves twice in a round
            count=0;
            if ((pos_x + 1) < world.GetWidth()) {
                arr[count][0] = pos_x + 1;
                arr[count][1] = pos_y;
                count++;
            }
            if ((pos_x - 1) > -1) {
                arr[count][0] = pos_x - 1;
                arr[count][1] = pos_y;
                count++;
            }
            if ((pos_y - 1) > -1) {
                arr[count][0] = pos_x;
                arr[count][1] = pos_y - 1;
                count++;
            }
            if ((pos_y + 1) < world.GetHeight()) {
                arr[count][0] = pos_x;
                arr[count][1] = pos_y + 1;
                count++;
            }
            int rand = r.nextInt(count);
            int newx = arr[rand][0];
            int newy = arr[rand][1];
            if (world.board[newx][newy].occupied == false) {
                world.board[newx][newy].organism = world.board[pos_x][pos_y].organism;
                world.board[pos_x][pos_y].occupied = false;
                world.board[newx][newy].occupied = true;
                pos_x = newx;
                pos_y = newy;
            } else {
                Meet(world.board[newx][newy].organism);
                break;
            }

        }
    }
}
