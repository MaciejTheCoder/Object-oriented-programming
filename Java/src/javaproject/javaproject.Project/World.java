/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package javaproject.Project;

import java.util.List;
import java.util.LinkedList;
import java.awt.Color;
import java.io.*;
import java.util.Random;
import javaproject.animals.*;
import javaproject.plants.*;

/**
 *
 * @author Maciej
 */
public class World implements Serializable {

    private int sinceLastSuperAbility;
    private boolean humanAlive;
    private final int width;
    private final int height;
    public List<Organism> organisms = new LinkedList<>();
    public Direction kierunek;
    public String log;
    public Frame frame;

    public Place[][] board;

    public World(int _width, int _height) {
        width = _width; 
        height = _height;
        board = new Place[width][height];
        for (int i = 0; i < width; i++) {
            for (int j = 0; j < height; j++) {
                board[i][j] = new Place();
                board[i][j].occupied = false;
            }
        }
        log = "";
        frame = new Frame(_width, _height, this);//creating Frame
        for (int i = 0; i < organisms.size(); i++) {
            organisms.get(i).draw();
            board[organisms.get(i).pos_x][organisms.get(i).pos_y].organism = organisms.get(i);
        }
        sinceLastSuperAbility = 5;
    }

    public World(int _width, int _height, List<Organism> _organisms, Place[][] _board, int _sinceLastSuperAbility, boolean _humanAlive) {//constructor after used after loading
        width = _width;
        height = _height;
        board = _board;
        log = "";
        frame = new Frame(_width, _height, this);
        organisms = _organisms;
        sinceLastSuperAbility = _sinceLastSuperAbility;
        humanAlive = _humanAlive;
        for (int i = 0; i < organisms.size(); i++) {
            organisms.get(i).world = this;
            organisms.get(i).draw();
        }
        drawboard();
    }

    public boolean GetIfAlive() {
        return humanAlive;
    }

    public void SetIfAlive(boolean alive) {
        humanAlive = alive;
    }

    public int GetSinceLastSuperAbility() {
        return sinceLastSuperAbility;
    }

    public void SetLastAbility() {
        sinceLastSuperAbility = 0;
    }

    public int GetWidth() {
        return width;
    }

    public int GetHeight() {
        return height;
    }

    public void Log(String _log) {
        frame.log.text.append(_log + "\n");
    }

    public void Add(Organism newO) {//adding new Organism
        if (organisms.isEmpty()) {
            organisms.add(newO);
        } else {
            boolean added = false;
            for (Organism it : organisms) {
                if (newO.initiative > it.initiative) {
                    organisms.add(organisms.indexOf(it), newO);
                    added = true;
                    break;
                } else if (newO.initiative == it.initiative) {
                    if (newO.GetAge() > it.GetAge()) {
                        organisms.add(organisms.indexOf(it), newO);
                        added = true;
                        break;
                    }
                }
            }
            if (!added) {
                organisms.add(newO);
            }
        }

        board[newO.pos_x][newO.pos_y].occupied = true;
        board[newO.pos_x][newO.pos_y].organism = newO;
        board[newO.pos_x][newO.pos_y].organism.setCooldown();
    }

    public void drawboard() {//drawing board
        for (int i = 0; i < width; i++) {
            for (int j = 0; j < height; j++) {
                frame.panel.buttons[i][j].setText("");
                frame.panel.buttons[i][j].setEnabled(true);
                frame.panel.buttons[i][j].setBackground(Color.DARK_GRAY);
            }
        }
        for (int i = 0; i < organisms.size(); i++) {
            organisms.get(i).draw();
        }
    }

    public void Save(String name) {//saving game to file
        SaveNLoad sNl;
        sNl = new SaveNLoad(width, height, organisms, board, sinceLastSuperAbility, humanAlive);
        sNl.Save(name);
    }

    public void Load(String name) {//loading game from file
        SaveNLoad sNl;
        sNl = new SaveNLoad(width, height, organisms, board, sinceLastSuperAbility, humanAlive);
        frame.setVisible(false);
        sNl.Load(name);
    }

    public void draw() {//draw at the beginning of the game
        int occupied[][];
        occupied = new int[height * width][2];//ocuppied places on board
        int count = 0;
        Random r = new Random();
        occupied[count][0] = r.nextInt(width);
        occupied[count][1] = r.nextInt(height);
        Add(new Human(this, occupied[count][0], occupied[count][1]));//only one human
        count++;
        for (int i = 0; i < (height * width) / 2; i++) {
            boolean ok = false;
            do {
                occupied[count][0] = r.nextInt(width);//get next place
                occupied[count][1] = r.nextInt(height);
                for (int k = 0; k < count; k++) {//checks if place is used
                    if (occupied[k][0] == occupied[count][0] && occupied[k][1] == occupied[count][1]) {
                        ok = false;
                        break;
                    }
                    ok = true;
                }
            } while (ok == false);

            switch (r.nextInt(7)) {//getting random Organism
                case 0:
                    Add(new Wolf(this, occupied[count][0], occupied[count][1]));
                    break;
                case 1:
                    Add(new Sheep(this, occupied[count][0], occupied[count][1]));
                    break;
                case 2:
                    Add(new Antelope(this, occupied[count][0], occupied[count][1]));
                    break;
                case 3:
                    Add(new Fox(this, occupied[count][0], occupied[count][1]));
                    break;
                case 4:
                    Add(new Turtle(this, occupied[count][0], occupied[count][1]));
                    break;
                case 5:
                    Add(new CyberSheep(this, occupied[count][0], occupied[count][1]));
                    break;    
                case 6: {
                    switch (r.nextInt(5)) {
                        case 0:
                            Add(new Belladona(this, occupied[count][0], occupied[count][1]));
                            break;
                        case 1:
                            Add(new Sosnowskys_hogweed(this, occupied[count][0], occupied[count][1]));
                            break;
                        case 2:
                            Add(new Sow_thistle(this, occupied[count][0], occupied[count][1]));
                            break;
                        case 3:
                            Add(new Grass(this, occupied[count][0], occupied[count][1]));
                            break;
                        case 4:
                            Add(new Guarana(this, occupied[count][0], occupied[count][1]));
                            break;
                    }
                    break;
                }
            }
            count++;
        }
    }

    public void NextRound() {//next round
        frame.log.text.setText("");
        humanAlive = false;
        for (int i = 0; i < organisms.size(); i++) {
            if (organisms.get(i).GetAge() > 0 || organisms.get(i) instanceof Human) {//if Organism is a newborn it cant move. Exeption - Human
                organisms.get(i).Action();//Organism's action(move)
            }
        }
        for (int i = 0; i < organisms.size(); i++) {//checking if human is alive
            if (organisms.get(i) instanceof Human) {
                humanAlive = true;
            }
            organisms.get(i).cooldown--;//cooldown makes sure that Organism cant reproduce in each turn
            organisms.get(i).SetAge(organisms.get(i).GetAge() + 1);//adding age
        }
        sinceLastSuperAbility++;
        drawboard();
        kierunek = null;
    }

}
