//THE WORLD AND ALL ITS FUNCTIONALITIES
//By Maciej Majewski, student id 184945
#pragma once
#include "Organism.h" 
#include "Point.h"
#include <iostream>
#include <vector>
using namespace std;

class Organism; //declaration of class Organism
class Human; //declaration of class Human

class World //our World  
{
protected:
	int width, high, round; //size of map and number of round
	bool over,humanalive; //is human alive and is gameover?
	void input(); //takes data from keyboard
	void organismssort(); //sortorganisms by iniciative and age
	Human* human; 
	Organism *** board;
	vector<Organism*>organisms; 
public:
	World();
	World(int width, int high);
	World(World&& other);
	~World(); //destructor 
	void generadeWorld(double filling); //gererading a World

	void makeRound(); //making a round
	void drawWorld(); //drawing World
	Organism*** getboard();
	vector<Organism*> GetOrganisms();

	Organism* gethuman();
	bool gethumanalive();
	void sethumanalive(bool shumanalive);

	int Getwidth();
	int Gethigh();
	const int Round(); //get number of round
	bool getgameover(); //is gameover?

	void addOrganism(Organism* Organism); //adding organism
	void deleteOrganism(Organism* Organism); //delete organism

	Point randomfreespace(); 
	bool isfree(Point point); //is this point free
	Organism* whatisit(Point point); //what is on this point
};

