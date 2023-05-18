import java.util.Scanner;
public class Game {

    public static void main(String[] args) {

        System.out.println("Welcome to Blackjack!");

        // Create the playing deck
        Deck playingDeck = new Deck();
        playingDeck.createFullDeck();
        playingDeck.shuffleDeck();

//        System.out.println(playingDeck);



        // Create hands for the player and the dealer - hands are created from methods that are made in the deck class
        Deck playerHand = new Deck();
        Deck dealerHand = new Deck();

        double playerMoney = 100.00;
        Scanner userInput = new Scanner(System.in);


        // Game loops
        while(playerMoney > 0) {
            // Continue Betting
            // Take in Players bet
            System.out.println("You have $" + playerMoney + " how much would you like to bet?");
            double playerBet = userInput.nextDouble();
            if(playerBet > playerMoney) {
                System.out.println("The amount you're betting is more than what you currently have!");
                break;
            }

            // Boolean to know when round is ended and goes to next round
            boolean endRound = false;

            // Start Dealing - Game Logic

            // Player draws 2 cards
            playerHand.draw(playingDeck);
            playerHand.draw(playingDeck);

            // Dealer draws 2 cards
            dealerHand.draw(playingDeck);
            dealerHand.draw(playingDeck);

            while(true) {
                System.out.println("Your hand:");
                System.out.println(playerHand.toString());
                // Display your hand
                System.out.println("Your hand is valued at: " + playerHand.cardsValue());

                // Display dealer's hand
                System.out.println("Dealer's hand: " + dealerHand.getCard(0).toString() + " and [Covered card]");

                // The player's decision whether to hit or stand
                System.out.println("would you like to (1) Hit or (2) Stand?");
                int response = userInput.nextInt();

                // If they Hit
                if(response == 1) {
                    playerHand.draw(playingDeck);
                    System.out.println("You draw a:" + playerHand.getCard(playerHand.deckSize()-1).toString()); // "-1" to get proper index 0 base(deck size) vs 1 base(player hand)

                    // If they Bust
                    if(playerHand.cardsValue() > 21) {
                        System.out.println("Bust! Currently valued at: " + playerHand.cardsValue());

                        // Amount of money they lose
                        playerMoney = playerMoney - playerBet;

                        // Boolean to know when round is ended and goes to next round
                        endRound = true;
                        break;
                    }
                }

                // If they Stand
                if(response==2) {
                    break;

                }

            }

            // Reveal Dealer's cards
            System.out.println("Dealer card's: " + dealerHand.toString());

            // Check to see if dealer's hand has more points than the player's hand && end round
            if((dealerHand.cardsValue() > playerHand.cardsValue()) && endRound == false) {
                System.out.println("Dealer Wins!");
                playerMoney = playerMoney - playerBet;
                endRound = true;

            }

            // Display Total Value for Dealer
            System.out.println("Dealer's hand is values at: " + dealerHand.cardsValue());

            // Determine if Dealer Busted
            if((dealerHand.cardsValue() > 21) && endRound == false) {
                System.out.println("Dealer busts! The Player wins!");
                playerMoney += playerBet;
                endRound = true;
            }

            // Determine if Push
            if((playerHand.cardsValue() == dealerHand.cardsValue()) && endRound == false) {
                System.out.println("Push");
                endRound = true;
            }

            if ((playerHand.cardsValue() > dealerHand.cardsValue()) && endRound == false) {
                System.out.println("The Player Wins the hand!");
                playerMoney += playerBet;
                endRound = true;
            }

            else if (endRound == false) {
                System.out.println("You lost the hand.");
                playerMoney -= playerBet;
                endRound = true;
            }


            // Dealer draws at 16, stand at 17
            while ((dealerHand.cardsValue() < 17) && endRound == false) {
                dealerHand.draw(playingDeck);
                System.out.println("Dealer Draws: " + dealerHand.getCard(dealerHand.deckSize()-1).toString());

            }

            // Move everything back to playing deck (top of the deck, beginning of the arrayList)
            playerHand.moveAllToDeck(playingDeck);
            dealerHand.moveAllToDeck(playingDeck);
            System.out.println("End of hand.");


        }

        System.out.println("Game over! You have run out of money! :'( Try again next time!  ");

    }
}