//
//By Maciej Majewski, student id 184945
//
#include <iostream>
#include <Windows.h>
#include <conio.h>
#include <ctime>
#include "World.h"

using namespace std;

int main()
{
    srand(time(NULL));
    char input;
    World * world;
    bool QUIT = false;

    while (true) {
        cout << "This game is created  by Maciej Majewski, student id 184945" << endl << endl << endl;
        cout << "Hello in my game" << endl;
        cout << "It's 2D virtual word simulator with a few different plants and animals" << endl;
        cout << "Every Animal has different sign, yours is 'H'" << endl;
        cout << "By default game is created on 10x10 dimension and 20% filling level " << endl;
        cout << "If you want create game on your own rules click C" << endl;
        cout << "Enjoy" << endl << endl;;
        Sleep(1500);
        cout << "Press button to continue..." << endl << endl;
        _getch();
        system("cls");
        cout << "S- start game  C - create game  Q - quit program" << endl;
        input=_getch();
        if ((input == 'C') || (input == 'c')) { //Create new game situation
            int width, high;
            double ipt, filling;
            cout << "Choose width of the World" << endl;
            cin >> width;
            cout << "Choose high of the World" << endl;
            cin >> high;
            cout << "Choose filling of the World (0-100)" << endl;
            cin>>ipt;
            filling = ipt*0.01;
            world = new World(width, high);
            world->generadeWorld(filling);
            break;
        }
        else if ((input == 'S') || (input == 's')) { //start default game situation
            world = new World(10, 10);
            world->generadeWorld(0.2);
            break;
        }
        else if (input == 'Q' || input == 'q') { //just leaving a program
            world = new World(1, 1);
            QUIT = true;
            cout << "Thanks for the game" << endl;
            break;
        }
    } 
    while ((world->getgameover() == false) && (QUIT == false)) {
        world->drawWorld();
        world->makeRound();
    }
    delete world;
    return 0;
}
  
