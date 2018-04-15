package cas2xb3_A2_hassan_th;
import java.util.Iterator;
//Reference to page 155 of Algorithms textbook
public class Bag<Item> implements Iterable<Item>{
	private Node first;
	
	private class Node{
		Item item;
		Node next;
	}
	
	public void add(Item item){
		Node oldfirst = first;
		first = new Node();
		first.item = item;
		first.next = oldfirst;
	}
	
	public Iterator<Item> iterator(){
		return new ListIterator();
	}
	
	private class ListIterator implements Iterator<Item>{
		private Node current = first;
		public boolean hasNext(){
			return current != null;
		}
		
		public void remove(){
			
		}
		
		public Item next(){
			Item item = current.item;
			current = current.next;
			return item;
		}
	}
}
