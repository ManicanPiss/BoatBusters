package c2f.boatbusters.classes;

public class Player {
	
	private int score = 0;
	private int choice;
	private int countSmall = 4;
	private int countMiddle = 3;
	private int countBig = 2;
	private int shipsCount = countSmall + countMiddle + countBig;
	private boolean shipsLeftToPut = (shipsCount > 0);
	private String name;
	
	public Player(String name) {
		this.name = name;
	}
	
	public String getName(){
		return name;
	}

	public int getScore() {
		return score;
	}
	public void increaseScore() {
		score += 1;
	}
	
	//-------------------------------
	
	public int getCountSmall() {
		return countSmall;
	}
	public void decreaseCountSmall() {
		countSmall -= 1;
	}
	
	//-------------------------------
	
	public int getCountMiddle() {
		return countMiddle;
	}
	public void decreaseCountMiddle() {
		countSmall -= 1;
	}
	
	//-------------------------------
	
	public int getCountBig() {
		return countBig;
	}
	public void decreaseCountBig() {
		countSmall -= 1;
	}
	
	//-------------------------------
	
	public int getShipsCount() {
		return shipsCount;
	}
	
	//-------------------------------
	
	public boolean areShipsLeftToPut() {
		return shipsLeftToPut;
	}
	
	//-------------------------------
	
	public int getChoice() {
		return choice;
	}
	public void setChoice(int choice) {
		this.choice = choice;
	}
	
	
	
	
	
	
	
	

}
