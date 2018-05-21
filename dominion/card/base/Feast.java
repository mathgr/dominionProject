package dominion.card.base;
import java.util.*;
import dominion.*;
import dominion.card.*;

/**
 * Carte Festin (Feast)
 * 
 * Écartez cette carte.
 * Recevez une carte coûtant jusqu'à 5 Pièces.
 */
public class Feast extends ActionCard {

	public Feast() {
		super("Feast", 4);
	}

	@Override
	public void play(Player p) {
		p.getGame().trashCard(this);
		p.getHand().remove(this);
		
		CardList availableCards = new CardList();
		for(Card c : p.getGame().availableSupplyCards()) {
			if(c.getCost() <=5) {
				availableCards.add(c);
			}
		}
		String cardName = p.chooseCard("Choisissez le nom d'une carte que vous souhaitez gagner : ", availableCards, false);
		p.gain(cardName);
	}
}