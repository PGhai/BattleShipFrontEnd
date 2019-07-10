package database;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;

import javafx.scene.Node;

public class Database {

	static public HashMap<String, ArrayList> coordinates = new HashMap<String, ArrayList>();
//	
//	ArrayList<String> Carrier_cd=new ArrayList<String>();	
//	ArrayList<String> Battleship_cd=new ArrayList<String>();	
//	ArrayList<String> Cruiser_cd=new ArrayList<String>();	
//	ArrayList<String> Submarine_cd=new ArrayList<String>();	
//	ArrayList<String> Destroyer_cd=new ArrayList<String>();	
 static HashMap<String,Node> list_of_buttons= new HashMap<String,Node>();
	static HashMap shipDirection = new HashMap<String, String>();
	static public HashSet set = new HashSet<String>();


	public static boolean dice = true;
	public boolean validateCoordinates(String ship, int shipLength, int x, int y, int maxWidth, int maxHeight) {

		if (coordinates.get(ship).size() == 1) {
			String[] xy = (String[]) ((String) coordinates.get(ship).get(0)).split("");
			int x_dir = Integer.parseInt(xy[0]);
			int y_dir = Integer.parseInt(xy[1]);
			System.out.println(x_dir + "---old--" + y_dir);
			System.out.println(x_dir + " " + shipLength);
			System.out.println((x_dir - shipLength) >= 0);
			if (x == x_dir + 1 && y == y_dir && (x_dir + shipLength) <= maxWidth
					&& doValidateDirection(shipLength, x, y, "x_direction")) {
				shipDirection.put(ship, "+x");
				System.out.println("valid");
				return true;
			} else if (x == x_dir - 1 && y == y_dir && (x_dir - shipLength) >= 0
					&& doValidateDirection(shipLength, x, y, "neg-x_direction")) {
				shipDirection.put(ship, "-x");
				System.out.println("valid");
				return true;
			} else if (y == y_dir + 1 && x == x_dir && (y_dir + shipLength) <= maxHeight
					&& doValidateDirection(shipLength, x, y, "y_direction")) {
				shipDirection.put(ship, "+y");
				System.out.println("valid");
				return true;
			} else if (y == y_dir - 1 && x == x_dir && (y_dir - shipLength) >= 0
					&& doValidateDirection(shipLength, x, y, "neg-y_direction")) {
				shipDirection.put(ship, "-y");
				System.out.println("valid");
				return true;
			} else {
				return false;
			}
		} else if (coordinates.get(ship).size() > 1) {
			String[] xy = (String[]) ((String) coordinates.get(ship).get(coordinates.get(ship).size() - 1)).split("");
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

	private boolean doValidateDirection(int shipLength, int x, int y, String direction) {
		System.out.println("x and y" + x + "  " + y);
		System.out.println(set);
		System.out.println(direction);
		boolean returnVal = true;
		switch (direction) {

		case "x_direction":
			for (int i = x; i < x + shipLength; i++) {
				if (set.contains(i + "" + y)) {
					returnVal = false;
					break;
				}
			}
			break;
		case "neg-x_direction":
			for (int i = x; i > x - shipLength; i--) {
				if (set.contains(i + "" + y)) {
					returnVal = false;
					break;
				}
			}
			break;
		case "y_direction":
			for (int i = y; i < y + shipLength; i++) {
				System.out.println(x + "" + i);
				if (set.contains(x + "" + i)) {
					returnVal = false;
					break;
				}
			}
			break;
		case "neg-y_direction":
			for (int i = y; i > y - shipLength; i--) {
				if (set.contains(i + "" + y)) {
					returnVal = false;
					break;
				}
			}
			break;

		}
		// TODO Auto-generated method stub
		return returnVal;
	}

	public boolean addCoordinates(String ship, int shipLength, String coordinates, int maxWidth, int maxHeight) {
		String[] xy = coordinates.split("");
		int x = Integer.parseInt(xy[0]);
		int y = Integer.parseInt(xy[1]);

		String new_coordinates = x+","+y;
		if (this.coordinates.containsKey(ship)) {

			if (validateCoordinates(ship, shipLength - 1, x, y, maxWidth, maxHeight)) {
				ArrayList<String> value = (ArrayList) Database.coordinates.get(ship);
				value.add(coordinates);
				set.add(coordinates);
				this.coordinates.put(ship, value);
			} else {
				return false;
			}
		} else {

			ArrayList<String> value = new ArrayList<>();
			value.add(coordinates);
			set.add(coordinates);
			this.coordinates.put(ship, value);
		}
//		}
		System.out.println(this.coordinates);
		return true;
	}
	public void addButtonToMap(String text,Node node) {
		list_of_buttons.put(text,node);
	}
	public Node getNode(String text) {
		if(list_of_buttons.containsKey(text)) {
			return list_of_buttons.get(text);
		}else {
			return null;
		}
	}

}
