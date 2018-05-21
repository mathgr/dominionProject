package dominion.card.base;
import java.util.*;
import dominion.*;
import dominion.card.*;

/**
 * Carte Voleur (Thief)
 * 
 * Tous vos adversaires dévoilent les 2 premières cartes de leur deck. S'ils dévoilent des cartes Trésor, ils en écartent 1 de votre choix. Parmi ces cartes Trésor écartées, recevez celles de votre choix. Les autres cartes dévoilées sont défaussées.
 */
public class Thief extends AttackCard {

	public Thief() {
		super("Thief", 4);
	}

	@Override
	public void play(Player p) {
		CardList trashedCards = new CardList();
		for(Player pl : p.getGame().otherPlayers(p)) {
			System.out.println("Au joueur " + pl.getName() + " de dévoiler les 2 premières cartes de son deck...");
			
		}
		
	}
	
}