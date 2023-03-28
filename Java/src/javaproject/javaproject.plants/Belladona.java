/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package javaproject.plants;

import java.awt.Color;
import javaproject.Project.Plant;
import javaproject.Project.World;

/**
 *
 * @author Maciej
 */
public class Belladona extends Plant {

    public Belladona(World _world, int _pos_x, int _pos_y) {
        this.pos_x = _pos_x;
        this.pos_y = _pos_y;
        this.world = _world;
        this.power = 99;//jagody kill after eating
        this.initiative = 0;
        this.symbol = "b";
        chance = 8;
        color = Color.MAGENTA;
        sign = "Belladona";
    }

    @Override
    public void Add(World world, int posx, int posy) {
        world.Add(new Belladona(world, posx, posy));
    }
}
