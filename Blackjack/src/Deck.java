import java.util.ArrayList;
import java.util.Collections;
import java.util.Random;

public class Deck {
    private ArrayList<Card> deck;

    public Deck() {
        this.deck = new ArrayList<Card>();
    }

    public void createFullDeck() {
        // Generate cards
        for (Suits cardSuits : Suits.values()) {
            for (Values cardValues : Values.values()) {
                // Add new card to mix
                this.deck.add(new Card(cardSuits, cardValues));

            }
        }

    }


    public String toString() {
        String cardListOutput = "";
        for (Card aCard : this.deck) {
            cardListOutput += "\n" + aCard.toString();  // Loop that makes a string with all our values in it
        }
        return cardListOutput;
    }

    // Shuffle Deck
    public void shuffleDeck() {
        // Method to Shuffle Deck
        ArrayList<Card> tempDeck = new ArrayList<Card>();
        // Use Random instance to generate random number
        Random random = new Random();
        int randomCardIndex = 0;
        int originalSize = this.deck.size();

        for (int i = 0; i < originalSize; i++) {
            // Generate random card; ran.nextInt((max-min)+1) + min;
            randomCardIndex = random.nextInt((this.deck.size() - 1 - 0) + 1) + 0;
            // Add card to temporary deck
            tempDeck.add(this.deck.get(randomCardIndex));
            // Remove card from original deck
            this.deck.remove(randomCardIndex);
        }

        this.deck = tempDeck;
        Collections.shuffle(deck);
    }


    public Card getCard(int i) {
        return this.deck.get(i);
    }

    public void removeCard(int i) {
        this.deck.remove(i);
    }

    public void addCard(Card addCard) {
        this.deck.add(addCard);
    }

    // Get the size of the deck
    public int deckSize() {
        return this.deck.size();
    }

    // Draws from the deck
    public void draw(Deck comingFrom) {
        this.deck.add(comingFrom.getCard(0)); // 0 index to get the first card in array List (card on top of deck)
        comingFrom.removeCard(0);
    }

    // This will move cards back into the deck to continue playing
    public void moveAllToDeck(Deck moveTo) {
        int thisDeckSize = this.deck.size();

        // Put cards into moveTo deck
        for (int i = 0; i < thisDeckSize; i++) {
            moveTo.addCard(this.getCard(i));
        }

        // Empty out moveTo deck
        for (int i = 0; i < thisDeckSize; i++) {
            this.removeCard(0); // until no cards left

        }
    }

    // Return total value of cards in deck
    public int cardsValue() {
        int totalValue = 0;
        int aces = 0;

        // for loop checking each value of the card
        for (Card aCard : this.deck) {
            switch (aCard.getValue()) {
                case TWO:
                    totalValue += 2;
                    break;
                case THREE:
                    totalValue += 3;
                    break;
                case FOUR:
                    totalValue += 4;
                    break;
                case FIVE:
                    totalValue += 5;
                    break;
                case SIX:
                    totalValue += 6;
                    break;
                case SEVEN:
                    totalValue += 7;
                    break;
                case EIGHT:
                    totalValue += 8;
                    break;
                case NINE:
                    totalValue += 9;
                    break;
                case TEN:
                    totalValue += 10;
                    break;
                case JACK:
                    totalValue += 10;
                    break;
                case KING:
                    totalValue += 10;
                    break;
                case QUEEN:
                    totalValue += 10;
                    break;
                case ACE:
                    aces += 1;
                    break;
            }
        }
        // Total value of the aces for every ace that they have
        for (int i = 0; i < aces; i++) {
            if (totalValue > 10) {
                totalValue += 1;
            } else {
                totalValue += 11;
            }
        }
        return totalValue;
    }
}