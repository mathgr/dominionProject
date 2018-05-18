import java.util.*;
import dominion.*;
import dominion.card.*;
import dominion.card.base.*;

/**
 * Classe pour l'exÃ©cution d'une partie de Dominion
 */
class Main {
	public static void main(String[] args) {
		// Noms des joueurs de la partie
		// (le nombre total de joueurs correspond au nombre de noms dans le 
		// tableau)
		String[] playerNames = new String[]{"Marco", "Polo"};
		// Prépare les piles "royaume" de la réserve (hors cartes communes)
		List<CardList> kingdomStacks = new ArrayList<CardList>();
		CardList stackVillage;
		// Ajouter un bloc d'instructions pour chaque carte royaume Ã  utiliser
		
		stackVillage = new CardList();
		for (int i = 0; i < 10; i++) {
			stackVillage.add(new Village());
		}
		
		CardList stackCellar;
		stackCellar = new CardList();
		for (int i = 0; i < 10; i++) {
			stackCellar.add(new Cellar());
		}
		CardList stackMarket;
		stackMarket = new CardList();
		for (int i = 0; i < 10; i++) {
			stackMarket.add(new Market());
		}
		CardList stackMilitia;
		stackMilitia = new CardList();
		for (int i = 0; i < 10; i++) {
			stackMilitia.add(new Militia());
		}
		CardList stackMine;
		stackMine = new CardList();
		for (int i = 0; i < 10; i++) {
			stackMine.add(new Mine());
		}
		CardList stackMoat;
		stackMoat = new CardList();
		for (int i = 0; i < 10; i++) {
			stackMoat.add(new Moat());
		}
		CardList stackRemodel;
		stackRemodel = new CardList();
		for (int i = 0; i < 10; i++) {
			stackRemodel.add(new Remodel());
		}
		CardList stackSmithy;
		stackSmithy = new CardList();
		for (int i = 0; i < 10; i++) {
			stackSmithy.add(new Smithy());
		}
		CardList stackWoodcutter;
		stackWoodcutter = new CardList();
		for (int i = 0; i < 10; i++) {
			stackWoodcutter.add(new Woodcutter());
		}
		CardList stackWorkshop;
		stackWorkshop = new CardList();
		for (int i = 0; i < 10; i++) {
			stackWorkshop.add(new Workshop());
		}
		
		kingdomStacks.add(stackVillage);
		kingdomStacks.add(stackCellar);
		kingdomStacks.add(stackMarket);
		kingdomStacks.add(stackMilitia);
		kingdomStacks.add(stackMine);
		kingdomStacks.add(stackMoat);
		kingdomStacks.add(stackRemodel);
		kingdomStacks.add(stackSmithy);
		kingdomStacks.add(stackWoodcutter);
		kingdomStacks.add(stackWorkshop);
		
		
		// Instancie et exÃ©cute une partie
		Game g = new Game(playerNames, kingdomStacks);
		g.run();
	}
}