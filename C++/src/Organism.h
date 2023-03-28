#pragma once
#include <string>
#include "World.h"
#include "Point.h"
#include "Commentator.h"


using namespace std;

class World;
class Point;

class Organism
{
public:
	enum class Move {
		DRIGHT,
		DLEFT,
		DDOWN,
		DUP,
		NONE
	};
	enum class OrganismType {
		HUMAN, //sign 'H'
		WOLF, //sign 'W' 
		SHEEP, //sign 'S'
		FOX, //sign 'F'
		TURTLE, //sign 'T'
		ANTELOPE, //sign 'A'
		CYBERSHEEP, //sign 'C'
		GRASS, //sign '*'
		SOWTHISLE, //sign '&'
		GUARANA, //sign 'g'
		BELLADONA, //sign 'b'
		SOSNOWSKYSHOGWEED //sign 's'
	};

	
	static OrganismType randomType(); //random organism
	static Organism* createneworganism(Organism::OrganismType type, World* world, Point point); //creating organisms
	virtual string OrganismTypestring() = 0; //Organisms name as string

	virtual void draw();
	virtual bool specialattack(Organism * one, Organism * two);
	virtual void action() = 0; 
	virtual void collision(Organism * other) = 0;
	virtual bool IfAnimal() = 0;;

	void move (Point newpoint);
	virtual Point RandomPoint(Point point);
	virtual Point RadndomfreePoint(Point point);
	
	bool getalive();
	void setalive(bool alive);
	int getstrength();
	void setstrength(int strength);
	int getinitiative();
	void setinitiative(int initiative);
	char getsign();
	void setsign(char sign);
	int getbornround();
	void setbornround(int bornround);
	bool getifchildren();
	void setifchildren(bool children);

	OrganismType gettype();
	void Settype(OrganismType type);
	Point getpoint();
	void setpoint(int x, int y);
	World * getworld();
	void setworld(World * world);

protected:
	World * world;
	OrganismType type;
	Point point;

	int strength, initiative, bornround;
	char sign;
	bool alive,children;
	bool * direction;
	static int number_of_created_organisms;

	Organism(OrganismType type, World* world, Point point, int strength, int initiative, int bornround);
	void unblockalldirections();
	void blockalldirections();
	void blockdirection(Move direction);
	void unblockdirection(Move direction);
	bool isitblocked(Move direction);
};

