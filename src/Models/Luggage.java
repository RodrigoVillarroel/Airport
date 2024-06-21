package Models;

import java.util.ArrayList;

public class Luggage<T>{
    private ArrayList<T> luggage;

    public Luggage() {
        luggage = new ArrayList<>();
    }

    public ArrayList<T> getLuggage() {
        return luggage;
    }

    public boolean isOverweight(int index){
        boolean response = false;
        if(luggage.get(index).getClass().getSimpleName().equalsIgnoreCase("Suitcase")){
            Suitcase suitcase = (Suitcase) luggage.get(index);
            if (suitcase.getWeight()>24F){
                response = true;
            }
        }
        if (luggage.get(index).getClass().getSimpleName().equalsIgnoreCase("Backpack")){
            Backpack backpack = (Backpack) luggage.get(index);
            if (backpack.getWeight()>4F){
                response = true;
            }
        }
        return response;
    }

    //ADDLUGGAGE
}