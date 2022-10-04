#include <iostream>
#include "commandLinePrototype.h"

using namespace std;

int main() 
{
  bool complete = false;
  while(!complete)
  {
    //Get user's weight
    cout << "Please enter your current weight in pounds." << endl;

    //Store as a float, since most people don't know their weight to a level float isn't precise to
    float weight;
    cin >> weight;

    //Get what percentage it should be, again as float because who's going to go below 2 digits of precision
    cout << "What percentage would you like to be? (Enter 1 for 1%)" << endl;
    float percent;
    cin >> percent;

    //Amount needed to consume to become that percentage of the thing.
    float amount = amountForPercent(weight, percent);

    //Output
    cout << "You would need to consume " << amount / nuggetWeightPounds << " chicken nuggets." << endl;

    //Exit if done
    cout << "Would you like to continue? (y/n)" << endl;
    char cont; bool validate = true;
    cin >> cont;
    while(validate) {
      if(cont == 'y' || cont == 'Y') validate = false;
      else if (cont == 'n' || cont == 'N') {
        validate = false; 
        complete = true;
      } else {
        cout << "Please enter a valid option" << endl;
        cin >> cont;
      }
    }
  }
}
