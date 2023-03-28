#include "Human.h"
#include "Commentator.h"

//Public part

//Ability
Human::Ability::Ability()
{
	cooldown = 0;
	time = 0;
	isitactive = false;
	canbeactivated = true;
}

int Human::Ability::gettime(){return time;}

void Human::Ability::setime(int time){this->time = time;}

int Human::Ability::getcooldown(){return cooldown;}

void Human::Ability::setcooldown(int cooldown){this->cooldown = cooldown;}

bool Human::Ability::getactive(){return isitactive;}

void Human::Ability::setactive(bool isitactive){this->isitactive = isitactive;}

bool Human::Ability::getcanbeactivated(){return canbeactivated;}

void Human::Ability::setcanbeactivated(bool canbeactivated){this->canbeactivated = canbeactivated;}

void Human::Ability::check()
{
	if (cooldown > 0) cooldown--;
	if (time > 0) time--;
	if (time == 0) deactive();
	if (cooldown == 0) canbeactivated = true;
}

void Human::Ability::active()
{
	if (cooldown == 0) {
		isitactive = true;
		canbeactivated = false;
		cooldown = 10;
		time = 5;
	}
	else if (cooldown > 0) {
		cout << "Ability can be activated " << cooldown << " rounds" << endl;
	}
}

void Human::Ability::deactive()
{
	isitactive = false;
}

//Rest
Human::Human(World* world, Point point, int round)
	:Animal(OrganismType::HUMAN, world, point, 5, 4, round)
{
	this->rangeofmove = 1;
	this->chanceofmove = 1;
	ability = new Ability();
	direction = Move::NONE;
	this->sign = 'H';

}

Human::~Human() {}

void Human::action()
{
	if (ability->getactive()) {
		Commentator::Addcomment(OrganismTypestring() + " Ability is active for "
			+ to_string(ability->gettime()) + " rounds");
		Commentator::Comment();
		Purification();
	}
	for (int i = 0; i < rangeofmove; i++) {
		Point nextposition = prepermove();

		if (world->isfree(nextposition)
			&& world->whatisit(nextposition) != this) {
			collision(world->whatisit(nextposition));
			break;
		}
		else if (world->whatisit(nextposition) != this) move(nextposition);
		if (ability->getactive()) Purification();
	}
	direction = Move::NONE;
	ability->check();
}

Human::Ability * Human::Getability()
{
	return ability;
}

string Human::OrganismTypestring()
{
	return "Human";
}

Organism::Move Human::getdirection()
{
	return direction;
}

void Human::setdirection(Move direction)
{
	this->direction = direction;
}



//Protected part
Point Human::prepermove()
{
	int x = point.GetX();
	int y = point.GetY();
	RandomPoint(point);
	if (isitblocked(direction)) return point;
	else {
		Point przyszlaPozycja;
		if (direction == Move::DDOWN) return Point(x, y + 1);
		if (direction == Move::DUP) return Point(x, y - 1);
		if (direction == Move::DLEFT) return Point(x - 1, y);
		if (direction == Move::DRIGHT) return Point(x + 1, y);
		if (direction == Move::NONE) return Point(x, y);
	}
}

void Human::Purification()
{
	RandomPoint(point);
	int x = point.GetX();
	int y = point.GetY();
	Organism* tmp = nullptr;
	for (int i = 0; i < 4; i++) {
		if (i == 0 && !isitblocked(Move::DDOWN))
			tmp = world->whatisit(Point(x, y + 1));
		else if (i == 1 && !isitblocked(Move::DUP))
			tmp = world->whatisit(Point(x, y - 1));
		else if (i == 2 && !isitblocked(Move::DLEFT))
			tmp = world->whatisit(Point(x - 1, y));
		else if (i == 3 && !isitblocked(Move::DRIGHT))
			tmp = world->whatisit(Point(x + 1, y));
		if (tmp != nullptr) {
			world->deleteOrganism(tmp);
			Commentator::Addcomment(OrganismTypestring() + " ability kills "
				+ tmp->OrganismTypestring());
		}
	}
}