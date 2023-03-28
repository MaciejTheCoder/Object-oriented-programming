#pragma once
#include "Animal.h"

class Sheep : public Animal
{
public:
	Sheep(World* world, Point point, int bornround);
	string OrganismTypestring() override;
};
 
