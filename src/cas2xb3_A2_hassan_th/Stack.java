package cas2xb3_A2_hassan_th;
//Reference to page 149 of Algorithms textbook
import java.util.*;

public class Stack<Item> implements Iterable<Item> {
	private Node<Item>  first;
	private int N;
	
	private static class Node<Item> {
		Item item;
		Node<Item> next;
	}
	
	public boolean isEmpty(){
		return first == null;
	}
	public int size(){
		return N;
	}
	
	public void push(Item item){
		Node<Item>  oldfirst = first;
		first = new Node<Item> ();
		first.item = item;
		first.next = oldfirst;
		N++;
	}
	
	public Item pop(){
		Item item = first.item;
		first = first.next;
		N--;
		return item;
	}
	
	//algs4 website
	public Iterator<Item> iterator() {
		return new ListIterator<Item>(first);
	}
	
	private class ListIterator<Item> implements Iterator<Item> {
		private Node<Item> current;

		public ListIterator(Node<Item> first) {
			current = first;
		}

		public boolean hasNext() {
			return current != null;
		}

		public void remove() {
			throw new UnsupportedOperationException();
		}

		public Item next() {
			if (!hasNext())
				throw new NoSuchElementException();
			Item item = current.item;
			current = current.next;
			return item;
		}
	}
}
