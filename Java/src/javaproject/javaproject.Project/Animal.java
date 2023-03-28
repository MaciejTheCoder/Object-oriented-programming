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
public abstract class Animal extends Organism {

    public void Meet(Organism organism) {
        if (symbol.equals(organism.symbol)) {//checks if Organism's are the same spiecies
            New(organism);//reproduction of animals
        } else if (organism.Collision(organism)) {//if Organism cant defend it self both Organism's start a fight
            if (GetPower() >= organism.GetPower()) {//checking which Organism is stronger
                if (organism instanceof Plant) {
                    world.Log(this.GetSign() + " eats " + organism.GetSign());
                } else {
                    world.Log(this.GetSign() + " kills " + organism.GetSign());
                }
                world.board[pos_x][pos_y].occupied = false;
                int i;
                for (i = 0; i < world.organisms.size(); i++) {
                    if (world.organisms.get(i).pos_x == organism.pos_x && world.organisms.get(i).pos_y == organism.pos_y) {
                        break;
                    }
                }
                if(i==world.organisms.size()){
                    i--;
                }
                pos_x = organism.pos_x;
                pos_y = organism.pos_y;
                world.board[pos_x][pos_y].organism = this;
                world.organisms.remove(i);
            } else {
                world.Log(organism.GetSign() + " kills " + this.GetSign());
                world.board[pos_x][pos_y].occupied = false;
                int i;
                for (i = 0; i < world.organisms.size(); i++) {
                    if (world.organisms.get(i).pos_x == pos_x && world.organisms.get(i).pos_y == pos_y) {
                        break;
                    }
                }
                world.organisms.remove(i);
            }
        }

    }

    void New(Organism organism) {
        if (cooldown <= 0 && organism.cooldown <= 0) {
            setCooldown();
            organism.setCooldown();
            int arr[][] = new int[8][2];
            Random r = new Random();
            int count = 0;
            //searching for a place for a new animal
            for (int i = -1; i < 2; i++) {
                for (int j = -1; j < 2; j++) {
                    if (pos_x + i < world.GetWidth() && pos_x + i >= 0 && pos_y + j < world.GetHeight() && pos_y + j >= 0) {
                        if (!(i == 0 && j == 0)) {
                            if (world.board[pos_x + i][pos_y + j].occupied == false) {
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
                Add(world, arr[rand][0], arr[rand][1]);
            }
        }
    }

    @Override
    public void Action() {
        int arr[][] = new int[4][2];
        int count = 0;
        Random r = new Random();
        //getting possible moves
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
        //draw of a move
        int newx = arr[rand][0];
        int newy = arr[rand][1];
        //checking if destination is occupied
        if (world.board[newx][newy].occupied == false) {//if not moving Organism to a new position
            world.board[newx][newy].organism = world.board[pos_x][pos_y].organism;
            world.board[pos_x][pos_y].occupied = false;
            world.board[newx][newy].occupied = true;
            pos_x = newx;
            pos_y = newy;
        } else {//else Organism's meet each other
            Meet(world.board[newx][newy].organism);
        }

    }

}
