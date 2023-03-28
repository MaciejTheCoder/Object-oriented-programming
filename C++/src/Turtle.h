#pragma once
#include "Animal.h"

class Turtle : public Animal
{
public:
	Turtle(World* world, Point point, int bornround);
	bool specialattack(Organism* one, Organism* two) override;
	string OrganismTypestring() override;
};