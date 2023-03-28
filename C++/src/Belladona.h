#pragma once
#include "Plant.h"

class Belladona : public Plant
{
public:
	Belladona(World* world, Point point, int bornround);
	bool specialattack(Organism* one, Organism* two) override;
	void action() override;
	string OrganismTypestring() override;
};