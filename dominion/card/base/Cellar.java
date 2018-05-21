package dominion.card.base;
import java.util.*;
import dominion.*;
import dominion.card.*;

/**
 * Carte Cave (Cellar)
 * 
 * +1 Action.
 * Défaussez autant de cartes que vous voulez.
 * +1 Carte par carte défaussée.
 */
public class Cellar extends ActionCard {

	public Cellar() {
		super("Cellar", 2);
		// TODO Auto-generated constructor stub
	}

	@Override
	public void play(Player p) {
		//action +1
		p.incrementActions(1);
		
		//défaussez les cartes
		String cardName ="helloworld"; //attribution bidon pour rentrer dans la boucle
		
		while(!cardName.equals("")) {
			cardName=p.chooseCard("Choisissez une carte à défausser (Entrée pour passer) : ", p.cardsInHand(), true);
			if(!cardName.equals("")) {
				//si le joueur rentre un nom de carte, on l'enlève puis on lui 
				// en pioche une autre
				p.gain(p.getHand().remove(cardName));
				p.getHand().add(p.drawCard());
				p.getDraw().remove(0);
			}
		}
		
		
	}
}