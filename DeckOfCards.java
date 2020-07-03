import java.util.ArrayList;
import java.util.Collections;

public class DeckOfCards {
	
	public ArrayList<Card> deck;

	public DeckOfCards() {
		deck = new ArrayList<Card>();
		int cs = 0;
		int cr = 0;
		for (int i = 0; i < 52; i++) {
			deck.add(new Card(cr, cs));
			cs = (cs + 1) % 4;
			cr = (cr + 1) % 13;
		}
		shuffle();
	}//DeckofCards()
	
	public String toString() {
		String string = "";
		for (int i = 0; i < deck.size(); i++) {
			string += deck.get(i) + "\n";
		}
		return string;
	}//toString()
	
	public Card dealTopCard() {		
		return deck.remove(0);
	}//dealTopCard()
	
	public boolean isEmpty() {
		return deck.isEmpty();
	}//isEmpty()
	
	public void shuffle() {
		Collections.shuffle(deck);
	}//shuffle
}//class
