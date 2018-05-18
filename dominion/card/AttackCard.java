package dominion.card;
import java.util.*;
import dominion.*;

/**
 * Les cartes Attaque
 * Rmq: les cartes Attaque sont toutes des cartes Action
 */
public abstract class AttackCard extends ActionCard {

	public AttackCard(String name, int cost) {
		super(name, cost);
		// TODO Auto-generated constructor stub
	}
	
	public List<CardType> getTypes() {
		List<CardType> TypeList=super.getTypes();
		TypeList.add(CardType.Attack);
		return TypeList;
	}
}