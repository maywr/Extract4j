package xyz.maywr.extract4j;

import xyz.maywr.extract4j.util.BytesUtils;
import xyz.maywr.extract4j.util.FileUtils;

import java.io.File;
import java.io.IOException;
import java.util.Arrays;

/**
 * @author maywr
 * 23.06.2022 16:32
 */
public enum Extractor
{
	INSTANCE;

	public static final byte[] JAR_HEADER = { 80, 75, 3, 4, 10, 0 };

	public void extract( File i, File o ) throws ExecutionException
	{
		try
		{
			byte[] exeData = FileUtils.readFileToByteArray( i );
			int index = BytesUtils.findIndex( exeData, JAR_HEADER );

			if ( index == -1 )
			{
				throw new ExecutionException( "given .exe file is not launch4j executable" );
			}

			FileUtils.writeByteArrayToFile( o, Arrays.copyOfRange( exeData, index, exeData.length ) );
		} catch ( IOException e )
		{
			throw new ExecutionException( "IO Error: " + e.getLocalizedMessage() );
		}
	}
}
