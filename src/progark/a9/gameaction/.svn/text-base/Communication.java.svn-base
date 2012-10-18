package progark.a9.gameaction;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;
import java.net.UnknownHostException;
import java.util.Arrays;
import java.util.StringTokenizer;
import progark.a9.Constants;
import progark.a9.screens.EnemyMap;
import progark.a9.screens.GameOverScreen;
import progark.a9.screens.GameScreen;
import progark.a9.screens.PlaceShipsScreen;


/**
 * @author firith
 * This class handles the communication
 * with the server. It usually runs in
 * a separate thread in order for it to 
 * not interfere with the game screen
 * transitions.
 */

public class Communication implements Runnable{

	private static Communication instance;
	private String lastMessage;
	private String option;
	private String attackCoordinate;
	private Socket socket = null;
	private PrintWriter out = null;
	private BufferedReader in = null; 


	/**
	 * Empty constructor of the class.
	 * Initializes the lastMessage variable
	 * which represents the last message
	 * received from the server.
	 */
	private Communication(){
		lastMessage = "";
	}

	/**
	 * Method used to retrieve the instance
	 * the class. This is a Singleton class.
	 * @return instance - the instance of this class.
	 */
	public static synchronized Communication getInstance(){
		if(instance == null)
			instance = new Communication();
		return instance;
	}

	/**
	 * Retrieves the last message sent
	 * by the server.
	 * @return lastMessage - The last message from the server
	 */
	public String getLastMessage() {
		return lastMessage;
	}


	/**
	 * Method used to join a game session in the server
	 * It receives the player id for and the id of
	 * the player enemies.
	 */
	public void joinGame(){
		out.println(0);
		try{
			//Get player id
			Constants.Session = in.readLine();
			Constants.Player = in.readLine();

			//Get enemies
			for(int i = 0; i < Constants.NumberPlayer - 1; i++)
				Constants.Enemies.add(in.readLine());
			System.out.println("Enemies: " + Arrays.toString(Constants.Enemies.toArray()));

			//Game started?
			lastMessage = in.readLine();
			Constants.MainGame.popState();
			Constants.MainGame.pushState(new PlaceShipsScreen());			

		}catch(IOException e){
			System.out.println("Read failed");
		}
	}

	/**
	 * Establish the socket communication with the server
	 */
	public void listenSocket(){
		//Create socket connection
		try{
			socket = new Socket(Constants.server, 8888);
			out = new PrintWriter(socket.getOutputStream(), true);
			in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
		} catch (UnknownHostException e) {
			System.out.println("Unknown host: kq6py.eng");
			System.exit(1);
		} catch  (IOException e) {
			System.out.println("No I/O");
			e.printStackTrace();
		}
	}


	/* (non-Javadoc)
	 * @see java.lang.Runnable#run()
	 */
	public void run() {
		//Chooses the method to be run
		//According to the set option
		if(option.equals("0"))
			joinGame();
		if(option.equals("1"))
			checkTurn();
		if(option.equals("3"))
			placeShips();
		if(option.equals("2")){
			String turn = checkTurn();
			Constants.turn = turn;
			if(turn.contains("Your Turn")){
				attack();
			}else{
				System.out.println("Not your turn");
				Constants.MainGame.popState();
				Constants.MainGame.pushState(new GameScreen());
			}
		}
	}

	/**
	 * This method send the information of the attack
	 * to the server which calculates the outcome.
	 * Afterwards it procedes to check if the game is over
	 * in case it is shows the Game Over Screen, otherwise
	 * it waits until it receives the outcome of the attack
	 * of the opponent.
	 */
	private void attack() {
		//Send Attack to the server
		option = "2|" + Constants.Session + "|" + Constants.Player + "|" + Constants.Enemies.get(0) + "|" + attackCoordinate;
		out.println(option);

		//Receive attack result from the server an updates the enemies map.
		try{
			String line = in.readLine();
			System.out.println("Attack result:" + line);
			StringTokenizer st = new StringTokenizer(line, "|");
			if(st.nextToken().contains("Hit")){
				if(st.nextToken().contains("NotDestroyed"))
					Constants.shipHitSound.play(new Float(0.25));
				else
					Constants.shipDestroyedSound.play(new Float(0.25));
				int coord = Integer.parseInt(st.nextToken());
				Constants.enemyMap.set(coord, ("T" + (Integer.parseInt(st.nextToken()) + 1)));
				System.out.println("Enemy map: " + Constants.enemyMap.get(coord));
			}else{
				int coord = Integer.parseInt(st.nextToken());
				Constants.enemyMap.set(coord, ("TF"));
				System.out.println("Enemy map: " + Constants.enemyMap.get(coord));
			}
			Constants.MainGame.popState();
			EnemyMap enemyMap = new EnemyMap();
			enemyMap.setAttackPerformed(true);
			Constants.MainGame.pushState(enemyMap);
			
			//Check if the game is over
			out.println("4|" + Constants.Session + "|" + Constants.Player + "|" + Constants.Enemies.get(0));
			line = in.readLine();
			if(line.contains("Game Over")){
				System.out.println("Game Over");
				st = new StringTokenizer(line, "|");
				st.nextToken();
				Constants.winner = st.nextToken();
				Constants.MainGame.popState();
				Constants.MainGame.pushState(new GameOverScreen());
			}else{
				System.out.println("establezco your turn");
				Constants.turn = "Your Turn";
				st = new StringTokenizer(line, "|");
				System.out.println("ENEMY ATTACK: " +line);
				if(st.nextToken().contains("Hit")){
					st.nextToken();
					int coord = Integer.parseInt(st.nextToken());
					Constants.playerMap.set(coord, ("T" + (Integer.parseInt(st.nextToken()) + 1)));
					System.out.println("Player map: " + Constants.playerMap.get(coord));
				}
				Constants.MainGame.popState();
				Constants.MainGame.pushState(new GameScreen());
			}
		} catch (IOException e){
			System.out.println("Read failed");
			System.exit(1);
		}

	}


