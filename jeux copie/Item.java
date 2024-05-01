/**

 *Classe représentant un objet dans le jeu.
 *Un objet est caractérisé par son nom, sa description et son poids.
 *@author Tsundyk Anastasia
 */ 
public class Item {
    private String aName;
    private String aDescription;
    private int aWeight;
    /**
     * Constructeur de la classe Item.
     * @param pName le nom de l'objet
     * @param pDescription la description de l'objet
     *@param pWeight le poids de l'objet
     */
    public Item(final String pName, final String pDescription, final int pWeight) {
        this.aName = pName;
        this.aDescription = pDescription;
        this.aWeight = pWeight;
    }

    /**
     * Retourne le poids de la description de l objet.
     */
    public String getDescription() {
        return this.aDescription;
    }

    /**
     * Accesseur pour le poids de l'objet.
     * @return le poids de l'objet.
     */
    public int getWeight() {
        return this.aWeight;
    }

    /**
     * Retourne la description de l'objet avec son poids.
     *@return la description de l'objet avec son poids.
     */public String getItemDescription()
    {
        return "Description de l item : "+this.aName+", "+this.aDescription+ "\n"+"Poids de litem : "+this.aWeight;
    }

    /**
     * Retourne le nom de l'objet.
     *@return le nom de l'objet.
     */public String getName()
    {
        return this.aName;
    }
}
