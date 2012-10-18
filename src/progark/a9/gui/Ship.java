package progark.a9.gui;

import java.util.ArrayList;

import progark.a9.Constants;

import sheep.game.Sprite;
import sheep.graphics.Image; 

/**
 * @author firith
 * This class helps with the manipulation of the ships in the game.
 */
public class Ship{

	private int slots;
	private ArrayList<Integer> coordinates;
	private ArrayList<String> shots;
	private Sprite sprite;
	private Image image;
	private Image imageAux;
	private Image imageDestroyed;
	private Image imageVerticalDestroyed;
	private boolean vertical;
	private ArrayList<Integer> lastCoordinates;
	private float lastX, lastY;
	private ArrayList<Ship> otherShips;
	
	/**
	 * Method - Establishes the other ships sharing the map
	 * with the current ship.
	 * @param s1 - Ship
	 * @param s2 - Ship
	 * @param s3 - Ship
	 * @param s4 - Ship
	 */
	public void setOtherShips(Ship s1, Ship s2, Ship s3, Ship s4){
		otherShips.add(s1);
		otherShips.add(s2);
		otherShips.add(s3);
		otherShips.add(s4);
	}

	/**
	 * Constructor - Initializes the Ship according to 
	 * the ship type received.
	 * @param ship - Ship type
	 */
	public Ship(int ship){
		switch(ship){
		case 1:
			image = Constants.Ship4Slots;
			imageAux = Constants.Ship4SlotsVertical;
			imageDestroyed = Constants.Ship4SlotsDestroyed;
			imageVerticalDestroyed = Constants.Ship4SlotsVerticalDestroyed;
			slots = 4;
			break;
		case 2:
			image = Constants.Ship3Slots1;
			imageAux = Constants.Ship3Slots1Vertical;
			imageDestroyed = Constants.Ship3Slots1Destroyed;
			imageVerticalDestroyed = Constants.Ship3Slots1VerticalDestroyed;
			slots = 3;
			break;
		case 3:
			image = Constants.Ship3Slots2;
			imageAux = Constants.Ship3Slots2Vertical;
			imageDestroyed = Constants.Ship3Slots2Destroyed;
			imageVerticalDestroyed = Constants.Ship3Slots2VerticalDestroyed;
			slots = 3;
			break;
		case 4:
			image = Constants.Ship2Slots1;
			imageAux = Constants.Ship2Slots1Vertical;
			imageDestroyed = Constants.Ship2Slots1Destroyed;
			imageVerticalDestroyed = Constants.Ship2Slots1VerticalDestroyed;
			slots = 2;
			break;
		case 5:
			image = Constants.Ship2Slots2;
			imageAux = Constants.Ship2Slots2Vertical;
			imageDestroyed = Constants.Ship2Slots2Destroyed;
			imageVerticalDestroyed = Constants.Ship2Slots2VerticalDestroyed;
			slots = 2;
			break;
		}
		
		sprite = new Sprite(image);
		coordinates = new ArrayList<Integer>();
		lastCoordinates = new ArrayList<Integer>();
		shots = new ArrayList<String>();
		vertical = false;
		otherShips = new ArrayList<Ship>();
		lastX = -1;
		lastY = -1;
	}

	/**
	 * Method - Sets the coordinates occupied by this ship
	 * @param c - Coordinates of the Ship
	 */
	public void setLastCoordinates(ArrayList<Integer>c){
		lastCoordinates = c;
	}
	
	/**
	 * Method - Gets the size of the ship
	 * @return slots - Slots in the map the ship occupies.
	 */
	public int getSlots() {
		return slots;
	}

	/**
	 * Method - Sets the size of the ship.
	 * @param slots - Slots in the map the ship occupies.
	 */
	public void setSlots(int slots) {
		this.slots = slots;
	}
	
	
	/**
	 * Method - Returns the coordinates of the map that
	 * the ship occupies.
	 * @return coordinates - coordinates of the map occupied by the ship. 
	 */
	public ArrayList<Integer> getCoordinates(){
		return coordinates;
	}
	
	/**
	 * Method - Checks if a shot has hit the ship.
	 * @param coordinate - Coordinate where the attack took place.
	 * @return - whether or not this ship was hit.
	 */
	public boolean checkShot(String coordinate){
		if(coordinates.contains(coordinate)){
			shots.add(coordinate);
			return true;
		}
		return false;
	}
	
	/**
	 * Method - Changes the image of the ship when it is destroyed.
	 * @return - Whether or not the image was changed.
	 */
	public boolean destroyedShip(){
		if(!vertical)
			sprite.setView(imageDestroyed);
		else
			sprite.setView(imageVerticalDestroyed);
		if(shots.size() == slots){
			if(!vertical)
				sprite.setView(imageDestroyed);
			else
				sprite.setView(imageVerticalDestroyed);
			return true;
		}
		return false;
	}
	
	/**
	 * Method - Checks if the ship is touched by the user.
	 * @param x - The position on the x-axis where the user touched the screen.
	 * @param y - The position on the x-axis where the user touched the screen.
	 * @return - Whether or not the ship was touched by the user.
	 */
	public boolean isTouched(float x, float y){
		return (x <= sprite.getX() + (image.getWidth() / 2)  && x >= sprite.getX() - (image.getWidth() / 2)
				&&	y <= sprite.getY() + (image.getHeight() / 2)  && y >= sprite.getY() - (image.getHeight() / 2));
	}

