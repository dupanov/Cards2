public class Card2 {
    /*
     * Test code.
     */
    public static void main(String[] args) {
        Deck deck = new Deck();
       //  check sortDeck
        Deck.shuffleDeck(deck);

      //  Deck.printDeck(deck);
//        Deck.sortDeck(deck);
  //      checkSorted(deck);
   //     Deck.shuffleDeck(deck);
        Deck.mergeSort(deck);
        checkSorted(deck);
       Deck.printDeck(deck);

       /**
        // check that findBisect finds each card
        int index;
        for (int i=0; i<52; i++) {
            index = Deck.findBisect(deck, deck.cards[i], 0,
                    deck.cards.length-1);
            if (index != i) {
                System.out.println("Not found!");
            }
        }

        // make a subdeck
        Deck hand = Deck.subdeck(deck, 8, 12);
        Deck.printDeck(hand);

        // check that findBisect doesn't find a card that's not there
        Card badCard = new Card(1, 1);
        index = Deck.findBisect(hand, badCard, 0, hand.cards.length-1);
        if (index != -1) {
            System.out.println("Found?");
        }

        // check mergeSort
        Deck.shuffleDeck(deck);
        deck = Deck.mergeSort(deck);
        checkSorted(deck);*/
    }

    /*
     * Checks that the deck is sorted.
     */
    public static void checkSorted(Deck deck) {
        for (int i=0; i<51; i++) {
            int flag = Card.compareCards(deck.cards[i], deck.cards[i+1]);
            if (flag != -1) {
                System.out.println("Not sorted!");
            }
       }
    }
}
