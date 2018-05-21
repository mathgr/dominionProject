package dominion.card.base;
import java.util.*;
import dominion.*;
import dominion.card.*;

/**
 * Carte Bureaucrate (Bureaucrat)
 * 
 * Recevez une carte Argent; placez-la sur votre deck.
 * Tous vos adversaires dévoilent une carte Victoire et la placent sur leur deck (sinon ils dévoilent leur main afin que vous puissiez voir qu'ils n'ont pas de cartes Victoire).
 */
public class Bureaucrat extends AttackCard {

	public Bureaucrat() {
		super("Bureaucrat", 4);
	}

	@Override
	public void play(Player p) {
		p.getDraw().add(p.getGame().removeFromSupply("Silver"));
		String cardNameToReveal;
		for(Player pl : p.getGame().otherPlayers(p)) {
			if(!this.aUneCarteMoat(pl)) {
				if(!pl.getVictoryCards().isEmpty()) {
					cardNameToReveal = pl.chooseCard("Le joueur " + pl.getName() + " doit jouer." + "Saisissez le nom d'une carte victoire de votre main à dévoiler : ", pl.getVictoryCards(), false);
					System.out.println("Carte dévoilée : " + cardNameToReveal);
					pl.getDraw().add(pl.getHand().remove(cardNameToReveal));
				}
				else {
					System.out.println("Le joueur " + pl.getName() + " n'a pas de carte Victoire, il dévoile toute sa main...");
					for(Card c : pl.getHand()) {
						System.out.println("Carte dévoilée : " + c.getName());
					}
				}
			}
		}
		
	}
}