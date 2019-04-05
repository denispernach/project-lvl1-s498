package games;

import org.slf4j.Logger;

import java.io.IOException;
import java.util.Arrays;

public class Choice {

    static final String LINE_SEPARATOR = System.lineSeparator();
    private static final Logger log = org.slf4j.LoggerFactory.getLogger(Choice.class);

    public static void main(String[] args) throws IOException {
        log.info("Chose the game please:\n1 - \"Slot\", 2 - \"Drunkard\", 3 - \"BlackJack\"");
        switch (getCharacterFromUser()) {
            case '1': Slot.main(null); break;
            case '2': Drunkard.main(null); break;
            case '3': BlackJack.main(null); break;
            default: log.info("Sorry, but we don't have such game!");
        }
    }

    static char getCharacterFromUser() throws IOException {
        byte[] input = new byte[1 + LINE_SEPARATOR.length()-1];
        if (System.in.read(input) != input.length){
            throw new RuntimeException("Not enough characters");
        }
        return (char) input[0];
    }
}
