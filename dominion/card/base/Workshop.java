package dominion.card.base;
import dominion.*;
import dominion.card.*;

/**
 * Carte Atelier (Workshop)
 * 
 * Recevez une carte coûtant jusqu'à 4 Pièces.
 */
public class Workshop extends ActionCard {

	public Workshop() {
		super("Workshop", 3);
	}

	@Override
	public void play(Player p) {
		
		CardList availableCards = new CardList();
		for(int i = 0; i < p.getGame().availableSupplyCards().size(); i++) {
			if(p.getGame().availableSupplyCards().get(i).getCost() <= 4) {
				availableCards.add(p.getGame().availableSupplyCards().get(i));
			}
		}
		String cardName = p.chooseCard("Choisissez une carte à recevoir (coûtant jusqu'à 4 Pièces) : ", availableCards, false);
		p.gain(p.getGame().removeFromSupply(cardName)); //carte à mettre dans la défausse ?
	}
	
}