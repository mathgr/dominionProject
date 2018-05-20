package dominion.card.base;
import dominion.*;
import dominion.card.*;

/**
 * Carte Chapelle (Chapel)
 * 
 * Écartez jusqu'à 4 cartes de votre main.
 */
public class Chapel extends ActionCard {

	public Chapel() {
		super("Chapel", 2);
	}

	@Override
	public void play(Player p) {
		String cardName = "in"; //pour rentrer dans la boucle
		int cptCCards = 0; //compte le nombre de cartes Chapel dans la main du joueur
		
		for(Card c: p.getHand()) {
			if(c.getName().equals("Chapel")) {
				cptCCards++;
			}
		}
		
		while(!cardName.equals("")) {
			cardName = p.chooseCard("Choisissez une carte de votre main à écarter (Entrée pour passer) : ", p.getHand(), true);
			if(!cardName.equals("")) {
				if(!cardName.equals("Chapel") || cptCCards > 1 ) {
					p.getGame().trashCard(p.getHand().remove(cardName));
				}
			}
		}
	}
}