package c2f.boatbusters.classes;

import java.util.ArrayList;
import java.util.Iterator;

import org.apache.logging.log4j.core.Logger;


//import java.util.Collection;
//import java.util.HashSet;

public class Highscore {
	
	
	private static final ArrayList<Player> bestenliste = new ArrayList <>();


	public static ArrayList<Player> getBestenliste() {
		return bestenliste;
	}

	//Gibt die bestenliste in einem bestimmten Format (gewährleistet durch die toString2-Methode aus.
	public static void printBestenliste() {
		Main.getLogger()
				.info("Player            | NumberOfWins\n                  "
						+ "                                                                "
						+ "                ------------------+---------------");
		bestenliste.forEach(o -> Main.getLogger().info(o.toString2()));
		}
	
	
	
    //Wenn die Methode einen Spieler findet, der mehr Siege (numberOfWins) hat, als die Person, die vor ihm in der
	//ArrayList bestenliste gelistet ist, vertauscht sie die beiden Spieler in bestenliste, und gibt die Information,
	//dass es eine Stelle mit falscher Reihenfolge gab, über den boolean wrongOrder weiter. Der Boolean sortOn wird
	//gesetzt auf den Wert von wrongOrder, also true, und es findet noch ein Schleifendurchlauf statt.
	//Am Anfang der Schleife wird der boolean wrongOrder erstmal auf false gesetzt. Und wenn dann kein Spieler mehr
	//gefunden wird, der falsch platziert ist, wird eben auch sortOn am Ende der Schleife auf false gesetzt und es findet
	//kein weiterer Schleifendurchlauf statt.
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
    

    //Geht mit dem Iterator die ArrayList bestenliste durch und checkt, ob diese den in der GUI eingegebenen
    //Spielernamen schon enthält. Wenn ja, gibt sie dessen Anzahl an Siegen als String zurück. Wenn nein,
    //gibt sie "0" Siege als String zurüc
    public static String checkIfArrayListContainsName(String namePlayer){
		Iterator<Player> itr = bestenliste.iterator();
    	while (itr.hasNext()){
    		if (itr.next().getName().equals(namePlayer)){
    			return (String) itr.next().numberOfWins();   			
    		}   		
    	}
    	return "0";
    }
    
 }

     
       