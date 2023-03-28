#include "Fox.h"

//Public part
Fox::Fox(World* world, Point point, int bornround):Animal(OrganismType::FOX, world, point, 3, 7, bornround)
{
	this->rangeofmove = 1;
	this->chanceofmove = 1;
	this->sign = 'F';
}

string Fox::OrganismTypestring()
{
	return "Fox";
}

//Protected part
Point Fox::RandomPoint(Point point)
{
	unblockalldirections();
	int pozX = point.GetX();
	int pozY = point.GetY();
	int sizeX = world->Getwidth();
	int sizeY = world->Gethigh();
	int options = 0;
	Organism * tmp;

	if (pozX == sizeX - 1) blockdirection(Move::DRIGHT);
	else {
		tmp = world->getboard()[pozY][pozX + 1];
		if (tmp != nullptr && tmp->getstrength() > this->strength) {
			blockdirection(Move::DRIGHT);
		}
		else options++;
	}
	if (pozX == 0) blockdirection(Move::DLEFT);
	else {
		tmp = world->getboard()[pozY][pozX - 1];
		if (tmp != nullptr && tmp->getstrength() > this->strength) {
			blockdirection(Move::DLEFT);
		}
		else options++;
	}
	if (pozY == 0) blockdirection(Move::DUP);
	else {
		tmp = world->getboard()[pozY - 1][pozX];
		if (tmp != nullptr && tmp->getstrength() > this->strength) {
			blockdirection(Move::DUP);
		}
		else options++;
	}
	if (pozY == sizeY - 1) blockdirection(Move::DDOWN);
	else {
		tmp = world->getboard()[pozY + 1][pozX];
		if (tmp != nullptr && tmp->getstrength() > this->strength) {
			blockdirection(Move::DDOWN);
		}
		else options++;
	}
	if (options == 0) return Point(pozX, pozY);
	while (true) {
		int tmpLosowanie = rand() % 4;
		if (tmpLosowanie == 0 && !isitblocked(Move::DLEFT))
			return Point(pozX - 1, pozY);
		else if (tmpLosowanie == 1 && !isitblocked(Move::DRIGHT))
			return Point(pozX + 1, pozY);
		else if (tmpLosowanie == 2 && !isitblocked(Move::DUP))
			return Point(pozX, pozY - 1);
		else if (tmpLosowanie == 3 && !isitblocked(Move::DDOWN))
			return Point(pozX, pozY + 1);
	}
}