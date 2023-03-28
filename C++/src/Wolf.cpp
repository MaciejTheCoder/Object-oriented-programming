#include "Wolf.h"


Wolf::Wolf(World* world, Point point, int bornround):Animal(OrganismType::WOLF, world, point, 9, 5, bornround)
{
	this->rangeofmove = 1;
	this->chanceofmove = 1;
	this->sign = 'W';
}

string Wolf::OrganismTypestring()
{
	return "Wolf";
}
