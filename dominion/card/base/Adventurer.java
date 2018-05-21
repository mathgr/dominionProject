package dominion.card.base;
import java.util.*;
import dominion.*;
import dominion.card.*;

/**
 * Carte Aventurier (Adventurer)
 * 
 * Dévoilez des cartes de votre deck jusqu'à ce que 2 cartes Trésor soient dévoilées. Ajoutez ces cartes Trésor à votre main et défaussez les autres cartes dévoilées.
 */
public class Adventurer extends ActionCard {

	public Adventurer() {
		super("Adventurer", 6);
	}

	@Override
	public void play(Player p) {
		int cptTreasureCards = 0;
		CardList revealedCards = new CardList();
		while(p.getDraw().size() != 0 && cptTreasureCards != 0) {
			System.out.println(p.getDraw().get(0).toString()); //on dévoile la première carte de la pioche
			if(p.getDraw().get(0).getTypes().contains(CardType.Treasure)) { //dans le cas où la  carte piochée est une carte de type Treasure
				cptTreasureCards++;
				p.transfer(p.getDraw(), p.getHand()); //ajoute la carte Treasure dans la main du joueur
			}
			else {
				p.transfer(p.getDraw(), revealedCards); //défausse la carte dévoilée dans un liste de carte intermédiaire : évite de la remettre dans la défausse pour éviter de faire une boucle infinie (si le joueur n'a qu'une carte de type Treasure
			}
		}
		for(Card c : revealedCards) { //défausse en fin de tour les cartes dévoilées
			p.transfer(revealedCards, p.getDiscard());
		}
	}
	
}