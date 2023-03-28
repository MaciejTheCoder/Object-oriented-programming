#include "Commentator.h"

string Commentator::contents = "";

void Commentator::Addcomment(string comment)
{
	contents += comment + "\n";
}

void Commentator::Deletecomments()
{
	contents = "";
}

void Commentator::Comment()
{
	if (contents == "")return;
	cout << contents;
}

