#include "Guarana.h"
#include "Commentator.h"

Guarana::Guarana(World* world, Point point, int bornturn):Plant(OrganismType::GUARANA, world, point, 0, 0, bornround)
{
	this->sign = 'g';
}

bool Guarana::specialattack(Organism * one, Organism * two)
{
	Point tmpPozycja = this->point;
	world->deleteOrganism(this);
	one->move(tmpPozycja);
	Commentator::Addcomment(one->OrganismTypestring() + " eats " + this->OrganismTypestring()
		+ "  and increases its power by 3");
	one->setstrength(one->getstrength() + 3);
	return true;
}

void Guarana::action()
{
	if (rand() % 10 < 2) Spread();
}

string Guarana::OrganismTypestring() { return "Guarana"; }