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
		p.getDraw().remove(0);
		
		p.incrementActions(1);
		
		String choice;
		
		System.out.println("Le joueur " + p.getName() + " dévoile la première carte de son deck : " + p.drawCard().toString());
		System.out.println("Voulez-vous la défausser (sinon elle est replacée sur votre deck) : (y/n)");
		choice = p.getGame().readLine();
		if(choice.equals("y")) {
			p.gain(p.drawCard());
			p.getDraw().remove(p.drawCard());
		}
		
		for(Player pl : p.getGame().otherPlayers(p)) {
			System.out.println("Le joueur " + pl.getName() + " dévoile la première carte de son deck : " + pl.drawCard().toString());
			System.out.println("Voulez-vous la défausser (sinon elle est replacée sur votre deck) : (y/n)");
			choice = pl.getGame().readLine();
			if(choice.equals("y")) {
				pl.gain(pl.drawCard());
				pl.getDraw().remove(pl.drawCard());
			}
		}
		
	}
	
}