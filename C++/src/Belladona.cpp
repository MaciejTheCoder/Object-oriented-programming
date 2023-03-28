#include "Belladona.h"
#include "Commentator.h"

Belladona::Belladona(World* world, Point point, int bornround):Plant(OrganismType::BELLADONA, world, point, 99, 0, bornround)
{
	this->sign = 'b';
}

bool Belladona::specialattack(Organism* one, Organism* two)
{
	Commentator::Addcomment(one->OrganismTypestring() + " eats " + this->OrganismTypestring());
	if (one->IfAnimal()) {
		world->deleteOrganism(one);
		Commentator::Addcomment(one->OrganismTypestring() + " is killed by belladona");
	}
	return true;
}

void Belladona::action()
{
	if (rand() % 10 < 1) Spread();
}

string Belladona::OrganismTypestring()
{
	return "Belladona";
}
