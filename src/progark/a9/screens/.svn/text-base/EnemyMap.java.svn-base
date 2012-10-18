package progark.a9.screens;

import java.util.ArrayList;
import progark.a9.Constants;
import progark.a9.Coordinate;
import progark.a9.gameaction.Attack;
import progark.a9.gui.Button;
import sheep.game.Sprite;
import sheep.game.State;
import android.graphics.Canvas;
import android.view.MotionEvent;

/**
 * @author firith
 * This class is the implementation of the enemy map
 * where the player can attack his opponent. 
 */
public class EnemyMap extends State{
	
	private Sprite backgroundSprite;
	private Button attackButton;
	private boolean attackButtonPushed = false;
	private Sprite ship4Slots, ship3Slots1, ship3Slots2, ship2Slots1, ship2Slots2;
	private boolean ship4SlotsDraw, ship3Slots1Draw, ship3Slots2Draw, ship2Slots1Draw, ship2Slots2Draw;
	private ArrayList<Sprite> goodShots;
	private ArrayList<Sprite> missedShots;
	private Sprite target;
	private boolean targetOn;
	private Coordinate coordinateTarget;
	private Attack attack;
	private boolean attackPerformed = false;
	
	/**
	 * Constructor - Initializes the screen.
	 */
	public EnemyMap(){
		backgroundSprite = new Sprite(Constants.MapBackgrounds[Constants.selectedBackground]); 
		
		attackButton = new Button(Constants.AttackButton, Constants.AttackButtonPushed);
		attackButton.setPosition(690, 100);
		
		goodShots = new ArrayList<Sprite>();
		missedShots = new ArrayList<Sprite>();
		
		target = new Sprite(Constants.Target);
		targetOn = false;
		
		ship4Slots = new Sprite();
		ship4SlotsDraw = false;
		
		ship3Slots1 = new Sprite();
		ship3Slots1Draw = false;
		
		ship3Slots2 = new Sprite();
		ship3Slots2Draw = false;
		
		ship2Slots1 = new Sprite();
		ship2Slots1Draw = false;
		
		ship2Slots2 = new Sprite();
		ship2Slots2Draw = false;
		
		attack = new Attack();
		
		intiMap();
		this.addTouchListener(this);
		
	}
	
