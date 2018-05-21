package dominion.card.base;
import java.util.*;
import dominion.*;
import dominion.card.*;

/**
 * Carte Chambre du conseil (Council Room)
 * 
 * +4 Cartes.
 * +1 Achat.
 * Tous vos adversaires piochent 1 carte.
 */
public class CouncilRoom extends ActionCard {

	public CouncilRoom() {
		super("CouncilRoom", 5);
	}

	@Override
	public void play(Player p) {
	
		p.incrementBuys(1);
		
		for(int i = 0; i < 4; i++) {
			p.getHand().add(p.drawCard());
			System.out.println("carte n°"+i+"piochée");
		}

		for(Player pl : p.otherPlayers()) {
			pl.getHand().add(pl.drawCard());
		}
	}
	
}