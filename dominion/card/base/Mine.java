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
			p.getGame().trashCard(p.getHand().remove(cardName));
			
			CardList availableSupplyCards = p.getGame().availableSupplyCards();
			CardList availableTreasureCards = new CardList();
			for(int i = 0; i < availableSupplyCards.size() ; i++) { //on sélectionne chaque carte Treasure de la pile coûtant jusqu'à 3 Pièces de plus que la carte qu'il a écarté 
				if(availableSupplyCards.get(i).getTypes().contains(CardType.Treasure) && availableSupplyCards.get(i).getCost() <= cost + 3) {
					availableTreasureCards.add(availableSupplyCards.get(i));
				}
			}
			
			cardName = p.chooseCard("Choisissez le nom d'une Treasure à recevoir : ", availableTreasureCards, false);
			p.getHand().add(p.getGame().removeFromSupply(cardName));
		}
		else {
			System.out.println("Vous n'avez pas de carte Treasure à écarter...");
		}
		
	}
}