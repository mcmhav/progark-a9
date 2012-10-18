package progark.a9.screens;

import progark.a9.Constants;
import sheep.game.Sprite;
import sheep.game.State;
import sheep.graphics.Image;
import android.content.Intent;
import android.graphics.Canvas;
import android.view.MotionEvent;


/**
 * @author firith
 * This class represents the options screen where the player
 * can choose between a 2 players game or a 3 and 5 players game
 * (not yet implemented) It also has a seeting buttons, for future
 * configuration (not implemented yet).
 */
public class OptionsScreen extends State{

	private Image backgroundImage = Constants.GeneralBackground;
	private Sprite twoPlayerButtonSprite;
	private Sprite threePlayerButtonSprite;
	private Sprite fivePlayerButtonSprite;
	private Sprite settingsButtonSprite;
	private boolean joinFlag = false;
	
	private Sprite backSprite;
	
	/**
	 * Constructor - Initializes the background
	 * and the sprites for the buttons.
	 */
	public OptionsScreen(){
		backSprite = new Sprite(backgroundImage);
		
		twoPlayerButtonSprite = new Sprite(Constants.TwoPlayersButton);
		twoPlayerButtonSprite.setPosition(375, 75);
		
		threePlayerButtonSprite = new Sprite(Constants.ThreePlayersButton);
		threePlayerButtonSprite.setPosition(375, 200);
		
		fivePlayerButtonSprite = new Sprite(Constants.FivePlayersButton);
		fivePlayerButtonSprite.setPosition(375, 325);
		
		settingsButtonSprite = new Sprite(Constants.SettingsButton);
		settingsButtonSprite.setPosition(600, 425);
		
		this.addTouchListener(this);
	}
	
	
	
	/* (non-Javadoc)
	 * @see sheep.game.State#draw(android.graphics.Canvas)
	 */
	public void draw(Canvas canvas){
		backSprite.draw(canvas);
	
		twoPlayerButtonSprite.draw(canvas);
		threePlayerButtonSprite.draw(canvas);
		fivePlayerButtonSprite.draw(canvas);
		settingsButtonSprite.draw(canvas);

	}

	/* (non-Javadoc)
	 * @see sheep.game.State#update(float)
	 */
	public void update(float dt){
		twoPlayerButtonSprite.update(dt);
		threePlayerButtonSprite.update(dt);
		fivePlayerButtonSprite.update(dt);
		settingsButtonSprite.update(dt);
	}

	@Override
	public boolean onTouchUp(MotionEvent event) {

		return false;
	}


	@Override
	public boolean onTouchMove(MotionEvent event) {

		
		return false;
	}


	/* (non-Javadoc)
	 * @see sheep.game.State#onTouchDown(android.view.MotionEvent)
	 * Checks if the 2 players button has been touch, if it is it destroys the current
	 * screen and creates a Waiting Screen
	 */
	@Override
	public boolean onTouchDown(MotionEvent event) {
		//Touch on 2 players button
		if((event.getX() <= twoPlayerButtonSprite.getX()  + Constants.TwoPlayersButton.getWidth() / 2 && event.getX() >= twoPlayerButtonSprite.getX() - Constants.TwoPlayersButton.getWidth() / 2)
				&& (event.getY() <= twoPlayerButtonSprite.getY() + Constants.TwoPlayersButton.getHeight() / 2 && event.getY() >= twoPlayerButtonSprite.getY() - Constants.TwoPlayersButton.getHeight() / 2)
				&& !joinFlag){
			joinFlag = true;
			WaitingScreen waitingScreen = new WaitingScreen();
			this.getGame().pushState(waitingScreen);

		}
		
		//Touch on setting button
		if((event.getX() <= settingsButtonSprite.getX()  + Constants.SettingsButton.getWidth() / 2 && event.getX() >= settingsButtonSprite.getX() - Constants.SettingsButton.getWidth() / 2)
				&& (event.getY() <= settingsButtonSprite.getY() + Constants.SettingsButton.getHeight() / 2 && event.getY() >= settingsButtonSprite.getY() - Constants.SettingsButton.getHeight() / 2)){
			
			//Intent ns = new Intent(Constants.main.getApplicationContext(), Setipscreen.class);
			
			//Constants.main.startActivity(ns);
			this.getGame().pushState(new SettingsScreen()); 
		}
			
		return false;
	}
	
}
