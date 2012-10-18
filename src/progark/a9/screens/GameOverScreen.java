package progark.a9.screens;


import progark.a9.Constants;
import sheep.game.Sprite;
import sheep.game.State;
import sheep.graphics.Color;
import sheep.input.TouchListener;
import android.graphics.Canvas;
import android.view.MotionEvent;


/**
 * @author firith
 * This class is the representation of the Game Over screen
 * it indicates whether the player won or lose.
 */
public class GameOverScreen extends State implements TouchListener{

	private Sprite backgroundSprite;
	private String gameOverText;

	/**
	 * Constructor - Initializes the background and the Game Over
	 * message for the player.
	 */
	public GameOverScreen(){
		backgroundSprite = new Sprite(Constants.MapBackgrounds[Constants.selectedBackground]);
		if(Constants.winner.equals(Constants.Player))
			gameOverText = "You just pwned " + Constants.Enemies.get(0) + " /pointAndLaugh now!";
		else
			gameOverText = "The n00b schoolbus has arrived because you just got schooled!";
		
		this.addTouchListener(this);
	}

	/* (non-Javadoc)
	 * @see sheep.game.State#draw(android.graphics.Canvas)
	 */
	public void draw(Canvas canvas){
		backgroundSprite.draw(canvas);
		canvas.drawText(gameOverText, 200, 200, Color.WHITE);
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
	 * Destroys this screen and takes you back to intro screen upon 
	 * touch.
	 */
	@Override
	public boolean onTouchDown(MotionEvent event) {
		this.getGame().popState();
		this.getGame().pushState(new IntroScreen());
		return false;
	}


}

