package dominion.card.base;
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
		// String cardName = p.chooseCard("Choisissez le nom d'une carte de votre main à écarter : ", p.cardsInHand(), false);
		p.getGame().trashCard(this);
		p.getHand().remove(this);
		
		String cardName = p.chooseCard("Choisissez le nom d'une carte que vous souhaitez gagner : ", p.getGame().availableSupplyCards(), false);
		p.gain(cardName);
	}
}