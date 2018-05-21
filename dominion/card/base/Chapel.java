package dominion.card.base;
import java.util.*;
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
		int cptCards = 0; //compte le nombre de cartes écartées par le joueur

		
		while(!cardName.equals("") && cptCards <= 4 && p.getHand().size() > 0) {
			cardName = p.chooseCard("Choisissez une carte de votre main à écarter (Entrée pour passer) : ", p.getHand(), true);
			if(!cardName.equals("")) {
				p.getGame().trashCard(p.getHand().remove(cardName));
				cptCards++;
			}
		}
	}
}