	/**
	 * Sends the information about the ships
	 * placement to the server and then checks
	 * if it is the players turn to attack.
	 * If negative it waits for the outcome of the 
	 * opponent's attack.
	 */
	private void placeShips() {
		//Send information about the position of the player's ships
		String coordinates = "";
		for(int i = 0; i < Constants.playerShips.size(); i++){
			for(int j = 0; j < Constants.playerShips.get(i).getCoordinates().size(); j++)
				coordinates += ("|" + Constants.playerShips.get(i).getCoordinates().get(j));
		}
		System.out.println(coordinates);
		option = "3|" + Constants.Session + "|" + Constants.Player + "|" + Constants.Enemies.get(0) + "|" + coordinates;
		out.println(option);
		
		//Receive the results of the ships positioning and checks for next turn
		try{
			String line = in.readLine();
			System.out.println("Ships result:" + line);

			String turn = checkTurn();
			System.out.println(turn);
			Constants.turn = turn;
			
			//If it isn't the player's turn it sends a game over message to the server in order
			//to find out the outcome of the opponent's attack
			if(!turn.contains("Your Turn")){
				out.println("4|" + Constants.Session + "|" + Constants.Player + "|" + Constants.Enemies.get(0));
				line = in.readLine();
				if(line.contains("Game Over")){
					System.out.println("Game Over");
				}else{
					StringTokenizer st = new StringTokenizer(line, "|");
					System.out.println("ENEMY ATTACK: " +line);
					Constants.turn = "Your Turn";
					if(st.nextToken().contains("Hit")){
						st.nextToken();
						int coord = Integer.parseInt(st.nextToken());
						Constants.playerMap.set(coord, ("T" + (Integer.parseInt(st.nextToken()) + 1)));
						System.out.println("Player map: " + Constants.playerMap.get(coord));
					}
					Constants.MainGame.popState();
					Constants.MainGame.pushState(new GameScreen());
				}
			}
		} catch (IOException e){
			System.out.println("Read failed");
			System.exit(1);
		}
		coordinates = "";
	}

	/**
	 * Method that checks with the server whose turn
	 * it is.
	 * @return turn - String representing the current turn.
	 */
	private String checkTurn() {
		//Checks whose turn it is with the server
		String option = "1|" + Constants.Session + "|" + Constants.Player;
		out.println(option);

		//Receive whose turn it is
		try{
			String line = in.readLine();

			System.out.println("Turn result:" + line);
			return line;
		} catch (IOException e){
			System.out.println("Read failed");
			System.exit(1);
			return null;
		}	
	}

	/**
	 * Method used to retrieve the option
	 * that represents the action to be done
	 * by the server.
	 * @return option - Action to be done by the server.
	 */
	public String getOption() {
		return option;
	}

	/**
	 * Method used to set the option that represents the action
	 * to be done by the server.
	 * @param option - Action to be done by the server.
	 */
	public void setOption(String option) {
		this.option = option;
	}

	/**
	 * Method used to retrieve the coordinate
	 * where the next attack by the player is 
	 * going to take place.
	 * @return attackCoordinate - Coordinate in the map where the player will attack.
	 */
	public String getAttackCoordinate() {
		return attackCoordinate;
	}

	/**
	 * Method used to set the coordinate
	 * where the player's attack is going
	 * to take place.
	 * @param attack - Coordinate in the map where the player will attack.
	 */
	public void setAttackCoordinate(String attack) {
		this.attackCoordinate = attack;
	}

}
