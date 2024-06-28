package Model;


import java.io.Serializable;
import java.util.Random;

public class RandomCodeGenerator implements Serializable {

    private static final String CHARACTERS = "ABCDEFGHIJKLMNOPQRSTUVWXYZ0123456789";
    private static final int CODE_LENGTH = 8;
    public static String generateRandomCode() {
        Random random = new Random();
        StringBuilder codeBuilder = new StringBuilder();

        for (int i = 0; i < CODE_LENGTH; i++) {
            int randomIndex = random.nextInt(CHARACTERS.length());
            char randomChar = CHARACTERS.charAt(randomIndex);
            codeBuilder.append(randomChar);
        }

        return codeBuilder.toString();
    }
    public static String generateRandomPassportNumber() {
        int PASSAPORT_NUMBER_LENGTH = 11;
        Random random = new Random();
        StringBuilder codeBuilder = new StringBuilder();
        String letterCharacters = "ABCDEFGHIJKLMNOPQRSTUVWXYZ"; // Only letters
        String numbers = "0123456789";

        for (int i = 0; i < 3; i++) {
            int randomIndex = random.nextInt(letterCharacters.length());
            char randomChar = letterCharacters.charAt(randomIndex);
            codeBuilder.append(randomChar);
        }

        for (int i = 3; i < PASSAPORT_NUMBER_LENGTH; i++) {
            int randomIndex = random.nextInt(numbers.length());
            char randomChar = numbers.charAt(randomIndex);
            codeBuilder.append(randomChar);
        }
        return codeBuilder.toString();
    }

    public static String generateRandomFlightAirline(String IATA_CODE) {
        int FLIGHTCODE_LENGTH = 6;
        Random random = new Random();
        StringBuilder codeBuilder = new StringBuilder();
        String letterCharacters = "ABCDEFGHIJKLMNOPQRSTUVWXYZ"; // Only letters

        codeBuilder.append(IATA_CODE.substring(0, IATA_CODE.length()));
        codeBuilder.append("-");
        for (int i = IATA_CODE.length()+1; i < FLIGHTCODE_LENGTH; i++) {
            int randomIndex = random.nextInt(letterCharacters.length());
            char randomChar = letterCharacters.charAt(randomIndex);
            codeBuilder.append(randomChar);
        }
        return codeBuilder.toString();
    }

    public static String generateRandomRegistrationNumber (String IATA_CODE) {
        int REGISTRATION_NUMBER_LENGTH = 9;
        Random random = new Random();
        StringBuilder codeBuilder = new StringBuilder();
        String letterCharacters = "ABCDEFGHIJKLMNOPQRSTUVWXYZ"; // Only letters
        String numbers = "0123456789";

        codeBuilder.append(IATA_CODE.substring(0, IATA_CODE.length()));
        codeBuilder.append("-");
        for (int i = IATA_CODE.length()+1; i < 6; i++) {
            int randomIndex = random.nextInt(letterCharacters.length());
            char randomChar = letterCharacters.charAt(randomIndex);
            codeBuilder.append(randomChar);
        }
        codeBuilder.append("-");
        for(int i=6; i<REGISTRATION_NUMBER_LENGTH;i++){
            int randomIndex = random.nextInt(numbers.length());
            char randomNumber = numbers.charAt(randomIndex);
            codeBuilder.append(randomNumber);
        }
        return codeBuilder.toString();
    }


}
