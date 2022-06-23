package xyz.maywr.extract4j.util;

/**
 * @author maywr
 * 23.06.2022 17:01
 */
public class BytesUtils
{
	public static int findIndex( byte[] searchIn, byte[] searchWhat )
	{
		for ( int i = 0 ; i < searchIn.length - searchWhat.length + 1 ; ++i )
		{
			boolean found = true;
			for ( int j = 0 ; j < searchWhat.length ; ++j )
			{
				if ( searchIn[ i + j ] != searchWhat[ j ] )
				{
					found = false;
					break;
				}
			}
			if ( found )
				return i;
		}
		return -1;
	}
}
