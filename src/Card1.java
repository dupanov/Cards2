import javax.swing.*;
import java.awt.*;

/**
 * Created by Вадик on 10.11.2015.
 */
public class Card1 {
    /*
     * Test code.
     */
    public static void main(String[] args) {

        Card[] cards = Card.makeDeck();

        JFrame frame = new JFrame();
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        // add the CardTable
        String cardset = "cardset-oxymoron";
        Canvas canvas = new CardTable(cardset);
        frame.getContentPane().add(canvas);

        // show the frame
        frame.pack();
        frame.setVisible(true);

        // check that findBisect finds each card
        int index;
        for (int i=0; i<52; i++) {
            index = Card.findBisect(cards, cards[i], 0, cards.length-1);
            if (index != i) {
                Card.printCard(cards[i]);
                System.out.println("Wrong!");
            }
        }

        System.out.println(Card.handScore(cards));
        Card.printDeck(cards);

        System.out.println(Card.suitHist(cards)[0] + "\f" + Card.suitHist(cards)[1]);
        System.out.println(Card.hasFlush(cards));

    }
}
