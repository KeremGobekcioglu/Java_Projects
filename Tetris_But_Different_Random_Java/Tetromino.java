import java.util.Arrays;
/**
 * My tetromino class has 3 variables and a few methods
 * private tetrominos enum_shape variables holds an enum and uses enum class
 * private char[][] shape holds the real life shapes of our tetrominos
 * private char _shape holds the char representation of our enum
 */
public class Tetromino
{
	private tetrominos enum_shape;
	private char[][] shape;
	private char _shape = 'P';
	Tetromino()
	{
		
	}
	Tetromino(tetrominos shape)
	{
		enum_shape = shape;
		allset();
	}
	/**
	 * 
	 * @return char[][] shape , returns copy of our shape
	 */
	public char[][] get_shape()
	{
		return Arrays.copyOf(shape,shape.length);
	}
	public tetrominos get_enum()
	{
		return enum_shape;
	}
	/***
	 * 
	 * @return char representation of our enum, uses a function from enum class
	 */
	public char get_char()
	{
		return enum_shape.char_getter();
	}
	/**
	 * setter for enum
	 * @param a
	 */
	public void set_enum(tetrominos a)
	{
		enum_shape = a;
		allset();
	}
	/**
	 * @name print
	 * Simple print function , prints shapes
	 */
	public void print()
	{
		for(int i = 0; i<shape.length; i++)
			System.out.println(shape[i]);
	}
	/**
	 * @name allset
	 * This function creates the necessary 2d char arrays for our shapes
	 */
	private void allset()
	{
		_shape = get_char();
		if(_shape == 'O')
		{
			shape = new char[][]{{'O','O'},{'O','O'}};
		}
		else if(_shape == 'L')
		{
			shape = new char[][]{{'L','.'},{'L','.'},{'L','L'}};
		}
		else if(_shape == 'Z')
		{
			shape = new char[][]{
				{'Z', 'Z', '.'},
				{'.', 'Z', 'Z'}};
		}
		else if(_shape == 'S')
		{
			shape = new char[][]{
				{'.', 'S', 'S'},
				{'S', 'S', '.'}};
		}
		else if(_shape == 'T')
		{
			shape = new char[][]{
				{'T', 'T', 'T'},
				{'.', 'T', '.'}};
		}
		else if(_shape == 'I')
		{
			shape = new char[][]{
				{'I', 'I', 'I', 'I'}};
		}
		else if(_shape == 'J')
		{
			shape = new char[][]{
				{'.', 'J'},
				{'.', 'J'},
				{'J', 'J'}};
		} 
	}
}