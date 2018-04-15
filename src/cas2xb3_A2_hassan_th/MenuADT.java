package cas2xb3_A2_hassan_th;

//Abstract Data Type for the items on the menu
public class MenuADT {
	private String restaurant;
	private String mealChoice;
	private double price;
	private String comment;
	
	//Constructor
	public MenuADT(String res, String meal, String priceString, String comment){
		String preRes = res.replace("’", "'"); //Has an error reading the apostrophe's in the Wendy's name
		this.restaurant = preRes;
		this.mealChoice = meal;
		String p = priceString.replace("$", "");
		this.price = Double.parseDouble(p);
		this.comment = comment;
	}
	
	
	//Getter methods
	public String getRes(){
		return this.restaurant;
	}
	
	public String getMeal(){
		return this.mealChoice;
	}
	
	public double getPrice(){
		return this.price;
	}
	
	public String comment(){
		return this.comment;
	}
}
