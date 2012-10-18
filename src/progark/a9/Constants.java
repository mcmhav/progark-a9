package progark.a9;

import java.util.ArrayList;

import android.app.Activity;
import android.widget.EditText;
import progark.a9.gui.Ship;
import sheep.audio.Sound;
import sheep.game.Game;
import sheep.graphics.Image;

public class Constants {

	/** 
	 * Map constants, format: "FF","TShipNumber","TF". 
	 * ship: True/Number. The number indicate which ship it is, 
	 * so we easily could find out if the ship is sank or not.
	 * 
	 * bombed spot: True/True if it hits
	 * 				True/False if it misses
	 * if map is 7x6 0-6 will be the first row
	 * 7-13 will be the second an so on.
	 */
	
	// the name of the player is set at the start of the game
	public static String Player = "";
	
	//The player's enemies id
	public static ArrayList<String> Enemies = new ArrayList<String>();
	
	//Asigned at the start of the game
	//Represents the game session in the server
	public static String Session = "";
	
	//The ip address of the server. It is established in the R.Strings file upon compile
	public static String server = "";
	
	//The number of player per game. It can change in the future.
	public static int NumberPlayer = 2;
	
	//The Game instance. Used for easier manipulation of the States.
	public static Game MainGame;
	
	//The Player and enemies map. Used to update their corresponding screens
	public static ArrayList<String> playerMap = new ArrayList<String>(); 
	public static ArrayList<String> enemyMap = new ArrayList<String>(); 
	
	//Helps identifying the position in pixels of the different coordinates
	public static ArrayList<Coordinate> coordinates = new ArrayList<Coordinate>();
	
	//Keeps track of the position of the player's ships
	public static ArrayList<Ship> playerShips = new ArrayList<Ship>();
	
	//Used to know whose turn it is. Established by the server after each attack.
	public static String turn = "";
	
	//The index of the background to be used in the game
	public static int selectedBackground = 0;
	
	//The player id of the winner. Establish by the server upon Game Over
	public static String winner = "";

	
	
	public static EditText et;
	public static Activity main;
	
	//Images
	
	//Shots
	public static final Image MissedShot = new Image(R.drawable.missedshot);
	public static final Image Fire = new Image(R.drawable.fire);
	public static final Image Target = new Image(R.drawable.target);
	
	//Backgrounds
	public static final Image IntroBackground = new Image(R.drawable.introbg);
	public static final Image GeneralBackground = new Image(R.drawable.generalbg);
	public static final Image[] MapBackgrounds = {new Image(R.drawable.attackbg1), new Image(R.drawable.attackbg2),
		new Image(R.drawable.attackbg3), new Image(R.drawable.attackbg4)};
	
	
	//Buttons
	public static final Image TwoPlayersButton = new Image(R.drawable.button2players);
	public static final Image ThreePlayersButton = new Image(R.drawable.button3players);
	public static final Image FivePlayersButton = new Image(R.drawable.button5players);
	public static final Image SettingsButton = new Image(R.drawable.buttonsettings);
	
	public static final Image PlaceShipsButton = new Image(R.drawable.placeshipsbutton);
	public static final Image PlaceShipsButtonPushed = new Image(R.drawable.placeshipsbuttonpushed);
	
	public static final Image EnemyMapButton = new Image(R.drawable.enemybutton);
	public static final Image EnemyMapButtonPushed = new Image(R.drawable.enemybuttonpushed);
	
	public static final Image AttackButton = new Image(R.drawable.attackbutton1);
	public static final Image AttackButtonPushed = new Image(R.drawable.attackbutton1pushed);
	
	//Waiting animation
	public static final Image[] WaitingAnimation = {new Image(R.drawable.wait1), new Image(R.drawable.wait2), new Image(R.drawable.wait3),
		new Image(R.drawable.wait4), new Image(R.drawable.wait5), new Image(R.drawable.wait6), new Image(R.drawable.wait7),
		new Image(R.drawable.wait8), new Image(R.drawable.wait9), new Image(R.drawable.wait10), new Image(R.drawable.wait11), new Image(R.drawable.wait12)};
	public static final Image WaitingMessage = new Image(R.drawable.waitingmessage);
	
	//Ships Images
	public static final Image Ship4Slots = new Image(R.drawable.ship4_slots);
	public static final Image Ship4SlotsVertical = new Image(R.drawable.ship4vertical_slots);
	public static final Image Ship4SlotsDestroyed = new Image(R.drawable.ship4_slotsdestroyed);
	public static final Image Ship4SlotsVerticalDestroyed = new Image(R.drawable.ship4vertical_slotsdestroyed);
	
	public static final Image Ship3Slots1 = new Image(R.drawable.ship3_slots1);
	public static final Image Ship3Slots1Vertical = new Image(R.drawable.ship3vertical_slots1);
	public static final Image Ship3Slots1Destroyed = new Image(R.drawable.ship3_slots1destroyed);
	public static final Image Ship3Slots1VerticalDestroyed = new Image(R.drawable.ship3vertical_slots1destroyed);
	
	public static final Image Ship3Slots2 = new Image(R.drawable.ship3_slots2);
	public static final Image Ship3Slots2Vertical = new Image(R.drawable.ship3vertical_slots2);
	public static final Image Ship3Slots2Destroyed = new Image(R.drawable.ship3_slots2destroyed);
	public static final Image Ship3Slots2VerticalDestroyed = new Image(R.drawable.ship3vertical_slots2destroyed);
	
	public static final Image Ship2Slots1 = new Image(R.drawable.ship2_slots1);
	public static final Image Ship2Slots1Vertical = new Image(R.drawable.ship2vertical_slots1);
	public static final Image Ship2Slots1Destroyed = new Image(R.drawable.ship2_slots1destroyed);
	public static final Image Ship2Slots1VerticalDestroyed = new Image(R.drawable.ship2vertical_slots1destroyed);
	
	public static final Image Ship2Slots2 = new Image(R.drawable.ship2_slots2);
	public static final Image Ship2Slots2Vertical = new Image(R.drawable.ship2vertical_slots2);
	public static final Image Ship2Slots2Destroyed = new Image(R.drawable.ship2_slots2destroyed);
	public static final Image Ship2Slots2VerticalDestroyed = new Image(R.drawable.ship2vertical_slots2destroyed);
	
	//Sounds 
	public static final Sound attackSound = new Sound(R.raw.blaster);
	public static final Sound shipHitSound = new Sound(R.raw.shiphit);
	public static final Sound shipDestroyedSound = new Sound(R.raw.shipdestoryed);
	public static final Sound backgroundMusic = new Sound(R.raw.backgroundmusic);
	public static final Sound winnerSound = new Sound(R.raw.obiwan_theforce);
	public static final Sound loserSound = new Sound(R.raw.darthvader_yourfather);
}