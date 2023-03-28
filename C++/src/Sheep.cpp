#include "Sheep.h"

//Public part
Sheep::Sheep(World* world, Point point, int bornround):Animal(OrganismType::SHEEP, world, point, 4, 4, bornround)
{
	this->rangeofmove = 1;
	this->chanceofmove = 1;
	this->sign = 'S';
}

string Sheep::OrganismTypestring()
{
	return "Sheep";
}

