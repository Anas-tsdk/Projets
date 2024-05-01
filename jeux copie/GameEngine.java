
import java.util.Scanner;
import java.io.FileNotFoundException;
import java.io.File;
import java.util.HashMap;
import java.util.concurrent.TimeUnit;
/**
 * Classe Game - le moteur du jeu d'aventure Zuul.
 *
 * @author A.Tsundyk
 */
public class GameEngine
{
    private Parser        aParser;
    private Room          aCurrentRoom;
    private UserInterface aGui;
    private Player aPlayer;
    private HashMap <String, Room> aRooms; 
    /**
     * Constructor for objects of class GameEngine
     * 
     */
    public GameEngine()
    {
        this.aParser = new Parser();
        this.createRooms();
        this.aRooms = new HashMap <String, Room>();

    }

    /** 
     * Modifie le visuels de l interface graphique.
     * @param pUserInterface Interface graphique
     */
    public void setGUI( final UserInterface pUserInterface )
    {
        this.aGui = pUserInterface;
        this.printWelcome();
    }

    /**
     * Procedure pour afficher les phrases principale du jeux 
     *
     */
    private void printWelcome(){
        this.aGui.println("Bienvenue sur enigma museum !");
        this.aGui.println("Tu as " + this.aPlayer.getMoves() + " mouvements avant que la bombe explose.");
        this.aGui.println("Tape 'aide' si tu as besoin d aide.");
        this.aGui.println("Repond aux questions et je te donnerais un indice."+"\n"+"Pour repondre tape d abord le mot (reponse)."+"\n"+ "Quand tu aura atteint la piece finale, reunis les indices et repond !!");
        printLocationInfo();
    }

    /**
     * Procedure pour créer les lieux
     */
    public void createRooms()
    {
        // déclarer et créer les lieux

        Room vOutside = new Room(" a l exterieur du musee","Images/Louvre.jpg.jpeg",""); 
        Room vPsycheEtLamour = new Room(" dans la piece de l oeuvre Psyche et l amour", "Images/Psyche.jpg","Lequel des deux personnages est Eros ? Celui (avec) ou (sans) les ailes.");
        Room vPieceSecreteUn = new Room(" dans une piece secrete", "Images/piece.jpg","");
        Room vJoconde = new Room(" dans la piece de l oeuvre La Joconde","Images/Joconde.jpg","Les sourcils de la Joconde ont ete efface Vraie ou Faux ?");
        Room vLaVenus = new Room(" dans la piece de l oeuvre La Venus", "Images/Venus.jpg","Ici, pas d enigme."+"\n"+"Mon sixième est un pronom personnel indéfini.");
        Room vPieceSecreteDeux = new Room(" dans une piece secrete", "Images/piece2.jpg",""); 
        Room vPieceSecreteTrois = new Room(" dans une piece secrete", "Images/piece3.jpg","");
        Room vPieceSecreteQuatre = new Room(" dans une piece secrete", "Images/piece4.jpg","");
        Room vHermaphroditeEndormi = new Room(" dans la piece de l oeuvre Hemaphrodite Endormi", "Images/endormi.JPG","Ici, pas d enigme."+"\n"+"Mon quatrieme est la meilleure carte d'un jeu normal de 52.");
        Room vLesNocesDeCana = new Room(" dans la piece de l oeuvre Les noces de Cana", "Images/noce.jpg","Combien de personnages sont sur le tableau ?");
        Room vLaLiberteGuidantLePeuple = new Room(" dans la piece de l oeuvre La liberté guidant le peuple", "Images/liberte.jpg","Quand a été faite cette peinture ?");
        Room vFinal = new Room(" dans la piece avec la bombe", "Images/final.jpg.jpeg","Donne moi la reponse a l enigme !!!");

        //creation Items
        Item vCookie =new Item("cookie","nourriture",10);
        Item vChocolat =new Item("chocolat","nourriture",25);
        Item vPomme =new Item("pomme","nourriture",10);
        Item vPiment =new Item("piment","nourriture",25);
        Item vSablier = new Item("sablier","minuteur",1);
        Item vBeamer = new Beamer("beamer","téléporteur, charge dans une pièce et active dans une autre"+ "\n" + "pour revenir là où tu l'as charge !",50);

        this.aPlayer = new Player("Thomas",vOutside,50);

        // creation des enigmes

        //positionner les sorties
        vOutside.setExits("est",vPsycheEtLamour);
        vOutside.getRoomItems().ajoutItem("sablier",vSablier);

        vPsycheEtLamour.setExits("sud",vPieceSecreteUn);
        vPsycheEtLamour.setExits("est",vLaVenus);
        vPsycheEtLamour.setExits("ouest",vOutside);

        vPieceSecreteUn.setExits("nord",vPsycheEtLamour);
        vPieceSecreteUn.setExits("est",vJoconde);
        vPieceSecreteUn.getRoomItems().ajoutItem("pomme",vPomme);
        vPieceSecreteUn.getRoomItems().ajoutItem("piment",vPiment);

        vJoconde.setExits("nord",vLaVenus);
        vJoconde.setExits("ouest",vPieceSecreteUn);
        vJoconde.getRoomItems().ajoutItem("beamer",vBeamer);

        vLaVenus.setExits("sud",vJoconde);
        vLaVenus.setExits("est",vPieceSecreteTrois);
        vLaVenus.setExits("ouest",vPsycheEtLamour);
        vLaVenus.setExits("haut",vPieceSecreteDeux);

        vPieceSecreteDeux.setExits("bas",vLaVenus);

        vPieceSecreteTrois.setExits("nord",vFinal);
        vPieceSecreteTrois.setExits("est",vPieceSecreteQuatre);
        vPieceSecreteTrois.setExits("ouest",vLaVenus);
        vPieceSecreteTrois.getRoomItems().ajoutItem("chocolat",vChocolat);
        vPieceSecreteTrois.getRoomItems().ajoutItem("cookie",vCookie);

        vPieceSecreteQuatre.setExits("nord",vLaLiberteGuidantLePeuple);
        vPieceSecreteQuatre.setExits("sud",vHermaphroditeEndormi);
        vPieceSecreteQuatre.setExits("ouest",vPieceSecreteTrois);
        vPieceSecreteQuatre.setExits("haut",vLesNocesDeCana);

        vHermaphroditeEndormi.setExits("nord",vPieceSecreteQuatre);
        vLesNocesDeCana.setExits("bas",vPieceSecreteQuatre);
        vLaLiberteGuidantLePeuple.setExits("sud",vPieceSecreteQuatre);
        vLaLiberteGuidantLePeuple.setExits("ouest",vFinal);

        vFinal.setExits("sud",vPieceSecreteTrois);
        vFinal.setExits("est",vLaLiberteGuidantLePeuple);  

    }

