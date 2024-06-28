package Model;

import java.util.ArrayList;
import java.util.Random;

public class Luggage<T>{
    private ArrayList<T> luggages;

    public Luggage() {
        luggages = new ArrayList<>();
    }

    public ArrayList<T> getLuggage() {
        return luggages;
    }

    public int countFines(){
        int count = 0;
        for (int i=0; i<luggages.size();i++) {
            boolean response=false;
            if (!luggages.isEmpty()) {
                if (luggages.get(i) instanceof Suitcase suitcase) {
                     if (suitcase.isOverDimension() || suitcase.isOverweight()) {
                         response = true;
                     }
                }
                if (luggages.get(i) instanceof Backpack backpack) {
                    response = backpack.isOverweight();
                }
            }
            if (response){
                count++;
            }
        }
        return count;
    }

    public static Luggage addRandomLuggage(){
        Suitcase suitcase = new Suitcase();
        Backpack backpack = new Backpack();
        Random random = new Random();
        Luggage <Equipaje> luggage = new Luggage<>();
        suitcase.setRandomCharacteristcs(random.nextInt(16));
        backpack.setRandomsCharacteristics(random.nextInt(11));
        luggage.getLuggage().add(suitcase);
        luggage.getLuggage().add(backpack);
        return luggage;
    }
}