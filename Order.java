import java.time.LocalTime;
import java.util.*;
public class Order
{
	List<Pizza> pizzas;
	LocalTime time;
    String notes;
    boolean status;

	public Order(List<Pizza>pizzas, LocalTime time, String notes, boolean status)
	{
		this.pizzas = pizzas;
		this.time = time;
    	this.notes = notes;
    	this.status = status;
	}

	public List<Pizza> getPizza()
	{
		return pizzas;
	}

	public LocalTime getTime()
	{
		return time;
	}
   
   public String getNotes()
	{
		return notes;
	}
   
   public boolean getStatus()
   {
      return status;
   }

   public double getPrice()
   {
	   double sum =0;
	   for (Pizza pizzas: pizzas)
	   {
		   sum+= pizzas.getPrice();
	   }
	   return sum;
   }

	public void setPizzas()
	{
		this.pizzas = pizzas;
	}
	
	public void setTime()
	{
		this.time = time;
	}
   
	public void setNotes()
	{
		this.notes = notes;
	}   
   
   public void setStatus(Boolean status)
   {
      this.status = status;
   }

	public void toPrint()
	{
		String statusText = status ? "Færdiggjort" : "Ikke færdig";
		for(Pizza pizza: pizzas)
		{
			pizza.toPrint();
		}
      	System.out.println("Ordren skal være klar til: " +time);
	  	if(!notes.isEmpty())
	  	{
			  System.out.println("Note: \""+notes+"\"");
	  	}
	  	System.out.println("Status: "+statusText);
	}


	public String toString()
	{
		String statusText = status ? "Færdiggjort" : "Ikke færdig";
		StringBuilder builder = new StringBuilder();
		for(Pizza pizza:pizzas)
		{
			builder.append(pizza.toString()).append("\n");
		}
		builder.append("Ordren skal være færdig: ").append(time).append("\n");
		builder.append("Status: ").append(statusText);
				//String nameTrimmed = name.substring(0,name.length()-1);
				return builder.toString();
	}

}