    /**
     * Accesseur de la pièce
     * @param pRoom Clef dont on souhaite récupérer la pièce liée dans la HashMap
     * @return Pièce en question
     */
    public Room getRoom (final String pRoom)
    {
        return this.aRooms.get(pRoom);
    }// getRoom ()

    /**
     * Procedure permettant le deplacement du joueur.
     * @param pCommand Commande de l'utilisateur transmise
     */
    private void goRoom(final Command pCommand){

        if (!pCommand.hasSecondWord()) {
            // a)
            this.aGui.println("Ou veux tu aller?");
            return;
        }

        // b)
        String vDirection = pCommand.getSecondWord();

        // c)
        if (this.aPlayer.getCurrentRoom().getExit(vDirection) == null) {
            this.aGui.println("Ici,il n'y a pas de porte !");
            return;
        }
        this.aPlayer.goRoom(vDirection);
        printLocationInfo();
        this.time();
    }

    /**
     * Procedure pour afficher les phrases d'aide.

     */
    private void printHelp(){
        this.aGui.println("Tes commandes sont: "+"\n"+ this.aParser.getCommands()+ "et il te reste "+this.aPlayer.getMoves()+" mouvements.");
    }

    /**
     * Fonction pour pouvoir quitter le jeux.
     *@param pCmd Commande transmise
     */
    private boolean quit(final Command pCmd){
        if (pCmd.hasSecondWord())
        {
            System.out.println("Quitter quoi?");
            return false;
        }
        else
        {
            return true;
        }
    }

