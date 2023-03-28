#pragma once
#include "Plant.h"

class Sowthistle : public Plant
{
public:
	Sowthistle(World* world, Point point, int bornround);
	void action() override;
	string OrganismTypestring() override;
};
