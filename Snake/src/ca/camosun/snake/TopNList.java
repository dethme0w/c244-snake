package ca.camosun.snake;

public interface TopNList<E extends Comparable<E>> {
	public void addScore(E item);
	public E getFirstOnList();
	public int size();
	public E get(int position);
}
