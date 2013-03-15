package ca.camosun.snake;


public class SingleScore implements Comparable<SingleScore> {
	private String name;
	private int score;
	
	public SingleScore(String inName, int inScore) {
		name = inName;
		score = inScore;
	}
	
	public int getScore() {
		return score;
	}
	
	public String getName(){
		return name;
	}
	
	public int compareTo(SingleScore other) {
		return score - other.score;
	}
	
	public boolean equals(Object what) {
		SingleScore other = (SingleScore) what;
		
		return name.equals(other.name) && score == other.score;
	}
	
	public void setName(String inName){
		name = inName;
	}
	
	public void ateFruit() {
		score++;
	}
}
