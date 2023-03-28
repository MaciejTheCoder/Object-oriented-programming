#include "Animal.h"
#include <Windows.h>
#include "Commentator.h"

//Public part
bool Animal::IfAnimal()
{
	return true;
}

Animal::~Animal() {}

void Animal::action()
{
	for (int i = 0; i < rangeofmove; i++) {
		Point nextposition = prepermove();
		if (world->isfree(nextposition)
			&& world->whatisit(nextposition) != this) {
			collision(world->whatisit(nextposition));
			break;
		}
		else if (world->whatisit(nextposition) != this) move(nextposition);
	}
}

void Animal::collision(Organism* other)
{
	if (type == other->gettype()) {
		if (rand() % 100 < 50)  multiplication(other);
	}
	else {
		if (other->specialattack(this, other)) return;
		if (specialattack(this, other)) return;
		if (strength >= other->getstrength()) {
			world->deleteOrganism(other);
			move(other->getpoint());
			Commentator::Addcomment(OrganismTypestring() + " kills " + other->OrganismTypestring());
		}
		else {
			world->deleteOrganism(this);
			Commentator::Addcomment(other->OrganismTypestring() + " kills " + OrganismTypestring());
		}
	}
}

double Animal::getchancetomove(){return chanceofmove;}

void Animal::setchancetomove(double chanceofmove){this->chanceofmove = chanceofmove;}

int Animal::getrangeofmove(){return rangeofmove;}

void Animal::setrangeofmove(int rangeofmove){this->rangeofmove = rangeofmove;}



//Protected part
Animal::Animal(OrganismType type, World* world, Point point, int strength, int initiative, int bornround):Organism(type, world, point, strength, initiative, bornround)
{
	direction = new bool[4]{ true,true,true,true };
	children = false;
}

Point Animal::prepermove()
{
	if (rand() % 100 >= (int)(chanceofmove * 100)) return point;
	else return RandomPoint(point);
}

void Animal::multiplication(Organism* other)
{
	if (this->children || other->getifchildren()) return;
	Point tmp1Point = this->RadndomfreePoint(point);
	if (tmp1Point == point) {
		Point tmp2Point = other->RadndomfreePoint(other->getpoint());
		if (tmp2Point == other->getpoint()) return;
		else {
			Organism* tmp = Organism::createneworganism(type, this->world, tmp2Point);
			Commentator::Addcomment(tmp->OrganismTypestring() + " was born");
			world->addOrganism(tmp);
			children = true;
			other->setifchildren(true);
		}
	}
	else {
		Organism* tmp = Organism::createneworganism(type, this->world, tmp1Point);
		Commentator::Addcomment(tmp->OrganismTypestring() + " was born");
		world->addOrganism(tmp);
		children = true;
		other->setifchildren(true);
	}
}

