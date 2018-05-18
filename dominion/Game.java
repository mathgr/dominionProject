package dominion;
import java.util.*;
import dominion.card.*;
import dominion.card.common.*;

/**
 * Class repr�sentant une partie de Dominion
 */
public class Game {
	/**
	 * Tableau contenant les joueurs de la partie
	 */
	private Player[] players;
	
	/**
	 * Index du joueur dont c'est actuellement le tour
	 */
	private int currentPlayerIndex;
	
	/**
	 * Liste des piles dans la r�serve du jeu.
	 * 
	 * On suppose ici que toutes les listes contiennent des copies de la m�me
	 * carte.
	 * Ces piles peuvent �tre vides en cours de partie si toutes les cartes de 
	 * la pile ont �t� achet�es ou gagn�es par les joueurs.
	 */
	private List<CardList> supplyStacks;
	
	/**
	 * Liste des cartes qui ont �t� �cart�es (trash)
	 */
	private CardList trashedCards;
	
	/**
	 * Scanner permettant de lire les entr�es au clavier
	 */
	private Scanner scanner;
	
	/**
	 * Constructeur
	 * 
	 * @param playerNames liste des noms des joueurs qui participent � la 
	 * partie. Le constructeur doit cr�er les objets correspondant aux joueurs
	 * @param kingdomStacks liste de piles de r�serve � utiliser correspondant 
	 * aux cartes "royaume" � utiliser dans la partie, auxquelles le 
	 * constructeur doit ajouter les piles "communes":
	 * - 60 Copper
	 * - 40 Silver
	 * - 30 Gold
	 * - 8 (si 2 joueurs) ou 12 (si 3 ou 4 joueurs) Estate, Duchy et Province 	 
	 * * - 10 * (n-1) Curse où n est le nombre de joueurs dans la partie
	 */
	public Game(String[] playerNames, List<CardList> kingdomStacks) {
		//ajout des joueurs dans players
		int i=0;
		while(i<playerNames.length) {
			this.players[i]=new Player(playerNames[i],this);
		}

		
		//cr�ation des piles de cartes communes
		CardList copperStack=new CardList();
		CardList silverStack=new CardList();
		CardList goldStack=new CardList();
		
		//Création des piles de cartes spéciales
		CardList estateStack=new CardList();
		CardList duchyStack=new CardList();
		CardList provinceStack=new CardList();
		CardList curseStack=new CardList();
		
		//Ajout des cartes dans les piles correspondantes
		for(i=0;i<59;i++) {
			copperStack.add(new Copper());
		}
		for(i=0;i<39;i++) {
			silverStack.add(new Silver());
		}
		for(i=0;i<29;i++) {
			goldStack.add(new Gold());
		}
		for(i=0;i<10*(playerNames.length-1);i++) {
			curseStack.add(new Curse());
		}
		
		//cartes Victoires
		if(playerNames.length==2) {
			for(i=0;i<8;i++) {
				estateStack.add(new Estate());
				duchyStack.add(new Duchy());
				provinceStack.add(new Province());
			}
		}
		else if(playerNames.length==3 || playerNames.length==4) {
			for(i=0;i<12;i++) {
				estateStack.add(new Estate());
				duchyStack.add(new Duchy());
				provinceStack.add(new Province());
			}
		}
		
		//ensuite on ajoute toutes ces piles de cartes dans la réserve
		this.supplyStacks.add(copperStack);
		this.supplyStacks.add(silverStack);
		this.supplyStacks.add(goldStack);
		this.supplyStacks.add(curseStack);
		this.supplyStacks.add(estateStack);
		this.supplyStacks.add(duchyStack);
		this.supplyStacks.add(provinceStack);
		this.supplyStacks.addAll(kingdomStacks); //addAll car c'est une liste de CardList
		
		//initialisation de thrashedCards même si je ne vois pas
		//où l'on s'en sert pour l'instant
		this.trashedCards=new CardList();
		
		
	}
	
	/**
	 * Renvoie le joueur correspondant � l'indice pass� en argument
	 * On suppose {@code index} est un indice valide du tableau 
	 * {@code this.players}
	 * 
	 * @param index indice dans le tableau des joueurs du joueur � renvoyer
	 */
	public Player getPlayer(int index) {
		return this.players[index];
	}
	
	/**
	 * Renvoie le nombre de joueurs participant � la partie
	 */
	public int numberOfPlayers() {
		return this.players.length;
	}
	
	/**
	 * Renvoie l'indice du joueur pass� en argument dans le tableau des 
	 * joueurs, ou -1 si le joueur n'est pas dans le tableau.
	 */
	private int indexOfPlayer(Player p) {
		int taille=this.numberOfPlayers();
		for(int i=0;i<taille;i++) {
			if(players[i].equals(p)) {
				return i;
			}
		}
		return -1;
	}
	
	/**
	 * Renvoie la liste des adversaires du joueur pass� en argument, dans 
	 * l'ordre dans lequel ils apparaissent � partir du joueur {@code p}.
	 * 
	 * @param p joueur dont on veut renvoyer la liste des adversaires. On 
	 * suppose que {@code p} est bien dans le tableau des joueurs.
	 * @return un {@code ArrayList} contenant les autres joueurs de la partie 
	 * en commen�ant par celui qui se trouve juste apr�s {@code p} et en 
	 * terminant par celui qui se trouve juste avant (le tableau est consid�r� 
	 * comme cyclique c'est-�-dire qu'apr�s le premier �l�ment on revient au 
	 * premier).
	 */
	public List<Player> otherPlayers(Player p) {
		List<Player> otherPlayers=new ArrayList<Player>();
		int i=0;
		while(players[i]!=p) {
			i++;
		}
		int taille=players.length;
		int j;
		for(j=i+1;j<taille;j++) {
			otherPlayers.add(players[j]);
		}
		for(j=0;j<i;j++) {
			otherPlayers.add(players[j]);
		}
		return otherPlayers;
	}
	
