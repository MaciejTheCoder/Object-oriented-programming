/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package javaproject.animals;

import javaproject.Project.Direction;
import javaproject.Project.Organism;
import javaproject.Project.World;
import javaproject.Project.Animal;
import javaproject.Project.Plant;
import java.awt.Color;
import java.util.Random;

/**
 *
 * @author Maciej
 */
public class Human extends Animal {

    public Human(World _world, int _pos_x, int _pos_y) {
        this.pos_x = _pos_x;
        this.pos_y = _pos_y;
        this.world = _world;
        this.power = 5;
        this.initiative = 4;
        this.symbol = "H";
        color = Color.RED;
        world.SetIfAlive(true);
        sign = "Human";
    }

    @Override
    public void Add(World world, int posx, int posy) {
        world.Add(new Human(world, posx, posy));
    }

    @Override
    public void Action() {
        int newx = pos_x;
        int newy = pos_y;
        if (world.kierunek == Direction.DOWN && pos_y < world.GetHeight() - 1) {
            newy++;
        } else if (world.kierunek == Direction.UP && pos_y > 0) {
            newy--;
        } else if (world.kierunek == Direction.LEFT && pos_x > 0) {
            newx--;
        } else if (world.kierunek == Direction.RIGHT && pos_x < world.GetWidth() - 1) {
            newx++;
        }

        if ((world.board[newx][newy].occupied == false && !(newx == pos_x && newy == pos_y)) || world.GetSinceLastSuperAbility() == 0) {
            world.board[newx][newy].organism = world.board[pos_x][pos_y].organism;
            world.board[pos_x][pos_y].occupied = false;
            world.board[newx][newy].occupied = true;
            pos_x = newx;
            pos_y = newy;
        } else if (!(newx == pos_x && newy == pos_y)) {
            Meet(world.board[newx][newy].organism);
        }
    }

    @Override
    public boolean Collision(Organism collider) {//human has special ability - immortality. He can use it once every 5 turns.
        if (world.GetSinceLastSuperAbility() == 0) {
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
        else{
            return true;
        }
    }

}
