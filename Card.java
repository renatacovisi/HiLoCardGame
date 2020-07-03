
public class Card {

	//Rank and suit of the card.
	int rank;
	int suit;

	public Card() {
	}//Card()

	public Card(int r, int s){
		rank = r;
		suit = s;
	}//Card()

	public boolean rankIsLessThan(Card c){
		return rank < c.getRank();
	}//rankIsLessThan()

	public boolean rankIsGreaterThan(Card c){
		return rank > c.getRank();
	}//rankIsGreaterThan()

	public boolean rankIsEqualTo(Card c){
		return rank == c.getRank();
	}//rankIsEqualTo()

	public String toString(){

		String cardSuit = "";
		String cardRank = "";
		String cardString;

		switch (suit){
		case 0:
			cardSuit = "hearts";
			break;
		case 1:
			cardSuit = "diamonds";
			break;
		case 2:
			cardSuit = "clubs";
			break;
		case 3:
			cardSuit = "spades";
			break;
		default:
			cardSuit = "n/a";
			break;
		}//switch suit

		switch(rank){
		case 0:
			cardRank = "ace";
			break;
		case 1:
			cardRank = "2";
			break;
		case 2:
			cardRank = "3";
			break;
		case 3:
			cardRank = "4";
			break;
		case 4:
			cardRank = "5";
			break;
		case 5:
			cardRank = "6";
			break;
		case 6:
			cardRank = "7";
			break;
		case 7:
			cardRank = "8";
			break;
		case 8:
			cardRank = "9";
			break;
		case 9:
			cardRank = "10";
			break;
		case 10:
			cardRank = "jack";
			break;
		case 11:
			cardRank = "queen";
			break;
		case 12:
			cardRank = "king";
			break;
		default:
			cardRank = "n/a " + suit;
			break;
		}//switch rank

		cardString = cardRank + "_of_" + cardSuit;

		return cardString;
	}//toString()

	public int getRank(){
		return rank;
	}//getRank()

	public int getSuit(){
		return suit;		
	}//getSuit()
}//class

