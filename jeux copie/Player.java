import java.util.HashMap;
import java.util.Stack;

/**
 * La classe Player représente le joueur et ses actions possibles.
 */
public class Player {
    // Variables d'instance
    private String aPseudo;         // Pseudonyme du joueur
    private int aMaxWeight;         // Poids maximal transportable par le joueur
    private Room aCurrentRoom;      // Pièce actuelle du joueur
    private Stack<Room> aPreceRooms; // Pile des pièces précédentes visitées par le joueur
    private ItemList aInvItems;     // Liste des items actuellement dans l'inventaire du joueur
    private int aInvWeight;         // Poids actuel de l'inventaire du joueur
    private int aMoves;             // Nombre de déplacements maximum avant de perdre
    private ItemList aItems;
    /**
     * Constructeur d'objets de classe Player
     * @param pPseudo Pseudonyme du joueur
     * @param pCurrentRoom Pièce de départ du joueur
     */
    public Player(final String pPseudo, final Room pCurrentRoom, final int pMoves) {
        this.aPseudo = pPseudo;
        this.aMaxWeight = 100;
        this.aPreceRooms = new Stack<Room>();
        this.aInvItems = new ItemList("Ton inventaire");
        this.aCurrentRoom = pCurrentRoom;
        this.aInvWeight = 0;
        this.aMoves = pMoves;
    }

    /**
     * Accesseur du pseudonyme
     * @return Pseudonyme du joueur
     */
    public String getPseudo() {
        return this.aPseudo;
    }

    /**
     * Getter de la pièce actuelle du joueur
     * @return Pièce actuelle du joueur
     */
    public Room getCurrentRoom() {
        return this.aCurrentRoom;
    }

    /**
     * Modificateur de la pièce actuelle du joueur
     * @param pRoom Nouvelle pièce actuelle du joueur
     */
    public void setCurrentRoom(final Room pRoom) {
        this.aCurrentRoom = pRoom;
    }

    /**
     * Getter de la pile des pièces précédentes visitées par le joueur
     * @return Pile des pièces précédentes visitées par le joueur
     */
    public Stack<Room> getPreceRoom() {
        return this.aPreceRooms;
    }

    /**
     * Procédure déplaçant le joueur de pièces en pièces
     * @param pDirection Direction de la pièce à récupérer
     */
    public void goRoom(final String pDirection) {
        Room vNextRoom = this.aCurrentRoom.getExit(pDirection);//obtenir la pièce suivante.
        this.aPreceRooms.push(this.aCurrentRoom);//Cela permet de conserver l'historique des pièces précédentes, afin que le joueur puisse revenir en arrière ultérieurement.
        this.aCurrentRoom = vNextRoom;//Cette ligne met à jour la pièce courante (this.aCurrentRoom) avec la pièce suivante (vNextRoom),
        this.aMoves -= 1;
    }

    /**
     * Permet de revenir sur ses pas
     */ 
    public boolean back() {
        if (this.aCurrentRoom.isExit(this.aPreceRooms.peek())){ // Vérifie si la pièce est dans les sorties de la pièce
            this.aCurrentRoom = this.aPreceRooms.pop();//met à jour la pièce courante (this.aCurrentRoom) en la remplaçant par la pièce précédente qui est retirée de la pile (this.aPreceRooms.pop()). Cela permet au joueur de revenir en arrière dans les pièces.
            this.aMoves -= 1;
            return true;
        }
        return false;//si pièce courante n est pas une sortie possible de la piece precedente
    }

    /**
     * Getter de la liste des items actuellement dans l'inventaire du joueur
     * @return Liste des items actuellement dans l'inventaire du joueur
     */
    public ItemList getInvItems() {
        return this.aInvItems;
    }

    /**
     * Getter du poids maximal transportable par le joueur
     * @return Poids maximal transportable par le joueur
     */
    public int getMaxWeight() {
        return this.aMaxWeight;
    }

    /**
     * Getter du poids actuel de l'inventaire du joueur
     * @return Poids actuel de l'inventaire du joueur
     */public int getInvWeight()
    {
        return this.aInvWeight;
    }

    /**

    Procédure permettant de ramasser un item
    @param pItem Item à ramasser
     */
    public void take (final Item pItem)
    {
        this.aInvItems.ajoutItem(pItem.getName(),pItem); // Ajoute à l'inventaire l'item actuellement présent dans la pièce
        this.aCurrentRoom.getRoomItems().removeItem(pItem.getName()); // Supprime l'item de la pièce
        this.aInvWeight += pItem.getWeight(); // Ajoute le poids de l'item au poids total de l'inventaire
    }

    /**

    Procédure permettant de déposer un item
    @param pItem Item à déposer
     */
    public void drop (final Item pItem)
    {
        this.aCurrentRoom.getRoomItems().ajoutItem(pItem.getName(), pItem); // Ajoute l'item actuellement présent dans l'inventaire à la pièce actuelle
        this.aInvItems.removeItem(pItem.getName()); // Supprime l'item de l'inventaire
        this.aInvWeight -= pItem.getWeight(); // Retire le poids de l'item au poids total de l'inventaire
    }

    /**

    Modificateur du poids maximal transportable par le joueur
    @param pWeight Poids que l'on souhaite ajouter en poids maximal
     */
    public void setMaxWeight (final int pWeight){
        this.aMaxWeight = pWeight;
    }

    /**

    Modificateur du poids de l'inventaire du joueur
    @param pWeight Poids que l'on souhaite soustraire du poids actuel de l'inventaire
     */
    public void removeInvWeight (final int pWeight){
        this.aInvWeight -= pWeight;
    }

    /**
     * Accesseur du nombre de déplacements restants
     * 
     * @return le nombre de déplacements avant de perdre
     */
    public int getMoves ()
    {
        return this.aMoves;
    }

    /**
     * Modificateur du nombre de déplacements restants
     * 
     */
    public void addMoves (final int pAdd)
    {
        this.aMoves += pAdd;
    }

    /**
     * Modificateur du nombre de déplacements restants
     * 
     */
    public void subMoves (final int pSub)
    {
        this.aMoves -= pSub;
    }
    
    /** Accesseur
     * @return un item de la HashMap d'items
     * @param pItem de type String
     */
    public Item getItemPlayer (final String pItem)
    {
        return this.aItems.getItem(pItem); 
    } 
    
    /**
     * Procédure qui charge le beamer
     * 
     * @param pBeamer Item beamer transmit par GameEngine
     */
    public void charge (final Beamer pBeamer)
    {
        pBeamer.setChargedRoom(this.aCurrentRoom); // Charge la pièce actuelle dans le beamer
        this.aMoves -= 1; // Retire un point de déplacement
    }// charge ()
    
    /**
     * Procédure qui utilise le beamer
     * 
     * @param pBeamer Item beamer transmit par GameEngine
     */
    public void fire (final Beamer pBeamer)
    {
        this.aPreceRooms.push(this.aCurrentRoom); // Ajoute la pièce actuelle aux anciennes pièces
        this.aCurrentRoom = pBeamer.getChargedRoom(); // Remplace la pièce actuelle par celle stockée dans le beamer
        this.aInvItems.removeItem(pBeamer.getName()); // Retire le beamer de l'inventaire
        this.aInvWeight -= pBeamer.getWeight(); // Retire le poids du beamer du poids total
        this.aMoves -= 1; // Retire un point de déplacement
    }
    
    
}

