package progark.a9.screens;

import java.util.Random;

import progark.a9.R;
import android.graphics.Canvas;
import android.os.Bundle;
import android.view.MotionEvent;
import android.view.Window;
import android.view.WindowManager;
import android.widget.EditText;
import progark.a9.Constants;
import sheep.game.Game;
import sheep.game.Sprite;
import sheep.game.State;
import sheep.gui.TextButton;
import sheep.gui.WidgetAction;
import sheep.gui.WidgetListener;

/**
 * @author firith
 * This class is the implementation of the Settings Screen
 * is not yet developed.
 */
public class SettingsScreen extends State implements WidgetListener{

	private Sprite backgroundsprite;
	private TextButton doneButton;
	//private EditText et;
	private String s;
	
	/**
	 * Constructor - Initializes the background and other controls
	 * for this screen.
	 */
	public SettingsScreen(){
		Random generator = new Random();
		backgroundsprite = new Sprite(Constants.MapBackgrounds[generator.nextInt(4)]); 
	
		//et = Constants.et;
		s = Constants.server;
		doneButton = new TextButton(30f, 290f, s);
		doneButton.addWidgetListener(this);
		this.addTouchListener(this);
		
	}
	
	
	
	/* (non-Javadoc)
	 * @see sheep.game.State#draw(android.graphics.Canvas)
	 */
	public void draw(Canvas c){
		backgroundsprite.draw(c);
		doneButton.draw(c);	
		//et.draw(c);
	}

	/* (non-Javadoc)
	 * @see sheep.game.State#update(float)
	 */
	public void update(float dt){
		super.update(dt);
	
	}

	/* (non-
	 * Javadoc)
	 * @see sheep.gui.WidgetListener#actionPerformed(sheep.gui.WidgetAction)
	 */
	public void actionPerformed(WidgetAction action) {
		if(action.getSource() == doneButton){
			//s = et.getText().toString();
			
			getGame().popState();
			getGame().pushState(new IntroScreen());
		}
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
	 */
	@Override
	public boolean onTouchDown(MotionEvent event) {
		Constants.MainGame.popState();
		Constants.MainGame.pushState(new OptionsScreen());
		return false;
	}
	
}