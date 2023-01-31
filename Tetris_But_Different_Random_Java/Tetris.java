import java.util.*;
/**
 * pos_x holds x coordinate of our shape
 * pos_y holds y coordinate of our shape
 * board is board and shape is shape
 */
public class Tetris{
	private char[][] board;
	private char[][] Shape;
	private int pos_x; // will hold x pos
	private int pos_y; // will hold y pos

	/**
	 * Empty constructor , creates a 10x10 board 
	 */
	Tetris() {
		System.out.println("10x10 Game Board");
		board = new char[10][10];
		set_dot();
		draw();
	};
	/**
	 * Creates a board heigth x width
	 * @param height
	 * @param width
	 */
	Tetris(int height, int width) {
		System.out.println(height + "x" + width + " Game Board");
		board = new char[height][width];
		set_dot();
		draw();
	};
	/**
	 * Draws the board and moves the cursor to up and sleeps 500 miliseconds
	 * Also prints the coordinates
	 * @name draw
	 */
	public void draw() {
		try {
			Thread.sleep(500);
			System.out.print("\033[2J");
        	System.out.print("\033[0;0f");
			for (char[] b : board) {
				System.out.println(b);
			}
			System.out.println("pos_x = "+get_x()+"||| pos_y = "+get_y());
			// System.out.println("\n");
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	};
	/**
	 * Fills table with dots
	 * @name set_dot
	 */
	private void set_dot() {
		for (int i = 0; i < board.length; i++)
			for (int j = 0; j < board[i].length; j++)
				board[i][j] = '.';
	}
	/**
	 * This function rotates our shape , creates a temporary array for rotating
	 * then i fill that temporary array with reflection of my shape to y = x graph
	 * After that , that temporary array is being mirrored to y axis
	 * @name rotate
	 * @param turn , this char tells you where to rotate
	 */
	public void rotate(char turn) {
		// i have to rotate the shapes
		// first they will br reflect
		char[][] temp = new char[Shape[0].length][Shape.length];
		for (int i = 0; i < Shape.length; i++)
			for (int j = Shape[0].length - 1; j >= 0; j--)
				temp[j][i] = Shape[i][j];
		char temporary;
		if (turn == 'L') {
			for (int i = 0; i < temp.length / 2; i++) {
				for (int j = 0; j < temp[0].length; j++) {
					temporary = temp[i][j];
					temp[i][j] = temp[temp.length - i - 1][j];
					temp[temp.length - i - 1][j] = temporary;
				}
			}
		} else {
			for (int i = 0; i < temp.length; i++) {
				for (int j = 0; j < temp[i].length / 2; j++) {
					temporary = temp[i][j];
					temp[i][j] = temp[i][temp[i].length - j - 1];
					temp[i][temp[i].length - j - 1] = temporary;
				}
			}
		}
		Shape = temp;
	};
	/**
	 * Checks whether there are enough space to add the tetromino
	 * @name check_for_first_add
	 * @param tetro
	 * @return booelan
	 */
	private boolean check_for_first_add(Tetromino tetro) {
		for (int i = 0; i < Shape.length; i++) {
			for (int j = (board[0].length / 2) - (Shape[0].length / 2), z = 0; j < board[0].length
					&& z < Shape[0].length; j++, z++) {
				if (board[i][j] != '.') {
					System.out.println("Shape cannot be added to top row");
					return false;
				}
			}
		}
		return true;
	}
	/**
	 * Adds the shape then deletes it because the shape will be add some random position after first addition
	 * Also i use this function to know whether the game is over
	 * @name add_and_delete
	 * @param tetro
	 * @return boolean
	 */
	public boolean add_and_delete(Tetromino tetro) // not random at the moment
	{
		Shape = tetro.get_shape();
		if(Shape.length > board.length)
		{
			System.out.println("Your board is too small !");
			return false;
		}
		if (!check_for_first_add(tetro))
			return false;
		for (int i = 0; i < Shape.length; i++) {
			for (int j = (board[0].length / 2) - (Shape[0].length / 2), z = 0; j < board[0].length
					&& z < Shape[0].length; j++, z++) {
				board[i][j] = Shape[i][z];
			}
		}
		this.draw();
		for (int i = 0; i < Shape.length; i++)
			for (int j = (board[0].length / 2) - (Shape[0].length / 2), z = 0; j < board[0].length
					&& z < Shape[0].length; j++, z++)
				board[i][j] = '.';
		return true;
	}
	/**
	 * It controls can i add my tetromino to 'the' random place
	 * @name check_pos_for_random
	 * @param random_x_pos
	 * @param random_y_pos
	 * @return boolean
	 */
	private boolean check_pos_for_random(int random_x_pos, int random_y_pos) {
		for (int i = random_y_pos, z = 0; i < board.length && z < Shape.length; i++, z++) {
			for (int j = random_x_pos, k = 0; j < board[0].length && k < Shape[0].length; j++, k++) {
				if (board[i][j] != '.')
					return false;
			}
		}
		return true;
	}
	/**
	 * This function creates random x coordinates, rotates Tetromino to random directions for random counts
	 * It uses check_pos_for_random function to know if Tetromino can be add to that position
	 * It works 1000 times most , because i think if we use 20x20 boards there wont be more than 1000 guess
	 * Adds the tetromino to that random position
	 * @name add_to_random_pos
	 * @param tetro
	 */
	public void add_to_random_pos(Tetromino tetro) {
		Shape = tetro.get_shape();
		Random rand = new Random();
		char[] arr = new char[] { 'L', 'R' };

		int how_many_turn = rand.nextInt(5);
		int where_to_turn = rand.nextInt(2);

		for (int i = 0; i < how_many_turn; i++)
			rotate(arr[where_to_turn]);

		int random_x_pos = rand.nextInt(board[0].length - Shape[0].length);
		// int random_y_pos = rand.nextInt(board.length - Shape.length);
		int random_y_pos = 0;
		int control = 0;
		// i have to search positions which i can put my shapes
		while (true) {
			if (check_pos_for_random(random_x_pos, random_y_pos))
				break;
			if (control >= 1000) {
				System.out.println("Tetromino cannot be added");
				return;
			}
			control++;
			random_x_pos = rand.nextInt(board[0].length - Shape[0].length);
			// random_y_pos = rand.nextInt(board.length - Shape.length);
		}
		set_x(random_x_pos);
		set_y(random_y_pos);
		for (int i = random_y_pos, z = 0; i < board.length && z < Shape.length; i++, z++) {
			for (int j = random_x_pos, k = 0; j < board[0].length && k < Shape[0].length; j++, k++)
				board[i][j] = Shape[z][k];
		}
	}
	/**
	 * This function controls the proper positions of tetrominos
	 * @name check_for_interpenetration
	 * @return booelan
	 */
	private boolean check_for_interpenetration() {
		for (int i = 0, control = 0; i < Shape[0].length; i++) {
			if (i + pos_x >= board[0].length || pos_y + Shape.length >= board.length)
				break;
			if (Shape[Shape.length - 1][i] == '.') {
				++control;
			} else if (board[pos_y + Shape.length][pos_x + i] == '.') {
				++control;
			} else if (board[pos_y + Shape.length][pos_x + i] == '.' &&
					board[pos_y + Shape.length - 1][pos_x + i] == Shape[Shape.length - 1][i]) {
				++control;
			}
			if (control == Shape[0].length)
				return true;
		}
		return false;
	}
	/**
	 * Erase the Tetromino for animate function
	 * @name erase
	 * @return boolean
	 */
	private boolean erase()
	{
		if (pos_y + Shape.length >= board.length)
				return false;
		for (int i = pos_y; i < Shape.length + pos_y; i++)
			for (int j = pos_x; j < Shape[0].length + pos_x; j++)
					board[i][j] = '.';
		return true;
	}
	/**
	 * First calls add_and_delete function and looks if the game ends or continues,
	 * if add_and_delete returns false , then animate returns false too and in main function we will understand the game over
	 * Otherwise add_and_delete returns true, it will call add_to_random_pos function and add the tetromino the random place
	 * While check_for_interpenetration function returns true , Tetromino will go down every for each loop , first
	 * erase function comes and deletes tetromino so tetromino will be add to lower position and this goes until the end
	 * If animate returns true , game continues 
	 * @param tetro
	 * @return boolean
	 */
	public boolean animate(Tetromino tetro) {

		if (!add_and_delete(tetro)) {
			return false;
		}
		add_to_random_pos(tetro);
		this.draw();
		while (check_for_interpenetration()) {
			if(!erase())
				break;
				pos_y++;
			for (int i = 0; i < Shape.length; i++) {
				for (int j = 0; j < Shape[0].length; j++) {
					if (board[i + pos_y][j + pos_x] == '.')
						board[i + pos_y][j + pos_x] = Shape[i][j];
				}
			}
			this.draw();
		}
		return true;
	};
	/**
	 * Never used it
	 * @return
	 */
	public char[][] get_board() {
		return Arrays.copyOf(board,board.length);
	}
	/**
	 * getter for x coordinate
	 * @return integer , x coordinate
	 */
	public int get_x() {
		return pos_x;
	}
	/**
	 * getter for y coordinate
	 * @return integer , y coordinate
	 */
	public int get_y() {
		return pos_y;
	}
	/**
	 * 
	 * @param pos
	 */
	private void set_x(int pos) {
		this.pos_x = pos;
	}
	/**
	 * 
	 * @param pos
	 */
	private void set_y(int pos) {
		this.pos_y = pos;
	}
	/**
	 * simple print function, prints the shape 
	 */
	public void print()
	{
		for(char[] b : Shape)
			System.out.println(b);
	}
}