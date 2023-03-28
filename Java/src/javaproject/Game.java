/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package javaproject;

import javaproject.Project.*;

/**
 *
 * @author Maciej
 */
public class Game {
    public Game(int size_x, int size_y) {
        World world = new World(size_x, size_y);
        world.draw();
        world.drawboard();
    }
}
