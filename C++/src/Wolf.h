#pragma once
#include "Animal.h"

class Wolf : public Animal
{
public:
	Wolf(World* world, Point point, int bornround);
	string OrganismTypestring() override;
};