	/**
	 * Renvoie la liste des cartes qui sont disponibles � l'achat dans la 
	 * r�serve.
	 * 
	 * @return une liste de cartes contenant la premi�re carte de chaque pile 
	 * non-vide de la r�serve (cartes royaume et cartes communes)
	 */
	public CardList availableSupplyCards() {
		CardList boutique;
		int taille = this.supplyStacks.size();
		boutique=new CardList();
		for(int i=0;i<taille;i++) {
			if(!this.supplyStacks.get(i).isEmpty()) {
				boutique.add(this.supplyStacks.get(i).get(0));
			}
		}
		return boutique;
	}
	
	/**
	 * Renvoie une repr�sentation de l'�tat de la partie sous forme d'une cha�ne
	 * de caract�res.
	 * 
	 * Cette repr�sentation comporte
	 * - le nom du joueur dont c'est le tour
	 * - la liste des piles de la r�serve en indiquant pour chacune :
	 *   - le nom de la carte
	 *   - le nombre de copies disponibles
	 *   - le prix de la carte
	 *   si la pile n'est pas vide, ou "Empty stack" si la pile est vide
	 */
	public String toString() {
		Player currentPlayer = this.players[this.currentPlayerIndex];
		String r = String.format("     -- %s's Turn --\n", currentPlayer.getName());
		for (List<Card> stack: this.supplyStacks) {
			if (stack.isEmpty()) {
				r += "[Empty stack]   ";
			} else {
				Card c = stack.get(0);
				r += String.format("%s x%d(%d)   ", c.getName(), stack.size(), c.getCost());
			}
		}
		r += "\n";
		return r;
	}
	
	/**
	 * Renvoie une carte de la r�serve dont le nom est pass� en argument.
	 * 
	 * @param cardName nom de la carte � trouver dans la r�serve
	 * @return la carte trouv�e dans la r�serve ou {@code null} si aucune carte 
	 * ne correspond
	 */
	public Card getFromSupply(String cardName) {
		int i;
		int taille=supplyStacks.size();
		for(i=0;i<taille;i++) {
			if(supplyStacks.get(i).getCard(cardName)!=null) {
				return supplyStacks.get(i).getCard(cardName);
			}			
		}
		return null;
	}
	
	/**
	 * Retire et renvoie une carte de la r�serve
	 * 
	 * @param cardName nom de la carte � retirer de la r�serve
	 * @return la carte retir�e de la r�serve ou {@code null} si aucune carte
	 * ne correspond au nom pass� en argument
	 */
	public Card removeFromSupply(String cardName) {
		int i;
		int taille=supplyStacks.size();
		for(i=0;i<taille;i++) {
			if(this.supplyStacks.get(i).getCard(cardName)!=null) {
				Card carte=this.getFromSupply(cardName);
				carte=this.supplyStacks.get(i).remove(cardName);
				return carte;
			}			
		}
		return null;
	}
	
	/**
	 * Teste si la partie est termin�e
	 * 
	 * @return un bool�en indiquant si la partie est termin�e, c'est-�-dire si
	 * au moins l'unedes deux conditions de fin suivantes est vraie
	 *  - 3 piles ou plus de la r�serve sont vides
	 *  - la pile de Provinces de la r�serve est vide
	 * (on suppose que toute partie contient une pile de Provinces, et donc si 
	 * aucune des piles non-vides de la r�serve n'est une pile de Provinces, 
	 * c'est que la partie est termin�e)
	 */
	public boolean isFinished() {
		boolean fini=false;
		int compteur=0;
		int taille=this.supplyStacks.size();
		if(this.getFromSupply("Province")==null) {
			return fini=true;
		}
		for(int i=0;i<taille && compteur>2;i++) {
			if(this.supplyStacks.get(i).isEmpty()) {
				compteur++;
			}
		}
		if(compteur>2) {
			return fini=true;
		}
		return fini;
	}
	
	/**
	 * Boucle d'ex�cution d'une partie.
	 * 
	 * Cette m�thode ex�cute les tours des joueurs jusqu'� ce que la partie soit
	 * termin�e. Lorsque la partie se termine, la m�thode affiche le score 
	 * final et les cartes poss�d�es par chacun des joueurs.
	 */
	public void run() {
		while (! this.isFinished()) {
			// joue le tour du joueur courant
			this.players[this.currentPlayerIndex].playTurn();
			// passe au joueur suivant
			this.currentPlayerIndex += 1;
			if (this.currentPlayerIndex >= this.players.length) {
				this.currentPlayerIndex = 0;
			}
		}
		System.out.println("Game over.");
		// Affiche le score et les cartes de chaque joueur
		for (int i = 0; i < this.players.length; i++) {
			Player p = this.players[i];
			System.out.println(String.format("%s: %d Points.\n%s\n", p.getName(), p.victoryPoints(), p.totalCards().toString()));
		}
	}
	
	/**
	 * Lit une ligne de l'entr�e standard
	 * 
	 * C'est cette m�thode qui doit �tre appel�e � chaque fois qu'on veut lire
	 * l'entr�e clavier de l'utilisateur (par exemple dans Player.choose), ce
	 * qui permet de n'avoir qu'un seul Scanner pour tout le programme
	 * 
	 * @return une cha�ne de caract�res correspondant � la ligne suivante de
	 * l'entr�e standard (sans le retour � la ligne final)
	 */
	public String readLine() {
		return this.scanner.nextLine();
	}
	
}