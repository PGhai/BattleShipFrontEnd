package controller;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.concurrent.ThreadLocalRandom;

import database.Database;
public class Player2 {
	
	int player2[][]=new int[10][10];
	Player1 p1;
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
	ArrayList<String> Carrier_cd=new ArrayList<String>();	
	ArrayList<String> Battleship_cd=new ArrayList<String>();	
	ArrayList<String> Cruiser_cd=new ArrayList<String>();	
	ArrayList<String> Submarine_cd=new ArrayList<String>();	
	ArrayList<String> Destroyer_cd=new ArrayList<String>();	
	HashSet attackedPositions = new HashSet<String>();
	int counter =0;
	public Player2() {
		initialize_board_and_ships();
		setup_board_for_AI();
	}
	public void set_player1_object(Player1 p1) {
		this.p1=p1;
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
			counter++;
			response = "carrier#c#";
//			return "carrier";
		}
			
		else if(Battleship_cd.isEmpty() && check_Battleship) {
			check_Battleship=false;
			counter++;
			response = "battleship#c#";
		}
			
		else if(Cruiser_cd.isEmpty() && check_Cruiser)
		{
			check_Cruiser=false;
			counter++;
			response = "cruiser#c#";
		}
			
		else if(Submarine_cd.isEmpty()&& check_Submarine) {
			check_Submarine=false;
			counter++;
			response = "submarine#c#";
		}
			
		else if(Destroyer_cd.isEmpty()&& check_Destroyer) {
			check_Destroyer=false;
			counter++;
			response = "destroyer#c#";
		}
		
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
				player2[i][j]=0;
			}
		}
		return " ";
	}
	
	public String setup_board_for_AI() {
		
//		ArrayList<String> ships=new ArrayList<String>();
//		ships.add("Carrier");
//		ships.add("Battleship");
		while(true) {
		System.out.println("shshhshs"+ships.size());
		int x = ThreadLocalRandom.current().nextInt(0, 10);
		int y = ThreadLocalRandom.current().nextInt(0, 10);
		ships.get(0);
		int ship_idx = ThreadLocalRandom.current().nextInt(0, ships.size());
		System.out.println( ship_idx );
		int ship_size = ships.get(ship_idx);
		System.out.println( ship_size );
		boolean up = false;
		boolean down = false;
		boolean left = false;
		boolean right = false;
		boolean done =false;
		boolean process=false;
		try {
			int test = player2[x-ship_size][y]; //left 
			for (int i=x-ship_size;i<x;i++) {
				if (player2[i][y]==1)
				{
					left=true;
					break;
				}
			}
			if (left==false) {
				for (int i=x-ship_size;i<x;i++) {
					player2[i][y]=1;
					set_coordinates(ship_size,i+""+y);
					done=true;
				}
			}
			
		}
		catch(Exception e) {
		}
		try {
			int test=player2[x][y+ship_size]; //up
			for (int i=y;i<y+ship_size;i++) {
				if (player2[x][i]==1)
				{
					up=true;
					break;
				}
			}
			if (up==false && done==false) {
				for (int i=y;i<y+ship_size;i++) {
					player2[x][i]=1;
					set_coordinates(ship_size,x+""+i);
					process=true;
				}
			}
			if (process==true && up==false && done==false) {
				done=true;
			}
		}
		catch(Exception e1) {
		}
		try {
			int test=player2[x+ship_size][y]; //right
			for (int i=x;i<x+ship_size;i++) {
				if (player2[i][y]==1)
				{
					right=true;
					break;
				}
				
			}
			
			if (right==false && done==false) {
				for (int i=x;i<x+ship_size;i++) {
					player2[i][y]=1;
					set_coordinates(ship_size,i+""+y);
					process=true;
				}
			}
			if (process==true && right==false && done==false) {
				done=true;
			}

		}
		
		catch(Exception e2) {
		}

		try {
			int test=player2[x][y-ship_size]; //down
			for (int i=y-ship_size;i<y;i++) {
				if (player2[x][i]==1)
				{
					down=true;
					break;
				}
			}
			if (down==false && done==false) {
				for (int i=y-ship_size;i<y;i++) {
					player2[x][i]=1;
					set_coordinates(ship_size,x+""+i);
					process=true;
				}
			}
			if (process==true && down==false && done==false) {
				done=true;
			}

		}
		catch(Exception e3) {
			
		}

		for (int i=0;i<10;i++)
		{
			for (int j=0;j<10;j++) {
				System.out.print(player2[i][j]+" ");
			}
			System.out.println();
		}


		if (done==true) {
			ships.remove(ship_idx);
		}
		if(ships.isEmpty()) {
			break;
		}
		
		if(ship_size==3 && first_cruise) {
			first_cruise=false;
		}
		
		}
		 
		return " ";
	}
	
	public String attack_AI() {
		if (!Database.dice) {
			int x = ThreadLocalRandom.current().nextInt(0, 10);
			int y = ThreadLocalRandom.current().nextInt(0, 10);
//			System.out.println("Enemy hitting on loc: "+x+"--"+y);
			
			if(!attackedPositions.contains(x+""+y)) {
				
				attackedPositions.add(x+""+y);
				String msg = p1.attack_received(x, y);
				if (msg.equalsIgnoreCase("invalid point")) {
					return attack_AI();
				} else {
					Database.dice =true;
					return msg;
				}
			}else {
				return attack_AI();
			}
			 
		}else {
			return null;
		}
	}
	public String attack_received(int x,int y) {
		
		if (player2[x][y]==0) {
			player2[x][y]=-1;
			return "miss#c#"+x+"#"+y+"#";
			
		}
		else if (player2[x][y]==1) {
			player2[x][y]=2;
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
	
	public String attack(int x,int y) {
		return p1.attack_received(x, y);
	}


//	public static void main(String[] args) {
//		Player2 g=new Player2();
//		g.initialize_board_and_ships();
//		g.setup_board_for_AI();
//		
//	}

}