package dominion.card.base;
import dominion.*;
import dominion.card.*;

/**
 * Carte Jardins (Gardens)
 * 
 * Vaut 1VP pour chaque 10 cartes dans votre deck (arrondi à l'unité inférieure).
 */
public class Gardens extends VictoryCard {

	public Gardens() {
		super("Gardens", 4);
		// TODO Auto-generated constructor stub
	}
	
	public int victoryValue(Player p) {
		int nbCards = p.totalCards().size();
		return nbCards / 10;
	}

	@Override
	public void play(Player p) {
		// carte de victoire, donc juste comptabilisée à la fin de la partie
	}
	
}