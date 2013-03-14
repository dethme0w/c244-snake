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
	
	public int compareTo(SingleScore other) {
		return score - other.score;
	}
	
	public boolean equals(Object what) {
		SingleScore other = (SingleScore) what;
		
		return name.equals(other.name);
	}
	
	public void ateFruit() {
		score++;
	}
}
