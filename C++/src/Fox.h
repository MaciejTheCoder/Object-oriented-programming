#pragma once
#include "Animal.h"

class Fox : public Animal
{
public:
	Fox(World* world, Point point, int bornround);
	string OrganismTypestring() override;
protected:
	Point RandomPoint(Point point) override;
};