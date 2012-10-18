package progark.a9.screens;

import java.util.ArrayList;
import android.graphics.Canvas;
import android.view.MotionEvent;
import progark.a9.Constants;
import progark.a9.gui.Button;
import progark.a9.gui.Ship;
import sheep.game.Sprite;
import sheep.game.State;

public class GameScreen extends State{

	private Sprite backgroundSprite;
	private Button enemyMapButton;
	private boolean enemyMapButtonPushed = false;
	private Ship ship4Slots, ship3Slots1, ship3Slots2, ship2Slots1, ship2Slots2;
	private ArrayList<Sprite> receivedShots;
	

	/**
	 * Constructor - Initializes the screen setting the player's ships
	 * and updating according to the array that saves the hits of the 
	 * opponent's attacks.
	 */
	public GameScreen(){
		backgroundSprite = new Sprite(Constants.MapBackgrounds[Constants.selectedBackground]); 

		enemyMapButton = new Button(Constants.EnemyMapButton, Constants.EnemyMapButtonPushed);
		enemyMapButton.setPosition(690, 100);
		
		receivedShots = new ArrayList<Sprite>();
		
		ship4Slots = Constants.playerShips.get(0);
		ship3Slots1 = Constants.playerShips.get(1);
		ship3Slots2 = Constants.playerShips.get(2);
		ship2Slots1 = Constants.playerShips.get(3);
		ship2Slots2 = Constants.playerShips.get(4);
		
		intiMap();
		this.addTouchListener(this);

	}
	
	/**
	 * Method - Initializes the Map of the player
	 * placing the good shots for the enemy as a fire sprite,
	 * the destroyed ships in red.
	 */
	private void intiMap() {
		int ship4SlotsHits = 0;
		int ship3Slots1Hits = 0;
		int ship3Slots2Hits = 0;
		int ship2Slots1Hits = 0;
		int ship2Slots2Hits = 0;
		
		//Checks the ArrayList that contains all the info about the enemy map
		//if a certain ship has been completely destroyed and sets the coordinates
		//where the destroyed ship is located.
		for(int i = 0; i < Constants.playerMap.size(); i++){
			if(Constants.playerMap.get(i).charAt(1) == '1')
				ship4SlotsHits++;
			if(Constants.playerMap.get(i).charAt(1) == '2')
				ship3Slots1Hits++;
			if(Constants.playerMap.get(i).charAt(1) == '3')
				ship3Slots2Hits++;
			if(Constants.playerMap.get(i).charAt(1) == '4')
				ship2Slots1Hits++;
			if(Constants.playerMap.get(i).charAt(1) == '5')
				ship2Slots2Hits++;
		}
		
		//Checks the Enemy map for hits in order to draw them on the screen
		for(int i = 0; i < Constants.playerMap.size(); i++){
			if(Constants.playerMap.get(i).charAt(0) == 'T'){
				if(Constants.playerMap.get(i).charAt(1) != 'F'){
					Sprite hit = new Sprite(Constants.Fire);
					hit.setPosition(Constants.coordinates.get(i).getX(), Constants.coordinates.get(i).getY());
					
					//Check if the ships are destroyed. If they are their sprites are created
					//and sets the flag to draw them as true. If they are not destroyed
					//It just adds the shot as a good shot. No hit is also added to its Array
					//Check Ship 4 Slots
					
					if(Constants.playerMap.get(i).charAt(1) == '1'){
						if(ship4SlotsHits < 4)
							receivedShots.add(hit);
						else
							ship4Slots.destroyedShip();
					}
					
					//Check Ship 3 Slots 1
					if(Constants.playerMap.get(i).charAt(1) == '2'){
						if(ship3Slots1Hits < 3)
							receivedShots.add(hit);
						else
							ship3Slots1.destroyedShip();
					}
					
					//Check Ship 3 Slots 2
					if(Constants.playerMap.get(i).charAt(1) == '3'){
						if(ship3Slots2Hits < 3)
							receivedShots.add(hit);
						else
							ship3Slots2.destroyedShip();
					}
					
					//Check Ship 2 Slots 1
					if(Constants.playerMap.get(i).charAt(1) == '4'){
						if(ship2Slots1Hits < 2)
							receivedShots.add(hit);
						else
							ship2Slots1.destroyedShip();
					}
					
					//Check Ship 2 Slots 2
					if(Constants.playerMap.get(i).charAt(1) == '5'){
						if(ship2Slots2Hits < 2)
							receivedShots.add(hit);
						else
							ship2Slots2.destroyedShip();
					}
				}
			}
		}
	}

	/* (non-Javadoc)
	 * @see sheep.game.State#draw(android.graphics.Canvas)
	 * Draws the map and buttons. It also draws the ships destroyed
	 * or the hits according to the case
	 */
	public void draw(Canvas canvas){
		backgroundSprite.draw(canvas);
		enemyMapButton.getSprite().draw(canvas);
		
		//Draw misses
		for(Sprite s : receivedShots)
			s.draw(canvas);
		
		
		ship4Slots.getSprite().draw(canvas);
		ship3Slots1.getSprite().draw(canvas);
		ship3Slots2.getSprite().draw(canvas);
		ship2Slots1.getSprite().draw(canvas);
		ship2Slots2.getSprite().draw(canvas);
		
		//Draw misses
		for(Sprite s : receivedShots)
			s.draw(canvas);
	}

	/* (non-Javadoc)
	 * @see sheep.game.State#update(float)
	 */
	public void update(float dt){
		enemyMapButton.getSprite().update(dt);
		
		//update misses
		for(Sprite s : receivedShots)
			s.update(dt);
		
		ship4Slots.getSprite().update(dt); 
		ship3Slots1.getSprite().update(dt);
		ship3Slots2.getSprite().update(dt);
		ship2Slots1.getSprite().update(dt);
		ship2Slots2.getSprite().update(dt);
	}
	
	/* (non-Javadoc)
	 * @see sheep.game.State#onTouchUp(android.view.MotionEvent)
	 * If the enemy button was pressed it takes the player
	 * to the enemy's map upon release
	 */
	@Override
	public boolean onTouchUp(MotionEvent event) {
		if(enemyMapButtonPushed){
			if(Constants.turn.contains("Your Turn")){
				this.getGame().popState();
				this.getGame().pushState(new EnemyMap());
			}
		} 
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
	 * Checks if the enemy button has been pressed.
	 */
	@Override
	public boolean onTouchDown(MotionEvent event) {
		if(enemyMapButton.isTouched(event.getX(), event.getY()))
			enemyMapButtonPushed = true;
		else
			enemyMapButtonPushed = false;
		
		return false;
	}

}
