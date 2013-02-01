package ca.camosun.snake;

public class Score implements Comparable<Score> {
	
	private int score;
	private String name;
	
	public Score(String inName, int inScore){
		name = inName;
		score = inScore;
		
	}
			
	@Override
	public int compareTo(Score newScore) {
		int difference = score - newScore.score;	
		return difference;
		
	}
	
	@Override
	public boolean equals(Object o){
		Score other = (Score) o;
		
		if(other.name != this.name){
			return false;
		}
		
		if(other.score != this.score){
			return false;
		}
		
		return true;
		
	}
	
	public int getScore(){
		return score;
	}

	public String getName(){
		return name;
	}
	
}
