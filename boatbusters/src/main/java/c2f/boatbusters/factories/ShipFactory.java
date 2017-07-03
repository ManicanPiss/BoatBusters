package c2f.boatbusters.factories;

import c2f.boatbusters.interfaces.WarShipInterface;

import c2f.boatbusters.classes.*;

public class ShipFactory {

	public WarShip getType(int type) {
		switch (type) {
		case 0:
			return new WarShip(0);
		case 2:
			return new WarShip(2);
		case 3:
			return new WarShip(3);
		case 4:
			return new WarShip(4);

		default:
			Main.getLogger().info("ERROR: Can not create a ship of this type!"); // TODO:
																					// log
			return null;
		}
	}
}
