import java.util.Scanner;

public class moduleBackendBoard{
	//PROPERTIES
	int intBoard[][] = new int[6][7];
	int intTurn;
	int intCurrentRow;
		
	//Add Player
	public boolean addPosition(int intCol){
		for(int intRow = 5; intRow >= 0; intRow--){
			int intPiece = intBoard[intRow][intCol];
			if(intPiece == 0){
				if(intTurn % 2 == 0){
					intBoard[intRow][intCol] = 1;
				}else{
					intBoard[intRow][intCol] = 2;
				}
				intTurn++;
				intCurrentRow = intRow;
				return true;
			}
		}
		return false;
	}
		
	//Check For Winner 
	public boolean checkWinner(int intPlayer ){
		//Row
		for(int intRow = 0; intRow < 6; intRow++){
			for(int intCol = 0; intCol < 7-3; intCol++){
				for(int intAdder = 0; intAdder < 4; intAdder++){
					if(intBoard[intRow][intCol+intAdder] != intPlayer){
						break;
					}else if(intAdder == 3){
						return true;
					}
				}
			}
		}			
		
		//Col
		for(int intRow = 0; intRow < 6-3; intRow++){
			for(int intCol = 0; intCol < 7; intCol++){
				for(int intAdder = 0; intAdder < 4; intAdder++){
					if(intBoard[intRow+intAdder][intCol] != intPlayer){
						break;
					}else if(intAdder == 3){
						return true;
					}
				}
			}
		}
		
		//Diagonal L-R
		for(int intRow = 3; intRow < 6; intRow++){
			for(int intCol = 0; intCol < 7 - 3; intCol++){
				for(int intAdder = 0; intAdder < 4; intAdder++){
					if(intBoard[intRow-intAdder][intCol+intAdder] != intPlayer){
						break;
					}else if(intAdder == 3){
						return true;
					}
				}
			}
		}
		
		//Diagonal R-L
		for(int intRow = 0; intRow < 6 - 3; intRow++){
			for(int intCol = 0; intCol < 7 - 3; intCol++){
				for(int intAdder = 0; intAdder < 4; intAdder++){
					if(intBoard[intRow+intAdder][intCol+intAdder] != intPlayer){
						break;
					}else if(intAdder == 3){
						return true;
					}
				}
			}
		}
		
		return false;
	}
		
	//Printout Screen (used for troubleshooting)
	/**
	public void printScreen(){
		System.out.println("0\t1\t2\t3\t4\t5\t6");
		System.out.println("------------------------------------------------------------------------------------------");
		for(int intRow = 0; intRow < 6; intRow++){
			String strRow = "";
			for(int intCol = 0; intCol < 7; intCol++){					
				strRow += intBoard[intRow][intCol] + "\t";
			}
			System.out.println(strRow);
		}
	}
		
	//CONSTRUCTOR
	public moduleBackendBoard(){
		intTurn = 0;
		for(int intRow = 0; intRow < 6; intRow++){
			for(int intCol = 0; intCol < 7; intCol++){
				intBoard[intRow][intCol] = 0;
			}
		}
	}

	//MAIN METHOD
	public static void main(String[] args) {
		moduleBackendBoard board = new moduleBackendBoard();
		while(!board.checkWinner(board.intTurn%2+1)){
			System.out.println(board.checkWinner(board.intTurn%2+1));
			board.printScreen();
			Scanner scan = new Scanner(System.in);
			int intCol = scan.nextInt();
			board.addPosition(intCol);
		}
	}
}


