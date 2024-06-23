package Utils;
import java.util.InputMismatchException;
import java.util.Scanner;

public class Input {
    private static final Scanner scanner = new Scanner(System.in);
    private static final int INVALID_OPTION = -1;

    private static int scanInt() {
        try {
            return scanner.nextInt();
        } catch (InputMismatchException e) {
            scanner.nextLine();
            System.out.println("El valor ingresado no es un numero entero, intente nuevamente");
            return INVALID_OPTION;
        }
    }

    public static int requestUserInputInt() {
        int option;
        do {
            option = scanInt();
        } while(option == INVALID_OPTION);
        return option;
    }

}
