 
/**
 * Classe Command - une commande du jeu d'aventure Zuul.
 *
 * @author A.Tsundyk
 */
public class Command
{
    private String aCommandWord;
    private String aSecondWord;
    /**
     * Constructeur Command() naturel 
     * @param pCommandWord Mot de commande
     * @param pSecondWord Mot de commande supplémentaire/Direction
     * 
     */public Command(final String pCommandWord, final String pSecondWord)
    {
        this.aCommandWord = pCommandWord;
        this.aSecondWord = pSecondWord;
    }   

    /**
     * premier accesseurs
     *
     */public String getCommandWord()
    {
        return this.aCommandWord;
    }

    /**
     * 
     *deuxieme accesseurs
     * 
     */public String getSecondWord()
    {
        return this.aSecondWord;
    }

    /**
     *  
     *La fonction qui verifie si il y a un second mot 
     * 
     */public boolean hasSecondWord()
    {
        return (this.aSecondWord != null);
    }

    /**
     * 
     *Fonction qui retourne vrai si le premier mot est null 
     * 
     */public boolean isUnknown()
    {
        return (this.aCommandWord == null);
    }
}// Command
