package dominion.card.base;
import java.util.*;
import dominion.*;
import dominion.card.*;

/**
 * Carte Salle du trône (Throne Room)
 * 
 * Choisissez 1 carte Action de votre main.
 * Jouez-la deux fois.
 */
public class ThroneRoom extends ActionCard {

	public ThroneRoom() {
		super("Throne Room", 4);
	}

	@Override
	public void play(Player p) {
		String cardName = p.chooseCard("Choisissez une carte action de votre main à jouer 2 fois : ", p.getActionCards(), false);
		p.playCard(cardName);
		p.getHand().add(p.getInPlay().remove(cardName));
		p.playCard(cardName);
	}
}