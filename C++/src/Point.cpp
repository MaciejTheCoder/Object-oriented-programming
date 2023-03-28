#include "Point.h"

Point::Point(int x, int y)
{
	this->x = x;
	this->y = y;
}

void Point::SetX(int a){this->x = a;}

void Point::SetY(int b){this->y = b;}

int Point::GetX(){return x;}

int Point::GetY(){return y;}

Point::Point()
{
	x = 0;
	y = 0;
}
