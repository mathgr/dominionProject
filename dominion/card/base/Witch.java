package dominion.card.base;
import java.util.*;
import dominion.*;
import dominion.card.*;

/**
 * Carte Sorcière (Witch)
 * 
 * +2 Cartes.
 * Tous vos adversaires recoivent une carte Curse.
 */
public class Witch extends AttackCard {

	public Witch() {
		super("Witch", 5);
	}

	@Override
	public void play(Player p) {

		for(int i = 0; i < 2; i++) {
			p.getHand().add(p.drawCard());
		}
		
		for(Player pl : p.getGame().otherPlayers(p)) {
			if(pl.getGame().getFromSupply("Curse") != null) {
				pl.gain("Curse");
			}
		}
	}
}