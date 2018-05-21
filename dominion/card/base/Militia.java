package dominion.card.base;
import java.util.*;
import dominion.*;
import dominion.card.*;

/**
 * Carte Milice (Militia)
 * 
 * 2 Pièces.
 * Tous vos adversaires défaussent leurs cartes de façon à n'avoir que 3 cartes en main.
 */
public class Militia extends AttackCard {

	public Militia() {
		super("Militia", 4);
	}

	@Override
	public void play(Player p) {
		p.incrementMoney(2);
		String cardNameToDiscard;
		for(Player pl : p.otherPlayers()) {
			System.out.println("Au joueur " + pl.getName());
			while(pl.getHand().size() > 3) {
				cardNameToDiscard = pl.chooseCard("Choisissez une carte de votre main à défausser : ", pl.getHand(), false);
				pl.gain(pl.getHand().remove(cardNameToDiscard));
			}
		}
	}
}