	/**
	 * Method - Gets the sprite of the current ship.
	 * @return sprite - The sprite of this ship.
	 */
	public Sprite getSprite() {
		return sprite;
	}
	
	/**
	 * Method - Gets the Image of this ship.
	 * @return image - The Image of this ship.
	 */
	public Image getImage() {
		return image;
	}
	
	/**
	 * Method - Changes the image of the ship to show its
	 * rotation and moves it accordingly in the map.
	 */
	public void rotate(){
		Image aux = image;
		image = imageAux;
		imageAux = aux;
		
		float x = sprite.getX();
		float y = sprite.getY();
		
		sprite = new Sprite(image);
		sprite.setPosition(x, y);
		vertical = !vertical;
		move(sprite.getX(), sprite.getY(), true);
	}
	
	/**
	 * Moves the ship in the map making sure it ends up inside some coordinates
	 * and does not collide with another ship.
	 * @param x - The position to which the ship will be moved on the x-axis.
	 * @param y - The position to which the ship will be moved on the y-axis.
	 * @param rotation - Indicates if the ship is moving due to rotation.
	 */
	public void move(float x, float y, boolean rotation){
		if(!vertical){
			int coordinateX = (int) ((x - image.getWidth() / 2) / (Constants.MapBackgrounds[0].getWidth() / 8));
			int coordinateY = (int) ((y + image.getHeight() / 2) / (Constants.MapBackgrounds[0].getHeight() / 6));
			if(coordinateY < 1)
				coordinateY = 1;
			if(coordinateY > 6)
				coordinateY = 6;
			if(coordinateX < 0)
				coordinateX = 0;
			if(coordinateX > 6 - slots)
				coordinateX = 7 - slots;
			sprite.setPosition(coordinateX * (Constants.MapBackgrounds[0].getWidth() / 8) + image.getWidth() / 2, 
					coordinateY * (Constants.MapBackgrounds[0].getHeight() / 6) - image.getHeight() / 2 + 7);
		}else{
			int coordinateX = (int) ((x - image.getWidth() / 2) / (Constants.MapBackgrounds[0].getWidth() / 8));
			int coordinateY = (int) ((y + image.getHeight() / 2) / (Constants.MapBackgrounds[0].getHeight() / 6));
			if(coordinateY < slots)
				coordinateY = slots;
			if(coordinateY > 6)
				coordinateY = 6;
			if(coordinateX < 0)
				coordinateX = 0;
			sprite.setPosition(coordinateX * (Constants.MapBackgrounds[0].getWidth() / 8) + image.getWidth() / 2, 
					coordinateY * (Constants.MapBackgrounds[0].getHeight() / 6) - (image.getHeight() / 2)); 
		}
		setCoordinates();
		checkCoordinates(rotation);

	}
	
	/**
	 * Method - Check if the ship collides with another one
	 * if it does not it saves the new values, otherwise it leaves 
	 * the ship in its last place.
	 * @param rotation - Indicates if the ship is being rotated.
	 */
	private void checkCoordinates(boolean rotation) {
		boolean flag = false;
		for(Ship s1: otherShips){
			for(Integer c : s1.getCoordinates()){
				if(coordinates.contains(c))
					flag = true;
			}
		}
		
		if(flag){
			sprite.setPosition(lastX, lastY);
			coordinates.clear();
			coordinates.addAll(lastCoordinates);
			if(rotation)
				rotate();
		}else{
			lastCoordinates.clear();
			lastCoordinates.addAll(coordinates);
			lastX = sprite.getX();
			lastY = sprite.getY();
		}
		
	}

	/**
	 * Method - Indicates if the movement of a ship leaves it
	 * inside the map or not.
	 * @param x - The position to which the ship will be moved on the x-axis.
	 * @param y - The position to which the ship will be moved on the y-axis.
	 * @return - Whether or not the movement leaves the ship inside the map.
	 */
	public boolean insideMap(float x, float y){
		if(!vertical)
			return x + image.getWidth() / 2 < (Constants.MapBackgrounds[0].getWidth() / 8) * 7 && x + image.getWidth() / 2 > 0 &&
					y > 0 && y < Constants.MapBackgrounds[0].getHeight();
		else{
			return x  < (Constants.MapBackgrounds[0].getWidth() / 8) * 7 && x > 0 &&
					 (y - image.getHeight() / 2) > 0;
		}		
	}
	
	/**
	 * Method - Establishes the coordinates to be occupied
	 * by this ship.
	 */
	public void setCoordinates(){
		coordinates.clear();
		int coordinateX = (int) ((sprite.getX() - image.getWidth() / 2) / (Constants.MapBackgrounds[0].getWidth() / 8));
		int coordinateY = (int) ((sprite.getY() + image.getHeight() / 2) / (Constants.MapBackgrounds[0].getHeight() / 6));
		int coordinate = coordinateX + coordinateY * 7 - 7;
		if(!vertical){
			for(int i = 0; i < slots; i++)
				coordinates.add(coordinate + i);
		}else{
			for(int i = 0; i < slots; i++)
				coordinates.add(coordinate - i * 7);
		}
	}
}