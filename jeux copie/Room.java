import java.util.HashMap;
import java.util.Set;

/**
 * Classe Room - un lieu du jeu d'aventure Zuul.
 *
 *@author A.Tsundyk
 */

public class Room {
    // La description de la pièce.
    public String aDescription;

    // Les sorties possibles de la pièce.
    private HashMap<String, Room> aExits;

    // Le nom de l'image correspondant à la pièce.
    private String aImageName;

    // Les items présents dans la pièce.
    private ItemList aRoomItems;

    // Nouvel attribut pour l'énigme
    private String aEnigma; 

    /**
     * Constructeur de Room.
     * 
     * @param pDescription Description de la pièce.
     * @param pImage Nom de l'image correspondant à la pièce.
     */
    public Room(final String pDescription, final String pImage,final String pEnigma) {
        this.aDescription = pDescription;
        this.aExits = new HashMap<String, Room>();// nouvelle HashMap vide
        this.aImageName = pImage;
        this.aRoomItems = new ItemList("");// Initialise la liste d'items de la pièce.
        this.aEnigma = pEnigma;
    }

    /**
     * Renvoie la description de la pièce.
     *
     * @return La description de la pièce.
     */
    public String getDescription() {
        return this.aDescription;
    }
    
    /**
     * Renvoie la description de l enigme.
     *
     */
    public String getEnigme() {
        return this.aEnigma;
    }

    /**
     * Définit une sortie pour cette pièce dans la direction donnée.
     *
     * @param pDirection La direction dans laquelle se trouve la sortie.
     * @param pSuivante La pièce dans cette direction.
     */
    public void setExits(final String pDirection, final Room pSuivante) {
        this.aExits.put(pDirection, pSuivante);
    }

    /**
     * Renvoie la pièce dans la direction donnée.
     *
     * @param pDirection La direction dont on veut connaître la sortie.
     * @return La pièce dans la direction donnée.
     */
    public Room getExit(final String pDirection) {
        return this.aExits.get(pDirection);
    }

    /**
     * Renvoie une chaîne de caractères indiquant les sorties possibles de la pièce.
     *
     * @return Une chaîne de caractères indiquant les sorties possibles de la pièce.
     */
    public String getExitString() {
        String returnString = "Sorties : ";
        Set<String> Keys = aExits.keySet();//Cette ligne déclare une variable de type Set<String> nommée Keys et l'initialise avec l'ensemble des clés (directions) présentes dans la HashMap aExits.
        for (String vExit : Keys) {//Cette ligne commence une boucle for-each qui itère sur chaque élément (vExit) du Set Keys.
         returnString += ' ' + vExit;//Cette ligne concatène la clé (vExit) avec un espace (' ') à la chaîne returnString. Cela permet de construire la chaîne de caractères finale qui représente les sorties possibles de la pièce.
        }
        return returnString.toString();//Cette ligne retourne la chaîne de caractères returnString en tant que résultat de la fonction getExitString()
    }

    /**
     * Renvoie une description détaillée de cette pièce.
     *
     * @return Une chaîne de caractères contenant une description détaillée de cette pièce.
     */
    public String getLongDescription() {
        return "Tu es actuellement " + this.aDescription +".\n" + this.aEnigma + "\n" + this.aRoomItems.getItemString() + "\n"
        + getExitString() + "\n";
    }

    /**
     * Renvoie le nom de l'image correspondant à cette pièce.
     *
     * @return Le nom de l'image correspondant à cette pièce.
     */
    public String getImageName() {
        return this.aImageName;
    }

    /**
     * Renvoie une chaîne de caractères indiquant les items présents dans la pièce.
     *
     * @return Une chaîne de caractères indiquant les items présents dans la pièce.
     */
    public String getItemString() 
    {
        StringBuilder vItemString = new StringBuilder("Items : ");
        Set <String> vItemsNames = this.aExits.keySet();//crée un ensemble (Set) de type String appelé vItemsNames et l'initialise avec les clés (noms des items) présentes dans la HashMap aExits. Cela permet de récupérer les noms des items présents dans la pièce sans avoir de doublons.
        for (String vName : vItemsNames) {
            vItemString.append(" "+vName);
        }
        return vItemString.toString();
    }

    /**
     * Accesseur de la liste de items de la pièce
     * 
     * @return Liste d'items de la pièce
     */
    public ItemList getRoomItems ()
    {
        return this.aRoomItems;
    }

    /**
     * Fonction qui vérifie si une pièce est accessible
     * @return True si la pièce est une sortie possible, False dans le cas contraire
     */
    public boolean isExit (final Room pRoom)
    {
        if (this.aExits.containsValue(pRoom)){
            return true;
        }
        return false;
    }

}// Room
