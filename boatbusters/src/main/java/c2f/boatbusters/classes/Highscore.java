package c2f.boatbusters.classes;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.stream.Collectors;

import c2f.boatbusters.factories.PlayerFactory;

public class Highscore {

	private static ArrayList<Player> bestenliste = new ArrayList<>();

	public ArrayList<Player> getBestenliste() {
		return bestenliste;
	}

	public void setBestenliste(ArrayList<Player> bestenliste) {
		Highscore.bestenliste = bestenliste;
	}

	// Gibt die bestenliste in einem bestimmten Format (gewährleistet durch die
	// toString2-Methode aus.
	public static void printBestenliste() {
		Main.getLogger()
				.info("Player            | NumberOfWins\n                  "
						+ "                                                                "
						+ "                ------------------+---------------");
		bestenliste.forEach(o -> Main.getLogger().info(o.toString2()));
	}

	// Wenn die Methode einen Spieler findet, der mehr Siege (numberOfWins) hat,
	// als die Person, die vor ihm in der
	// ArrayList bestenliste gelistet ist, vertauscht sie die beiden Spieler in
	// bestenliste, und gibt die Information,
	// dass es eine Stelle mit falscher Reihenfolge gab, über den boolean
	// wrongOrder weiter. Der Boolean sortOn wird
	// gesetzt auf den Wert von wrongOrder, also true, und es findet noch ein
	// Schleifendurchlauf statt.
	// Am Anfang der Schleife wird der boolean wrongOrder erstmal auf false
	// gesetzt. Und wenn dann kein Spieler mehr
	// gefunden wird, der falsch platziert ist, wird eben auch sortOn am Ende
	// der Schleife auf false gesetzt und es findet
	// kein weiterer Schleifendurchlauf statt.

	// Für die Galerie, die eigene Sortier-Methode
	public void sortArrayListOld() {

		boolean sortOn = true;
		while (sortOn) {
			boolean wrongOrder = false;
			for (int i = 0; i < bestenliste.size() - 1; i++) {

				if ((bestenliste.get(i).getNumberOfWins()) - (bestenliste.get(i + 1).getNumberOfWins()) < 0) {
					Player o = bestenliste.get(i);
					bestenliste.set(i, bestenliste.get(i + 1));
					bestenliste.set(i + 1, o);
					wrongOrder = true;
				}
			}
			sortOn = wrongOrder;
		}
	}

	// Stream wird sortiert und zu Liste gemacht, danach übergeben an
	// result, result wird zu ArrayList gecastet
	// und zurückgegeben
	public ArrayList<Player> sortBestenlisteStream() {

		List<Player> result = bestenliste.stream()
				.sorted((p1, p2) -> Integer.compare(p2.getNumberOfWins(), p1.getNumberOfWins()))
				.collect(Collectors.toList());
		return (ArrayList<Player>) result;
	}

	// Geht mit dem Iterator die ArrayList bestenliste durch und checkt, ob
	// diese den in der GUI eingegebenen
	// Spielernamen schon enthält. Wenn ja, gibt sie dessen Anzahl an Siegen als
	// String zurück. Wenn nein,
	// gibt sie "0" Siege als String zurüc
	public int checkIfArrayListContainsName(String namePlayer) {
		Iterator<Player> itr = bestenliste.iterator();
		while (itr.hasNext()) {
			if (itr.next().getName().equals(namePlayer)) {
				return itr.next().getNumberOfWins();
			}
		}
		return 0;
	}

	// Diese Methode wird ausgeführt, nachdem ein Spieler gewonnen hat. Sie
	// aktualisiert die Anzahl der Siege
	// des Spielers und gibt den Spieler neu an die Array List am gleichen Index
	// zurück
	public void updateBestenliste(Player player) {
		int index = Main.getHighscore().getBestenliste().indexOf(player);
		int wins = Main.getHighscore().getBestenliste().get(index).getNumberOfWins();
		PlayerFactory pf2 = new PlayerFactory();
		wins++;
		Main.getHighscore().getBestenliste().set(index, pf2.createPlayer(player.getName(), wins));
	}

}
