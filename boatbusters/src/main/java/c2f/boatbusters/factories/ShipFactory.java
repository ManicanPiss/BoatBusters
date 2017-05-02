package c2f.boatbusters.factories;

import c2f.boatbusters.interfaces.WarShipInterface;
import c2f.boatbusters.classes.*;


public class ShipFactory {

	public WarShipInterface getType (String type){
		if(type == null){
			return null;
		}	

		if (type.equalsIgnoreCase("SMALL")){
			return new WarShip(2);
		} else if(type.equalsIgnoreCase("MIDDLE")){
			return new WarShip(3);
		} else if(type.equalsIgnoreCase("BIG")){
			return new WarShip(4);
		}

		return null;
	}
}
