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
		List<Player> otherPlayers = p.getGame().otherPlayers(p);
		for(int i = 0; i < otherPlayers.size(); i++) {
			otherPlayers.get(i).drawCard();
		}
	}
	
}