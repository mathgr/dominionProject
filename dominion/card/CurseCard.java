package dominion.card;
import java.util.*;
import dominion.*;

/**
 * Les cartes Malédiction
 */
public abstract class CurseCard extends Card {

	public CurseCard(String name, int cost) {
		super(name, cost);
		// TODO Auto-generated constructor stub
	}
	
	public List<CardType> getTypes() {
		List<CardType> TypeList=super.getTypes();
		TypeList.add(CardType.Curse);
		return TypeList;
	}
}