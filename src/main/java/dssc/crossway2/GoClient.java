package dssc.crossway2;

import java.io.*;
import java.net.Socket;

import static java.lang.Integer.parseInt;


public class GoClient implements Runnable {

    private final Console console;
    private int boardSize;
    private String ip           = "localhost";
    private int port            = 10431;
    private Socket socket;
    private PrintWriter writer;
    private BufferedReader reader;


    @Override
    public void run() {
        try {
            initClient();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public GoClient(String ip, int port) {
        this.ip = ip;
        this.port = port;
        this.console = System.console();

    }


    private void initClient() throws IOException {

        connectToServer();
        getBoardSizeFromServer();
        String line;
        while ((line = reader.readLine()) != null) {
            listenAndExecute(line);
        }
    }

    private void listenAndExecute(String line) throws IOException {
        Message message = new Message(line);
        switch (message.getType()) {
            case BOARDSIZE: {
                this.boardSize = parseInt(message.getContent());
                break;
            }

            case COMMUNICATION: {
                console.printf( "*server said:* %s", message.getContent() );
                break;
            }
            case UPDATEBOARD: {
                String[] lines =  message.getContent().split( "<nl>" );
                for(int i = 0; i < this.boardSize; i++) {
                    console.printf( "\n%s", lines[i] );
                }
                break;
            }
            case MOVEREQUEST: {
                console.printf( "Server requested a new move: Enter in format 'x:_,y:_' -> " );
                String coordinates = console.readLine();
                Message move = new Message (MessageTypes.MOVE, coordinates);
                writer.println( move.toString() );
                break;
            }
            case ENDGAME: {
                console.printf( "%s won the game.\nShutting down, bye!", message.getContent() );
                socket.close();
                break;
            }
            default: console.printf( "An unknown message arrived from server." );
        }

    }

    private void connectToServer() throws IOException {


            this.socket = new Socket( this.ip, this.port );
            this.writer = new PrintWriter( this.socket.getOutputStream(), true);
            this.reader = new BufferedReader( new InputStreamReader( this.socket.getInputStream() ));
            getBoardSizeFromServer();
    }

    private void getBoardSizeFromServer() {
        Message sizeRequest = new Message( MessageTypes.SIZEREQUEST, "null" );
        writer.println( sizeRequest );
    }
}
