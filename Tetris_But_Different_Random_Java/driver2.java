import java.util.*;

public class driver2 {

	public static void main(String[] args) {
		try (Scanner scanner = new Scanner(System.in)) {
			/**
			 * Used a tetrominos array to represent my enums
			 */
			tetrominos[] arr_tetrominos = new tetrominos[] { tetrominos.R, tetrominos.S, tetrominos.L, tetrominos.J,
					tetrominos.Z, tetrominos.T, tetrominos.O, tetrominos.I };

			int[] measurements = new int[2];

			do {
				System.out.println("Enter height and width and put a char between them , no matter what you enter we will turn it two positive integers and use");
				System.out.println("Height = "+measurements[0]);
				System.out.println("Width = "+measurements[1]);
			} while (/*!(uz > 0 && gen > 0)*/!string_to_integer(scanner.next(),measurements));
			Tetris board = new Tetris(measurements[0], measurements[1]);
			char b = 'P';
			Tetromino a = new Tetromino();
			while (b != 'Q') {
				System.out.println("Enter the tetromino you want(R for Random,O,T,J,S,L,Z,I) : Q for quit");
				b = scanner.next().charAt(0);
				if(b == 'Q')
					{
						board.draw();
						System.out.println("Game over");
						return;
				}
				while (b != 'R' && b != 'Z' && b != 'S' && b != 'J' &&
						b != 'O' && b != 'T' && b != 'I' && b != 'L' && b != 'Q') {
					System.out.println("WRONG INPUT");
					b = scanner.next().charAt(0);
				}
				if (b == 'O') {
					// Tetromino a = new Tetromino(arr_tetrominos[6]);
					a.set_enum(arr_tetrominos[6]);
					if(!board.animate(a))
					{
						System.out.println("Game over !");
						return;
					}
				} 
				else if (b == 'L') {
					// Tetromino a = new Tetromino(arr_tetrominos[2]);
					a.set_enum(arr_tetrominos[2]);
					if(!board.animate(a))
					{
						System.out.println("Game over !");
						return;
					}
				} 
				else if (b == 'Z') {
					// Tetromino a = new Tetromino(arr_tetrominos[4]);
					a.set_enum(arr_tetrominos[4]);
					if(!board.animate(a))
					{
						System.out.println("Game over !");
						return;
					}
				} 
				else if (b == 'S') {
					// Tetromino a = new Tetromino(arr_tetrominos[1]);
					a.set_enum(arr_tetrominos[1]);
					if(!board.animate(a))
					{
						System.out.println("Game over !");
						return;
					}
				} 
				else if (b == 'T') {
					// Tetromino a = new Tetromino(arr_tetrominos[5]);
					a.set_enum(arr_tetrominos[5]);
					if(!board.animate(a))
					{
						System.out.println("Game over !");
						return;
					}
				} 
				else if (b == 'I') {
					// Tetromino a = new Tetromino(arr_tetrominos[7]);
					a.set_enum(arr_tetrominos[7]);
					if(!board.animate(a))
					{
						System.out.println("Game over !");
						return;
					}
				}
				else if (b == 'J') {
					// Tetromino a = new Tetromino(arr_tetrominos[3]);
					a.set_enum(arr_tetrominos[3]);
					if(!board.animate(a))
					{
						System.out.println("Game over !");
						return;
					}
				}
				else {
					// Tetromino a = new Tetromino(arr_tetrominos[0]);
					a.set_enum(arr_tetrominos[0]);
					if(!board.animate(a))
					{
						System.out.println("Game over !");
						return;
					}
				}
				}

			}
		}
		/**
		 * This function controls the input
		 * @param str : The string which comes from user
		 * @param boardsize : it holds our height and width
		 * @return booelan
		 */
		public static boolean string_to_integer(String str,int[] boardsize) // for input control
		{
			if(str.length() == 0)
				return false;
			int k = 0;
			for(int i = 1,l = 0; i<str.length(); i++)
			{
				if(str.charAt(i-1) >= '0' && str.charAt(i-1) <= '9' && str.charAt(i) >= '0' && str.charAt(i) <= '9')
				{
					k = (str.charAt(i-1) - '0') * 10;
					k+= str.charAt(i) - '0';
					boardsize[l] = (k);
					i++;
					l++;
				}
				else if(str.charAt(i-1) >= '0' && str.charAt(i-1) <= '9' && (str.charAt(i) < '0' || str.charAt(i) > '9'))
				{
					boardsize[l] = (str.charAt(i-1) - '0');
					l++;
				}
				else
				{
					if(i == str.length()-1)
					{
						if(str.charAt(i) >= '0' && str.charAt(i) <= '9')
							boardsize[l++] = (str.charAt(i) - '0');
					}
				}
				k = 0;
			}
			System.out.println("uzunluk = "+boardsize[0]);
			System.out.println("genislik = "+boardsize[1]);
			if(boardsize[0] != 0 && boardsize[1] != 0)
				return true;
			return false;
		}
}
