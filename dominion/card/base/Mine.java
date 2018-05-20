package dominion.card.base;
import java.util.*;
import dominion.*;
import dominion.card.*;

/**
 * Carte Mine
 * 
 * Écartez une carte Trésor de votre main. Recevez une carte Trésor coûtant jusqu'à 3 Pièces de plus ; ajoutez cette carte à votre main.
 */
public class Mine extends ActionCard {

	public Mine() {
		super("Mine", 5);
	}

	@Override
	public void play(Player p) {
		if(!p.getTreasureCards().isEmpty()) {
			String cardName = p.chooseCard("Choisissez une carte Treasure de votre main à écarter : ", p.getTreasureCards(), false);
			int cost = p.getHand().getCard(cardName).getCost();
			p.getGame().trashCard(p.getHand().getCard(cardName));
			
			
			for(int i = 0; i < //de où on prend les cartes ? ; i++) {
				
			}
		}

		
	}
}