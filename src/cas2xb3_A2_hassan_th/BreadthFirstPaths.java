package cas2xb3_A2_hassan_th;
import java.util.*; 
import java.io.*;
import java.lang.Iterable;

//Reference to page 540 of Algorithms textbook
public class BreadthFirstPaths {
	private boolean[] marked;
	private int[] edgeTo;
	private final int s;

	public BreadthFirstPaths(Digraph G, int s){
		marked = new boolean[G.V()];
		edgeTo = new int[G.V()];
		this.s = s;
		bfs(G, s);
	}
	
	private void bfs(Digraph G, int s){
		Queue<Integer> queue = new Queue<Integer>();
		marked[s] = true;
		queue.enqueue(s);
		while (!queue.isEmpty()){
			int v = queue.dequeue();
			for(int w: G.adj(v))
				if (!marked[w]){
					edgeTo[w] = v;
					marked[w] = true;
					queue.enqueue(w);
				}
		}
	}
	
	public boolean hasPathTo(int v){
		return marked[v];
	}
	
	public Iterable<Integer> pathTo(int v){
		if (!hasPathTo(v))
			return null;

		Stack<Integer> path = new Stack<Integer>();
		for (int x = v; x != s; x = edgeTo[x])
			path.push(x);
		path.push(s);
		return path;
	}
}
