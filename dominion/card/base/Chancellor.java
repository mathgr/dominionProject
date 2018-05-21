package dominion.card.base;
import java.util.*;
import dominion.*;
import dominion.card.*;

/**
 * Carte Chancellier (Chancellor)
 * 
 * +2 Pièces.
 * Vous pouvez immédiatement défausser votre deck.
 */
public class Chancellor extends ActionCard {

	public Chancellor() {
		super("Chancellor", 3);
	}

	@Override
	public void play(Player p) {
		//+2 Pièces.
		p.incrementMoney(2);
		
		System.out.println("Voulez-vous défausser votre deck ? (y/n) ");
		String answer = p.getGame().readLine();
		if(answer.equalsIgnoreCase("y")) {
			while(!p.getDraw().isEmpty()) { //pour chaque carte de la pioche du joueur
				p.gain(p.getDraw().remove(0)); //On ajoute directement dans la défausse les
												//cartes qu'on prend dans la pioche du joueur
			}
		}
	}
}