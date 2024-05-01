 
/**
 * This class is part of the "World of Zuul" application. 
 * "World of Zuul" is a very simple, text based adventure game.  
 * 
 * This class holds an enumeration table of all command words known to the game.
 * It is used to recognise commands as they are typed in.
 *
 * @author  A.Tsundyk
 * @version 7.34
 */
public class CommandWords
{
   
    private final String[] aValidCommands;

    /**
     * Constructor - initialise the command words.
     */
    public CommandWords()
    {
        this.aValidCommands = new String[14];
        this.aValidCommands[0] = "go";
        this.aValidCommands[1] = "aide";
        this.aValidCommands[2] = "quitter";
        this.aValidCommands[3] = "regarder";
        this.aValidCommands[4] = "manger";
        this.aValidCommands[5] = "retour";
        this.aValidCommands[6] = "test";
        this.aValidCommands[7] = "prendre";
        this.aValidCommands[8] = "laisser";
        this.aValidCommands[9] = "inventaire";
        this.aValidCommands[10] = "charger";
        this.aValidCommands[11] = "activer";
        this.aValidCommands[12] = "reponse";
        this.aValidCommands[12] = "hello";
    } 

    /**
     * Check whether a given String is a valid command word. 
     * @return true if a given string is a valid command,
     * false if it isn't.
     */
    public boolean isCommand( final String pString )
    {
        for ( int vI=0; vI<this.aValidCommands.length; vI++ ) {
            if ( this.aValidCommands[vI].equals( pString ) )//vérifie si l'élément actuel du tableau aValidCommands est égal à la chaîne pString passée en paramètre
                return true;
        } 
        
        return false;
    } 
    
    /**
     * Affiche toutes les commandes valides.
     */
    public String getCommandList()
    {
        String vResult =" ";
        for(String command : aValidCommands){
            vResult += command+ " ";//Cela permet de construire une chaîne qui contient toutes les commandes valides séparées par des espaces.
        }
        return vResult;
    }
    
} // CommandWords