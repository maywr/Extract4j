package xyz.maywr.extract4j.util;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;

/**
 * @author maywr
 * 23.06.2022 16:28
 */
public class FileUtils
{
	public static byte[] readFileToByteArray( File f )
	{
		try
		{
			return Files.readAllBytes( Paths.get( f.getAbsolutePath() ) );
		} catch ( IOException e )
		{
			return new byte[]{ 0 };
		}
	}

	public static void writeByteArrayToFile( File dump, byte[] copyOfRange ) throws IOException
	{
		Files.write( Paths.get( dump.getAbsolutePath() ), copyOfRange );
	}
}
