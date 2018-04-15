package cas2xb3_A2_hassan_th;
import java.math.*; 
import java.io.*;
import java.util.*;

//Class to find and load the connected paths
public class Paths {
	
	//Prevents the same meal from being eaten twice in a row
	private static int skip = -1;
	
	
	//Stoeres the connections in a bag
	public static void findConnections(String file, ArrayList<USCitiesADT> cities,
			ArrayList<LongLatADT> mcDonaldsLoc,
			ArrayList<LongLatADT> burgerKingLoc,
			ArrayList<LongLatADT> wendysLoc, 
			ArrayList<MenuADT> menus) throws IOException{
		//Reads the connectedCities.txt file
		Scanner input = new Scanner(new File(file));
		String line;
		String city1 = new String();
		String city2 = new String();
		
		while(input.hasNext()){
			line = input.nextLine();
			//Splits the cities at the comma and stores them in two column arrays
			String[] cols = line.split(", ");
			city1 = cols[0];
			city2 = cols[1];
			
			//Finds the index numbers of the connected cities
			int index1 = BinarySearch.rank(city1.toUpperCase(), cities);
			int index2 = BinarySearch.rank(city2.toUpperCase(), cities); 
			//For BFS and DFS, it adds the index number of the connected city to the bag for the primary city
			cities.get(index1).addToBag(index2);
			
			//For Dijkstra, it calculates the price between the connected cities using cheapestPrice method
			int cheap = cheapestPrice(index2, cities, mcDonaldsLoc, burgerKingLoc, wendysLoc, menus);
			//double dist = Math.sqrt(Math.pow(cities.get(index1).getLat()-cities.get(index2).getLat(), 2)+Math.pow(cities.get(index1).getLong()-cities.get(index1).getLong(), 2));
			
			//Loads the city indices and the weight of the distance in a DirectedEdge
			DirectedEdge edge = new DirectedEdge(index1, index2, menus.get(cheap).getPrice());
			//Adds the directed edge of the connected city to the primary city
			cities.get(BinarySearch.rank(city1.toUpperCase(), cities)).addToBag2(edge);
		}
	}
	
  	//Finds the cheaps priced meal at the chosen city
	public static int cheapestPrice(int c, ArrayList<USCitiesADT> cities,
			ArrayList<LongLatADT> mcDonaldsLoc,
			ArrayList<LongLatADT> burgerKingLoc,
			ArrayList<LongLatADT> wendysLoc, 
			ArrayList<MenuADT> menus){
		
		
		//Looks through the locations of each restuarant and if it is within a 0.5 mile radius of the city, it sets the has methods to true
		for (int i = 0; i < mcDonaldsLoc.size(); i++){
			double dist = Math.sqrt(Math.pow(cities.get(c).getLat()-mcDonaldsLoc.get(i).getLat(), 2)+Math.pow(cities.get(c).getLong()-mcDonaldsLoc.get(i).getLong(), 2));
			if (dist <= Math.sqrt(Math.pow(0.5, 2)+Math.pow(0.5, 2))){
				cities.get(c).setMcD(true);
			}
		}
		for (int i = 0; i < burgerKingLoc.size(); i++){
			double dist = Math.sqrt(Math.pow(cities.get(c).getLat()-burgerKingLoc.get(i).getLat(), 2)+Math.pow(cities.get(c).getLong()-burgerKingLoc.get(i).getLong(), 2));
			if (dist <= Math.sqrt(Math.pow(0.5, 2)+Math.pow(0.5, 2))){
				cities.get(c).setBK(true);
			}
		}
		for (int i = 0; i < wendysLoc.size(); i++){
			double dist = Math.sqrt(Math.pow(cities.get(c).getLat()-wendysLoc.get(i).getLat(), 2)+Math.pow(cities.get(c).getLong()- wendysLoc.get(i).getLong(), 2));
			if (dist <= Math.sqrt(Math.pow(0.5, 2)+Math.pow(0.5, 2))){
				cities.get(c).setWendy(true);
			}
		}
		
		
		//Sorts the menu by price
  		if (menus.size() > 0) {
  			Collections.sort(menus, new Comparator<MenuADT>() {
  			    @Override
  			    public int compare(MenuADT c1, MenuADT c2) {
  			        return Double.compare(c1.getPrice(), c2.getPrice());
  			    }
  			});
    	}
    		
  		//Goes through the menu, and if the city contains the restaurant, and it user does not repeat the meal, it returns the index of the cheapest meal on the menu
    	for (int i = 0; i < menus.size(); i++){
    		if (menus.get(i).getRes().equals("McDonald's") && cities.get(c).hasMcD() && skip != i){
    			skip = i;
    			cities.get(c).setMenuIndex(i);
    			return i;
    		}
    		else if (menus.get(i).getRes().equals("Burger King") && cities.get(c).hasBK() && skip != i){
    			skip = i;
    			cities.get(c).setMenuIndex(i);
    			return i;
    		}
    		else if (menus.get(i).getRes().equals("Wendy's") && cities.get(c).hasWendy() && skip != i){
    			skip = i;
    			cities.get(c).setMenuIndex(i);
    			return i;
    		}
  		}
    	return 0;
	}
	
}
