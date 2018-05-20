package dominion.card.base;
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
		while(p.getDraw().size() != 0 && cptTreasureCards != 0) {
			System.out.println(p.getDraw().get(0).toString()); //on dévoile la carte
			if(p.getDraw().get(0).getTypes().contains(CardType.Treasure)) { //dans le cas où la première carte de la pioche du joueur est une carte de type Treasure
				cptTreasureCards++;
				p.transfer(p.getDraw(), p.getHand()); //ajoute la carte Treasure dans la main du joueur
			}
			else {
				p.transfer(p.getDraw(), p.getDiscard()); //défausse la carte dévoilée
			}
		}
	}
	
}