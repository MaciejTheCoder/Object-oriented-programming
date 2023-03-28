/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package javaproject.Project;

import java.io.*;
import java.util.LinkedList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Maciej
 */
public class SaveNLoad implements Serializable {

    private int width;
    private int height;
    public List<Organism> organisms = new LinkedList<>();
    public Place[][] board;
    public int since;
    public boolean humanAlive;

    public SaveNLoad(int _width, int _height, List<Organism> _organisms, Place[][] _board, int _since, boolean _alive) { // contructor that copies all important informations from world
        width = _width;
        height = _height;
        organisms = _organisms;
        board = _board;
        since = _since;
        humanAlive = _alive;
    }

    public void Save(String name) {//saving to file
        try {
            FileOutputStream f = new FileOutputStream(new File(name + ".bin"));
            ObjectOutputStream o = new ObjectOutputStream(f);
            o.writeObject(SaveNLoad.this);
        } catch (IOException e) {
            System.out.println("Error initializing stream");
        }
    }

    public void Load(String name) {//reading from file
        try {
            FileInputStream fi = new FileInputStream(new File(name + ".bin"));
            ObjectInputStream oi = new ObjectInputStream(fi);
            SaveNLoad a = (SaveNLoad) oi.readObject();
            World b = new World(a.width, a.height, a.organisms, a.board, a.since, a.humanAlive);//creating new world with appropriate constructor
            oi.close();
            fi.close();
        } catch (FileNotFoundException ex) {
            Logger.getLogger(SaveNLoad.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IOException | ClassNotFoundException ex) {
            Logger.getLogger(SaveNLoad.class.getName()).log(Level.SEVERE, null, ex);
        }

    }

}
