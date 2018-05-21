package dominion.card.base;
import java.util.*;
import dominion.*;
import dominion.card.*;

/**
 * Carte Espion (Spy)
 * 
 * +1 Carte.
 * +1 Action.
 * Tous les joueurs (vous aussi) dévoilent la première carte de leur deck. Vous décidez ensuite si chaque carte dévoilée est défaussée ou replacée sur son deck.
 */
public class Spy extends AttackCard {

	public Spy() {
		super("Spy", 4);
	}

	@Override
	
	public void play(Player p) {
		p.getHand().add(p.drawCard());
		p.incrementActions(1);
		
		Player pl;
		Card carte;
		String choice;
		
		for(int i = 0; i < p.getGame().numberOfPlayers(); i++) {
			pl = p.getGame().getPlayer(i);
			carte = pl.drawCard();
			if(carte != null) {
				if(pl.getHand().getCard("Moat") == null){
					choice = p.choose("Défausser (y) ou remettre cette carte dans le deck (n)?", new ArrayList<String>(Arrays.asList("y", "n")), false);
				
					if(choice .equalsIgnoreCase("y")) {
						pl.gain(carte);
					}else if(choice.equalsIgnoreCase("n")) {
						pl.getDraw().add(carte);
					}
				}
			}
		}
	}
	
}