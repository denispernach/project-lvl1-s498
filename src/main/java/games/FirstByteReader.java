package games;

import java.io.IOException;


public class FirstByteReader {

    public static void main(String... __) throws IOException {
        int bufLength = 20;
        byte[] buf = new byte[bufLength];
        for (int i = 0; i < 3; i++) {
            System.out.print("Введите что-нибудь: ");
            System.in.read(buf);
            char c = (char) buf[0];
            while (System.in.available() > 0){
                System.in.read(buf, 0, Math.min(System.in.available(), bufLength));
            }
            System.out.println("Вы ввели: " + c);
        }

    }
}
