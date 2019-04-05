package games;

import org.apache.commons.math3.util.MathArrays;

public class CardUtils {
    static final int PARS_TOTAL_COUNT = Par.values().length;
    static final int CARDS_TOTAL_COUNT = PARS_TOTAL_COUNT * Suit.values().length;

    static int[] initDeck(){
        int[] cards = new int[CARDS_TOTAL_COUNT];
        for (int i = 0; i < CARDS_TOTAL_COUNT; i++) {
            cards[i] = i;
        }
        MathArrays.shuffle(cards);
        return cards;
    }

    static Suit getSuit(int cardNumber) {
        return Suit.values()[cardNumber / PARS_TOTAL_COUNT];
    }

    enum Suit {
        SPADES, // пики
        HEARTS, // червы
        CLUBS, // трефы
        DIAMONDS // бубны
    }

    enum Par {
        SIX,
        SEVEN,
        EIGHT,
        NINE,
        TEN,
        JACK, // Валет
        QUEEN, // Дама
        KING, // Король
        ACE // Туз
    }

    static Par getPar(int cardNumber) {
        return Par.values()[cardNumber % PARS_TOTAL_COUNT];
    }

    static String toString(int cardNumber) {
        return getPar(cardNumber) + " " + getSuit(cardNumber);
    }
}