    /**
     * Fonction pour les commandes principales.
     * @param pCommandLine Chaine de caractère tapée au clavier
     */public void interpretCommand(final String pCommandLine)
    {
        this.aGui.println( "> " + pCommandLine );
        Command vCommand = this.aParser.getCommand( pCommandLine );

        if ( vCommand.isUnknown() ) {
            this.aGui.println( "Je ne comprend pas..." );
            return;
        }

        String vCommandWord = vCommand.getCommandWord();
        if ( vCommandWord.equals( "aide" ) )
            this.printHelp();
        else if ( vCommandWord.equals( "regarder" ) )
            this.look(vCommand);
        else if ( vCommandWord.equals( "manger" ) )
            this.eat(vCommand);
        else if ( vCommandWord.equals( "go" ) )
            this.goRoom( vCommand );
        else if ( vCommandWord.equals( "quitter" ) ) {
            if ( vCommand.hasSecondWord() )
                this.aGui.println( "Quitter pourquoi?" );
            else
                this.endGame();
        }
        else if (vCommandWord.equals("retour"))
            this.back(vCommand);

        else if (vCommandWord.equals("test"))
            this.test(vCommand);

        else if (vCommandWord.equals("prendre"))
            this.take(vCommand);

        else if (vCommandWord.equals("laisser"))
            this.drop(vCommand);

        else if(vCommandWord.equals("inventaire"))
            this.inventaire(vCommand);

        else if(vCommandWord.equals("charger"))
            this.charge(vCommand);

        else if(vCommandWord.equals("activer"))
            this.fire(vCommand);
        else if(vCommandWord.equals("reponse"))
            this.rep(vCommand);
         else if(vCommandWord.equals("hello"))
            this.hello(vCommand);
    }

    /**
     * on crée une méthode
    printLocationInfo() dans la classe Game qui permet à chaque appel
    de connaître la sortie de la salle actuelle.
     *
     */
    public void printLocationInfo(){
        this.aGui.println(this.aPlayer.getCurrentRoom().getLongDescription());
        if (this.aPlayer.getCurrentRoom().getImageName() != null){
            this.aGui.showImage(this.aPlayer.getCurrentRoom().getImageName() );
        }
    }

    /**
     * Affiche les détails d'un élément spécifique ou la description complète de la pièce.
     * @param pCommand Commande de l'utilisateur transmise.
     */
    private void look(final Command pCommand) {
        if (pCommand.hasSecondWord()) {
            String vItemName = pCommand.getSecondWord();
            Item vItem = this.aPlayer.getCurrentRoom().getRoomItems().getItem(vItemName);
            if (vItem == null) {
                this.aGui.println("Il n'y a pas d'objet ici avec ce nom.");
            } else {
                this.aGui.println(vItem.getItemDescription());
            }
        } else {
            this.aGui.println(this.aPlayer.getCurrentRoom().getLongDescription());
        }
    }

    /**
     * pour afficher la commande eat
     * @param pCommand Commande de l'utilisateur transmise
     */
    private void eat (final Command pCommand)
    {
        if (!pCommand.hasSecondWord()){ // Vérification de la présence d'un second mot
            this.aGui.println("Que veux tu manger?");
            return;
        }

        String vFood = pCommand.getSecondWord();
        if (vFood.equals("cookie")){ // Vérification si le second mot est bien "cookie"
            if (this.aPlayer.getInvItems().getItem("cookie") == null){
                this.aGui.println("Tu n as pas de cookie dans ton inventaire !");
                return;
            }else {this.aPlayer.removeInvWeight(this.aPlayer.getInvItems().getItem("cookie").getWeight());
                this.aPlayer.setMaxWeight(10); // Augmentation du poids possible transportable
                this.aPlayer.getInvItems().removeItem("cookie"); // Suppression du cookie de l'inventaire
                this.aGui.println("Tu as mange le cookie, tu as donc augmente la capacite de ton inventaire de 10 % !");

            }
        }

        if (vFood.equals("chocolat")){ 
            if (this.aPlayer.getInvItems().getItem("chocolat") == null){
                this.aGui.println("Tu n as pas de chocolat dans ton inventaire !");
                return;
            }
            else{this.aPlayer.removeInvWeight(this.aPlayer.getInvItems().getItem("chocolat").getWeight());
                this.aPlayer.getInvItems().removeItem("chocolat"); 
                this.aGui.println("Tu as mange le chocolat, mais il ne t'a rien apporte !");

            }
        }

        if (vFood.equals("piment")){ 
            if (this.aPlayer.getInvItems().getItem("piment") == null){
                this.aGui.println("Tu n as pas de piment dans ton inventaire !");
                return;
            }else {this.aPlayer.removeInvWeight(this.aPlayer.getInvItems().getItem("piment").getWeight());
                this.aPlayer.addMoves(25);// Augmentation du nbr de mvt
                this.aPlayer.getInvItems().removeItem("piment"); 
                this.aGui.println("Tu as mange le piment, tu as donc augmente ton nombre de mouvement!");

            }
        }

        if (vFood.equals("pomme")){ 
            if (this.aPlayer.getInvItems().getItem("pomme") == null){
                this.aGui.println("Tu n as pas de pomme dans ton inventaire !");
                return;
            }else {this.aPlayer.removeInvWeight(this.aPlayer.getInvItems().getItem("pomme").getWeight());
                this.aPlayer.subMoves(10);// Diminution du nbr de mvt
                this.aPlayer.getInvItems().removeItem("pomme"); 
                this.aGui.println("Tu as mange la pomme, tu as donc diminue ton nombre de mouvement!");

            }
        }
    }

