
/**
 * 
 * classe Beamer qui est un teleporteur
 *
 * @author A.TSUNDYK
 */
public class Beamer extends Item
{
    private Room aChargedRoom; // Pièce dans laquelle le beamer a été chargé
    
    /**
     * Constructeur naturel
     * 
     * @param pName Nom du beamer
     * @param pDescription Description du beamer
     * @param pWeight Poids du beamer
     */
    public Beamer(final String pName, final String pDescription, final int pWeight)
    {
        super(pName, pDescription, pWeight);
    }
    
    /**
     * Accesseur de la pièce stockée dans le beamer
     * 
     * @return Pièce stockée
     */
    public Room getChargedRoom ()
    {
        return this.aChargedRoom;
    }
    
    /**
     * Modificateur de la pièce chargée dans le beamer
     * 
     * @param pRoom Pièce dans laquelle on charge le beamer
     */
    public void setChargedRoom (final Room pRoom)
    {
        this.aChargedRoom = pRoom;
    }
    
    /**
     * Fonction booléenne qui vérifie si le beamer est chargé
     * 
     * @return True si une pièce est stockée dans le beamer, False dans le cas contraire
     */
    public boolean isCharged ()
    {
        return this.aChargedRoom != null;
    }
}
