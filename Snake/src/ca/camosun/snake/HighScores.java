package ca.camosun.snake;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;

public class HighScores implements Iterable<Score> {
	private List<Score> highScores;
	int scoresToKeep;

	public HighScores(int inScoresToKeep) {
		highScores = new ArrayList<Score>();
		scoresToKeep = inScoresToKeep;
	}

	@Override
	public Iterator<Score> iterator() {
		Collections.sort(highScores);
		Collections.reverse(highScores);

		return highScores.iterator();
	}

	public void addScore(String inName, int inScore) {

		Score newScore = new Score(inName, inScore);
		Score lowestScore = Collections.min(highScores);
		
		if (highScores.size() == scoresToKeep) {
			int comparison = newScore.compareTo(lowestScore);
			if (comparison > 0) {
				highScores.remove(lowestScore);
			}

		}

		highScores.add(newScore);
	}

}
