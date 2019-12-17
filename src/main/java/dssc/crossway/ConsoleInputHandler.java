package dssc.crossway;

import java.util.Scanner;

public class ConsoleInputHandler {

    private static final String WRONG_FORMAT_MESSAGE = "The input format is not correct";
    private static final String ILLEGAL_VALUE_MESSAGE = "The inserted value is illegal";

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
            {
                System.out.println(WRONG_FORMAT_MESSAGE);
            }
            else if(!((input>=minIncluded) && (input<maxExcluded)))
            {
                System.out.println(ILLEGAL_VALUE_MESSAGE);
            }
            else
            {
                validInput = true;
            }
            //validInput = correctFormat && (input>=minIncluded) && (input<maxExcluded);
        }

        return input;

    }


}
