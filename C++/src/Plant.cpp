#include "Plant.h"

//Public part
void Plant::action()
{
	if (rand() % 100 < 30) Spread();
}

bool Plant::IfAnimal()
{
	return false;
}

Plant::~Plant() {}


//Protected part
Plant::Plant(OrganismType type, World* world, Point point, int strength, int initiative, int bornround):Organism(type, world, point, strength, initiative, bornround)
{
	direction = new bool[4]{ true,true,true,true };
}

void Plant::collision(Organism * other) {}

void Plant::Spread()
{
	Point tmp1Punkt = this->RadndomfreePoint(point);
	if (tmp1Punkt == point) return;
	else {
		Organism* tmpOrganizm = Organism::createneworganism (type, this->world, tmp1Punkt);
		Commentator::Addcomment("New plant " + tmpOrganizm->OrganismTypestring());
		world->addOrganism(tmpOrganizm);
	}
}
