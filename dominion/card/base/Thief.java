package dominion.card.base;
import java.util.*;
import dominion.*;
import dominion.card.*;

/**
 * Carte Voleur (Thief)
 * 
 * Tous vos adversaires dévoilent les 2 premières cartes de leur deck. S'ils dévoilent des cartes Trésor, ils en écartent 1 de votre choix. Parmi ces cartes Trésor écartées, recevez celles de votre choix. Les autres cartes dévoilées sont défaussées.
 */
public class Thief extends AttackCard {

	public Thief() {
		super("Thief", 4);
	}

	@Override
	public void play(Player p) {
		String cardTrashed, cardChoosen;
		cardChoosen = "in";
		Card cardOne, cardTwo;
		CardList revealCards = new CardList();
		CardList treasureCards = new CardList();
		CardList trashedCards = new CardList();
		
		for(Player pl : p.getGame().otherPlayers(p)) {
			System.out.println("Au joueur " + pl.getName() + " de dévoiler les 2 premières cartes de son deck...");
			cardOne = pl.drawCard();
			cardTwo = pl.drawCard();
			
			if(cardOne == null && cardTwo == null) {
				System.out.println("Vous n'avez pas de cartes dans votre deck");
			}
			else if(cardOne != null && cardTwo == null) {
				System.out.println("Vous avez une carte dans votre deck");
				if(!cardOne.getTypes().contains(CardType.Treasure)) {
					revealCards.add(cardOne);
				}
				else {
					trashedCards.add(cardOne);
				}
			}
			else {
				System.out.println("Vous avez au moins deux cartes dans votre deck : " + cardOne.toString() + ", " + cardTwo.toString());
				revealCards.add(cardOne);
				revealCards.add(cardTwo);
				
				if(cardOne.getTypes().contains(CardType.Treasure)) {
					treasureCards.add(cardOne);
				}
				if(cardTwo.getTypes().contains(CardType.Treasure)) {
					treasureCards.add(cardTwo);
				}
				
				if(!treasureCards.isEmpty()) {
					cardTrashed = p.chooseCard("Choisir la carte à écarter : ", treasureCards, false);
					trashedCards.add(treasureCards.remove(cardTrashed));
					revealCards.remove(cardTrashed);
				}
			}
			pl.getDiscard().addAll(revealCards);
			revealCards.clear();
			treasureCards.clear();
		}
		while(!cardChoosen.equals("")) {
			cardChoosen = p.chooseCard("Choisissez la carte que vous voulez gagner (Entrée pour passer) : ", trashedCards, true);
			p.gain(trashedCards.remove(cardChoosen));
		}
		
	}
	
}