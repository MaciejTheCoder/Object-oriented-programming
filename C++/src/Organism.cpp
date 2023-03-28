#include "Organism.h"
#include "Wolf.h"
#include "Sheep.h"
#include "Fox.h"
#include "Turtle.h"
#include "Antelope.h"
#include "Cybersheep.h"
#include "Grass.h"
#include "Sowthistle.h"
#include "Guarana.h"
#include "Belladona.h"
#include "Sosnowskyshogweed.h"
#include "Human.h"
using namespace std;

//Public part
Organism::OrganismType Organism::randomType()
{
	int number = rand() % 11;
	if (number % 11 == 0) return OrganismType::WOLF;
	if (number % 11 == 1) return OrganismType::SHEEP;
	if (number % 11 == 2) return OrganismType::FOX;
	if (number % 11 == 3) return OrganismType::TURTLE;
	if (number % 11 == 4) return OrganismType::ANTELOPE;
	if (number % 11 == 5) return OrganismType::CYBERSHEEP;
	if (number % 11 == 6) return OrganismType::GRASS;
	if (number % 11 == 7) return OrganismType::SOWTHISLE;
	if (number % 11 == 8) return OrganismType::GUARANA;
	if (number % 11 == 9) return OrganismType::BELLADONA;
	if (number % 11 == 10) return OrganismType::SOSNOWSKYSHOGWEED;
}

Organism * Organism::createneworganism(Organism::OrganismType type, World* world, Point point)
{
	switch (type) {
	case Organism::OrganismType::HUMAN: return new Human(world, point, world->Round());
	case Organism::OrganismType::WOLF: return new Wolf(world, point, world->Round());
	case Organism::OrganismType::SHEEP: return new Sheep(world, point, world->Round());
	case Organism::OrganismType::FOX: return new Fox(world, point, world->Round());
	case Organism::OrganismType::TURTLE: return new Turtle(world, point, world->Round());
	case Organism::OrganismType::ANTELOPE: return new Antelope(world, point, world->Round());
	case Organism::OrganismType::CYBERSHEEP: return new Cybersheep(world, point, world->Round());
	case Organism::OrganismType::GRASS: return  new Grass(world, point, world->Round());
	case Organism::OrganismType::SOWTHISLE: return  new Sowthistle(world, point, world->Round());
	case Organism::OrganismType::GUARANA: return  new Guarana(world, point, world->Round());
	case Organism::OrganismType::BELLADONA: return  new Belladona(world, point, world->Round());
	case Organism::OrganismType::SOSNOWSKYSHOGWEED: return  new Sosnowskyshogweed(world, point, world->Round());
	default: return nullptr;
	}
}



void Organism::draw()
{
	cout << sign;
}

bool Organism::specialattack(Organism* one, Organism* two) //one is always attacker and two is defender
{
	return false;
}



void Organism::move(Point newpoint)
{
	int x = newpoint.GetX();
	int y = newpoint.GetY();
	world->getboard()[point.GetY()][point.GetX()] = nullptr;
	world->getboard()[y][x] = this;
	point.SetX(x);
	point.SetY(y);
}

Point Organism::RandomPoint(Point point)
{
	unblockalldirections();
	int X = point.GetX();
	int Y = point.GetY();
	int maxX = world->Getwidth();
	int maxY = world->Gethigh();
	int options = 0;

	if (X == 0) blockdirection(Move::DLEFT);
	else options++;
	if (X == maxX - 1) blockdirection(Move::DRIGHT);
	else options++;
	if (Y == 0) blockdirection(Move::DUP);
	else options++;
	if (Y == maxY - 1) blockdirection(Move::DDOWN);
	else options++;
	if (options == 0) return Point(X, Y);

	while (true) {
		int random = rand() % 4;
		if ((random == 0) && !isitblocked(Move::DLEFT)) {//move left
			return Point(X - 1, Y);
		}
		else if ((random == 1) && !isitblocked(Move::DRIGHT)) { //move right
			return Point(X + 1, Y);
		}
		else if ((random == 2) && !isitblocked(Move::DDOWN)) { //move down
			return Point(X, Y + 1);
		}
		else if ((random == 3) && !isitblocked(Move::DUP)) { //move up
			return Point(X, Y - 1);
		}
	}
}

