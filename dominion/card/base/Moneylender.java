package dominion.card.base;
import java.util.*;
import dominion.*;
import dominion.card.*;

/**
 * Carte Prêteur sur gages (Moneylender)
 * 
 * Écartez une carte Cuivre de votre main.
 * Dans ce cas, +3 Pièces.
 */
public class Moneylender extends ActionCard {

	public Moneylender() {
		super("Moneylender", 4);
	}

	@Override
	public void play(Player p) {
		if(p.getHand().getCard("Copper") != null) {
			p.getGame().trashCard(p.getHand().remove("Copper"));
			p.incrementMoney(3);
		}
	}
}