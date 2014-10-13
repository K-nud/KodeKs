import java.io.File;
import java.io.Serializable;
import java.util.ArrayList;

import javax.sound.sampled.AudioFormat;
import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import javax.sound.sampled.DataLine;
import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JPanel;





	 /**
	    * An object of this class holds data about a game of KodeKs.
	    * It knows what kind of piece is on each square of the KodeKsboard.
	    * Methods are provided to return lists of available legal moves.
	    */
	   public class KodeKsData extends JPanel implements Serializable {
	       	   
		private static final long serialVersionUID = 547642L;

		/*  The following constants represent the possible contents of a square
        on the board.  The constants RED and BLUE also represent players
        in the game. */
		int[][] board;  // board[r][c] is the contents of row r, column c.  
	    static ArrayList<KodeKsMove> moves;  
	    private static ArrayList<ThreatenStone> threaten;
	    private static ArrayList<LaserField> laserFields;
	    public static LaserField[] laserFieldsArray;
	    public static Move moving=new Move();

	
	/**
	* A KodeKsMove object represents a move in the game of KodeKs.
	* It holds the row and column of the piece that is to be moved
	* and the row and column of the square to which it is to be moved.
	* (This class makes no guarantee that the move is legal.)   
	*/
		public static class KodeKsMove implements Serializable{
			/**
		 * 
		 */
		private static final long serialVersionUID = 1L;
			int fromRow, fromCol;  // Position of piece to be moved.
			int toRow, toCol;      // Square it is to move to.
			KodeKsMove(int r1, int c1, int r2, int c2) {
				// Constructor.  Just set the values of the instance variables.
				fromRow = r1;
				fromCol = c1;
				toRow = r2;
				toCol = c2;
			}
			
		}  // end class KodeKsMove
		   
		/**
		* A ThreatenStone object represents an opponents piece which can be taken.
		* It holds the row and column of that piece that can be taken by the current player.
		*/      
		public static class ThreatenStone implements Serializable{
			/**
			 * 
			 */
			private static final long serialVersionUID = 1L;
			int threatenRow, threatenCol;      // Position of threaten piece
			ThreatenStone(int row, int col) {
				// Constructor.  Just set the values of the instance variables.
				threatenRow = row;
				threatenCol = col;   
			}
		}  // end class ThreatenStone
		
		
		public static class LaserField implements Serializable{
			
			private static final long serialVersionUID = 1L;
			int row, column;
			String orientation; //orientation can be "horizontal", "vertical", "diagonalNW" or "diagonalNE"
			
			LaserField(int x, int y, String z) {
				row = x;
				column = y;
				orientation = z;
			}
			
		}

		
		/*
		 * 	The three possible states of a field		   
		 */
		static final int 	EMPTY = 0,
							RED = 1,
							BLUE = 2;
		
		
		/**
		 * This array shows the value of each field of the gameboard
		 */		   
		public final static int[][] fieldvalue= {
			{ 2, 3,	4,	1,	2,	3,	1,	4,	3,	2},
			{ 3, 1,	2,	3,	4,	1,	3,	2,	1,	3},
			{ 4, 2,	1,	4,	1,	4,	2,	1,	2,	4},
			{ 1, 3,	2,	3,	2,	1,	3,	4,	3,	1},
			{ 3, 1,	4,	1,	2,	4,	2,	1,	4,	2},
			{ 2, 4,	1,	2,	4,	2,	1,	4,	1,	3},
			{ 1, 3,	4,	3,	1,	2,	3,	2,	3,	1},
			{ 4, 2,	1,	2,	4,	1,	4,	1,	2,	4},
			{ 3, 1,	2,	3,	1,	4,	3,	2,	1,	3},
			{ 2, 3,	4,	1,	3,	2,	1,	4,	3,	2}
		};	
	      
		/**
		 * This array shows, where are pieces on the board at the beginning of the game
		 */  
		public final static int[][] BOARD_AT_START =
		{ 
	   
		{ 0, 0, 0, 0, 0, 0, 0, 0, 0, 0},
		{ 0, 2, 1, 2, 1, 2, 1, 2, 1, 0},
		{ 0, 1, 0, 0, 0, 0, 0, 0, 2, 0},
		{ 0, 2, 0, 0, 0, 0, 0, 0, 1, 0},
		{ 0, 1, 0, 0, 0, 0, 0, 0, 2, 0},
		{ 0, 2, 0, 0, 0, 0, 0, 0, 1, 0},
		{ 0, 1, 0, 0, 0, 0, 0, 0, 2, 0},
		{ 0, 2, 0, 0, 0, 0, 0, 0, 1, 0},
		{ 0, 1, 2, 1, 2, 1, 2, 1, 2, 0},
		{ 0, 0, 0, 0, 0, 0, 0, 0, 0, 0}
		};
	      
		
		/**
		 * Constructor.  Create the board and set it up for a new game.
		 */	      
		public KodeKsData() {
			board = new int[10][10];
			setUpGame();
		}
	      /**
	       * Set up the board with KodeKs in position for the beginning
	       * of a game. 
	       */
		void setUpGame() {
	         for (int row = 0; row < 10; row++) {
	            for (int col = 0; col < 10; col++) {
	               	board[row][col] = BOARD_AT_START[row][col];
	            }
	         }
	      }  // end setUpGame()
	      

	      /**
	       * Return the contents of the square in the specified row and column.
	       */
	      int pieceAt(int row, int col) {
	         return board[row][col];
	      }
	      
	      /**
	       * Returns the number of left pieces of the player on the current board.
	       * @param player - int
	       * @return sum of left pieces on the board
	       */
	      int getNumberOfStones(int player){
	    	  int sum=0;
	    	  
	    	  for (int row = 0; row < 10; row++) {
	    		  for (int col = 0; col < 10; col++) {
	    			  if (board[row][col] == player) {sum++;}}}
	    	  return sum;
	      }

	    	/**
	    	 * Method to check if a position is on or beside the board
	    	 * @param row - int
	    	 * @param col - int
	    	 * @return boolean - true or false
	    	 */
	      public boolean onBoard(int row, int col){
	    	  if (row < 0 || row >= 10 || col < 0 || col >= 10)
		            return false;
	    	  else return true;
	      }

	      /**
	       * Take a specified threaten Stone of the opponent
	       */
	      void takeStone(ThreatenStone take){
	    	  takeStone(take.threatenRow,take.threatenCol);
	      }
	      
	      /**
	       * Take the opponents stone. It is assumed that the stone can be taken.
	       * and put it on the panel beside the board
	       */
	      void takeStone(int row, int col){
	    	  if (board[row][col] != EMPTY){  
	    		  board[row][col] = EMPTY;
	    	  
	    		  JLabel point= new JLabel(""); //puts the taken stone on the "stonepanel"
	    		  point.setAlignmentX(CENTER_ALIGNMENT);
	    		  if (KodeKsBoard.currentPlayer==RED){
	    			  ImageIcon iconP1 = new ImageIcon(getClass().getClassLoader().getResource("blueStone.png"));
	    			  point.setIcon(iconP1);
	    			  GUI.board.Player1_Stones.add(point);
	    		  }
	    		  else {
	    			  ImageIcon iconP2 = new ImageIcon(getClass().getClassLoader().getResource("redStone.png"));
	    			  point.setIcon(iconP2);
	    			  GUI.board.Player2_Stones.add(point);
	    		  }	 
	    		  try{
	    	            AudioInputStream audioInputStream = AudioSystem.getAudioInputStream(new File("res/Glass_Break.wav"));
	    	            AudioFormat af     = audioInputStream.getFormat();
	    	            int size      = (int) (af.getFrameSize() * audioInputStream.getFrameLength());
	    	            byte[] audio       = new byte[size];
	    	            DataLine.Info info      = new DataLine.Info(Clip.class, af, size);
	    	            audioInputStream.read(audio, 0, size);
	    	           
	    	            Clip clip = (Clip) AudioSystem.getLine(info);
	    	            clip.open(af, audio, 0, size);
	    	            clip.start();
	    	           
	    	        }catch(Exception e){ e.printStackTrace(); }
	    	 
	    	         
	    	KodeKsBoard.NumberOfRedStones=GUI.board.getNumberOfStones(RED);//recount the left pieces of each player
	    	KodeKsBoard.NumberOfBlueStones=GUI.board.getNumberOfStones(BLUE);
	    	  }
	      }
	      
   LaserField[] getLaserFields() {
	   
	   return laserFieldsArray;
   }
	      
	      
	      /**
	       * Return an array containing all the threaten stones 
	       * for the opponent player on the current board.  If the player
	       * isn't threatening an opponents piece, null is returned.  
	       * @param player - int   The value of player should be one of the constants RED or BLUE
	       * @return ArrayList of threaten pieces of the opponent player
	       */
	      
	 ThreatenStone[] getThreatenStone(int player) {
	         
	         
	         if (player != RED && player != BLUE)
	            return null;
	           
	         threaten = new ArrayList<ThreatenStone>();  //reset list of threatened stones
	         laserFields = new ArrayList<LaserField>();	 //reset list of fields filled with lasers
	         //This list holds lasers that may or may not connect to a stone, if no connection is made.
	         //If a connection is made, this list will be added to laserFields. If not, the list will be scrapped.
	         ArrayList<LaserField> potentialLaserFields; 
	         
	         
	                 
	         /*  
	          If that square contains one of the player's pieces, look at a possible
	          move in each of the four directions from that square.  If there is 
	          a legal move in that direction, put it in the moves ArrayList.
	          */
	           
	           for (int row = 0; row < 10; row++) {
	               for (int col = 0; col < 10; col++) {
	                  if (board[row][col] == player) {
	                	  int range=0;							//reset laser range for every new stone
	                	  boolean laserEndpointFound = false;	//reset final laser position for every new stone
	                	  potentialLaserFields = new ArrayList<LaserField>();	//reset list of potential lasers for each new stone
	                	  
	                     	if (row+1<10 && pieceAt(row+1,col) == player){
	                     		range=((fieldvalue[row][col]) + (fieldvalue[row+1][col]));
	                     		for (int i=1; i<=range && laserEndpointFound == false; i++){
	                     			if (onBoard((row+1)+i,col)){
	                     				if (pieceAt((row+1)+i, col) == EMPTY){
	                     					potentialLaserFields.add(new LaserField((row + 1 +i), col, "vertical"));
	                     					
	                     				}
	                     				else if (pieceAt((row+1)+i,col) != EMPTY)
	                     					if (canTakeThreatenStone(player,(row+1)+i,col)){
	                     						threaten.add(new ThreatenStone((row+1)+i,col));
	                     						laserEndpointFound = true;
	                     						laserFields.addAll(potentialLaserFields);
	                     					}
	                     			}
	                     		}
	                     	}
	                     	if (col+1<10 && pieceAt(row,col+1) == player){
	                     		range=((fieldvalue[row][col]) + (fieldvalue[row][col+1]));
	                     		for (int i=1; i<=range && laserEndpointFound == false; i++){
	                     			if (onBoard(row,(col+1)+i)){
	                     				if (pieceAt(row,(col+1)+i) == EMPTY){
	                     					potentialLaserFields.add(new LaserField(row, (col + i + 1), "horizontal"));
	                     				}
	                     				else if (pieceAt(row,(col+1)+i) != EMPTY)
	                     					if (canTakeThreatenStone(player,row,(col+1)+i)){
	                     						threaten.add(new ThreatenStone(row,(col+1)+i));
	                     						laserEndpointFound = true;
	                     						laserFields.addAll(potentialLaserFields);
	                     					}
	                     				}
	                     			}
	                     		}
	                     	if (row+1<10 && col+1<10 && pieceAt(row+1,col+1) == player){
	                     		range=((fieldvalue[row][col]) + (fieldvalue[row+1][col+1]));
	                     		for (int i=1; i<=range && laserEndpointFound == false; i++){
	                     			if (onBoard((row+1)+i,(col+1)+i)){
	                     				if (pieceAt((row+1)+i,(col+1)+i) == EMPTY){
	                     					potentialLaserFields.add(new LaserField((row + 1 + i), (col + i + 1), "diagonalNW"));
	                     				}
	                     				else if (pieceAt((row+1)+i,(col+1)+i) != EMPTY)
	                     					if (canTakeThreatenStone(player,(row+1)+i,(col+1)+i)){
	                     						threaten.add(new ThreatenStone((row+1)+i,(col+1)+i));
	                     						laserEndpointFound = true;
	                     						laserFields.addAll(potentialLaserFields);
	                     					}
	                     			}
	                     		}
	                     	}
	                     	if (row+1<10 && col-1>=0 && pieceAt(row+1,col-1) == player ){
	                     		range=((fieldvalue[row][col]) + (fieldvalue[row+1][col-1]));
	                     		for (int i=1; i<=range && laserEndpointFound == false; i++){
	                     			if (onBoard((row+1)+i,(col-1)-i)){
	                     				if (pieceAt((row+1)+i,(col-1)-i) == EMPTY){
	                     					potentialLaserFields.add(new LaserField((row + 1 + i), (col - i - 1), "diagonalNE"));
	                     				}	                     			
	                     				else if (pieceAt((row+1)+i,(col-1)-i) != EMPTY)
	                     					if (canTakeThreatenStone(player,(row+1)+i,(col-1)-i)){
	                     						threaten.add(new ThreatenStone((row+1)+i,(col-1)-i));
	                     						laserEndpointFound = true;
	                     						laserFields.addAll(potentialLaserFields);
	                     					}
	                     			}
	                     		}
	                     	}
	                     	if (row-1>=0 && col+1<10 && pieceAt((row-1),(col+1)) == player){
	                     		range=((fieldvalue[row][col]) + (fieldvalue[row-1][col+1]));
	                     		for (int i=1; i<=range && laserEndpointFound == false; i++){
	                     			if (onBoard((row-1)-i,(col+1)+i)){
	                     				if (pieceAt((row-1)-i,(col+1)+i) == EMPTY){
	                     					potentialLaserFields.add(new LaserField((row - 1 - i), (col + i + 1), "diagonalNE"));
	                     				}	 
	                     				else if (pieceAt((row-1)-i,(col+1)+i) != EMPTY)
	                     					if (canTakeThreatenStone(player,(row-1)-i,(col+1)+i)){
	                     						threaten.add(new ThreatenStone((row-1)-i,(col+1)+i));
	                     						laserEndpointFound = true;
	                     						laserFields.addAll(potentialLaserFields);
	                     					}
	                     			}
	                     		}
	                     	}
	                     	if (row-1>=0 && pieceAt(row-1,col) == player){
	                     		range=((fieldvalue[row][col]) + (fieldvalue[row-1][col]));
	                     		for (int i=1; i<=range && laserEndpointFound == false; i++){
	                     			if (onBoard((row-1)-i,col)){
	                     				if (pieceAt((row-1)-i,col) == EMPTY){
	                     					potentialLaserFields.add(new LaserField((row - 1 - i), col, "vertical"));
	                     				}	
	                     				else if (pieceAt((row-1)-i,col) != EMPTY)
	                     					if (canTakeThreatenStone(player,(row-1)-i,col)){
	                     						threaten.add(new ThreatenStone((row-1)-i,col));
	                     						laserEndpointFound = true;
	                     						laserFields.addAll(potentialLaserFields);
	                     					}
	                     			}
	                     		}
	                     	}
	                     	if (col-1>=0 && pieceAt(row,col-1) == player){
	                     		range=((fieldvalue[row][col]) + (fieldvalue[row][col-1]));
	                     		for (int i=1; i<=range && laserEndpointFound == false; i++){
	                     			if (onBoard(row,(col-1)-i)) {
	                     				if (pieceAt(row,(col-1)-i) == EMPTY){
	                     					potentialLaserFields.add(new LaserField(row, (col - 1 - i), "horizontal"));
	                     				}
	                     				else if (pieceAt(row,(col-1)-i) != EMPTY)
	                     					if (canTakeThreatenStone(player,row,(col-1)-i)){
	                     						threaten.add(new ThreatenStone(row,(col-1)-i));
	                     						laserEndpointFound = true;
	                     						laserFields.addAll(potentialLaserFields);
	                     					}
	                     			}
	                     		}
	                     	}
	                     	if (row-1>=0 && col-1>=0 && pieceAt(row-1,col-1) == player){
	                     		range=((fieldvalue[row][col]) + (fieldvalue[row-1][col-1]));
	                     		for (int i=1; i<=range && laserEndpointFound == false; i++){
	                     			if (onBoard((row-1)-i,(col-1)-i)){
	                     				if (pieceAt((row-1)-i,(col-1)-i) == EMPTY){
	                     					potentialLaserFields.add(new LaserField((row - 1 - i), (col - 1 - i), "diagonalNW"));
	                     				}
	                     				else if (pieceAt((row-1)-i,(col-1)-i) != EMPTY)
	                     					if (canTakeThreatenStone(player,(row-1)-i,(col-1)-i)){
	                     						threaten.add(new ThreatenStone((row-1)-i,(col-1)-i));
	                     						laserEndpointFound = true;
	                     						laserFields.addAll(potentialLaserFields);
	                     					}
	                     			}	
	                     		}
	                     	}
	                  }
	               }
	           }
	           
	           //Combination of more than 2 opponent stones?
	           for (int row = 0; row < 10; row++) {
	               for (int col = 0; col < 10; col++) {
	                  if (board[row][col] != player && board[row][col] != EMPTY) {
	                	  int stonesOwnedInProximity=0;
	                	  //top left
	                	  if (onBoard(row-1,col-1) && board[row-1][col-1] != player && board[row-1][col-1] != EMPTY) {
	                		  stonesOwnedInProximity++;
	                	  }
	                	  //top 
	                	  if (onBoard(row-1,col) && board[row-1][col] != player && board[row-1][col] != EMPTY) {
	                		  stonesOwnedInProximity++;
	                	  }
	                	  //top right
	                	  if (onBoard(row-1,col+1) && board[row-1][col+1] != player && board[row-1][col+1] != EMPTY) {
	                		  stonesOwnedInProximity++;
	                	  }
	                	  //left
	                	  if (onBoard(row,col-1) && board[row][col-1] != player && board[row][col-1] != EMPTY) {
	                		  stonesOwnedInProximity++;
	                	  }
	                	  //right
	                	  if (onBoard(row,col+1) && board[row][col+1] != player && board[row][col+1] != EMPTY) {
	                		  stonesOwnedInProximity++;
	                	  }
	                	  //bottom left
	                	  if (onBoard(row+1,col-1) && board[row+1][col-1] != player && board[row+1][col-1] != EMPTY) {
	                		  stonesOwnedInProximity++;
	                	  }
	                	  //bottom
	                	  if (onBoard(row+1,col) && board[row+1][col] != player && board[row+1][col] != EMPTY) {
	                		  stonesOwnedInProximity++;
	                	  }
	                	  //bottom right
	                	  if (onBoard(row+1,col+1) && board[row+1][col+1] != player && board[row+1][col+1] != EMPTY) {
	                		  stonesOwnedInProximity++;
	                	  }
	                	  if (stonesOwnedInProximity > 1){
//	                		  //top left
//		                	  if (onBoard(row-1,col-1) && board[row-1][col-1] != player && board[row-1][col-1] != EMPTY) {
//		                		  threaten.add(new ThreatenStone((row-1),(col-1)));
//		                	  }
//		                	  //top 
//		                	  if (onBoard(row-1,col) && board[row-1][col] != player && board[row-1][col] != EMPTY) {
//		                		  threaten.add(new ThreatenStone((row-1),(col)));
//		                	  }
//		                	  //top right
//		                	  if (onBoard(row-1,col+1) && board[row-1][col+1] != player && board[row-1][col+1] != EMPTY) {
//		                		  threaten.add(new ThreatenStone((row-1),(col+1)));
//		                	  }
//		                	  //left
//		                	  if (onBoard(row,col-1) && board[row][col-1] != player && board[row][col-1] != EMPTY) {
//		                		  threaten.add(new ThreatenStone((row),(col-1)));
//		                	  }
		                	  //self
		                	  threaten.add(new ThreatenStone((row),(col)));
//		                	  //right
//		                	  if (onBoard(row,col+1) && board[row][col+1] != player && board[row][col+1] != EMPTY) {
//		                		  threaten.add(new ThreatenStone((row),(col+1)));
//		                	  }
//		                	  //bottom left
//		                	  if (onBoard(row+1,col-1) && board[row+1][col-1] != player && board[row+1][col-1] != EMPTY) {
//		                		  threaten.add(new ThreatenStone((row+1),(col-1)));
//		                	  }
//		                	  //bottom
//		                	  if (onBoard(row+1,col) && board[row+1][col] != player && board[row+1][col] != EMPTY) {
//		                		  threaten.add(new ThreatenStone((row+1),(col)));
//		                	  }
//		                	  //bottom right
//		                	  if (onBoard(row+1,col+1) && board[row+1][col+1] != player && board[row+1][col+1] != EMPTY) {
//		                		  threaten.add(new ThreatenStone((row+1),(col+1)));
//		                	  }
	                	  }
	                  }
	               }
	           }
	           
	                	  
	                	  
	           
	         //fill laserFieldsArray with content.
	         //this is awfully ugly, make sure to call getThreatenStone before getLaserFields
	         
	         if (laserFields.size() == 0 )
	        	 laserFieldsArray = null;
	         else {
	        	 laserFieldsArray = new LaserField[laserFields.size()];
	        	 for (int i = 0; i < laserFields.size(); i++){
	        		 laserFieldsArray[i] = laserFields.get(i);
	        	 }
	         }
	           
	        
	           /* If no threaten pieces have been found, return null.  Otherwise, create
	          an array just big enough to hold all the threatened pieces, copy these
	           from the ArrayList into the array, and return the array. */
	         
	         if (threaten.size() == 0)
	            return null;
	         else {
	            ThreatenStone[] threatenArray = new ThreatenStone[threaten.size()];
	            for (int i = 0; i < threaten.size(); i++){
	               threatenArray[i] = threaten.get(i);
	               
	            }
	            return threatenArray;
	         }
	      }  // end getThreatenStone
	                     	
	      /**
	       * This is called by the getThreatenStone() method to determine whether
	       * the player can take an opponents stone from (row,col).  It is
	       * assumed that (row,col) contains one of the opponent player's pieces.
	       */
	      private boolean canTakeThreatenStone(int player, int row, int col) {
	               
	        	 if (board[row][col] != player)
	        		 return true;  // board[row][col] contains an opponents piece.
	         
	         return false;	// The take is illegal.
	         }	// end canTakeTheatenStone()

	      
	      /**
	       * Make the specified move.  It is assumed that move
	       * is non-null and that the move it represents is legal.
	       */
	      void makeMove(KodeKsMove move) {
	         makeMove(move.fromRow, move.fromCol, move.toRow, move.toCol);
	      }
	      
	      /**
	       * Make the move from (fromRow,fromCol) to (toRow,toCol).  It is
	       * assumed that this move is legal.  
	       */
	      void makeMove(int fromRow, int fromCol, int toRow, int toCol) {
	    	  board[toRow][toCol] = board[fromRow][fromCol];
	          board[fromRow][fromCol] = EMPTY;
	      }   
	      
	      
	      /**
	       * Return an array containing all the legal KodeKsMoves
	       * for the specified player on the current board.  If the player
	       * has no legal moves, null is returned.
	       * @param player - int   The value of player should be one of the constants RED or BLUE
	       * @return ArrayList of legal moves of the current player
	       */
	      KodeKsMove[] getLegalMoves(int player) {
	         
	         if (player != RED && player != BLUE)
	            return null;
	             
	        moves = new ArrayList<KodeKsMove>();  // Moves will be stored in this list.
	                 
	         /*  
	          If that square contains one of the player's pieces, look at a possible
	          move in each of the four directions from that square.  If there is 
	          a legal move in that direction, put it in the moves ArrayList.
	          */
	           
	         if (moves.size() == 0) {
	            for (int row = 0; row < 10; row++) {
	               for (int col = 0; col < 10; col++) {
	                  if (board[row][col] == player) {
	                     	if (canMove(player,row,col,row+(fieldvalue[row][col]),col+(fieldvalue[row][col])))
	                       	 	moves.add(new KodeKsMove(row,col,row+(fieldvalue[row][col]),col+(fieldvalue[row][col])));
	                    	if (canMove(player,row,col,row-(fieldvalue[row][col]),col+(fieldvalue[row][col])))
	                        	moves.add(new KodeKsMove(row,col,row-(fieldvalue[row][col]),col+(fieldvalue[row][col])));
	                    	if (canMove(player,row,col,row+(fieldvalue[row][col]),col-(fieldvalue[row][col])))
	                      	  	moves.add(new KodeKsMove(row,col,row+(fieldvalue[row][col]),col-(fieldvalue[row][col])));
	                    	if (canMove(player,row,col,row-(fieldvalue[row][col]),col-(fieldvalue[row][col])))
	                        	moves.add(new KodeKsMove(row,col,(row-fieldvalue[row][col]),(col-fieldvalue[row][col])));
	                    	if (canMove(player,row,col,(row+fieldvalue[row][col]),col))
	                        	moves.add(new KodeKsMove(row,col,row+(fieldvalue[row][col]),col));
	                     	if (canMove(player,row,col,row,col+(fieldvalue[row][col])))
	                        	moves.add(new KodeKsMove(row,col,row,col+(fieldvalue[row][col])));
	                     	if (canMove(player,row,col,row-(fieldvalue[row][col]),col))
	                        	moves.add(new KodeKsMove(row,col,row-(fieldvalue[row][col]),col));
	                     	if (canMove(player,row,col,row,col-(fieldvalue[row][col])))
	                        	moves.add(new KodeKsMove(row,col,row,col-(fieldvalue[row][col])));
	                     	if (fieldvalue[row][col]==4)
	                     		for (int i=1; i<4; i++){
	                     			if (canMove(player,row,col,row+((fieldvalue[row][col])-i),col+((fieldvalue[row][col])-i)))
	                               	 	moves.add(new KodeKsMove(row,col,row+(fieldvalue[row][col])-i,col+((fieldvalue[row][col])-i)));
	                            	if (canMove(player,row,col,row-((fieldvalue[row][col])-i),col+((fieldvalue[row][col])-i)))
	                                	moves.add(new KodeKsMove(row,col,row-((fieldvalue[row][col])-i),col+((fieldvalue[row][col])-i)));
	                            	if (canMove(player,row,col,row+((fieldvalue[row][col])-i),col-((fieldvalue[row][col])-i)))
	                              	  	moves.add(new KodeKsMove(row,col,row+((fieldvalue[row][col])-i),col-((fieldvalue[row][col])-i)));
	                            	if (canMove(player,row,col,row-((fieldvalue[row][col])-i),col-((fieldvalue[row][col])-i)))
	                                	moves.add(new KodeKsMove(row,col,row-((fieldvalue[row][col])-i),col-((fieldvalue[row][col])-i)));
	                            	if (canMove(player,row,col,row+((fieldvalue[row][col])-i),col))
	                                	moves.add(new KodeKsMove(row,col,row+((fieldvalue[row][col])-i),col));
	                             	if (canMove(player,row,col,row,col+((fieldvalue[row][col])-i)))
	                                	moves.add(new KodeKsMove(row,col,row,col+((fieldvalue[row][col])-i)));
	                             	if (canMove(player,row,col,row-((fieldvalue[row][col])-i),col))
	                                	moves.add(new KodeKsMove(row,col,row-((fieldvalue[row][col])-i),col));
	                             	if (canMove(player,row,col,row,col-((fieldvalue[row][col])-i)))
	                                	moves.add(new KodeKsMove(row,col,row,col-((fieldvalue[row][col])-i)));
	                     		}
	                  }
	               }
	            }
	         }
	         
	         /* If no legal moves have been found, return null.  Otherwise, create
	          an array just big enough to hold all the legal moves, copy the
	          legal moves from the ArrayList into the array, and return the array. */
	         
	         if (moves.size() == 0)
	            return null;
	         else {
	            KodeKsMove[] moveArray = new KodeKsMove[moves.size()];
	            for (int i = 0; i < moves.size(); i++)
	               moveArray[i] = moves.get(i);
	            return moveArray;
	         } 
	      }  // end getLegalMoves
	      
	      
	      
	      /**
	       * This is called by the getLegalMoves() method to determine whether
	       * the player can legally move from (r1,c1) to (r2,c2).  It is
	       * assumed that (r1,r2) contains one of the player's pieces and
	       * that (r2,c2) is a neighboring square.
	       */
	      private boolean canMove(int player, int r1, int c1, int r2, int c2) {
	         
	         if (r2 < 0 || r2 >= 10 || c2 < 0 || c2 >= 10)
	            return false;  // (r2,c2) is off the board.
	         
	         if (board[r2][c2] != EMPTY)
	            return false;  // (r2,c2) already contains a piece.
	         
	         return true;  // The move is legal.
	         } // end canMove()
	      
	      
	   } // end class KodeKsData


