package c2f.boatbusters.classes;

import java.util.ArrayList;

import org.apache.logging.log4j.core.Logger;


//import java.util.Collection;
//import java.util.HashSet;

public class Highscore {
	
	private int phase;
	
	public Highscore(int i){
		this.phase = i;
	}

	private static final ArrayList<Player> bestenliste = new ArrayList <>();
	
	public ArrayList<Player> getBestenliste(){
		return bestenliste;
	}
	
	public static void printBestenliste (){
		Main.getLogger().info("Player            | NumberOfWins\n------------------+---------------");
		for (int i = 0; i < bestenliste.size(); i++){
			Main.getLogger().info(bestenliste.get(i));
			
		}
	}
	
   // public static int searchForPlayerWithMostWins(){
   //	   int mostWins = Integer.parseInt((String) bestenliste.get(0).numberOfWins());
   //	   int indexOfPlayerWithMostWins = 0;
   // for (int i = 1; i < bestenliste.size(); i++){
   //   if  (Integer.parseInt((String) bestenliste.get(i).numberOfWins()) >  mostWins){
   //	   mostWins = Integer.parseInt((String) bestenliste.get(i).numberOfWins());
   //	   indexOfPlayerWithMostWins = i;
   // } 
   //}
   //return indexOfPlayerWithMostWins;
   //}

    public static void sortArrayList(){
    
    	boolean sortOn = true;
    	while (sortOn){
    	  boolean wrongOrder = false;
    	  for (int i = 0; i < bestenliste.size()-1; i++){
    	        
    	  if((Integer.parseInt((String) bestenliste.get(i).numberOfWins())
    			  - (Integer.parseInt((String) bestenliste.get(i+1).numberOfWins())) < 0)){
    	           Player o = bestenliste.get(i);
    	     	   bestenliste.set(i, bestenliste.get(i+1));
    	     	   bestenliste.set(i+1, o);
    	     	   wrongOrder = true;
              }
          }
    	  sortOn = wrongOrder;
       }
  } 

    
    public static String checkIfArrayListContainsName(String namePlayer1){
    	for (int i = 0; i < bestenliste.size(); i++){
    		if (bestenliste.get(i).getName().equals(namePlayer1)){
    			return (String) bestenliste.get(i).numberOfWins();   			
    		}   		
    	}
    	return "0";
    }
    
 }

     
       