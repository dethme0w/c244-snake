package ca.camosun.snake.test;

import static org.junit.Assert.*;
import org.junit.Before;
import org.junit.Test;

import ca.camosun.snake.HighScores;
import ca.camosun.snake.SingleScore;

public class HighScoresTest {
	private static final int SCORES_TO_KEEP = 3;
	
	HighScores<SingleScore> scores;
	
	SingleScore [] scoreData = {
		new SingleScore("a", 1000),
		new SingleScore("b", 3000),
		new SingleScore("c", 2000),
		new SingleScore("d", 4000),
	};

	@Before
	public void setUp() throws Exception {
		scores = new HighScores<SingleScore>(SCORES_TO_KEEP);
		
		for(SingleScore aScore : scoreData) {
			scores.addScore(aScore);
		}
	}
	
	@Test
	public void firstOnList() {
		assertTrue(scores.getFirstOnList() == scoreData[3]);
	}
	
	@Test
	public void scoresInOrder() {
		assertTrue(scores.size() == SCORES_TO_KEEP);
	
		assertTrue(scores.get(0) == scoreData[3]);
		assertTrue(scores.get(1) == scoreData[1]);
		assertTrue(scores.get(2) == scoreData[2]);
	}
}
