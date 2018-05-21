package dominion.card.base;
import java.util.*;
import dominion.*;
import dominion.card.*;

/**
 * Carte Rénovation (Remodel)
 * 
 * Écartez une carte de votre main.
 * Recevez une carte coûtant jusqu'à 2 Pièces de plus que la carte écartée.
 */
public class Remodel extends ActionCard {

	public Remodel() {
		super("Remodel", 4);
	}

	@Override
	public void play(Player p) {
		
		if(!p.getHand().isEmpty()) {
			String cardName = p.chooseCard("Choisissez une carte de votre main à écarter : ", p.getHand(), false);
			int cost = p.getHand().getCard(cardName).getCost();
			p.getHand().remove(cardName);
			
			CardList availableSupplyCards = p.getGame().availableSupplyCards();
			CardList availableCards = new CardList();
			for(int i = 0; i < availableSupplyCards.size(); i++) {
				if(availableSupplyCards.get(i).getCost() <= cost+2) {
					availableCards.add(availableSupplyCards.get(i));
				}	
			}
			
			String cardNameTwo = p.chooseCard("Choisissez une carte à recevoir (dont son prix coûte jusqu'à 2 Pièces de plus que la carte écartée) :  ", availableCards, false);
			p.gain(p.getGame().removeFromSupply(cardNameTwo));
		}
		else {
			System.out.println("Vous n'avez pas de carte à écarter...");
		}
	}
}