    /**
     * Termine le jeu.
     */
    private void endGame()
    {
        this.aGui.println( "Merci d avoir joue.  Au revoir." );
        this.aGui.enable( false );
    }

    /**
     * Fonction de retour en arrière dans le jeu.
     * @param pCommand La commande de l'utilisateur transmise.
     */
    private void back(final Command pCommand) {
        // Vérifie si la commande a un second mot
        if (pCommand.hasSecondWord()) {
            this.aGui.println("Je ne comprends pas le deuxième mot.");
            return;
        }

        // Vérifie s'il y a une pièce précédente dans la pile des pièces visitées
        if (this.aPlayer.getPreceRoom().isEmpty()) {
            this.aGui.println("Il n'y a pas de pièce précédente.");
            return;
        }

        // Récupère la pièce précédente dans la pile
        Room previousRoom = this.aPlayer.getPreceRoom().pop();
        this.aPlayer.setCurrentRoom(previousRoom);

        // Affiche les informations de la pièce actuelle
        printLocationInfo();
        this.time();
    }

    /**
     * test le jeux
     * @param pCommand Commande transmise
     */private void test(final Command pCommand)
    {
        if (!pCommand.hasSecondWord()) {
            this.aGui.println("Veuillez spécifier le nom du fichier après la commande test.");
            return;
        }

        String vFilename = pCommand.getSecondWord();
        if (!vFilename.endsWith(".txt")) {
            vFilename += ".txt";
        }
        try{//on effectue les instructions dans le cas où le fichier existe.
            Scanner vScanner = new Scanner (new File (vFilename));
            while(vScanner.hasNextLine()){
                String vCommLine = vScanner.nextLine();
                interpretCommand(vScanner.nextLine());
            }
        }
        catch(final FileNotFoundException pE)//Ce dernier va nous permettre de de traiter l’erreur évoquée au début de ces explications.
        {
            this.aGui.println("Le fichier " + vFilename + " n'a pas été trouvé.");
        }
    }

    /**
     * Procédure permettant au joueur de ramasser un objet
     * 
     * @param pCommand Commande de l'utilisateur transmise
     */
    private void take (final Command pCommand)
    {
        if (!pCommand.hasSecondWord()){ 
            this.aGui.println("Que veux-tu prendre ?");
            return;
        }

        String vItemName = pCommand.getSecondWord();// Obtient le nom de l'objet à partir de la commande
        Item vItem = this.aPlayer.getCurrentRoom().getRoomItems().getItem(vItemName);// Obtient l'objet de la pièce actuelle
        if (vItem == null){ 
            this.aGui.println("Il n y a pas d item ici...");
            return;
        }

        if (!(vItem.getWeight() + this.aPlayer.getInvWeight() <= this.aPlayer.getMaxWeight())){ // Vérification si l'item n'est pas trop lourd pour l'inventaire
            this.aGui.println("Tu n as plus de place dans ton inventaire");
            return;
        }
        this.aPlayer.take(vItem);// Ajoute l'objet à l'inventaire du joueur
        this.aGui.println("Tu as l item: " + vItem.getName() + "\n");
        this.aGui.println(vItem.getItemDescription());
    }

    /**
     * Procédure qui permet au joueur de déposer un item
     * 
     * @param pCommand Commande de l'utilisateur transmise
     */
    private void drop(final Command pCommand) {
        if (pCommand.hasSecondWord()) {
            Item vItem = this.aPlayer.getInvItems().getItem(pCommand.getSecondWord());
            if (vItem != null) { // Vérification de l'absence de l'item dans l'inventaire
                this.aPlayer.drop(vItem);
                this.aGui.println("Tu as laisse l'item : " + vItem.getName());
            } else {
                this.aGui.println("Tu n'as pas cet item dans ton inventaire !");
            }
        } else {
            this.aGui.println("Tu veux laisser quoi ?");
        }
    }

    /**
     * acceder a l inventaire
     * @param pCommand Commande transmise
     */private void inventaire (final Command pCommand)
    {
        if (pCommand.hasSecondWord()){ 
            this.aGui.println("Tape inventaire si tu veux le voir !");
            return;
        }
        // Calcule et affiche le pourcentage de remplissage de l'inventaire du joueur
        int vInventoryRound = (int)Math.round((this.aPlayer.getInvWeight()));
        this.aGui.println("Ton inventaire est rempli a " + vInventoryRound + "%"); // Affichage du poids actuel de l'inventaire
    }

