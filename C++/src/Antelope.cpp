#include "Antelope.h"
#include "Commentator.h"

Antelope::Antelope(World* world, Point point, int bornround):Animal(OrganismType::ANTELOPE, world, point, 4, 4, bornround)
{
	this->rangeofmove = 2;
	this->chanceofmove = 1;
	this->sign = 'A';
}

bool Antelope::specialattack(Organism * one, Organism * two)
{
	if (rand() % 10 < 5) {
		if (this == one) {
			Commentator::Addcomment(OrganismTypestring() + " run away from " + two->OrganismTypestring());
			move(RadndomfreePoint(two->getpoint()));
		}
		else if (this == two) {
			Commentator::Addcomment(OrganismTypestring() + " run away from " + one->OrganismTypestring());
			Point tmp = this->point;
			move(RadndomfreePoint(this->point));
			if (point == tmp) {
				world->deleteOrganism(this);
				Commentator::Addcomment(one->OrganismTypestring() + " kills " + OrganismTypestring());
			}
			one->move(tmp);
		}
		return true;
	}
	else return false;
}

string Antelope::OrganismTypestring()
{
	return "Antelope";
}
