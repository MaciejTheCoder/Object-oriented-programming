#pragma once
#include "Animal.h"

class Antelope : public Animal
{
public:
	Antelope(World* world, Point point, int bornround);
	bool specialattack(Organism* one, Organism* two) override;
	string OrganismTypestring() override;
};
