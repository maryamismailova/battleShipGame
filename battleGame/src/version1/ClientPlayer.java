package version1;

public class ClientPlayer extends Player {

	@Override
	public void initializeBoard() {
		String pointString="A1 A2 A3 A4 A5";
		String[] points=pointString.split(" ");
		for(int i=0;i<points.length;i++) {
			int indexH=((int)points[i].charAt(0))-((int)'A');
			int indexV=Character.getNumericValue(points[i].charAt(1));
			board[indexV][indexH]='+';
		}
	}
	public ClientPlayer(String name) {
		this.name=name;
		this.board=new char[10][10];
		for(int i=0;i<10;i++) {
			for(int j=0;j<10;j++){
				board[i][j]='0';
			}
		}
	}
	@Override
	public void makeMove(String move) {
		// TODO Auto-generated method stub
		
	}

}
