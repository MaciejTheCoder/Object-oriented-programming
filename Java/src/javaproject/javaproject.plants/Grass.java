/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package javaproject.plants;

import javaproject.Project.World;
import javaproject.Project.Plant;
import java.awt.Color;

/**
 *
 * @author Maciej
 */
public class Grass extends Plant {

    public Grass(World _world, int _pos_x, int _pos_y) {
        this.pos_x = _pos_x;
        this.pos_y = _pos_y;
        this.world = _world;
        this.power = 0;
        this.initiative = 0;
        this.symbol = "*";
        chance = 80;
        color = Color.GREEN;
        sign = "Grass";
    }

    @Override
    public void Add(World world, int posx, int posy) {
        world.Add(new Grass(world, posx, posy));
    }
}
