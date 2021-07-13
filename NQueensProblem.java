public class NQueensProblem {
	final static int N = 6; // size of chess board & number of queens
	
	public static void printBoard(int board[][]) {
		for(int i = 0; i < N; i++) {
			for(int j = 0; j < N; j++) {
				if(board[i][j] == 0)
					System.out.print("* ");
				else
					System.out.print("𝓠 ");//board[i][j] + " ");
			}
			System.out.println();
		}
	}
	
	public static boolean isSafe(int board[][], int row, int col) {
		int i, j;
		
		// check left to the position
		for(i = 0; i < col; i++)
			if(board[row][i] == 1)
				return false;
		
		// check upper left diagonal
		for(i = row, j = col; i >= 0 && j >= 0; i--, j--)
			if(board[i][j] == 1)
				return false;
		
		// check lower left diagonal
		for(i = row, j = col; i < N && j >= 0; i++, j--)
			if(board[i][j] == 1)
				return false;
		
		// check upper right diagonal
		for(i = row, j = col; i >= 0 && j < N; i--, j++)
			if(board[i][j] == 1)
				return false;
		
		// if board[row][col] is safe to place queen
		return true;
	}
	
	public static boolean solveNQueens(int board[][], int col) {
		// if all queens are placed : base case
		if(col >= N)
			return true;
		
		// checking every rows, because we're placing queens column-wise
		for(int x = 0; x < N; x++) {
			if(isSafe(board, x, col)) {
				// if (x, col) is safe to place queen then, place the queen
				board[x][col] = 1;
				
				// check for rest of columns
				if(solveNQueens(board, col + 1))
					return true;
				// it is unsafe to place queen at (x, col) then remove the queen from the place
				board[x][col] = 0;
			}
		}
		return false;
	}
  	public static void main(String[] args) {
		int board[][] = new int[N][N];
		
		if(solveNQueens(board, 0))
			printBoard(board);
		else
			System.out.println("It is impossible.");
  	}
}