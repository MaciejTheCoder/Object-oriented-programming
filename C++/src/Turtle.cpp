#include "Turtle.h"
#include "Commentator.h"

Turtle::Turtle(World* world, Point point, int bornround):Animal(OrganismType::TURTLE, world, point, 2, 1, bornround)
{
	this->rangeofmove = 1;
	this->chanceofmove = 0.25;
	this->sign = 'T';
}

bool Turtle::specialattack(Organism * one, Organism* two)
{
	if (this == two) {
		if (one->getstrength() < 5 && one->IfAnimal()) {
			Commentator::Addcomment(OrganismTypestring() + " defend himself from " + one->OrganismTypestring());
			return true;
		}
		else return false;
	}
	else if (this == one) {
		if (one->getstrength() >= two->getstrength()) return false;
		else {
			if (two->getstrength() < 5 && two->IfAnimal()) {
				Commentator::Addcomment(OrganismTypestring() + " defend himself from " + two->OrganismTypestring());
				return true;
			}
			else return false;
		}
	}
}

string Turtle::OrganismTypestring()
{
	return "Turle";
}