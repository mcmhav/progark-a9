package progark.a9.screens;

import java.util.ArrayList;
import progark.a9.Constants;
import progark.a9.gameaction.Communication;
import progark.a9.gui.Button;
import progark.a9.gui.Ship;
import sheep.game.Sprite;
import sheep.game.State;
import android.graphics.Canvas;
import android.view.MotionEvent;


/**
 * @author firith
 * This class represents the screen where the player
 * can positions their ships as they want.
 */
public class PlaceShipsScreen extends State{
	
	private Sprite backgroundSprite;
	private Button placeShipsButton;
	private boolean placeShipsButtonPushed = false;
	Ship ship4Slots, ship3Slots1, ship3Slots2, ship2Slots1, ship2Slots2;
	boolean ship4SlotsTouched = false, ship3Slots1Touched = false, ship3Slots2Touched = false,
			ship2Slots1Touched = false, ship2Slots2Touched = false;
	private int msCounter;
	private Communication communication;
	private boolean shipsSent = true;
	
	/**
	 * Constructor - Initializes the background and ships
	 * sprites.
	 */
	public PlaceShipsScreen(){
		backgroundSprite = new Sprite(Constants.MapBackgrounds[Constants.selectedBackground]); 
		
		placeShipsButton = new Button(Constants.PlaceShipsButton, Constants.PlaceShipsButtonPushed);
		placeShipsButton.setPosition(690, 100);
		
		ship4Slots = new Ship(1);
		ship4Slots.move(200, 100, false);
		
		ship3Slots1 = new Ship(2);
		ship3Slots1.move(200, 200, false);
		
		ship3Slots2 = new Ship(3);
		ship3Slots2.move(200, 300, false);
		
		ship2Slots1 = new Ship(4);
		ship2Slots1.move(200, 400, false);
		
		ship2Slots2 = new Ship(5);
		ship2Slots2.move(200, 500, false);
		
		ship4Slots.setOtherShips(ship3Slots1, ship3Slots2, ship2Slots1, ship2Slots2);
		ship3Slots1.setOtherShips(ship4Slots, ship3Slots2, ship2Slots1, ship2Slots2);
		ship3Slots2.setOtherShips(ship4Slots, ship3Slots1, ship2Slots1, ship2Slots2);
		ship2Slots1.setOtherShips(ship4Slots, ship3Slots1, ship3Slots2, ship2Slots2);
		ship2Slots2.setOtherShips(ship4Slots, ship3Slots1, ship3Slots2, ship2Slots1);

		msCounter = 0;
		this.addTouchListener(this);

	}
	
	
	
	/* (non-Javadoc)
	 * @see sheep.game.State#draw(android.graphics.Canvas)
	 */
	public void draw(Canvas canvas){
		backgroundSprite.draw(canvas);
		placeShipsButton.getSprite().draw(canvas);
		ship4Slots.getSprite().draw(canvas);
		ship3Slots1.getSprite().draw(canvas);
		ship3Slots2.getSprite().draw(canvas);
		ship2Slots1.getSprite().draw(canvas);
		ship2Slots2.getSprite().draw(canvas);
	}

