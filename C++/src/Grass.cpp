 #include "Grass.h"

Grass::Grass(World* world, Point point, int bornturn):Plant(OrganismType::GRASS, world, point, 0, 0, bornround)
{
	this->sign = '*';
}

void Grass::action()
{
	if (rand() % 10 < 2) Spread();
}

string Grass::OrganismTypestring()
{
	return "Grass";
}
