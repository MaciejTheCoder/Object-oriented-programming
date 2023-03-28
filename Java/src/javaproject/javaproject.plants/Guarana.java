/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package javaproject.plants;

import java.awt.Color;
import javaproject.Project.Plant;
import javaproject.Project.World;
import javaproject.Project.Organism;

/**
 *
 * @author Maciej
 */
public class Guarana extends Plant {

    public Guarana(World _world, int _pos_x, int _pos_y) {
        this.pos_x = _pos_x;
        this.pos_y = _pos_y;
        this.world = _world;
        this.power = 0;
        this.initiative = 0;
        this.symbol = "g";
        chance = 5;
        color = Color.BLUE;
        sign = "Guarana";
    }

    @Override
    public void Add(World world, int posx, int posy) {
        world.Add(new Guarana(world, posx, posy));
    }

    @Override
    public boolean Collision(Organism collider) {//after eating guarana you get 3 additional power
        collider.SetPower(collider.GetPower() + 3);
        return true;
    }
}
