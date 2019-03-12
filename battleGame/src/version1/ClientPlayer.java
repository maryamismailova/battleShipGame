package version1;

import java.util.Scanner;

public class ClientPlayer extends Player {

	@Override
	public void initializeBoard() {
		System.out.println("Player "+this.name+" enter boat coordinates\n");
		for(int i=0;i<5;i++) {
			System.out.println("Boat "+i);
			Scanner in=new Scanner(System.in);
			String pointString=in.nextLine();
			String[] points=pointString.split(" ");
			Coordinate[] coordinates=new Coordinate[points.length];
			for(int j=0;j<coordinates.length;j++) {
				coordinates[j]=new Coordinate(points[j]);
			}
			ships[i]=new Ship(coordinates);
			System.out.println("Boat coord: "+ships[i]);
		}
	}
	
	public ClientPlayer(String name) {
		this.name=name;
		ships=new Ship[5]; 
	}
	public void addHitOnPlayer(Coordinate c) {
		for(int i=0;i<ships.length;i++) {
			if(ships[i].contains(c)==true) {
				ships[i].addHit(c);
				break;
			}
		}
	}
	
	@Override
	public Coordinate makeMove() {
		System.out.println("Player "+this.name+" make a move: ");
		Scanner in=new Scanner(System.in);
		Coordinate coord=new Coordinate(in.nextLine());
		return coord;
	}

}
