package games;

import java.io.IOException;

public class Choice {

    public static void main(String[] args) throws IOException {
        System.out.println("Выберите игру:\n1 - \"однорукий бандит\", 2 - \"пьяница\"");
        switch (System.in.read()) {
            case '1': Slot.main(null); break;
            case '2': Drunkard.main(null); break;
            default: System.out.println("Игры с таким номером нет!");
        }
    }
}
