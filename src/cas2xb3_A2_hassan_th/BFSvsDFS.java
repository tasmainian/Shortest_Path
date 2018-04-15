package cas2xb3_A2_hassan_th;
/**
 * @author Tasmiha Hassan, hassat3, 400083305
 */

import static org.junit.Assert.*;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

public class BFSvsDFS {
	private static ArrayList<USCitiesADT> list;
	private static ArrayList<LongLatADT> mcDonaldsLoc;
	private static ArrayList<LongLatADT> burgerKingLoc;
	private static ArrayList<LongLatADT> wendysLoc;
	private static ArrayList<MenuADT> menus;
	
	
	//Loads the files into ADT's and loads all the connectedCities
	public static void setup() throws IOException{
  		list = Parser.parseCity("data/USCities.csv");
  		mcDonaldsLoc = Parser.parseLongLat("data/mcdonalds.csv");
  		burgerKingLoc = Parser.parseLongLat("data/burgerking.csv");
  		wendysLoc = Parser.parseLongLat("data/wendys.csv");
  		menus = Parser.parseMenu("data/menu.csv");  
  		sortCities();
		Paths.findConnections("data/connectedCities.txt", list, mcDonaldsLoc, burgerKingLoc, wendysLoc, menus);
	}
	
	//Sorts the cities alphbetically and assigns them an index value
	public static void sortCities(){
  		if (list.size() > 0) {
    		  Collections.sort(list, new Comparator<USCitiesADT>() {
    		      @Override
    		      public int compare(final USCitiesADT object1, final USCitiesADT object2) {
    		          return object1.getName().compareTo(object2.getName());
    		      }
    		  });
    		}
    		
    		for (int i = 0; i < list.size(); i++){
    			list.get(i).setIndex(i);
    		}
	}

	//Testing the BFP
	@Test
	public void testBFS() throws IOException{
		setup();
		boolean error = false;
		Digraph d = new Digraph(list);
		
		//testing for repetition
		//Boston to Minneapolis
		BreadthFirstPaths BFP = new BreadthFirstPaths(d, Main.cityIndex("BOSTON", list));
		for (int i : BFP.pathTo(Main.cityIndex("MINNEAPOLIS", list))) {
			if(list.get(i).getName()==list.get(i+1).getName()){
				error=true;
			}
		}	
		
		//testing for path length
		//Philadelphia to Baltimore
		BFP = new BreadthFirstPaths(d, Main.cityIndex("Philadelphia".toUpperCase(), list));
		ArrayList<String> path = new ArrayList<String>();;
		for (int i : BFP.pathTo(Main.cityIndex("Baltimore".toUpperCase(), list))) {
			path.add(list.get(i).getName());
		}
		
		if(path.size()!= 2)
			error= true;
			
		if(error)
			fail("The path is not distinct for BFS");
	}
	
	//Testing the DFP
	@Test
	public void testDFS() throws IOException{
		setup();
		boolean error = false;
		Digraph d = new Digraph(list);
		
		//Boston to Minneapolis
		DepthFirstPaths DFP = new DepthFirstPaths(d, Main.cityIndex("BOSTON", list));
		for (int i : DFP.pathTo(Main.cityIndex("MINNEAPOLIS", list))) {
			if(list.get(i).getName()==list.get(i+1).getName()){
				error=true;
			}
		}
		
		//testing for path length
		//Philadelphia to Baltimore
		DFP = new DepthFirstPaths(d, Main.cityIndex("Philadelphia".toUpperCase(), list));
		ArrayList<String> path = new ArrayList<String>();;
		for (int i : DFP.pathTo(Main.cityIndex("Baltimore".toUpperCase(), list))) {
			path.add(list.get(i).getName());
		}
		
		if(path.size()!= 2)
			error= true;

		if(error)
			fail("The path is not distinct for DFS");
	}

}
