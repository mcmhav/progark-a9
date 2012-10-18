package progark.a9.screens;

import java.util.Random;
import progark.a9.Constants;
import progark.a9.gameaction.Communication;
import sheep.game.Sprite;
import sheep.game.State;
import android.graphics.Canvas;

/**
 * @author firith
 * This class is shows when the player is waiting
 * for the enemies. It shows the amazing Nyan Cat!
 */
public class WaitingScreen extends State{

	private Sprite waitingSprite;
	private Sprite waitingMessageSprite;
	private Sprite backSprite;
	
	private Communication communication;
	
	private int msCounter;
	private int index = 0;
	
	/**
	 * Constructor - Initializes the Screen with the animation variables
	 * and background. It also opens the communication class to send the 
	 * signal to server of a joining player.
	 */
	public WaitingScreen(){
		backSprite = new Sprite(Constants.GeneralBackground);
		waitingMessageSprite = new Sprite(Constants.WaitingMessage);
		waitingMessageSprite.setPosition(350, 100);
		waitingSprite = new Sprite(Constants.WaitingAnimation[index]);
		waitingSprite.setPosition(375, 300);
		msCounter = 0;
		Random generator = new Random();
		Constants.selectedBackground = generator.nextInt(4);
		communication = Communication.getInstance();
		communication.setOption("0");
		communication.listenSocket();
		Thread thread = new Thread(communication);
		thread.start();		
	}
	
	/* (non-Javadoc)
	 * @see sheep.game.State#draw(android.graphics.Canvas)
	 */
	public void draw(Canvas canvas){
		backSprite.draw(canvas);
		waitingMessageSprite.draw(canvas); 
		waitingSprite.draw(canvas);
	}

	/* (non-Javadoc)
	 * @see sheep.game.State#update(float)
	 * Animation of the Nyan Cat is done here
	 */
	public void update(float dt){

		int millisecondsSinceLastFrame = (int) (dt * 1000);
		msCounter += millisecondsSinceLastFrame;
		//Change Image for the sprite every 100ms iterating through the images arrays
		if(msCounter >= 75){
			if(index < Constants.WaitingAnimation.length - 1)
				index++;
			else
				index = 0;
			waitingSprite.setView(Constants.WaitingAnimation[index]);
			msCounter = 0; 
		}
		waitingMessageSprite.update(dt);
		waitingSprite.update(dt);

	}	
}