package dominion.card;
import java.util.*;
import dominion.*;

/**
 * Les cartes Réaction
 * Rmq: les cartes Réaction sont toutes des cartes Action
 */
public abstract class ReactionCard extends ActionCard {

	public ReactionCard(String name, int cost) {
		super(name, cost);
		// TODO Auto-generated constructor stub
	}
	
	public List<CardType> getTypes() {
		List<CardType> TypeList=super.getTypes();
		TypeList.add(CardType.Reaction);
		return TypeList;
	}
}