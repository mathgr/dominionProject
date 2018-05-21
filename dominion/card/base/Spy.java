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
		
		String choice;
		if(p.getDraw().get(0).toString()!=null) {
			System.out.println("Le joueur " + p.getName() + " dévoile la première carte de son deck : " + p.getDraw().get(0).toString());
			System.out.println("Voulez-vous la défausser (sinon elle est replacée sur votre deck) : (y/n)");
			choice = p.getGame().readLine();
			if(choice.equals("y")) {
				p.getDiscard().add(p.getDraw().get(0));
			}
		}
		
		for(Player pl : p.otherPlayers()) {
			if(pl.getDraw().get(0).toString()!=null) {
				System.out.println("Le joueur " + pl.getName() + " dévoile la première carte de son deck : " + pl.getDraw().get(0).toString());
				System.out.println("Voulez-vous la défausser (sinon elle est replacée sur votre deck) : (y/n)");
				choice = pl.getGame().readLine();
	
				if(choice.equals("y")) {
					pl.getDiscard().add(pl.getDraw().get(0));
				}
			}
		}
		
	}
	
}