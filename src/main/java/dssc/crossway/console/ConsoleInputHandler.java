package dssc.crossway.console;

import java.util.Scanner;

public class ConsoleInputHandler {


    private ConsoleMessageWriter consoleMessageWriter = new ConsoleMessageWriter();

    public int askUserForInteger() {

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