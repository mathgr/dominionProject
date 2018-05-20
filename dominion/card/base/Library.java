package dominion.card.base;
import dominion.*;
import dominion.card.*;

/**
 * Carte Bibliothèque (Library)
 * 
 * Piochez jusqu'à ce que vous ayez 7 cartes en main. Chaque carte Action piochée peut être mise de côté. Défaussez les cartes mises de côté lorsque vous avez terminé de piocher.
 */
public class Library extends ActionCard {

	public Library() {
		super("Library", 5);
	}

	@Override
	public void play(Player p) {
		String s;
		CardList cl = new CardList(); //liste des cartes mises de côté
		while(p.getHand().size() < 7) {
			if(p.drawCard().getTypes().contains(CardType.Action)) {
				System.out.println("Voulez-vous mettre de côté cette carte action : (y/n)");
				s = p.getGame().readLine();
				if(s.equals("y")) {
					cl.add(p.drawCard());
					p.getDraw().remove(0);
				}
				else {
					p.getHand().add(p.drawCard());
					p.getDraw().remove(0);
				}
			}
			else {
				p.getHand().add(p.drawCard());
				p.getDraw().remove(0);
			}
		}
		for(int i = 0; i < cl.size(); i++) {
			p.transfer(cl, p.getDiscard());
		}
		
	}
}