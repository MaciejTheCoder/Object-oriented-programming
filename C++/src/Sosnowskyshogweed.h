#pragma once
#include "Plant.h"

class Sosnowskyshogweed : public Plant
{
public:
	Sosnowskyshogweed(World* world, Point point, int bornround);
	bool specialattack(Organism* one, Organism* two) override;
	void action() override;
	string OrganismTypestring() override;
};

