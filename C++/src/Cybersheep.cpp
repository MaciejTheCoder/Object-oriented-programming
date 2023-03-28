#include "Cybersheep.h"

//Public part
Cybersheep::Cybersheep(World* world, Point point, int bornround):Animal(OrganismType::CYBERSHEEP, world, point, 11, 4, bornround)
{
	this->rangeofmove = 1;
	this->chanceofmove = 1;
	this->sign = 'C';
}

string Cybersheep::OrganismTypestring()
{
	return "Cybersheep";
}

