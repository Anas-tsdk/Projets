import java.util.HashMap;
import java.util.Set;

/**
 * Cette classe est responsable de la gestion des items du jeu
 * 
 * Anastasia Tsundyk
 */
public class ItemList
{
    private HashMap <String, Item> aItems; // HashMap d'items
    private String aLocation; // La où les items sont (inventaire ou pièce)
    
    /**
     * Constructeur naturel
     * 
     * @param pLocation Localisation de la liste d'items
     */
    public ItemList (final String pLocation)
    {
        this.aItems = new HashMap <String, Item> ();
        this.aLocation = pLocation;
    }
    
    /**
     * Accesseur de l'item
     * 
     * @param pItem Chaine de caractères de l'item auquel on souhaite acceder dans la liste d'items
     * @return L'item recherché par son nom
     */
    public Item getItem (final String pItem)
    {
        return this.aItems.get(pItem);
    }
    
    /**
     * Accesseur de la liste d'items
     * 
     * @return HashMap contenant tous les items
     */
    public HashMap <String, Item> getItems ()
    {
        return aItems;
    }
    
    /**
     * Fonction qui retourne sous forme de chaine de caractère la liste d'items
     * 
     * @return Chaine de caractères de la liste
     */
    public String getItemString ()
    {
        if (this.aItems.isEmpty()){//Cette condition vérifie si la liste aItems des items est vide
           return "Il n y a pas d item  dans la piece " + this.aLocation;
        }
        
        StringBuilder vChaine = new StringBuilder ("Items dans la piece " + this.aLocation + " :");
        Set <String> vItemsNames = this.aItems.keySet();
        for (String vName : vItemsNames){
            vChaine.append(" " + vName);
        }
        return vChaine.toString();
    }
    
    /**
     * Procédure qui ajoute un item à la liste d'items
     * 
     * @param pName Clef de la HashMap (nom de l'item)
     * @param pItem Item à insérer dans la HashMap
     */
    public void ajoutItem(final String pName, final Item pItem){
        this.aItems.put(pName, pItem);
    }
    
    /**
     * Procédure qui supprime un item de la HashMap
     * 
     * @param pName Clef de l'item à supprimer
     */
    public void removeItem (final String pName){
        this.aItems.remove(pName);
    }
}