	/* (non-Javadoc)
	 * @see sheep.game.State#update(float)
	 * Increments the counter if and only if
	 * one of the ships has been touched.
	 */
	public void update(float dt){
		if(ship4SlotsTouched || ship3Slots1Touched || ship3Slots2Touched || ship2Slots1Touched || ship2Slots2Touched){
			msCounter += (int) (dt * 1000);
		}
		placeShipsButton.getSprite().update(dt);
		ship4Slots.getSprite().update(dt); 
		ship3Slots1.getSprite().update(dt);
		ship3Slots2.getSprite().update(dt);
		ship2Slots1.getSprite().update(dt);
		ship2Slots2.getSprite().update(dt);
	}

	
	/* (non-Javadoc)
	 * @see sheep.game.State#onTouchUp(android.view.MotionEvent)
	 * Rotates the ships in case they have been touched for less than
	 * 200 ms. It also stars the communication thread to send the information
	 * about the position of the ships if the place ships button has been
	 * pushed.
	 */
	@Override
	public boolean onTouchUp(MotionEvent event) {
		if(ship4SlotsTouched){
			if(msCounter < 200)
				ship4Slots.rotate();
			
			msCounter = 0;
			ship4SlotsTouched = false;
		}
		
		if(ship3Slots1Touched){
			if(msCounter < 200)
				ship3Slots1.rotate();
			
			msCounter = 0;
			ship3Slots1Touched = false;
		}
		
		if(ship3Slots2Touched){
			if(msCounter < 200)
				ship3Slots2.rotate();
			
			msCounter = 0;
			ship3Slots2Touched = false;
		}
		
		if(ship2Slots1Touched){
			if(msCounter < 200)
				ship2Slots1.rotate();
			
			msCounter = 0;
			ship2Slots1Touched = false;
		}
		
		if(ship2Slots2Touched){
			if(msCounter < 200)
				ship2Slots2.rotate();
			
			msCounter = 0;
			ship2Slots2Touched = false;
		}
		
		if(placeShipsButtonPushed && shipsSent){
			shipsSent = false;
			Constants.playerShips.add(ship4Slots);
			Constants.playerShips.add(ship3Slots1);
			Constants.playerShips.add(ship3Slots2);
			Constants.playerShips.add(ship2Slots1);
			Constants.playerShips.add(ship2Slots2);
			
			this.getGame().popState();
			this.getGame().pushState(new GameScreen());
			communication = Communication.getInstance();
			communication.setOption("3");
			communication.listenSocket();
			Thread thread = new Thread(communication);
			thread.start();		
		}
		return false;
	}


	/* (non-Javadoc)
	 * @see sheep.game.State#onTouchMove(android.view.MotionEvent)
	 * Drags the ships around the map and positions them.
	 */
	@Override
	public boolean onTouchMove(MotionEvent event) {
		if(ship4SlotsTouched){
			if(ship4Slots.insideMap(event.getX(), event.getY())){
				ArrayList<Ship> otherShips = new ArrayList<Ship>();
				otherShips.add(ship3Slots1);
				ship4Slots.move(event.getX(), event.getY(), false);
			}
		}
		
		if(ship3Slots1Touched){
			if(ship3Slots1.insideMap(event.getX(), event.getY())){
				ship3Slots1.move(event.getX(), event.getY(), false);
			}
		}
		
		if(ship3Slots2Touched){
			if(ship3Slots2.insideMap(event.getX(), event.getY())){
				ship3Slots2.move(event.getX(), event.getY(), false);
			}
		}
		
		if(ship2Slots1Touched){
			if(ship2Slots1.insideMap(event.getX(), event.getY())){
				ship2Slots1.move(event.getX(), event.getY(), false);
			}
		}
		
		if(ship2Slots2Touched){
			if(ship2Slots2.insideMap(event.getX(), event.getY())){
				ship2Slots2.move(event.getX(), event.getY(), false);
			}
		}
		
		return false;
	}


	/* (non-Javadoc)
	 * @see sheep.game.State#onTouchDown(android.view.MotionEvent)
	 * Checks if the ships or the place ships button have been
	 * touched.
	 */
	@Override
	public boolean onTouchDown(MotionEvent event) {
		
		if(ship4Slots.isTouched(event.getX(), event.getY()))
			ship4SlotsTouched = true;
		else
			ship4SlotsTouched = false;
		
		if(ship3Slots1.isTouched(event.getX(), event.getY()))
			ship3Slots1Touched = true;
		else
			ship3Slots1Touched = false;
		
		if(ship3Slots2.isTouched(event.getX(), event.getY()))
			ship3Slots2Touched = true;
		else
			ship3Slots2Touched = false;
		
		if(ship2Slots1.isTouched(event.getX(), event.getY()))
			ship2Slots1Touched = true;
		else
			ship2Slots1Touched = false;
		
		if(ship2Slots2.isTouched(event.getX(), event.getY()))
			ship2Slots2Touched = true;
		else
			ship2Slots2Touched = false;
		
		if(placeShipsButton.isTouched(event.getX(), event.getY()))
			placeShipsButtonPushed = true;
		else
			placeShipsButtonPushed = false;
		
		return false;
	}
}
