/**
 * Name: Anh Bach
 * ID: A17133630
 * Email: tbach@ucsd.edu
 * Sources used: Lecture Slides, Oracle, Piazza
 * This file contains Sanctuary class that uses HashMap 
 * to keep track of animal species and total number
 * of animals. It has all the methods to check for number
 * of animals and to rescue/ release them.
 */

import java.util.Collection;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Set;

/**
 * Sanctuary class contains instance variables to keep track of
 * animals and uses HashMap to store species and number of animals
 * for each species. It has maximum number of species and animals
 * that can be keeped at the sanctuary.
 */
public class Sanctuary {
    
    HashMap<String, Integer> sanctuary;
    int maxAnimals;
    int maxSpecies;

    /**
     * Constructor to initialize the HashMap with no elements. 
     * Initialize the other instance variables accordingly
     * @param maxAnimals - maximum number of animals 
     * that the sanctuary can support
     * @param maxSpecies -  maximum number of species 
     * that the sanctuary can support
     */
    public Sanctuary(int maxAnimals, int maxSpecies) {
        if (maxAnimals < 0 || maxSpecies < 0) {
            throw new IllegalArgumentException();
        }
        sanctuary = new HashMap<>(0);
        this.maxAnimals = maxAnimals;
        this.maxSpecies = maxSpecies;
    }

    /**
     * Method to get number of animals at the sanctuary 
     * that are of the given species
     * @param species - given species to find number of animals
     * @return number of animals at the sanctuary 
     * that are of the given species
     */
    public int getNum(String species) {
        // Exception
        if (species == null) {
            throw new IllegalArgumentException();
        }
        // If the species exists in sanctuary
        if (sanctuary.containsKey(species)) {
            return sanctuary.get(species);
        }
        // If the species doesn't exist
        return 0;
    }
    
    /**
     * Method to get total number of animals at the sanctuary
     * @return total number of animals at the sanctuary
     */
    public int getTotalAnimals() {
        // Total animals in the sanctuary
        int total = 0;
        // Create a for loop to traverse the key set
        for (String key: sanctuary.keySet()) {
            // Add number of animals to total animals
            total += sanctuary.get(key);
        }
        return total;
    }
    
    /**
     * Method to get total number of species at the sanctuary
     * @return total number of species at the sanctuary
     */
    public int getTotalSpecies() {
        return sanctuary.size();
    }

    /**
     * Method to determine if the sanctuary is able to rescue animals 
     * and new species.
     * @param species - given species to be rescued
     * @param num - number of animals of the species need to be rescued
     * @return number of animals that could not be rescued,
     * 0 if sanctuary can rescue all the animals
     */
    public int rescue(String species, int num) {
        // Exception
        if (num <= 0) {
            throw new IllegalArgumentException();
        }
        if (species == null) {
            throw new IllegalArgumentException();
        }
        // Number of animals that are able to be added
        int numAnimals = this.maxAnimals >= 
            this.getTotalAnimals() + num ?
            num: this.maxAnimals - this.getTotalAnimals();
            
        // Check if species that are already in sanctuary
        boolean contain = this.sanctuary.containsKey(species);
        //Check if total of animals & species are within 
        // max. capacity of sanctuary
        if (this.getTotalAnimals() < this.maxAnimals &&
            this.getTotalSpecies() < this.maxSpecies) {
            int addAnimals = this.sanctuary.get(species) == null ?
                numAnimals : this.sanctuary.get(species) + numAnimals;
            this.sanctuary.put(species, addAnimals);
            return num - numAnimals;
        }
        else if (this.getTotalAnimals() < this.maxAnimals &&
            this.getTotalSpecies() == this.maxSpecies) {
            // Rescue number of animals of species that are
            // already in sanctuary
            if (contain) {
                int addAnimals = this.sanctuary.get(species) == null ?
                numAnimals : this.sanctuary.get(species) + numAnimals;
                this.sanctuary.put(species, addAnimals);
                return num - numAnimals;
            }
            else {
                return num;
            }
        }
        else if (this.getTotalAnimals() == this.maxAnimals &&
            this.getTotalSpecies() < this.maxSpecies) {
            return num;
        }
        return num;
    }

        // Old Code
        //Check if total of animals is within 
        // max. capacity of sanctuary
        /*if (getTotalAnimals() < maxAnimals) {
            // Check if total of species is within 
            // max. capacity of sanctuary
            if (getTotalSpecies() < maxSpecies) {
                if ((getTotalAnimals() + num) > maxAnimals) {
                    numAnimals = maxAnimals - getTotalAnimals();
                }
                else {
                    numAnimals = num;
                }
                sanctuary.put(species, numAnimals);
            }
            // If it exceed max species capacity of sanctuary
            else {
                // Rescue number of animals of species that are
                // already in sanctuary
                if (sanctuary.containsKey(species)) {
                    if ((getTotalAnimals() + num) > maxAnimals) {
                        numAnimals = maxAnimals - getTotalAnimals();
                    }
                    else {
                        numAnimals = num;
                    }
                    sanctuary.put(species, numAnimals);
                }
                // Otherwise, don't rescue any new animals
                else {
                    numAnimals = 0;
                }
            }
        }
        // Otherwise, don't rescue any new animals
        else {
            numAnimals = 0;
        }
        return (num - numAnimals);*/
    
    
    /**
     * Method to release number animals of species from the sanctuary
     * @param species - given species that will be released
     * @param num - number animals of species that will be removed
     */
    public void release(String species, int num) {
        // Exception
        if (num <= 0 || num > sanctuary.get(species) || 
            species == null || !(sanctuary.containsKey(species))) {
            throw new IllegalArgumentException();
        }
        // Release specific number of animals 
        sanctuary.put(species, (sanctuary.get(species)-num));
        // Remove species if number of animals is 0
        if (sanctuary.get(species) == 0) {
            sanctuary.remove(species);
        }
    }
}
