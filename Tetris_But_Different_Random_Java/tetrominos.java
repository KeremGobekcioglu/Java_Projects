import java.util.Random;

/**
 * simple enum class for representing our tetrominos
 */
public enum tetrominos
{
	O,
	L,
	J,
	S,
	Z,
	T,
	I,
	R;
	/**
	 * 
	 * @return the char representation of the enum
	 */
	public char char_getter()
	{
		if(this == O)
		{
			return 'O';
		}
		else if(this == L)
		{
			return 'L';
		}
		else if(this == Z)
		{
			return 'Z';
		}
		else if(this == S)
		{
			return 'S';
		}
		else if(this == T)
		{
			return 'T';
		}
		else if(this == I)
		{
			return 'I';	
		}
		else if(this == J)
			return 'J';
		else
		{
			char[] arr = new char[]{'L','J','S','T','Z','I','O'};
			Random rand = new Random();
			int random_char = rand.nextInt(7);
			return arr[random_char];
		}
	
		}
}