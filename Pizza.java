public class Pizza
{
	int pizzaNumber;
	String name;
	String toppings;
	int price;
	
	
	public Pizza(int pizzaNumber,String name, String toppings, int price)
	{
		this.pizzaNumber = pizzaNumber;
		this.name = name;
		this.toppings = toppings;
		this.price = price;
	}

	public int getPizzaNumber()
	{
		return pizzaNumber;
	}

	public String getName()
	{
		return name;
	}

	public String getToppings()
	{
		return toppings;
	}

	public int getPrice()
	{
		return price;
	}

	public void setToppings(String toppings)
	{
		this.toppings = toppings;
	}
   
   public void addToppings(String additions)
   {
      this.toppings = toppings + additions;
   }

	public void setPrice()
	{
		this.price = price;
	}

	public void toPrint()
	{
		System.out.println(+pizzaNumber +" "+ name+ " "+ toppings+" "+ price+"kr.");
	}

	public String toString(){
		return (pizzaNumber + " " + name+" "+ toppings+" "+price +"kr.");
	}

}//end of pizza



