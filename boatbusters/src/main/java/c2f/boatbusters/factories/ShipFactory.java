package c2f.boatbusters.factories;

import c2f.boatbusters.interfaces.WarShipInterface;
import c2f.boatbusters.classes.*;


public class ShipFactory {

	public WarShipInterface getType (int type){
		switch (type) {
		case 1 : return new WarShip(2);
		case 2 : return new WarShip(3);
		case 3 : return new WarShip(4);
	
		default: System.out.println("ERROR: Can not create a ship of this type!"); // TODO: log
		return null;
		}
	}
}
