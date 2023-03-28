/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package javaproject.animals;

import java.awt.Color;
import java.util.Random;
import javaproject.Project.Organism;
import javaproject.Project.Animal;
import javaproject.Project.World;

/**
 *
 * @author Maciej
 */
public class Turtle extends Animal {

    public Turtle(World _world, int _pos_x, int _pos_y) {
        this.pos_x = _pos_x;
        this.pos_y = _pos_y;
        this.world = _world;
        this.power = 2;
        this.initiative = 1;
        this.symbol = "T";
        color=Color.GRAY;
        sign = "Turtle";
    }

    @Override
    public void Add(World world, int posx, int posy) {
        world.Add(new Turtle(world, posx, posy));
    }

    @Override
    public boolean Collision(Organism collider) {
        return (collider.GetPower() >= 5);//if collider(Organism that bumps into zolw)have less power than 5 it return false and Organism is bumped into other place
    }

    @Override
    public void Action() {
        int arr[][] = new int[4][2];
        int count = 0;
        Random r = new Random();
        int random = r.nextInt(4);
        if (random == 0) {//it has 1 in 4 chance to move
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
            }
        }
    }
}
