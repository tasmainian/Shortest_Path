package cas2xb3_A2_hassan_th;
//Reference to page 643 of Algorithms textbook
import java.util.*;
public class EdgeWeightedDigraph
{
	private final int V; // number of vertices
	private int E; // number of edges
	private Bag<DirectedEdge>[] adj; // adjacency lists
	private ArrayList<USCitiesADT> cities;

	public EdgeWeightedDigraph(ArrayList<USCitiesADT> cities)
	{
		this.V = 32;
		this.E = 0;
		adj = (Bag<DirectedEdge>[]) new Bag[V];
		
		for (int v = 0; v < V; v++){
			USCitiesADT city = cities.get(v);
			adj[city.getIndex()] = city.getBag2();		
		}
	}
	
	//public EdgeWeightedDigraph(In in)
	// See Exercise 4.4.2.
	public int V() { return V; }
	public int E() { return E; }
	public void addEdge(DirectedEdge e)
	{
		adj[e.from()].add(e);
		E++;
	}
	public Bag<DirectedEdge> adj(int v)
	{ return adj[v]; }
	public Iterable<DirectedEdge> edges()
	{
		Bag<DirectedEdge> bag = new Bag<DirectedEdge>();
		for (int v = 0; v < V; v++)
			for (DirectedEdge e : adj[v])
				bag.add(e);
		return bag;
	}
}