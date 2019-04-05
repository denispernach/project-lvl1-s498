package games;

import java.io.IOException;

public class Choice {

    public static void main(String[] args) throws IOException {
        System.out.println("Chose the game please:\n1 - \"Slot\", 2 - \"Drunkard\"");
        switch (System.in.read()) {
            case '1': Slot.main(null); break;
            case '2': Drunkard.main(null); break;
            default: System.out.println("Sorry, but we don't have such game!");
        }
    }
}
