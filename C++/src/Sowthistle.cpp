#include "Sowthistle.h"

//Public part

Sowthistle::Sowthistle(World* world, Point point, int bornround):Plant(OrganismType::SOWTHISLE, world, point, 0, 0, bornround)
{
	this->sign = '&';
}

void Sowthistle::action()
{
	int i = 0;
	while (i<3) {
		if (rand() % 10 < 2) Spread();	
		i++;
	}
}

string Sowthistle::OrganismTypestring()
{
	return "Sowthistle";
}

