#pragma once
#include <iostream>

using namespace std;

class Commentator
{
public: 
	static void Addcomment(string comment);
	static void Deletecomments();
	static void Comment();
	
protected:
	static string contents;
};

