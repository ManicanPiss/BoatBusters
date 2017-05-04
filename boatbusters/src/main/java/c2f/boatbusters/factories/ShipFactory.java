package c2f.boatbusters.factories;

//import c2f.boatbusters.interfaces.WarShipInterface;
import c2f.boatbusters.classes.*;


public class ShipFactory {

	public static WarShip getType (int type){
		
		if (type == 1){
			return new WarShip(2);
		} else if(type == 2){
			return new WarShip(3);
		} else if(type == 3){
			return new WarShip(4);
		} else {
			// TODO error log
			return null;
		}
	}
}
