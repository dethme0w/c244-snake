package ca.camosun.snake;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;

public class HighScores implements Iterable<Score> {
	private List<Score> highScores;
	int scoresToKeep = 10;

	public HighScores() {
		highScores = new ArrayList<Score>();
	}

	@Override
	public Iterator<Score> iterator() {
		Collections.sort(highScores);
		Collections.reverse(highScores);

		return highScores.iterator();
	}

	public void addScore(String inName, int inScore) {

		Score newScore = new Score(inName, inScore);

		if (highScores.size() == scoresToKeep) {
			int comparison = newScore.compareTo(Collections.min(highScores));
			if (comparison > 0) {
				highScores.remove(Collections.min(highScores));
			}

		}

		highScores.add(newScore);
	}

}
