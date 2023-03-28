#include "Sosnowskyshogweed.h"
#include "Commentator.h"

//Public part
Sosnowskyshogweed::Sosnowskyshogweed(World* world, Point point, int bornround):Plant(OrganismType::SOSNOWSKYSHOGWEED, world, point, 10, 0, bornround)
{
	this->sign = 's';
}

bool Sosnowskyshogweed::specialattack(Organism* one, Organism* two)
{
	if (one->getstrength() >= 10) {
		world->deleteOrganism(this);
		Commentator::Addcomment(one->OrganismTypestring() + " eats " + this->OrganismTypestring());
	}
	if ((one->IfAnimal() && one->gettype() != OrganismType::CYBERSHEEP)
		|| one->getstrength() < 10) {
		world->deleteOrganism(one);
		Commentator::Addcomment(this->OrganismTypestring() + " kills " + one->OrganismTypestring());
	}
	if (one->IfAnimal() && one->gettype() == OrganismType::CYBERSHEEP)
	{
		world->deleteOrganism(this);
		Commentator::Addcomment(one->OrganismTypestring() + " kills " + this->OrganismTypestring());
	}
	return true;
}

void Sosnowskyshogweed::action()
{
	int pozX = point.GetX();
	int pozY = point.GetY();
	Organism* tmp = nullptr;
	RadndomfreePoint(point);
	for (int i = 0; i < 4; i++) {
		if (i == 0 && !isitblocked(Move::DDOWN))
			tmp = world->whatisit(Point(pozX, pozY + 1));
		else if (i == 1 && !isitblocked(Move::DUP))
			tmp = world->whatisit(Point(pozX, pozY - 1));
		else if (i == 2 && !isitblocked(Move::DLEFT))
			tmp = world->whatisit(Point(pozX - 1, pozY));
		else if (i == 3 && !isitblocked(Move::DRIGHT))
			tmp = world->whatisit(Point(pozX + 1, pozY));

		if (tmp != nullptr && tmp->IfAnimal()
			&& tmp->gettype() != OrganismType::CYBERSHEEP) {
			world->deleteOrganism(tmp);
			Commentator::Addcomment(OrganismTypestring() + " kills " + tmp->OrganismTypestring());
		}
	}
	if (rand() % 10 < 2) Spread();
}

string Sosnowskyshogweed::OrganismTypestring()
{
	return "Sosnowsky's hogweed";
}