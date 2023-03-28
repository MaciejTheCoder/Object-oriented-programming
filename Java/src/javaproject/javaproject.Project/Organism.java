/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package javaproject.Project;

import java.awt.*;
import java.io.Serializable;

/**
 *
 * @author Maciej
 */
public abstract class Organism implements Serializable {

    private int age;
    protected int power;
    protected int initiative;
    protected World world;
    protected int pos_x;
    protected int pos_y;
    protected int cooldown = 0;
    protected String symbol;
    protected Color color;
    protected String sign;

    public Organism() {

    }

    public Organism(World _world, int _pos_x, int _pos_y) {
        world = _world;
        pos_x = _pos_x;
        pos_y = _pos_y;
        age = 0;
    }

    public Organism(World _world, int _pos_x, int _pos_y, int _age, int _init, int _power) {
        world = _world;
        pos_x = _pos_x;
        pos_y = _pos_y;
        power = _power;
        age = _age;
        initiative = _init;
    }

    public int GetAge() {
        return age;
    }

    public void SetAge(int _age) {
        age = _age;
    }

    public int GetPower() {
        return power;
    }

    public void SetPower(int _power) {
        power = _power;
    }

    public int GetInitiative() {
        return initiative;
    }

    public int GetPos_x() {
        return pos_x;
    }

    public int GetPos_y() {
        return pos_y;
    }

    public void setCooldown() {
        cooldown = 5;
    }

    public int GetCooldown() {
        return cooldown;
    }

    abstract public void Action();

    public boolean Collision(Organism collider) {
        return true;
    }

    public String GetSign() {
        return sign;
    }

    public void draw() {//changing color and symbol of a place where Organism is
        world.frame.panel.buttons[pos_x][pos_y].setText(symbol);
        world.frame.panel.buttons[pos_x][pos_y].setEnabled(false);
        world.frame.panel.buttons[pos_x][pos_y].setBackground(color);
    }

    abstract public void Add(World world, int posx, int posy);//method to add Organism of certain spieces
}
