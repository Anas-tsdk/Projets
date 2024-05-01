import javax.swing.JFrame;
import javax.swing.JTextField;
import javax.swing.JTextArea;
import javax.swing.JLabel;
import javax.swing.ImageIcon;
import javax.swing.JScrollPane;
import javax.swing.JPanel;
import javax.swing.JButton;
import java.awt.Dimension;
import java.awt.BorderLayout;
import java.awt.GridLayout;
import java.awt.Color;
import java.awt.event.ActionListener;
import java.net.URL;
import java.awt.event.WindowAdapter;
import java.util.EventObject;
import java.awt.AWTEvent;
import java.awt.event.WindowEvent;
import java.awt.event.ActionEvent;

/**
 * This class implements a simple graphical user interface with a text entry
 * area, a text output area and an optional image.
 * 
 * @author Michael Kolling + Wendy Gorlier
 * @version 1.0 (Jan 2003) DB edited (2019)
 */
public class UserInterface implements ActionListener
{
    private GameEngine aEngine;
    private JFrame     aMyFrame;
    private JTextField aEntryField;
    private JTextArea  aLog;
    private JLabel     aImage;
    private JPanel     aPanelEast; //ensemble des boutons à l'est
    private JButton    aButtonregarder;
    private JButton    aButtonaide;
    private JButton    aButtonquitter;
    private JButton    aButtoninventaire;
    /**
     * Construct a UserInterface. As a parameter, a Game Engine
     * (an object processing and executing the game commands) is
     * needed.
     * 
     * @param pGameEngine de type GameEngine
     */
    public UserInterface( final GameEngine pGameEngine )
    {
        this.aEngine = pGameEngine;
        this.createGUI();
    } // UserInterface(.)

    /**
     * 
     * Print out some text into the text area.
     * @param pText de type String
     */
    public void print( final String pText )
    {
        this.aLog.append( pText );
        this.aLog.setCaretPosition( this.aLog.getDocument().getLength() );
    } // print(.)

    /**
     * Print out some text into the text area, followed by a line break.
     * @param pText de type String
     */
    public void println( final String pText )
    {
        this.print( pText + "\n" );
    } // println(.)

    /**
     * Show an image file in the interface.
     * @param pImageName de type String
     */
    public void showImage( final String pImageName )
    {
        String vImagePath = "" + pImageName; // to change the directory
        URL vImageURL = this.getClass().getClassLoader().getResource( vImagePath );
        if ( vImageURL == null )
            System.out.println( "Image not found : " + vImagePath );
        else {
            ImageIcon vIcon = new ImageIcon( vImageURL );
            this.aImage.setIcon( vIcon );
            this.aMyFrame.pack();
        }
    } // showImage(.)

    /**
     * Enable or disable input in the input field.
     * @param pOnOff de type boolean
     */
    public void enable( final boolean pOnOff )
    {
        this.aEntryField.setEditable( pOnOff );// enable/disable
        if ( ! pOnOff ) { // disable
            this.aEntryField.getCaret().setBlinkRate( 0 ); // cursor won't blink
            this.aEntryField.removeActionListener( this ); // won't react to entry
        }
        this.aButtonaide.setEnabled(false);
        this.aButtonregarder.setEnabled(false);
        this.aButtonquitter.setEnabled(false);
        this.aButtoninventaire.setEnabled(false);
    } // enable(.)

    /**
     * Set up graphical user interface.
     */
    private void createGUI()
    {
        this.aMyFrame = new JFrame( "Zork" ); // change the title
        this.aEntryField = new JTextField( 34 );

        this.aLog = new JTextArea();
        this.aLog.setEditable( false );
        JScrollPane vListScroller = new JScrollPane( this.aLog );
        vListScroller.setPreferredSize( new Dimension(200, 200) );
        vListScroller.setMinimumSize( new Dimension(100,100) );

        JPanel vPanel = new JPanel();
        this.aImage = new JLabel();

        vPanel.setLayout( new BorderLayout() ); // ==> only five places
        vPanel.add( this.aImage, BorderLayout.NORTH ); //mettre l'image dans la bordure Nord
        vPanel.add( vListScroller, BorderLayout.CENTER ); //mettre la zone d'affichage de texte dans le centre de la fenêtre
        vPanel.add( this.aEntryField, BorderLayout.SOUTH ); //mettre la zone où on tape les commandes dans la bordure sud
        
        // initialisation des boutons
        this.aButtonregarder = new JButton ("regarder");
        this.aButtonregarder.addActionListener( this );
       
        
        this.aButtonaide = new JButton ("aide");
        this.aButtonaide.addActionListener( this );
        
        this.aButtonquitter = new JButton ("quitter");
        this.aButtonquitter.addActionListener( this );
        
        this.aButtoninventaire = new JButton ("inventaire");
        this.aButtoninventaire.addActionListener( this );
                
                
        //appel de la méthode pour exécuter la création
        makeButton();
        
        vPanel.add(this.aPanelEast, BorderLayout.EAST); //mettre l'ensemble Est dans la bordure Est

        this.aMyFrame.getContentPane().add( vPanel, BorderLayout.CENTER );
        // add some event listeners to some components
        this.aEntryField.addActionListener( this );

        // to end program when window is closed
        this.aMyFrame.addWindowListener( new WindowAdapter(){
                public void windowClosing(WindowEvent e) { System.exit(0); }
            } );

        this.aMyFrame.pack();
        this.aMyFrame.setVisible( true );
        this.aEntryField.requestFocus();

        
    } // createGUI()

    /**
     * Actionlistener interface for entry textfield.
     * @param pE de type ActionEvent
     */
    public void actionPerformed( final ActionEvent pE ) 
    {
        // no need to check the type of action at the moment
        // because there is only one possible action (text input) :
        // this.processCommand(); // never suppress this line
        
        if(pE.getSource() == this.aButtonregarder){
            this.aEngine.interpretCommand("regarder");
        }        
        else if(pE.getSource() == this.aButtonaide){
            this.aEngine.interpretCommand("aide");
        }        
      else if(pE.getSource() == this.aButtonquitter){
            this.aEngine.interpretCommand("quitter");
        }   
        else if(pE.getSource() == this.aButtoninventaire){
            this.aEngine.interpretCommand("inventaire");
        }        
        else this.processCommand();
        
    } // actionPerformed(.)

    /**
     * A command has been entered. Read the command and do whatever is 
     * necessary to process it.
     */
    private void processCommand()
    {
        String vInput = this.aEntryField.getText();
        this.aEntryField.setText( "" );

        this.aEngine.interpretCommand( vInput );
    } // processCommand()

    /**
     * Procédure qui va créer un ensemble pour y ajouter les boutons
     */
    public void makeButton()
    {
        this.aPanelEast = new JPanel();
        this.aPanelEast.setLayout( new GridLayout(0,1,3,5));
        this.aPanelEast.add(this.aButtonregarder);
        this.aPanelEast.add(this.aButtonaide);
        this.aPanelEast.add(this.aButtonquitter);
        this.aPanelEast.add(this.aButtoninventaire);
    } //makeButon()
    
} // UserInterface 
