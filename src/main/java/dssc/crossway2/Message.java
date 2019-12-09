package dssc.crossway2;

public class Message {
    private final MessageTypes type;
    private final String content;

    public Message(MessageTypes type, String content) {
        this.type = type;
        this.content = content;
    }

    // type=MOVE;content=x:1,y:5    ->  [TYPE],[CONTENT]

    public Message(String line) {
        System.out.println( "Printone iniziale " + line );
        String[] tokens = line.split( ";" );
//        System.out.println( "Printone dopo tokenone " + tokens[0] + "\t" + tokens[1] );
        this.type = MessageTypes.valueOf(  tokens[0].split( "=" )[1] );
        this.content = tokens[1].split( "=" )[1];
    }

    @Override
    public String toString() {
        return( "type="+ this.type.toString() + ";content=" + this.content);
    }

    public MessageTypes getType() {
        return type;
    }

    public String getContent() {
        return content;
    }
}
