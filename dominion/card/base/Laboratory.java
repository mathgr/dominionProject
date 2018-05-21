package dominion.card.base;
import dominion.*;
import dominion.card.*;

/**
 * Carte Laboratoire (Laboratory)
 * 
 * +2 Cartes.
 * +1 Action.
 */
public class Laboratory extends ActionCard {

	public Laboratory() {
		super("Laboratory", 5);
	}

	@Override
	public void play(Player p) {
		for(int i = 0; i < 2; i++ ) {
			p.getHand().add(p.drawCard()); //ajoute à la main du joueur une carte piochée
		}
		p.incrementActions(1);
	}
}