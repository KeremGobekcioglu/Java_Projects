import java.util.*;
public class driver1 {
	//main method
	public static void main(String[] args) {
		try(Scanner scanner = new Scanner(System.in)){
			System.out.println("Testing the Tetromino functions");
			Tetromino f = new Tetromino();
			tetrominos a = tetrominos.R; 
			f.set_enum(a); // setters and getters tested
			System.out.println(f.get_enum());
			f.print();
			Tetris tetris = new Tetris(10,10);
			tetrominos b = tetrominos.R;
			tetrominos c = tetrominos.R;
			tetrominos d = tetrominos.R;
			tetrominos k = tetrominos.R;
			// Tetromino arr_tetromino = new Tetromino()[]{}
			Tetromino ab = new Tetromino();
			ab.set_enum(a);
			// rotate and print is tested
			// add_and_delete is tested
			// add_to_random_pos is tested
			// you will probably see al the shapes in this while
			int i = 0;
			/**
			 * This while loop tests for rotate , set_enum , add_and_delete and print
			 * functions.
			 */
			while(i<20)			
			{	
				a = tetrominos.R;
				f.set_enum(a);
				tetris.add_and_delete(f);
				tetris.print();
				System.out.println();
				tetris.rotate('R');// 3R = L;
				tetris.print();
				System.out.println();
				tetris.rotate('R');
				tetris.print();
				System.out.println();
				tetris.rotate('R');
				tetris.print();
				i++;
			}
			Tetromino abc = new Tetromino(b);
			Tetromino abcd = new Tetromino(c);
			Tetromino abcde = new Tetromino(d);
			Tetromino z = new Tetromino(k);
			ab.print();
			abc.print();
			abcd.print();
			abcde.print();
			tetris.animate(ab);
			tetris.animate(abc);
			tetris.animate(abcd);
			tetris.animate(f);
			tetris.animate(abcde);
			tetris.animate(z);
			tetris.draw();
			System.out.println("Test phase is over.");
		}
	}	
}
