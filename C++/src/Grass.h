#pragma once
#include "Plant.h"

class Grass : public Plant
{
public:
	Grass(World* world, Point point, int bornround);
	void action() override;
	string OrganismTypestring() override;
};

