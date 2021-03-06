package dominion.card.base;
import java.util.*;
import dominion.*;
import dominion.card.*;

/**
 * Carte Douves (Moat)
 * 
 * +2 Cartes.
 * Lorsqu’un adversaire joue une carte Attaque, vous pouvez dévoiler cette carte de votre main. Dans ce cas, l’Attaque n’a pas d’effet sur vous.
 */
public class Moat extends ReactionCard {

	public Moat() {
		super("Moat", 2);
		// TODO Auto-generated constructor stub
	}

	@Override
	public void play(Player p) {
		for(int i = 0; i < 2; i++) {
			p.getHand().add(p.drawCard());
		}
		
	}
	
	public boolean devoileCarte(Player p){
		String answer = p.choose("Voulez vous jouer votre carte Moat?", new ArrayList<String>(Arrays.asList("y", "n")), false);
		if(answer.equalsIgnoreCase("y")) return true;
		else return false;
	}
}