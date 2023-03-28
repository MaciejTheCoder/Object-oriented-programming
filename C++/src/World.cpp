//THE WORLD AND ALL ITS FUNCTIONALITIES
//By Maciej Majewski, student id 184945
#pragma once
#include "World.h"
#include "Commentator.h"
#include <algorithm>
#include <Windows.h>
#include <tuple>
#include <iostream>
#include <conio.h>
#include "Human.h"
using namespace std;

//PROTECTED PART
void World::input() //takes data from keyboard
{
	while (true) {
		cout << endl << "Press button" << endl; 
		int input;
		input = _getch(); 
		if (input == 224) {
			if (humanalive) {
				input = _getch();
				if (input == 72) {
					human->setdirection(Organism::Move::DUP);
					break;
				}
				else if (input == 80) {
					human->setdirection(Organism::Move::DDOWN);
					break;
				}
				else if (input == 75) {
					human->setdirection(Organism::Move::DLEFT);
					break;
				}
				else if (input == 77) {
					human->setdirection(Organism::Move::DRIGHT);
					break;
				}
			}
			else { //when human is dead
				cout << "You are dead my friend" << endl;
				_getch();
				continue;
			}
		}
		if (humanalive && input == 80 || input == 112) { ///activation of special ability
			Human::Ability* tmp = human->Getability();
			if (tmp->getcanbeactivated()) {
				tmp->active();
				Commentator::Addcomment("ABILITY ACTIVATED");
				break;
			}
			else if (tmp->getactive()) {
				cout << "It has been already activated, it's active for "
					<< tmp->gettime() << " rounds" << endl;
				continue;
			}
			else {
				cout << "Error" << endl;
				continue;
			}
		}
		else if ((!humanalive) && input == 80 || input == 112) { //you cant activate ability when you are dead
			cout << "You are dead my friend" << endl;
			continue;
		}
		if (input == 113 || input == 81) {
			cout << "Thanks for the game" << endl;
			over = true; //exit
			break;
		}
		if (input == 13) { break; }//next round
		else { //error
			cout << "Error" << endl;
			continue;
		}
	}
}

void World::organismssort() //sortorganisms by iniciative and age
{
	sort(organisms.begin(), organisms.end(),
		[](Organism* org1, Organism* org2) {
			int in1 = org1->getinitiative();
			int in2 = org2->getinitiative();
			int bornround1 = org1->getbornround();
			int bornround2 = org2->getbornround();
			return tie(in2, bornround1) < tie(in1, bornround2);
		});
}

//PUBLIC PART
World::World()
{
	this->width = 0;
	this->high = 0;
	round = 0;
	humanalive = true;
	organisms.reserve(0);
}

World::World(int x, int y) {
	this->width = x; 
	this->high = y;
	round = 0;
	humanalive = true;
	over = false;
	organisms.reserve(width * high / 2);

	board = new Organism * *[high];
	for (int i = 0; i < high; i++) {
		board[i] = new Organism * [width];
	}
	for (int i = 0; i < high; i++) {
		for (int j = 0; j < width; j++) {
			board[i][j] = nullptr;
		}
	}
}

World::World(World&& other)
{
	this->width = other.width;
	this->high = other.high;
	this->round = other.round;
	this->board = other.board;
	this->humanalive = other.humanalive;
	this->over = other.over;
	this->organisms = other.organisms;
	this->human = other.human;
	for (size_t i = 0; i < organisms.size(); i++) {organisms[i]->setworld(this);}
	other.width = 0;
	other.high = 0;
	other.round = 0;
	other.board = nullptr;
	other.humanalive = false;
	other.over = false;
	other.human = nullptr;
}

World::~World() { //destructor 
	for (int i = 0; i < high; i++) {
		delete[] board[i];
	}
	delete board;
}

void World::generadeWorld(double filling) { //gererading a World
	int numberoforganisms = floor(width * high * filling);
	Point position = randomfreespace();//HUMAN FIRST
	Organism* tmpOrganism = Organism::createneworganism(Organism::OrganismType::HUMAN, this, position);
	addOrganism(tmpOrganism);
	human = (Human*)tmpOrganism;
	for (int i = 0; i < numberoforganisms - 1; i++) { //ADDING ORGANISMS
		Point position = randomfreespace();
		if (!(position == Point(-1, -1))) {
			addOrganism(Organism::createneworganism(Organism::randomType(), this, position));
		}
		else return;
	}
}



void World::makeRound() //round
{
	Commentator::Deletecomments();
	input();
	if (over) return;
	round++;
	drawWorld();
	organismssort();
	for (size_t i = 0; i < organisms.size(); i++) {
		if (organisms[i]->getalive() == true) { continue; }
		else if (organisms[i]->getbornround() != round) {
			organisms[i]->action();
			drawWorld();
		}
	}
	for (size_t i = 0; i < organisms.size(); i++) {
		if (organisms[i]->getalive() == true) {
			delete organisms[i];
			organisms.erase(organisms.begin() + i);
		}
	}
	for (size_t i = 0; i < organisms.size(); i++) {
		organisms[i]->setifchildren(false);
	}
}

void World::drawWorld() { //drawing world in # frame
	system("cls");
	cout << "This game is created  by Maciej Majewski, student id 184945" << endl << endl;
	cout << "Round number: " << round << endl;
	cout << "P - superpower		Q - quit	Enter - next round" << endl << endl;
	for (int i = 0; i < width+2; i++) cout << "# ";
	cout << endl;
	for (int i = 0; i < high; i++) {
			cout << '#';
		for (int j = 0; j < width; j++) {
			cout << " ";
			if (board[i][j] == nullptr) cout << "_";
			else board[i][j]->draw();
		}
		cout << " ";
		cout << '#';
		cout << endl;
	}
	for (int i = 0; i < width+2; i++) {
		cout << '#';
		cout << " ";
	}
	cout << endl;
	Commentator::Comment();
}

Organism*** World::getboard(){return board;}

vector<Organism*> World::GetOrganisms(){return organisms;}



Organism* World::gethuman(){return human;}

bool World::gethumanalive(){return humanalive;}

void World::sethumanalive(bool humanalive){this->humanalive = humanalive;}



int World::Getwidth() {return width;}

int World::Gethigh() {return high;}

const int World::Round() {return round;}

bool World::getgameover(){return over;}




void World::addOrganism(Organism* organism)
{
	organisms.push_back(organism);
	board[organism->getpoint().GetY()][organism->getpoint().GetX()] = organism;
}

void World::deleteOrganism(Organism* organism)
{
	getboard()[organism->getpoint().GetY()][organism->getpoint().GetX()] = nullptr;
	organism->setalive(true);
	if (organism->gettype() == Organism::OrganismType::HUMAN) {
		humanalive = false;
		human = nullptr;
	}
}



Point World::randomfreespace()
{
	for (int i = 0; i < high; i++) {
		for (int j = 0; j < width; j++) {
			if (board[i][j] == nullptr) {
				while (true) {
					int x = rand() % width;
					int y = rand() % high;
					if (board[y][x] == nullptr) return Point(x, y);
				}
			}
		}
	}
	return Point(-1, -1);
}

bool World::isfree(Point point)
{
	if (board[point.GetY()][point.GetX()] == nullptr) return false;
	else return true;
}

Organism* World::whatisit(Point point){	return board[point.GetY()][point.GetX()];}



