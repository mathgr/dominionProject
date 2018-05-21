package dominion.card;
import java.util.*;

import dominion.*;
import dominion.card.base.Moat;

/**
 * Les cartes Attaque
 * Rmq: les cartes Attaque sont toutes des cartes Action
 */
public abstract class AttackCard extends ActionCard {

	public AttackCard(String name, int cost) {
		super(name, cost);
		// TODO Auto-generated constructor stub
	}
	
	public boolean aUneCarteMoat(Player p){
		Card moat;	
		if(p.getHand().getCard("Moat")!=null) {
			moat = p.getHand().getCard("Moat");
			return ((Moat)moat).devoileCarte(p);
		}
		return false;
	}
	
	public List<CardType> getTypes() {
		List<CardType> TypeList=super.getTypes();
		TypeList.add(CardType.Attack);
		return TypeList;
	}
}