#pragma once
#include "Organism.h"

class Animal : public Organism
{
public:
	bool IfAnimal() override;
	virtual ~Animal();
	virtual void action() override; 
	virtual void collision(Organism* other) override;
	double getchancetomove();
	void setchancetomove(double chanceofmove);
	int getrangeofmove();
	void setrangeofmove(int rangeofmove);
protected:
	int rangeofmove;
	double chanceofmove;
	Animal(OrganismType type, World* world, Point point, int bornturn, int strength, int initiative);
	virtual Point prepermove();
	virtual void multiplication(Organism* other);
};