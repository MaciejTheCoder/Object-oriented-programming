#pragma once


class Point
{
public:
	Point(int x, int y);
	void SetX(int x);
	void SetY(int y);
	int GetX();
	int GetY();
	Point();
	bool operator==(const Point & other) {return (x == other.x && y == other.y);}

protected:
	int x;
	int y;
};

