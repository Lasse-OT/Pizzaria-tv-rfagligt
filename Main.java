
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.*;
import java.io.*;
public class Main
{
   public static void main(String[] args)throws FileNotFoundException
   {
      //instantiate scanner to read from console
      Scanner input = new Scanner(System.in);
      
      //instantiate scanner to read from menukort.txt
      Scanner read = new Scanner(new File("menukort.txt"));   
      
      //instantiate a date / time object
      LocalTime currentTime = LocalTime.now().withSecond(0).withNano(0);
      
            
      //instantiate arrayList to hold the menu
      ArrayList<Pizza>allPizzas = new ArrayList<>();

      //read from file menukort.txt and add the pizzas to the arraylist
      allPizzas = readFromFile(read);
      
      //instantiate arraylist to hold orders of the day
      ArrayList<Order> daysOrders = new ArrayList<>();
      
      //instantiate arraylist to hold current orders
      ArrayList<Order> currentOrders = new ArrayList<>();

      //call the menu
   	menu(allPizzas, input,currentOrders,daysOrders);
      
   
   
   }//end of main
   
   
   //this is basically the entire program//
   public static void menu(ArrayList<Pizza> allPizzas, Scanner input,ArrayList<Order>currentOrders,ArrayList<Order> daysOrders)throws FileNotFoundException
   {
   	System.out.println();
   	System.out.println("Velkommen, vælg mellem følgende valgmuligheder");
   	
   	System.out.println("1\tVis menukort\n" +
   	"2\tLav bestilling\n" +
   	"3\tVis åbne bestillinger\n" +
   	"4\tFærdiggør bestilling\n" +
   	"5\tFjern bestilling\n" +
    "6\tSe bestillinger for hele dagen\n"+
   	"7\tGem og luk");
   	int navigator = input.nextInt();
   	switch(navigator)
   	{
   		case 1:
   		displayMenu(allPizzas);
   		menu(allPizzas, input,currentOrders,daysOrders);
   		break;
   
   		case 2:
   		placeOrder(input, currentOrders,allPizzas);
   		menu(allPizzas, input,currentOrders,daysOrders);
   		break;
   
   		case 3:
   		showOrders(currentOrders,input);
   		menu(allPizzas, input,currentOrders,daysOrders);
   		break;
   
   		case 4:
   		finishOrder(currentOrders,daysOrders, input);
   		menu(allPizzas, input,currentOrders,daysOrders);
   		break;
   
   		case 5:
   		removeOrder(currentOrders,daysOrders, input);
   		menu(allPizzas, input,currentOrders,daysOrders);
   		break;

        case 6:
        showOrders(daysOrders,input);
        menu(allPizzas, input,currentOrders,daysOrders);

   		case 7:
   		writeToFile(daysOrders,allPizzas);
   		System.exit(navigator);
   
   		default:
   		menu(allPizzas, input,currentOrders,daysOrders);
   		
   		
   	}
   }//end of menu
   
   public static ArrayList<Pizza> readFromFile(Scanner read)
   {
      ArrayList<Pizza>allPizzas = new ArrayList<>();
      while (read.hasNextLine())
      {
         String line = read.nextLine();
         Scanner lineScan = new Scanner(line);
         
         //read pizzanumber
         int number = lineScan.nextInt();
                  
         //read pizzaname
         String name = lineScan.next();
                  
         //read toppings
         String toppings = "";
         while (lineScan.hasNext() && !lineScan.hasNextInt()) 
         {
            toppings += lineScan.next() + " ";
         }
         
         //lineScan.nextLine();
         int price = lineScan.nextInt();
         
         //create pizza and add it to arrayList
         Pizza tempPizza = new Pizza(number,name,toppings,price);
         allPizzas.add(tempPizza);
      }
      return allPizzas;
   }//end of readFromFile
   
   //calls the toPrint to every pizza in the menu//
   public static void displayMenu(ArrayList<Pizza> allPizzas)
   {
   	for(Pizza tempPizza: allPizzas)
   		tempPizza.toPrint();
   }//end of displayMenu
   
