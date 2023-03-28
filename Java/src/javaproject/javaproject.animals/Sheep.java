/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package javaproject.animals;

import java.awt.Color;
import javaproject.Project.Animal;
import javaproject.Project.World;

/**
 *
 * @author Maciej 
 */
public class Sheep extends Animal {

    public Sheep(World _world, int _pos_x, int _pos_y) {
        this.pos_x = _pos_x;
        this.pos_y = _pos_y;
        this.world = _world;
        this.power = 4;
        this.initiative = 4;
        symbol = "S";
        color=Color.LIGHT_GRAY;
        sign = "Sheep";
    }

    @Override
    public void Add(World world, int posx, int posy) {
        world.Add(new Sheep(world, posx, posy));
    }
}
