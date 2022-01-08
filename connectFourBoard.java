public class connectFourBoard{
	public static void main(String[] args){
		//PROPERTIES
		int intBoard[][] = new int[6][7];
		int intTurn;
		
		//Add Player
		public void addPosition(int intCol){
			
		}
		
		//Generates Valid Positions
		public int[] validPositions(){
			int intValidPosition[] = new int[7];
			
			for(int intCount0=0; intCount0<7; intCount0++){
				int intValidPosition[intCount0] = null;
				for(int intCount1=0; intCount1<6; intCount1++){
					int positionalValue = intBoard[intCount1][intCount0];
					if(positionalValue == 0){
						
					}
				}
			}
		}
		
		//Check If There Is a Winner: 0 - no winner, 1 - player 1 winner, 2 - player 2 winner
		public int checkWinner ()
		
		//CONSTRUCTOR
		public connectFourBoard(){}
	}
}
