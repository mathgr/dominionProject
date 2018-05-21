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
			if(!pl.getVictoryCards().isEmpty()) {
				cardNameToReveal = pl.chooseCard("Le joueur " + pl.getName() + " doit jouer." + "Saisissez le nom d'une carte victoire de votre main à dévoiler : ", pl.getVictoryCards(), false);
				System.out.println("Carte dévoilée : " + cardNameToReveal);
			}
		}
		
	}
}