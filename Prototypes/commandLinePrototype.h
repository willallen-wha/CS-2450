// VALUES

// Average weight of chicken nugget according to McDonalds
// Probably not 100% accurate, but a 
const float nuggetWeightPounds = 16.5 * 0.0022046226;


// FUNCTIONS

// Returns (as a float) the weight required for the new weight to be some percentage larger than the original weight.
//
// Arguments -
// orig, a float: the original weight of the person
// percent, a float: the percentage increase
//
// Example -
// amountForPercentage(99, 1) will return 1 since a 99 pound person who ate a pound of chicken nuggets would be 100 pounds, one percentage (a single pound) of which is chicken nuggets.
float amountForPercent(float orig, float percent) {
    //New weight needed for consumption
    float weight;

    //The general formula is orginal weight times percent over 100 minus percent. Source: trust me I did the math
    weight = (orig * percent) / (100 - percent);
    
    //Return it
    return weight;
}

