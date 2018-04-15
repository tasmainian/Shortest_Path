package cas2xb3_A2_hassan_th;

import java.util.*;
import java.io.*;
import java.lang.Iterable;

//Reference to page 569 of Algorithms textbook
public class Digraph {
	private final int V;
	private int E;
	private Bag<Integer>[] adj;
	private ArrayList<USCitiesADT> cities;
	
	public Digraph(ArrayList<USCitiesADT> cities){
		this.V = 32; //Cities in the US;
		this.E = 0;
		this.cities = cities;
		adj = (Bag<Integer>[]) new Bag[V];
		
		for (int v = 0; v < V; v++){
			USCitiesADT city = cities.get(v);
			adj[city.getIndex()] = city.getBag();		
		}
	}
	
	public int V(){
		return V;
	}
	
	public int E(){
		return E;
	}
	
	public void addEdge(int v, int w){
		adj[v].add(w);
		E++;
	}
	
	public Iterable<Integer> adj(int v){
		return (Iterable<Integer>) adj[v];
	}
	
	public Digraph reverse(){
		Digraph R = new Digraph(cities);
		for (int v = 0; v < V; v++)
			for (int w: adj(v))
				R.addEdge(w, v);
		return R;
	}
}
