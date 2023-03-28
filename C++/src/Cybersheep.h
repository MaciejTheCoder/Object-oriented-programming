#pragma once
#include "Animal.h"

class Cybersheep : public Animal
{
public:
	Cybersheep(World* world, Point point, int bornround);
	string OrganismTypestring() override;
};