Point Organism::RadndomfreePoint(Point point)
{
	unblockalldirections();
	int X = point.GetX();
	int Y = point.GetY();
	int maxX = world->Getwidth();
	int maxY = world->Gethigh();
	int options = 0;

	if (X == 0) blockdirection(Move::DLEFT);
	else {
		if (world->isfree(Point(X - 1, Y)) == false) options++;
		else blockdirection(Move::DLEFT);
	}

	if (X == maxX - 1) blockdirection(Move::DRIGHT);
	else {
		if (world->isfree(Point(X + 1, Y)) == false) options++;
		else blockdirection(Move::DRIGHT);
	}

	if (Y == 0) blockdirection(Move::DUP);
	else {
		if (world->isfree(Point(X, Y - 1)) == false) options++;
		else blockdirection(Move::DUP);
	}

	if (Y == maxY - 1) blockdirection(Move::DDOWN);
	else {
		if (world->isfree(Point(X, Y + 1)) == false) options++;
		else blockdirection(Move::DDOWN);
	}

	if (options == 0) return Point(X, Y);
	while (true) {
		int tmpLosowanie = rand() % 100;
		if (tmpLosowanie < 25 && !isitblocked(Move::DLEFT)) //move left
			return Point(X - 1, Y);
		else if (tmpLosowanie >= 25 && tmpLosowanie < 50 && !isitblocked(Move::DRIGHT)) //move right
			return Point(X + 1, Y);
		else if (tmpLosowanie >= 50 && tmpLosowanie < 75 && !isitblocked(Move::DUP)) //move up
			return Point(X, Y - 1);
		else if (tmpLosowanie >= 75 && !isitblocked(Move::DDOWN)) //move down
			return Point(X, Y + 1);
	}
}



bool Organism::getalive(){return alive;}

void Organism::setalive(bool alive){this->alive = alive;}

int Organism::getstrength(){return strength;}

void Organism::setstrength(int strength){this->strength = strength;}

int Organism::getinitiative(){return initiative;}

void Organism::setinitiative(int initiative){this->initiative = initiative;}

char Organism::getsign(){return sign;}

void Organism::setsign(char sign){this->sign = sign;}

int Organism::getbornround(){return bornround;}

void Organism::setbornround(int bornround){this->bornround = bornround;}

bool Organism::getifchildren(){return children;}

void Organism::setifchildren(bool children){this->children = children;}



Organism::OrganismType Organism::gettype(){return type;}

void Organism::Settype(OrganismType type){this->type = type;}

Point Organism::getpoint()
{
	return Point(point.GetX(), point.GetY());
}

void Organism::setpoint(int x, int y)
{
	point.SetX(x);
	point.SetY(y);
}

World* Organism::getworld(){return world;}

void Organism::setworld(World* world){this->world = world;}



//Protected part
Organism::Organism(OrganismType type, World* world, Point point, int strength, int initiative, int bornround)
{
	this->world = world;
	this->type = type;
	this->strength = strength;
	this->initiative = initiative;
	this->bornround = bornround;
	this->sign = sign;
	alive = false;
	this->point = point;
}

void Organism::unblockalldirections()
{
	unblockdirection(Move::DLEFT);
	unblockdirection(Move::DRIGHT);
	unblockdirection(Move::DUP);
	unblockdirection(Move::DDOWN);
}

void Organism::blockalldirections()
{
	blockdirection(Move::DLEFT);
	blockdirection(Move::DRIGHT);
	blockdirection(Move::DUP);
	blockdirection(Move::DDOWN);
}

void Organism::blockdirection(Move direction)
{
	this->direction[(int)direction] = false;
}

void Organism::unblockdirection(Move direction)
{
	this->direction[(int)direction] = true;
}

bool Organism::isitblocked(Move direction)
{
	return !(this->direction[(int)direction]);
}

