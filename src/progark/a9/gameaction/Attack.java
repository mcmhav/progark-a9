package progark.a9.gameaction;

import progark.a9.Constants;

/**
 * @author firith
 * This class manages the attacks.
 * It makes sure the player hasn't attack
 * the same spot and sends the player's attack information
 * to the server.
 */
public class Attack {
	
	Communication communication;
	int attackCoordinate;
	
	/**
	 * Constructor - Initializes the coordinate to be
	 * attacked to -1.
	 */
	public Attack(){
		attackCoordinate = -1;
	}

	/**
	 * Method - Checks if the player has attack that spot before.
	 * If not it opens a new Thread in the communication class
	 * to send the information of the attack to the server
	 */
	public void fireEnemy(){
		if(Constants.enemyMap.get(attackCoordinate).charAt(0) == 'T')
			System.out.println("Can't fire there again captain");
		else{
			Constants.attackSound.play(new Float(0.25));
			System.out.println("Fire!");
			System.out.println("attack at: " + attackCoordinate);
			communication = Communication.getInstance();
			communication.setOption("2");
			communication.setAttackCoordinate(Integer.toString(attackCoordinate));
			communication.listenSocket();
			Thread thread = new Thread(communication);
			thread.start();
		}
	}

	/**
	 * Method - Gets the coordinate to be attacked
	 * @return attackCoordinate - Coordinate to be attacked.
	 */
	public int getAttackCoordinate() {
		return attackCoordinate;
	}

	/**
	 * Method - Sets the coordinate to be attacked
	 * @param attackCoordinate - Coordinate to be attacked.
	 */
	public void setAttackCoordinate(int attackCoordinate) {
		this.attackCoordinate = attackCoordinate;
	}
	
}