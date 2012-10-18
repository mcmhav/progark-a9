package progark.a9.gui;

import sheep.game.Sprite;
import sheep.graphics.Image;

/**
 * @author firith
 * This class manages the creation and position of the different
 * buttons on the game.
 */
public class Button {
	
	private Sprite sprite;
	private Image image, imagePushed;
	private float positionX, positionY;
	
	/**
	 * Constructor - Initializes the button with the
	 * images given.
	 * @param i - Button image
	 * @param i2 - Button pressed image
	 */
	public Button(Image i, Image i2){
		image = i;
		imagePushed = i2;
		sprite = new Sprite(image);
		positionX = 0;
		positionY = 0;
	}

	/**
	 * Method - Gets the sprite of the button
	 * @return sprite - The sprite of the button
	 */
	public Sprite getSprite() {
		return sprite;
	}

	/**
	 * Method - Gets the position of the button
	 * in the x-axis.
	 * @return positionX - The position of the button on the x-axis.
	 */
	public float getPositionX() {
		return positionX;
	}

	/**
	 * Method - Sets the position of the button with the given
	 * parameters
	 * @param positionX - Position on the x-axis.
	 * @param positionY - Position on the y-axis.
	 */
	public void setPosition(float positionX, float positionY) {
		this.positionX = positionX;
		this.positionY = positionY;
		sprite.setPosition(positionX, positionY);
	}

	/**
	 * Method - Gets the position of the button
	 * in the y-axis.
	 * @return positionY - The position of the button on the y-axis.
	 */
	public float getPositionY() {
		return positionY;
	}
	
	/**
	 * Method - Determines if the button has been touched
	 * by the user.
	 * @param x - The position on the x-axis where the user has touched the screen.
	 * @param y - The position on the y-axis where the user has touched the screen.
	 * @return whether the button has been touched or not.
	 */
	public boolean isTouched(float x, float y){
		if(x <= sprite.getX() + image.getWidth() / 2 && x >= sprite.getX() - image.getWidth() / 2 &&
				y <= sprite.getY() + image.getHeight() / 2 && y >= sprite.getY() - image.getHeight() / 2){
			sprite.setView(imagePushed);
			return true;
		}else{
			sprite.setView(image);
			return false;
		}
	}
}