	/**
	 * Method - Initializes the Map of the enemy
	 * placing the good shots for the player as a fire sprite,
	 * the destroyed ships in red and the misses with an X.
	 */
	private void intiMap() {
		int ship4SlotsHits = 0;
		ArrayList<Integer> ship4SlotsCoordinates = new ArrayList<Integer>();
		int ship3Slots1Hits = 0;
		ArrayList<Integer> ship3Slots1Coordinates = new ArrayList<Integer>();
		int ship3Slots2Hits = 0;
		ArrayList<Integer> ship3Slots2Coordinates = new ArrayList<Integer>();
		int ship2Slots1Hits = 0;
		ArrayList<Integer> ship2Slots1Coordinates = new ArrayList<Integer>();
		int ship2Slots2Hits = 0;
		ArrayList<Integer> ship2Slots2Coordinates = new ArrayList<Integer>();
		
		//Checks the ArrayList that contains all the info about the enemy map
		//if a certain ship has been completely destroyed and sets the coordinates
		//where the destroyed ship is located.
		for(int i = 0; i < Constants.enemyMap.size(); i++){
			if(Constants.enemyMap.get(i).charAt(1) == '1'){
				ship4SlotsHits++;
				ship4SlotsCoordinates.add(i);
			}
			if(Constants.enemyMap.get(i).charAt(1) == '2'){
				ship3Slots1Hits++;
				ship3Slots1Coordinates.add(i);
			}
			if(Constants.enemyMap.get(i).charAt(1) == '3'){
				ship3Slots2Hits++;
				ship3Slots2Coordinates.add(i);
			}
			if(Constants.enemyMap.get(i).charAt(1) == '4'){
				ship2Slots1Hits++;
				ship2Slots1Coordinates.add(i);
			}
			if(Constants.enemyMap.get(i).charAt(1) == '5'){
				ship2Slots2Hits++;
				ship2Slots2Coordinates.add(i);
			}
		}
		
		//Checks the Enemy map for hits in order to draw them on the screen
		for(int i = 0; i < Constants.enemyMap.size(); i++){
			if(Constants.enemyMap.get(i).charAt(0) == 'T'){
				System.out.println(Constants.enemyMap.get(i).charAt(1));
				if(Constants.enemyMap.get(i).charAt(1) != 'F'){
					Sprite hit = new Sprite(Constants.Fire);
					hit.setPosition(Constants.coordinates.get(i).getX(), Constants.coordinates.get(i).getY());
					
					//Check if the ships are destroyed. If they are their sprites are created
					//and sets the flag to draw them as true. If they are not destroyed
					//It just adds the shot as a good shot. No hit is also added to its Array
					
					//Check Ship 4 Slots
					if(Constants.enemyMap.get(i).charAt(1) == '1'){
						if(ship4SlotsHits < 4)
							goodShots.add(hit);
						else{
							if(Math.abs(ship4SlotsCoordinates.get(0) - ship4SlotsCoordinates.get(1)) > 1){
								ship4Slots = new Sprite(Constants.Ship4SlotsVerticalDestroyed);
								ship4Slots.setPosition(Constants.coordinates.get(ship4SlotsCoordinates.get(3)).getCoordinateX() * (Constants.MapBackgrounds[0].getWidth() / 8) + Constants.Ship4SlotsVerticalDestroyed.getWidth() / 2, 
										Constants.coordinates.get(ship4SlotsCoordinates.get(3)).getCoordinateY() * (Constants.MapBackgrounds[0].getHeight() / 6) - (Constants.Ship4SlotsVerticalDestroyed.getHeight() / 2));
							}else{
								ship4Slots = new Sprite(Constants.Ship4SlotsDestroyed);
								ship4Slots.setPosition(Constants.coordinates.get(ship4SlotsCoordinates.get(0)).getCoordinateX() * (Constants.MapBackgrounds[0].getWidth() / 8) + Constants.Ship4SlotsDestroyed.getWidth() / 2, 
										Constants.coordinates.get(ship4SlotsCoordinates.get(0)).getCoordinateY() * (Constants.MapBackgrounds[0].getHeight() / 6) - Constants.Ship4SlotsDestroyed.getHeight() / 2 + 7);
							}
							ship4SlotsDraw = true;
						}
					}
					
					//Check Ship 3 Slots 1
					if(Constants.enemyMap.get(i).charAt(1) == '2'){
						if(ship3Slots1Hits < 3)
							goodShots.add(hit);
						else{
							if(Math.abs(ship3Slots1Coordinates.get(0) - ship3Slots1Coordinates.get(1)) > 1){
								ship3Slots1 = new Sprite(Constants.Ship3Slots1VerticalDestroyed);
								ship3Slots1.setPosition(Constants.coordinates.get(ship3Slots1Coordinates.get(2)).getCoordinateX() * (Constants.MapBackgrounds[0].getWidth() / 8) + Constants.Ship3Slots1VerticalDestroyed.getWidth() / 2, 
										Constants.coordinates.get(ship3Slots1Coordinates.get(2)).getCoordinateY() * (Constants.MapBackgrounds[0].getHeight() / 6) - (Constants.Ship3Slots1VerticalDestroyed.getHeight() / 2));
							}else{
								ship3Slots1 = new Sprite(Constants.Ship3Slots1Destroyed);
								ship3Slots1.setPosition(Constants.coordinates.get(ship3Slots1Coordinates.get(0)).getCoordinateX() * (Constants.MapBackgrounds[0].getWidth() / 8) + Constants.Ship3Slots1Destroyed.getWidth() / 2, 
										Constants.coordinates.get(ship3Slots1Coordinates.get(0)).getCoordinateY() * (Constants.MapBackgrounds[0].getHeight() / 6) - Constants.Ship3Slots1Destroyed.getHeight() / 2 + 7);
							}
							ship3Slots1Draw = true;
						}
					}
					
					//Check Ship 3 Slots 2
					if(Constants.enemyMap.get(i).charAt(1) == '3'){
						if(ship3Slots2Hits < 3)
							goodShots.add(hit);
						else{
							if(Math.abs(ship3Slots2Coordinates.get(0) - ship3Slots2Coordinates.get(1)) > 1){
								ship3Slots2 = new Sprite(Constants.Ship3Slots2VerticalDestroyed);
								ship3Slots2.setPosition(Constants.coordinates.get(ship3Slots2Coordinates.get(2)).getCoordinateX() * (Constants.MapBackgrounds[0].getWidth() / 8) + Constants.Ship3Slots2VerticalDestroyed.getWidth() / 2, 
										Constants.coordinates.get(ship3Slots2Coordinates.get(2)).getCoordinateY() * (Constants.MapBackgrounds[0].getHeight() / 6) - (Constants.Ship3Slots2VerticalDestroyed.getHeight() / 2));
							}else{
								ship3Slots2 = new Sprite(Constants.Ship3Slots2Destroyed);
								ship3Slots2.setPosition(Constants.coordinates.get(ship3Slots2Coordinates.get(0)).getCoordinateX() * (Constants.MapBackgrounds[0].getWidth() / 8) + Constants.Ship3Slots2Destroyed.getWidth() / 2, 
										Constants.coordinates.get(ship3Slots2Coordinates.get(0)).getCoordinateY() * (Constants.MapBackgrounds[0].getHeight() / 6) - Constants.Ship3Slots2Destroyed.getHeight() / 2 + 7);
							}
							ship3Slots2Draw = true;
						}
					}
					
					//Check Ship 2 Slots 1
					if(Constants.enemyMap.get(i).charAt(1) == '4'){
						if(ship2Slots1Hits < 2)
							goodShots.add(hit);
						else{
							if(Math.abs(ship2Slots1Coordinates.get(0) - ship2Slots1Coordinates.get(1)) > 1){
								ship2Slots1 = new Sprite(Constants.Ship2Slots1VerticalDestroyed);
								ship2Slots1.setPosition(Constants.coordinates.get(ship2Slots1Coordinates.get(1)).getCoordinateX() * (Constants.MapBackgrounds[0].getWidth() / 8) + Constants.Ship2Slots1VerticalDestroyed.getWidth() / 2, 
										Constants.coordinates.get(ship2Slots1Coordinates.get(1)).getCoordinateY() * (Constants.MapBackgrounds[0].getHeight() / 6) - (Constants.Ship2Slots1VerticalDestroyed.getHeight() / 2));
							}else{
								ship2Slots1 = new Sprite(Constants.Ship2Slots1Destroyed);
								ship2Slots1.setPosition(Constants.coordinates.get(ship2Slots1Coordinates.get(0)).getCoordinateX() * (Constants.MapBackgrounds[0].getWidth() / 8) + Constants.Ship2Slots1Destroyed.getWidth() / 2, 
										Constants.coordinates.get(ship2Slots1Coordinates.get(0)).getCoordinateY() * (Constants.MapBackgrounds[0].getHeight() / 6) - Constants.Ship2Slots1Destroyed.getHeight() / 2 + 7);
							}
							ship2Slots1Draw = true;
						}
					}
					
					//Check Ship 2 Slots 2
					if(Constants.enemyMap.get(i).charAt(1) == '5'){
						if(ship2Slots2Hits < 2)
							goodShots.add(hit);
						else{
							if(Math.abs(ship2Slots2Coordinates.get(0) - ship2Slots2Coordinates.get(1)) > 1){
								ship2Slots2 = new Sprite(Constants.Ship2Slots2VerticalDestroyed);
								ship2Slots2.setPosition(Constants.coordinates.get(ship2Slots2Coordinates.get(1)).getCoordinateX() * (Constants.MapBackgrounds[0].getWidth() / 8) + Constants.Ship2Slots2VerticalDestroyed.getWidth() / 2, 
										Constants.coordinates.get(ship2Slots2Coordinates.get(1)).getCoordinateY() * (Constants.MapBackgrounds[0].getHeight() / 6) - (Constants.Ship2Slots2VerticalDestroyed.getHeight() / 2));
							}else{
								ship2Slots2 = new Sprite(Constants.Ship2Slots2Destroyed);
								ship2Slots2.setPosition(Constants.coordinates.get(ship2Slots2Coordinates.get(0)).getCoordinateX() * (Constants.MapBackgrounds[0].getWidth() / 8) + Constants.Ship2Slots2Destroyed.getWidth() / 2, 
										Constants.coordinates.get(ship2Slots2Coordinates.get(0)).getCoordinateY() * (Constants.MapBackgrounds[0].getHeight() / 6) - Constants.Ship2Slots2Destroyed.getHeight() / 2 + 7);
							}
							ship2Slots2Draw = true;
						}
					}
				}else{
					System.out.println("entre a missed");
					Sprite missed = new Sprite(Constants.MissedShot);
					missed.setPosition(Constants.coordinates.get(i).getX(), Constants.coordinates.get(i).getY());
					missedShots.add(missed);
				}
			}
		}
	}
		
	
	/* (non-Javadoc)
	 * @see sheep.game.State#draw(android.graphics.Canvas)
	 * Draws the map and buttons. It also draws the ships destroyed
	 * or the hits and misses according to the case
	 */
	public void draw(Canvas canvas){
		backgroundSprite.draw(canvas);
		attackButton.getSprite().draw(canvas);
		
		//Draw ships if flags is set to true
		if(ship4SlotsDraw)
			ship4Slots.draw(canvas);
		if(ship3Slots1Draw)
			ship3Slots1.draw(canvas);
		if(ship3Slots2Draw)
			ship3Slots2.draw(canvas);
		if(ship2Slots1Draw)
			ship2Slots1.draw(canvas);
		if(ship2Slots2Draw)
			ship2Slots2.draw(canvas); 
		
		//Draw good shots
		for(Sprite s : goodShots)
			s.draw(canvas);
		
		//Draw missed shots
		for(Sprite s : missedShots)
			s.draw(canvas);
		
		//Draws the target if this has been position by the player
		if(targetOn)
			target.draw(canvas);
	}

