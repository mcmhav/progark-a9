package progark.a9.screens;


import java.util.ArrayList;
import android.graphics.Canvas;
import android.view.MotionEvent;
import progark.a9.Constants;
import progark.a9.Coordinate;
import progark.a9.gui.Ship;
import sheep.game.Sprite;
import sheep.game.State;
import sheep.input.TouchListener;

/**
 * @author firith
 * The Intro Screen. It only needs a touch to start playing
 */
public class IntroScreen extends State implements TouchListener{

	private Sprite backgroundSprite;
	
	/**
	 * Constructor - Initializes all the constants to be used in the game.
	 * It also initializes the background for this screens.
	 */
	public IntroScreen(){
	
		backgroundSprite = new Sprite(Constants.IntroBackground);
		
		Constants.playerMap = new ArrayList<String>(); 
		Constants.enemyMap = new ArrayList<String>(); 
		Constants.coordinates = new ArrayList<Coordinate>();
		Constants.playerShips = new ArrayList<Ship>();
		
		Constants.turn = ""; 	//Server choose next player
		
		Constants.selectedBackground = 0;
		
		//Initilizes the player and enemy maps with not shots performed
		for(int i = 0; i < 42; i++){
			Constants.playerMap.add("FF");
			Constants.enemyMap.add("FF");
		}
		
		//Initializes the coordinates specifying their position in pixels
		for(int j = 1; j < 7; j++){
			for(int i = 0; i < 7; i++){
				Coordinate c = new Coordinate(i * (Constants.MapBackgrounds[0].getWidth() / 8) + Constants.MissedShot.getWidth() / 2, 
						j * (Constants.MapBackgrounds[0].getHeight() / 6) - Constants.MissedShot.getHeight() / 2);
				c.setCoordinateX(i);
				c.setCoordinateY(j);
				Constants.coordinates.add(c);
			}
		}
		this.addTouchListener(this);
	}
	
	/* (non-Javadoc)
	 * @see sheep.game.State#draw(android.graphics.Canvas)
	 */
	public void draw(Canvas c){
		backgroundSprite.draw(c);
	}

	/* (non-Javadoc)
	 * @see sheep.game.State#update(float)
	 */
	public void update(float dt){
	}

	
	/* (non-Javadoc)
	 * @see sheep.game.State#onTouchUp(android.view.MotionEvent)
	 */
	@Override
	public boolean onTouchUp(MotionEvent event) {
		return false;
	}

	/* (non-Javadoc)
	 * @see sheep.game.State#onTouchMove(android.view.MotionEvent)
	 */
	@Override
	public boolean onTouchMove(MotionEvent event) {	
		return false;
	}

	/* (non-Javadoc)
	 * @see sheep.game.State#onTouchDown(android.view.MotionEvent)
	 * Destroys the Intro Screen and creates the options screen
	 */
	@Override
	public boolean onTouchDown(MotionEvent event) {
		this.getGame().popState();
		this.getGame().pushState(new OptionsScreen());
		return false;
	}
	
	
}
