package controller;

import java.util.ArrayList;
import database.*;
public class Player1{
	
	
	Database db  = new Database();
	
	int player1[][]=new int[10][10];
	Player2 p2;
	final int Carrier=5;
	final int Battleship=4;
	final int Cruiser=3;
	final int Submarine=3;
	final int Destroyer=2;
	boolean first_cruise=true;
	boolean check_carrier=true;
	boolean check_Battleship=true;
	boolean check_Cruiser=true;
	boolean check_Submarine=true;
	boolean check_Destroyer=true;
	ArrayList<Integer> ships=new ArrayList<Integer>();	
	ArrayList<String> Carrier_cd= new ArrayList<String>();	
	ArrayList<String> Battleship_cd=new ArrayList<String>();		
	ArrayList<String> Cruiser_cd=new ArrayList<String>();	
	ArrayList<String> Submarine_cd=new ArrayList<String>();	
	ArrayList<String> Destroyer_cd=new ArrayList<String>();	
	int counter =0;
	
	public Player1() {
		
		Carrier_cd= db.coordinates.get("carrier");	
		Battleship_cd=db.coordinates.get("battleShip");	
		Cruiser_cd=db.coordinates.get("cruiser");	
		Submarine_cd=db.coordinates.get("subMarine");
		Destroyer_cd=db.coordinates.get("destroyer");
		initialize_board_and_ships();

	}
	
		public void set_player2_object(Player2 p2) {
			this.p2=p2;
		}
		
		public void set_coordinates(int length, String coodindates) {
			if (length==5)
				Carrier_cd.add(coodindates);
			else if (length==4)
				Battleship_cd.add(coodindates);
			else if (length==3 && first_cruise) {
				Cruiser_cd.add(coodindates);
			}
			else if (length==3)
				Submarine_cd.add(coodindates);
			else if (length ==2)
				Destroyer_cd.add(coodindates);
				
		}
		
		public String get_coordinates(String coodindates) {
			
			String response ="";
			
			if (Carrier_cd.contains(coodindates))
				Carrier_cd.remove(coodindates);
			else if (Battleship_cd.contains(coodindates))
				Battleship_cd.remove(coodindates);
			else if (Cruiser_cd.contains(coodindates))
				Cruiser_cd.remove(coodindates);
			else if (Submarine_cd.contains(coodindates))
				Submarine_cd.remove(coodindates);
			else if (Destroyer_cd.contains(coodindates))
				Destroyer_cd.remove(coodindates);

			if (Carrier_cd.isEmpty()&& check_carrier) {
				check_carrier=false;
				counter= counter+1;
				response = "carrier";
//				return "carrier";
			}
				
			else if(Battleship_cd.isEmpty() && check_Battleship) {
				check_Battleship=false;
				counter= counter+1;
				response = "battleship#c#";
			}
				
			else if(Cruiser_cd.isEmpty() && check_Cruiser)
			{
				check_Cruiser=false;
				counter= counter+1;
				response = "cruiser#c#";
			}
				
			else if(Submarine_cd.isEmpty()&& check_Submarine) {
				check_Submarine=false;
				counter= counter+1;
				response = "submarine#c#";
			}
				
			else if(Destroyer_cd.isEmpty()&& check_Destroyer) {
				check_Destroyer=false;
				counter= counter+1;
				response = "destroyer#c#";
			}
			System.out.println("Counter is"+ counter);
				if(counter == 5) {
					response= response.split("#")[0];
					return response+"#"+"win#";
				}else {
					return response;
				}

			
		}
		
		public String initialize_board_and_ships() {
			ships.add(Carrier);
			ships.add(Battleship);
			ships.add(Cruiser);
			ships.add(Submarine);
			ships.add(Destroyer);
			
			
			
			for (int i=0;i<10;i++)
			{
				for (int j=0;j<10;j++) {
					//System.out.println(i+" "+j);
					player1[i][j]=0;
				}
			}
			return " ";
		}
		

		
		public String attack_received(int x,int y) {
			
//			if (player1[x][y]==0) {
//				player1[x][y]=-1;
//				return "miss";
//				
//			}
			if(!Database.set.contains(x+""+y)) {
				return "miss#c#"+x+"#"+y+"#";
			}
			else if(Database.set.contains(x+""+y)){
				String msg=get_coordinates(x+""+y);
				if (msg.equalsIgnoreCase(""))
					return "hit#c#"+x+"#"+y+"#";
				else {
					return msg+x+"#"+y+"#";
				}
			}
			else {
				return "invalid point";
			}
			
		}
		
//		public static void main(String[] args) {
//			Player1 g=new Player1();
//			g.initialize_board_and_ships();
//			//g.setup_board_for_AI();
//			
//		}
		
		public String attack(int x,int y) {
	return p2.attack_received(x, y);
		}




	}