	/* (non-Javadoc)
	 * @see sheep.game.State#update(float)
	 */
	public void update(float dt){
		attackButton.getSprite().update(dt);
		
		//update misses
		for(Sprite s : goodShots)
			s.update(dt);
		
		//update missed shots
		for(Sprite s : missedShots)
			s.update(dt);
	
		if(ship4SlotsDraw)
			ship4Slots.update(dt);
		if(ship3Slots1Draw)
			ship3Slots1.update(dt);
		if(ship3Slots2Draw)
			ship3Slots2.update(dt);
		if(ship2Slots1Draw)
			ship2Slots1.update(dt);
		if(ship2Slots2Draw)
			ship2Slots2.update(dt);
		
		if(targetOn)
			target.update(dt);
	}
	
	/* (non-Javadoc)
	 * @see sheep.game.State#onTouchUp(android.view.MotionEvent)
	 * If the attack button was pressed it shoots upon release
	 */
	@Override
	public boolean onTouchUp(MotionEvent event) {
		if(attackButtonPushed && !attackPerformed && targetOn){
			attackPerformed = true;
			attack.fireEnemy();
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
	 * Checks if the attack button has been pressed.
	 * It also positions the target according to the player's
	 * positioning upon touch
	 */
	@Override
	public boolean onTouchDown(MotionEvent event) {
		if(attackButton.isTouched(event.getX(), event.getY()))
			attackButtonPushed = true;
		else
			attackButtonPushed = false;
	
		if(event.getX() > 0 && event.getX() < (Constants.MapBackgrounds[0].getWidth() / 8) * 7){
			int x = (int) ((event.getX()) / (Constants.MapBackgrounds[0].getWidth() / 8));
			int y = (int) ((event.getY()) / (Constants.MapBackgrounds[0].getHeight() / 6));
			coordinateTarget = new Coordinate(x * (Constants.MapBackgrounds[0].getWidth() / 8) + Constants.MissedShot.getWidth() / 2, 
					(y + 1) * (Constants.MapBackgrounds[0].getHeight() / 6) - Constants.MissedShot.getHeight() / 2);
			target.setPosition(coordinateTarget.getX(), coordinateTarget.getY());
			targetOn = true;
			attack.setAttackCoordinate(x + (y * 7));			
		}	
		return false;
	}

	public void setAttackPerformed(boolean attackPerformed) {
		this.attackPerformed = attackPerformed;
	}
	
}