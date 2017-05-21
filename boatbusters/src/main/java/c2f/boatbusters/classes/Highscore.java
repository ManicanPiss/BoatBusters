package c2f.boatbusters.classes;

import java.util.ArrayList;

import org.apache.logging.log4j.core.Logger;


//import java.util.Collection;
//import java.util.HashSet;

public class Highscore {
	

	static final ArrayList<Player> bestenliste = new ArrayList <>();
	
	public static void printBestenliste (){
		Main.getLogger().info("Player            | NumberOfWins\n------------------+---------------");
		for (int i = 0; i < bestenliste.size(); i++){
			Main.getLogger().info(bestenliste.get(i));
	
		}
	}
	
    public static int searchForPlayerWithMostWins(){
	   int mostWins = Integer.parseInt(toString2(bestenliste.get(0).numberOfWins()));
	   int indexOfPlayerWithMostWins = 0;
    for (int i = 1; i < bestenliste.size(); i++){
 	   if  (Integer.parseInt(toString2(bestenliste.get(i).numberOfWins())) >  mostWins){
 		   mostWins = Integer.parseInt(toString2(bestenliste.get(i).numberOfWins()));
 		   indexOfPlayerWithMostWins = i;
 	   } 
    }
    return indexOfPlayerWithMostWins;
}
    
    public static String toString2(Object numberOfWins){
    	return (String) numberOfWins;
    }
    
    
    public static void sortArrayList(){
    int indexOfPlayerWithMostWins = searchForPlayerWithMostWins();
    for (int j = 0; j < indexOfPlayerWithMostWins; j++){
    for (int i = 0; i < bestenliste.size()-1; i++){
    
    if(Integer.parseInt(toString2(bestenliste.get(i).numberOfWins())) - Integer.parseInt(toString2(bestenliste.get(i+1).numberOfWins())) < 0){
        Player o = bestenliste.get(i);
 	   bestenliste.set(i, bestenliste.get(i+1));
 	   bestenliste.set(i+1, o);
      }
    }
  }
}

   
}
     
       