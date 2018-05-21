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
		CardList treasureCards = new CardList();
		Card c;
		
		while((c=p.drawCard()) != null && cptTreasureCards < 2) {
			
			System.out.println("Carte dévoilée : "+c.toString()); //on dévoile la carte piochée
			if(c.getTypes().contains(CardType.Treasure)) { //dans le cas où la  carte piochée est une carte de type Treasure
				cptTreasureCards++;
				treasureCards.add(c);
			}
			else {
				revealedCards.add(c); //défausse la carte dévoilée dans un liste de carte intermédiaire : évite de la remettre dans la défausse pour éviter de faire une boucle infinie (si le joueur n'a qu'une carte de type Treasure
			}
			c = p.drawCard();
		}
		
		
		p.getDiscard().addAll(revealedCards);//défaussage des cartes révélées
		p.getHand().addAll(treasureCards);//ajout des cartes trésor dans la main du joueur
		
	}

	
}