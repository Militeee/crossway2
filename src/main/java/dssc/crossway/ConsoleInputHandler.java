package dssc.crossway;

import java.util.Scanner;

/**
 * This class has static functions that handles user input from console.
 */
public class ConsoleInputHandler {


    private ConsoleMessageWriter consoleMessageWriter = new ConsoleMessageWriter();

    /**
     * This method ask to the user an int between a certain interval, until it inputs a valid number.
     * @return the number inserted by the user
     */
    public int askUserForInput() {

        Scanner scanner = new Scanner(System.in);

        int input = -1;

        boolean validInput = false;

        while(!validInput) {
            String line = scanner.nextLine();

            try {
                input = Integer.parseInt(line);
                validInput = true;
            } catch (NumberFormatException e) {
                this.consoleMessageWriter.writeWrongFormatMessage();
            }

        }
        return input;

    }

}