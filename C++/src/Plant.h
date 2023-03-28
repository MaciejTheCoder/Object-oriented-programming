#pragma once
#include "Organism.h"

class Plant : public Organism
{
public:
	virtual void action() override;
	bool IfAnimal() override;
	virtual ~Plant();
protected:
	Plant(OrganismType type, World* world, Point point, int strenght, int initiative, int bornround);
	void collision(Organism* other) override;
	virtual void Spread();
};