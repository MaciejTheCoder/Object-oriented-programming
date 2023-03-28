#pragma once
#include "Animal.h"

class Human : public Animal
{
public:
	class Ability
	{
	public:
		Ability();
		int gettime();
		void setime(int time);
		int getcooldown();
		void setcooldown(int cooldown);
		bool getactive();
		void setactive(bool active);
		bool getcanbeactivated();
		void setcanbeactivated(bool canbeactivated);
		void check();
		void active();
		void deactive();
	protected:
		int time;
		int cooldown;
		bool canbeactivated;
		bool isitactive;
	};
	Human(World* world, Point point, int bornround);
	~Human();
	void action() override;
	Ability* Getability();
	string OrganismTypestring() override;
	Move getdirection();
	void setdirection(Move direction);
protected:
	Move direction;
	Ability* ability;
	Point prepermove() override;
	void Purification();
};