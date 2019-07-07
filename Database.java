package database;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;

import javax.swing.plaf.metal.OceanTheme;

public class Database {

	static HashMap<String, ArrayList> coordinates = new HashMap<String, ArrayList>();

	static HashMap shipDirection = new HashMap<String, String>();

	public boolean validateCoordinates(String ship, int x, int y,int maxWidth, int maxHeight) {

		if (coordinates.get(ship).size() == 1) {
			String[] xy = (String[]) ((String) coordinates.get(ship).get(0)).split("");
			int x_dir = Integer.parseInt(xy[0]);
			int y_dir = Integer.parseInt(xy[1]);
			System.out.println(x_dir + " " + y_dir);
			System.out.println(x + " " + y);
			if (x == x_dir + 1 && y == y_dir && x + 4 <= maxWidth ) {
				System.out.println(x+ "is "+maxWidth);
				shipDirection.put(ship, "+x");
				System.out.println("valid");
				return true;
			} else if (x == x_dir - 1 && y == y_dir &&  x - 4 >= 0) {
				shipDirection.put(ship, "-x");
				System.out.println("valid");
				return true;
			} else if (y == y_dir + 1 && x == x_dir && y + 4 <= maxHeight) {
				shipDirection.put(ship, "+y");
				System.out.println("valid");
				return true;
			} else if (y == y_dir - 1 && x == x_dir && y - 4 >= 0) {
				shipDirection.put(ship, "-y");
				System.out.println("valid");
				return true;
			} else {
				return false;
			}
		} else if (coordinates.get(ship).size() > 1) {
			String[] xy = (String[]) ((String) coordinates.get(ship).get(coordinates.get(ship).size()-1)).split("");
			int x_dir = Integer.parseInt(xy[0]);
			int y_dir = Integer.parseInt(xy[1]);
			switch ((String) shipDirection.get(ship)) {

			case "+x":
				if (x == x_dir + 1 && y == y_dir) {
					return true;
				}
				break;
			case "-x":
				if (x == x_dir - 1 && y == y_dir) {
					return true;
				}
				break;
			case "+y":
				if (x == x_dir && y == y_dir + 1) {
					return true;
				}
				break;
			case "-y":
				if (x == x_dir && y == y_dir - 1) {
					return true;
				}
				break;

			}
			return false;

		}

		return true;
	}

	public boolean addCoordinates(String ship, String coordinates, int maxWidth, int maxHeight) {
		String[] xy = coordinates.split("");
		int x = Integer.parseInt(xy[0]);
		int y = Integer.parseInt(xy[1]);

//		
		if (this.coordinates.containsKey(ship)) {

			if (validateCoordinates(ship, x, y, maxWidth,maxHeight)) {
				ArrayList<String> value = (ArrayList) Database.coordinates.get(ship);
				value.add(coordinates);
				this.coordinates.put(ship, value);
			} else {
				return false;
			}
		} else {

			ArrayList<String> value = new ArrayList<>();
			value.add(coordinates);
			this.coordinates.put(ship, value);
		}
//		}
		System.out.println(this.coordinates);
		return true;
	}

}
