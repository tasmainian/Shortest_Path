package cas2xb3_A2_hassan_th;
/**
 * @author Tasmiha Hassan, hassat3, 400083305
 */
import java.util.*;
import java.io.*;
public class Main {
	
	//Helper method to return the index of a certain city
	public static int cityIndex(String city, ArrayList<USCitiesADT> cities){
		for (int i = 0; i < cities.size(); i++){
			if (city.toUpperCase().equals(cities.get(i).getName()))
				return i;	
		}
		return 0;
	}

	//Writing the file and implementation of the graphing algorithms
  	public static void main(String[] args) throws IOException {
  		
  		//Reading the csv files and storing the data in their specific ADT classes
  		ArrayList<USCitiesADT> list = Parser.parseCity("data/USCities.csv");
  		ArrayList<LongLatADT> mcDonaldsLoc = Parser.parseLongLat("data/mcdonalds.csv");
  		ArrayList<LongLatADT> burgerKingLoc = Parser.parseLongLat("data/burgerking.csv");
  		ArrayList<LongLatADT> wendysLoc = Parser.parseLongLat("data/wendys.csv");
  		ArrayList<MenuADT> menus = Parser.parseMenu("data/menu.csv");  		
  		
  		//Sorts the list of cities alphabetically
  		if (list.size() > 0) {
  		  Collections.sort(list, new Comparator<USCitiesADT>() {
  		      @Override
  		      public int compare(final USCitiesADT object1, final USCitiesADT object2) {
  		          return object1.getName().compareTo(object2.getName());
  		      }
  		  });
  		}
  		
  		//Sets an index value for each city from the sorted cities
  		for (int i = 0; i < list.size(); i++){
  			list.get(i).setIndex(i);
			//System.out.println(list.get(i).getIndex() + " " + list.get(i).getName());
		}
  		
  		//Finds paths connecting these cities and stores the connections
		Paths.findConnections("data/connectedCities.txt", list, mcDonaldsLoc, burgerKingLoc, wendysLoc, menus);
		
		//Creates a digraph of the list of cities
		Digraph direct = new Digraph(list);
		//Finds all the possible paths from Boston using a BreadthFirst and DepthFirst search
		BreadthFirstPaths BFP = new BreadthFirstPaths(direct, cityIndex("BOSTON", list));
		DepthFirstPaths DFP = new DepthFirstPaths(direct, cityIndex("BOSTON", list));

		//Creates the output file
		BufferedWriter writer = new BufferedWriter(new FileWriter("data/a2_out.txt"));

		writer.write("BFS: ");
		//Iterates through each step on its way to the destination city
		for (int i : BFP.pathTo(cityIndex("MINNEAPOLIS", list))) {
			//Writes the names of the cities outputted in the output file
			if (i == cityIndex("MINNEAPOLIS", list))
				writer.write(list.get(i).getName());
			else
				writer.write(list.get(i).getName()+", ");
		}		
		
		//Similar steps and BFS
		writer.write("\nDFS: ");
		for (int i : DFP.pathTo(cityIndex("MINNEAPOLIS", list))) {
			if (i == cityIndex("MINNEAPOLIS", list))
				writer.write(list.get(i).getName());
			else
				writer.write(list.get(i).getName()+", ");
		}
  		
		
		//******************************Shortest Path*********************************************
		writer.write("\n\nCity\t\t\t\t\tRestaurant\t\t\t\t\tMeal\t\t\t\t\tPrice\n");
		
		//Loads the list of cities onto the Digraph
    	EdgeWeightedDigraph edgeW = new EdgeWeightedDigraph(list);
    	
    	//Implements Dijkstras shortest path algorithm
    	DijkstraSP SP = new DijkstraSP(edgeW, cityIndex("BOSTON", list));
    	
    	//Initializes the total cost of the trip
    	double total = 0;
    	
    	//Iterates through each edge on it's path to the destination city
		for (DirectedEdge i : SP.pathTo(cityIndex("MINNEAPOLIS", list))){
			
			//Finds the menu index of the cheapest meal at each city
			int cheap = Paths.cheapestPrice(list.get(i.to()).getIndex(), list, mcDonaldsLoc, burgerKingLoc, wendysLoc, menus);
			
			//Writes the cities it goes through in an output file, along with the meal and price at each stop
			if(list.get(i.from()).getName().equals("BOSTON")){
				writer.write("\n"+list.get(i.from()).getName()+"\t\t\t\t\t--------------\t\t\t--------------\t\t\t--------------"); //Skips the meal of the first city
				writer.write("\n"+list.get(i.to()).getName()+"\t\t\t\t\t"+menus.get(list.get(i.from()).getMenuIndex()).getRes()+"\t\t\t\t"+menus.get(cheap).getMeal()+"\t\t\t\t\t"+menus.get(cheap).getPrice());
				total += menus.get(list.get(i.from()).getMenuIndex()).getPrice();
			}
			else{
				writer.write("\n"+list.get(i.to()).getName()+"\t\t\t\t\t"+menus.get(list.get(i.from()).getMenuIndex()).getRes()+"\t\t\t\t"+menus.get(cheap).getMeal()+"\t\t\t\t\t"+menus.get(cheap).getPrice());
				total += menus.get(list.get(i.from()).getMenuIndex()).getPrice();
			}
		}
		
		//Writes the total value it cost for the trip
		writer.write("\nTotal\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t"+total);


		writer.close();
	}
}