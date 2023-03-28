#pragma once
#include "Plant.h"

class Guarana : public Plant
{
public:
	Guarana(World* world, Point point, int bornround);
	bool specialattack(Organism* one, Organism* two) override;
	void action() override;
	string OrganismTypestring() override;
};
