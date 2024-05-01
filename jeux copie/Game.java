
/**

 *Classe Game - lance le jeu
 *Cette classe permet de créer et lancer une instance du jeu. Elle contient une instance de la classe GameEngine,
 *qui gère la logique du jeu, et une instance de la classe UserInterface, qui fournit une interface utilisateur pour
 *interagir avec le jeu.
 *@author Anastasia Tsundyk
 */
public class Game
{
    private UserInterface aGui;
    private GameEngine aEngine;
    /**
     *Constructeur par défaut de la classe Game.
     *Crée une nouvelle instance de GameEngine et de UserInterface, et associe l'interface utilisateur au moteur de jeu.
     */
    public Game()
    {
        this.aEngine = new GameEngine();
        this.aGui = new UserInterface( this.aEngine );
        this.aEngine.setGUI( this.aGui );
    }
}



