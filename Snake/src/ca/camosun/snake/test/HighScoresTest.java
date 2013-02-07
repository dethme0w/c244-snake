package ca.camosun.snake.test;

import static org.junit.Assert.*;

import java.util.Iterator;

import org.junit.Test;

import ca.camosun.snake.HighScores;
import ca.camosun.snake.Score;

public class HighScoresTest {

	@Test
	public void highScoreTest() {

		HighScores scores;

		Score[] scoreData = { new Score("i", 1000), new Score("f", 3000),
				new Score("h", 2000), new Score("e", 4800),
				new Score("d", 4900), new Score("c", 6000),
				new Score("b", 7000), new Score("a", 9000),
				new Score("g", 2600), new Score("j", 900), };

		scores = new HighScores(10);

		for (int i = 0; i < scoreData.length; i++) {
			scores.addScore(scoreData[i].getName(), scoreData[i].getScore());

		}

		Iterator<Score> iterator = scores.iterator();

		assertTrue(iterator.hasNext() == true);
		assertTrue(iterator.next().equals(new Score("a", 9000)));
		assertTrue(iterator.next().equals(new Score("b", 7000)));
		assertTrue(iterator.next().equals(new Score("c", 6000)));
		assertTrue(iterator.next().equals(new Score("d", 4900)));
		assertTrue(iterator.next().equals(new Score("e", 4800)));
		assertTrue(iterator.next().equals(new Score("f", 3000)));
		assertTrue(iterator.next().equals(new Score("g", 2600)));
		assertTrue(iterator.next().equals(new Score("h", 2000)));
		assertTrue(iterator.next().equals(new Score("i", 1000)));
		assertTrue(iterator.next().equals(new Score("j", 900)));

	}
	
	@Test
	public void highScoreMaxSize() {
		HighScores scores;
		scores = new HighScores(3);
		
		Score a = new Score("a", 1);
		Score b = new Score("b", 2);
		Score c = new Score("c", 3);
		Score d = new Score("d", 4);
		
		assertTrue(scores.size()==0);
		
		scores.addScore(a.getName(), a.getScore());
		assertTrue(scores.size() == 1);
		
		scores.addScore(b.getName(), b.getScore());
		assertTrue(scores.size() == 2);
		
		scores.addScore(c.getName(), c.getScore());
		assertTrue(scores.size() == 3);
		
		scores.addScore(d.getName(), d.getScore());
		assertTrue(scores.size() == 3);
		
		Iterator<Score> iterator = scores.iterator();

		assertTrue(iterator.next().equals(d));
		assertTrue(iterator.next().equals(c));
		assertTrue(iterator.next().equals(b));
		
		assertTrue(iterator.hasNext() == false);
		
	}

}