    private void time (){
        if (this.aPlayer.getMoves() == 0){ // Vérifie si le joueur a des déplacements restants
            this.aGui.println("Le minuteur est arrive a 0, la bombe a explose !");
            this.endGame();
        }
    }

    /**
     * Procédure qui charge le beamer
     * 
     * @param pCommand Commande de l'utilisateur transmise
     */
    private void charge(Command pCommand) {
        if (!pCommand.hasSecondWord()) {
            this.aGui.println("Que veux-tu charger ?");
            return;
        }

        String vSecondWord = pCommand.getSecondWord();
        if (!vSecondWord.equals("beamer")) {
            this.aGui.println("Je ne peux pas charger cet objet.");
            return;
        }

        Item vItem = this.aPlayer.getInvItems().getItem(vSecondWord);// Récupère le beamer dans l'inventaire du joueur
        if (vItem == null || !(vItem instanceof Beamer)) {
            this.aGui.println("Tu n as pas le beamer dans ton inventaire !");
            return;
        }

        Beamer vBeamer = (Beamer) vItem;
        this.aPlayer.charge(vBeamer);
        this.aGui.println("Ton beamer est charge !");
        this.time();
    }

    /**
     * Procédure qui décharge le beamer
     * 
     * @param pCommand Commande de l'utilisateur transmise
     */
    private void fire(Command pCommand) {
        if (!pCommand.hasSecondWord()) {
            this.aGui.println("Que veux-tu activer ?");
            return;
        }

        String vSecondWord = pCommand.getSecondWord();
        if (!vSecondWord.equals("beamer")) {
            this.aGui.println("Je ne peux pas activer cet objet !");
            return;
        }
        // Récupère le beamer dans l'inventaire du joueur
        Item vItem = this.aPlayer.getInvItems().getItem(vSecondWord);
        if (vItem == null) {
            this.aGui.println("Tu n'as pas le beamer dans ton inventaire !");
            return;
        }

        if (!(vItem instanceof Beamer)) {
            this.aGui.println("Cet objet n'est pas un beamer !");
            return;
        }

        Beamer vBeamer = (Beamer) vItem;
        if (!vBeamer.isCharged()) {
            this.aGui.println("Tu dois le charger avant de l'utiliser !");
            return;
        }

        this.aPlayer.fire(vBeamer);
        this.aGui.println("Ton beamer a été déclenché !");
        printLocationInfo(); // Affiche les informations sur la pièce
    }

    /**
     * Procédure qui valide les reponses
     * 
     */
    private void rep (final Command pCommand)
    {
        if (!pCommand.hasSecondWord()){ // Vérifie si il y a un second mot
            this.aGui.println("Que veux tu repondre ?");
            return;
        }

        String vSecondWord = pCommand.getSecondWord();
        if (vSecondWord.equals("avec")){ // Vérifie second mot
            this.aGui.println("Indice : Mon premier et la première l’être de l’alphabet.");
            return;
        }

        if (vSecondWord.equals("132")){ // Vérifie second mot
            this.aGui.println("Indice : Mon deuxième est utilisé par les prisonniers pour s'échapper dans les bandes-dessinées.");
            return;
        }

        if (vSecondWord.equals("Vraie")){ // Vérifie second mot
            this.aGui.println("Indice : Mon troisième est un verbe passif qu’on dit régulièrement pour décrire les vieux châteaux.");
            return;
        }

        if (vSecondWord.equals("1830")){ // Vérifie second mot
            this.aGui.println("Indice : Mon cinquième est un pronom personnel.");
            return;
        }

        if (vSecondWord.equals("Alimentation")){ // Vérifie second mot
            this.aGui.println("Bravo tu as gagner, la bombe n'a pas explose."+"\n"+"N hesite pas a rejouer.");
            this.endGame();
        }

        this.aGui.println("C est une mauvaise reponse. ");

    }

    /**
     * Procédure permettant au joueur de ramasser un objet
     * 
     * @param pCommand Commande de l'utilisateur transmise
     */
    private void hello (final Command pCommand)
    {
        String vMot = pCommand.getSecondWord();
        if (pCommand.hasSecondWord()){ 
            this.aGui.println("hello "+ vMot);
            return;
        }
        else{
            this.aGui.println("hello ");
            return;
        }
    }
}
