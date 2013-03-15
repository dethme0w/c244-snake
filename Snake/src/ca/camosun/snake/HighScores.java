package ca.camosun.snake;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class HighScores<E extends Comparable<E>> implements TopNList<E> {
	
	private List<E> aList;
	private int scoresToKeep;
	
	public HighScores(int inScoresToKeep) {
		aList = new ArrayList<E>();
		scoresToKeep = inScoresToKeep;
	}

	public void addScore(E item) {		
		if(aList.size() < scoresToKeep) {
			aList.add(item);
			
			Collections.sort(aList);
			Collections.reverse(aList);
			return;
		}
		
		int position = aList.indexOf(item);
		if(position >= 0) {
			aList.remove(item);
			aList.add(item);
			
			Collections.sort(aList);
			Collections.reverse(aList);
			return;
		}
		
		E smallest = Collections.min(aList);
		
		if(item.compareTo(smallest) > 0) {
			aList.remove(smallest);
			aList.add(item);
			
			Collections.sort(aList);
			Collections.reverse(aList);
			return;
		}
		
		// normal case: ignore since most scores are better
	}
	
	public E getFirstOnList() {
		return Collections.max(aList);
	}
	
	public E getLastOnList(){
		return Collections.min(aList);
	}

	public int size() {
		return aList.size();
	}
	
	public E get(int position) {
		return aList.get(position);
	}
}
