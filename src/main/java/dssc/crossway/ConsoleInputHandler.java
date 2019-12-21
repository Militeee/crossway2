package dssc.crossway;

import java.util.Scanner;

/**
 * This class has static functions that handles user input from console.
 */
public class ConsoleInputHandler {

    private static final String WRONG_FORMAT_MESSAGE = "The input format is not correct";
    private static final String ILLEGAL_VALUE_MESSAGE = "The inserted value is illegal";

    /**
     * This method ask to the user an int between a certain interval, until it inputs a valid number.
     * @param minIncluded minimum (included) accepted value
     * @param maxExcluded maximum (excluded) accepted value
     * @param message the message printed on console before asking an input to the user
     * @return the number inserted by the user
     */
    public static int askInt(int minIncluded, int maxExcluded, String message) {

        Scanner scanner = new Scanner(System.in);

        int input = -1;

        boolean validInput = false;

        while(!validInput)
        {
            boolean correctFormat = true;

            System.out.println(message);
            String line = scanner.nextLine();

            try {
                input = Integer.parseInt(line);
            } catch (Exception e)
            {
                correctFormat = false;
            }

            if(!correctFormat)
                System.out.println(WRONG_FORMAT_MESSAGE);
            else if(!((input>=minIncluded) && (input<maxExcluded)))
                System.out.println(ILLEGAL_VALUE_MESSAGE);
            else
                validInput = true;
        }

        return input;

    }

}