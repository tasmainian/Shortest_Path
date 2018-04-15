package cas2xb3_A2_hassan_th;
//Abstract Data Type for the cities in the US
public class USCitiesADT {
	private String name;
	private double latitude;
	private double longitude;
	private boolean McD;
	private boolean BK;
	private boolean Wendy;
	private int menuIndex;
	private Bag<Integer> adj;
	private Bag<DirectedEdge> adj2;
	private int index;
	
	//Constructors
	public USCitiesADT(String name, double latitude, double longitude){
		this.name = name;
		this.latitude = latitude;
		this.longitude = longitude;
		this.McD = false;
		this.BK = false;
		this.Wendy = false;
		adj = new Bag<Integer>();
		adj2 = new Bag<DirectedEdge>();
	}
	
	//Getter methods
	public String getName(){
		return this.name;
	}
	
	public double getLat(){
		return this.latitude;
	}
	
	public double getLong(){
		return this.longitude;
	}
	
	//Boolean getter and setter methods to see and set if it have a restaurant in that city
	public boolean hasMcD(){
		return this.McD;
	}
	
	public void setMcD(boolean has){
		this.McD = has;
	}
	
	public boolean hasBK(){
		return this.BK;
	}
	
	public void setBK(boolean has){
		this.BK = has;
	}
	
	public boolean hasWendy(){
		return this.Wendy;
	}
	
	public void setWendy(boolean has){
		this.Wendy = has;
	}
	
	
	//Assigns a item on the menu to the city
	public int getMenuIndex(){
		return this.menuIndex;
	}
	
	public void setMenuIndex(int i){
		this.menuIndex = i;
	}
	
	
	//Assigns an index number to each city to identify them numerically
	public int getIndex() {
		return index;
	}

	public void setIndex(int i) {
		this.index = i;
	}
	
	
	//Adds connected cities
	
	//For BFS and DFS
	public void addToBag(Integer city) {
		this.adj.add(city);
	}
	
	public Bag<Integer> getBag() {
		return adj;
	}
	
	//For Dijkstras
	public void addToBag2(DirectedEdge city) {
		this.adj2.add(city);
	}
	
	public Bag<DirectedEdge> getBag2() {
		return adj2;
	}

}
