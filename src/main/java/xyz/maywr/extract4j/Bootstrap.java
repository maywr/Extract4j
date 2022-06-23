package xyz.maywr.extract4j;

import xyz.maywr.extract4j.ui.Window;

import javax.swing.*;

/**
 * @author maywr
 * 23.06.2022 11:48
 */
public class Bootstrap
{
	public static final String TITLE = "Extract4j 1.0";

	public static void main( String[] args )
	{
		try
		{
			UIManager.setLookAndFeel( UIManager.getSystemLookAndFeelClassName() );
		} catch ( Exception e )
		{
			e.printStackTrace();
		}
		new Window().setVisible( true );
	}


}