   public static void placeOrder(Scanner input, ArrayList<Order>currentOrders, ArrayList<Pizza>allPizzas)
   {

      int i=0, orderMore=0;
      Pizza tempPizza=null;
      List<Pizza> tempPizzas = new ArrayList<>();
      do {
          System.out.println("1\tPizza fra menu\n" +
                  "2\tCustom pizza\n" +
                  "3\tAnnuller\n");
          int navigator = input.nextInt();
          switch (navigator) {
              case 1:
                  System.out.println("Hvilken pizza skal der tilføjes?");
                  i = input.nextInt();
                  tempPizzas.add(allPizzas.get(i-1));
                  input.nextLine();
                  break;

              case 2:
                  String name = "Custom";
                  int yesNo = 0, number = 0;
                  System.out.println("Hvad for nogle toppings skal den have?");
                  String toppings = input.next();
                  System.out.println("Skal der tilføjes yderligere toppings?\n" +
                          "1\tNej\n" +
                          "2\tJa\n");
                  input.nextLine();
                  yesNo = input.nextInt();

                  switch (yesNo) {
                      case 1:
                          break;

                      case 2:
                          System.out.println("Indtast yderligere toppings: ");
                          input.nextLine();
                          String toppingsAdd = input.nextLine();
                          toppings += ", "+toppingsAdd;
                          System.out.println(toppingsAdd + " tilføjet");
                          break;

                  }
                  System.out.println("Indtast pris for custom pizza");
                  int price = input.nextInt();
                  tempPizza = new Pizza(number, name, toppings, price);
                  tempPizzas.add(tempPizza);
                  break;

              default:
                  break;
          }

          System.out.println("Skal der tilføjes flere pizzaer til ordren?\n" +
                  "1\tja\n" +
                  "2\tnej\n");
          orderMore = input.nextInt();
      }
      while(orderMore==1);

      System.out.println();
      LocalTime time = LocalTime.now();
      System.out.println("Hvad tid skal ordren være klar?\n"+
      "1\t Snarest muligt (15 minutter)\n"+
      "2\t En halv time\n"+
      "3\t En time\n"+
      "4\t Indtast tid");
      int timeCase = input.nextInt();
      switch(timeCase)
      {
         case 1:
             time = time.plusMinutes(15).withSecond(0).withNano(0);
         
         break;
                  
         case 2:
             time = time.plusMinutes(30).withSecond(0).withNano(0);
         break;
                  
         case 3:
             time = time.plusHours(1).withSecond(0).withNano(0);
         break;
                  
         case 4:
             System.out.println("Indtast tiden ordren skal være klar til");
             Scanner splitScan = new Scanner(System.in);
             splitScan.useDelimiter(":");
             String setTime = splitScan.nextLine();
             String[] timeSplit = setTime.split(":");
             int hour = Integer.parseInt(timeSplit[0]);
             int minute = Integer.parseInt(timeSplit[1]);


             LocalTime pizzaTime = LocalTime.of(hour,minute);
             time = pizzaTime;

         break;
                  
         default:
             time = time.plusMinutes(15).withSecond(0).withNano(0);

      }
   System.out.println("Skal der tilføjes en note til ordren, så skriv den her, ellers bare klik videre: ");
   input.nextLine();
   String notes = input.nextLine();
   Order newOrder = new Order(tempPizzas,time,notes,false);
   System.out.println("Prisen for ordren er: "+newOrder.getPrice()+"kr.");
   currentOrders.add(newOrder);
   Collections.sort(currentOrders, new OrderComparator());
   }//end of addOrder
      
   
   public static void showOrders(ArrayList<Order>orders, Scanner input)
   {
      int i =1;
      if(!orders.isEmpty())
      {
          System.out.println("Her er ordreoversigten: \n");

          for (Order tempOrder : orders) {
              System.out.println("Ordrenummer: " + i);
              System.out.println();
              tempOrder.toPrint();
              System.out.println();
              i++;
          }
      }
      else
      {
          System.out.println("Ingen ordre tilføjet til listen endnu");
      }
   }//end of showOrders

   public static void finishOrder(ArrayList<Order> currentOrders, ArrayList<Order>daysOrders, Scanner input)
   {
       showOrders(currentOrders,input);
       if(!currentOrders.isEmpty()) {
           System.out.println("Hvilken ordrer ønsker du at afslutte?: ");
           int finishIndex = input.nextInt() - 1;
           Order orderToFinishDays = currentOrders.get(finishIndex);
           orderToFinishDays.setStatus(true);
           daysOrders.add(orderToFinishDays);
           currentOrders.remove(finishIndex);
           System.out.println("Order listen er blevet opdateret");
       }
       else
       {
           System.out.println("Ingen ordre at fuldføre");
       }
   }


    public static void removeOrder(ArrayList<Order> currentOrders, ArrayList<Order>daysOrders, Scanner input)
    {
        showOrders(currentOrders,input);
        System.out.println("Hvilken ordrer ønsker du at annullere?: ");
        int deleteIndex = input.nextInt()-1;
        currentOrders.remove(deleteIndex);
        System.out.println("Order listen er blevet opdateret");

    }

   public static void writeToFile(ArrayList<Order>orders,ArrayList<Pizza> allPizzas)throws FileNotFoundException
   {
   LocalDate currentDate = LocalDate.now();
   HashMap<Pizza, Integer> salesMap = new HashMap<>();
   String fileName = "Ordrer_for_"+currentDate+".txt";
   PrintStream out = new PrintStream(new File(fileName));
   int i = 1;
   double cashFlow =0;
   for(Order tempOrder:orders)
   {
       cashFlow += tempOrder.getPrice();
       out.println("Ordrenummer: " + i);
       out.println(tempOrder.toString());
       i++;
       out.println();


       for (Pizza tempPizza: tempOrder.getPizza())
       {
           if(salesMap.containsKey(tempPizza))
           {
               salesMap.put(tempPizza, salesMap.get(tempPizza)+1);
           }
           else
           {
               salesMap.put(tempPizza,1);
           }
       }
   }
   out.println();
   out.println("I dag er salgene fordelt således");
   for(Pizza key: salesMap.keySet())
   {
       int salesCount = salesMap.get(key);
       out.println(key.getPizzaNumber()+":"+key.getName()+" antal solgte: "+salesCount);
   }
   //Map<Integer, Integer> treeMap = new TreeMap<>(salesMap);
   out.println();
   out.println("Cashflow for the day: ");
   out.println(cashFlow);
   out.println();
   
   }//end of writeToFile
}//end of class




