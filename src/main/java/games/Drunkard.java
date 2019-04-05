package games;


import org.apache.commons.math3.util.MathArrays;

public class Drunkard {

    private static int[][] playersCards = new int[2][CardUtils.CARDS_TOTAL_COUNT];
    private static int[] playersCardTails = new int[2];
    private static int[] playersCardHeads = new int[2];
    private static boolean firstWin;

    public static void main(String[] args) {
        int[] deck = CardUtils.initDeck();
        dealCards(deck);
        game();
        if(firstWin){
            System.out.println("The first player won the game");
        } else {
            System.out.println("The second player won the game");
        }
    }



    private static void dealCards(int[] deck){
        int half = CardUtils.CARDS_TOTAL_COUNT/2;
        for (int i = 0; i <half ; i++) {
            playersCards[0][i] = deck[i];
            playersCards[1][i] = deck[half + i];
        }
        playersCardTails[0] = 0;
        playersCardTails[1] = 0;
        playersCardHeads[0] = half;
        playersCardHeads[1] = half;
    }

    private static int getCard(int player){
        int card = playersCards[player][playersCardTails[player]];
        playersCardTails[player] = incrementIndex(playersCardTails[player]);
        return card;
    }

    private static void addCard(int player, int card){
        playersCards[player][playersCardHeads[player]] = card;
        playersCardHeads[player] = incrementIndex(playersCardHeads[player]);
    }


    private static int incrementIndex(int i) {
        return (i + 1) % CardUtils.CARDS_TOTAL_COUNT;
    }

    private static void game(){
        int count = 0;
        while(true){
            if(playerCardsIsEmpty(0) || playerCardsIsEmpty(1)) break;
            count ++;
            int card1 = getCard(0);
            int card2 = getCard(1);
            System.out.println("Iteration No" + count + " player No1 card: " + CardUtils.toString(card1) +
                    " player No2 card: " + CardUtils.toString(card2));
            int ignoreSuitCard1 = card1 % CardUtils.PARS_TOTAL_COUNT;
            int ignoreSuitCard2 = card2 % CardUtils.PARS_TOTAL_COUNT;
            if(ignoreSuitCard1 == 0 && ignoreSuitCard2 == 8){
                currentWin(true, card1, card2);
                continue;
            }
            if(ignoreSuitCard1 == 8 && ignoreSuitCard2 == 0){
                currentWin(false, card1, card2);
                continue;
            }
            if(ignoreSuitCard1 > ignoreSuitCard2){
                currentWin(true, card1, card2);
                continue;
            }
            if(ignoreSuitCard1 < ignoreSuitCard2){
                currentWin(false, card1, card2);
                continue;
            }
            draw(card1, card2);
        }
    }

    private static boolean playerCardsIsEmpty(int player) {
        int tail = playersCardTails[player];
        int head = playersCardHeads[player];
        return tail == head;
    }

    private static void currentWin(boolean firstWin, int card1, int card2){
        Drunkard.firstWin = firstWin;
        if(firstWin){
            System.out.println("The first player won!");
            addCard(0, card1);
            addCard(0, card2);
        } else {
            System.out.println("The second player won!");
            addCard(1, card1);
            addCard(1, card2);
        }
    }

    private static void draw(int card1, int card2){
        System.out.println("draw!");
        addCard(0, card1);
        addCard(1, card2);
    }


}
