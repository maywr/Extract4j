package xyz.maywr.extract4j.ui;

import xyz.maywr.extract4j.Bootstrap;
import xyz.maywr.extract4j.ExecutionException;
import xyz.maywr.extract4j.Extractor;

import javax.imageio.ImageIO;
import javax.swing.*;
import javax.swing.filechooser.FileNameExtensionFilter;
import java.awt.*;
import java.io.File;
import java.io.IOException;

/**
 * @author maywr
 * 23.06.2022 16:29
 */
public class Window extends JFrame
{

	public static final Dimension SCREEN_SIZE = Toolkit.getDefaultToolkit().getScreenSize();
	public static final int WIDTH = 455, HEIGHT = 200;

	public Window()
	{
		super( Bootstrap.TITLE );
		this.setBounds( SCREEN_SIZE.width / 2 - ( WIDTH / 2 ), SCREEN_SIZE.height / 2 - ( HEIGHT / 2 ), WIDTH, HEIGHT );
		this.setDefaultCloseOperation( EXIT_ON_CLOSE );
		this.setLayout( null );
		this.setIcon( "logo.png" );
		this.setResizable( false );

		JButton extractButton = new JButton( "extract" );
		extractButton.setBounds( 5, 120, 428, 30 );
		this.add( extractButton );

		JLabel exePathLabel = new JLabel( "input exe path:" );
		exePathLabel.setBounds( 5, 15, 180, 15 );
		exePathLabel.setFont( new Font( "Arial", 0, 16 ) );
		this.add( exePathLabel );

		JTextField exePathField = new JTextField();
		exePathField.setBounds( 5, 35, 400, 23 );
		exePathField.setEditable( false );
		this.add( exePathField );

		JButton chooseExeButton = new JButton( "..." );
		chooseExeButton.setBounds( 408, 35, 25, 23 );
		this.add( chooseExeButton );

		JLabel jarPathLabel = new JLabel( "save jar to:" );
		jarPathLabel.setBounds( 5, 65, 180, 15 );
		jarPathLabel.setFont( new Font( "Arial", 0, 16 ) );
		this.add( jarPathLabel );

		JTextField jarFileField = new JTextField();
		jarFileField.setBounds( 5, 85, 400, 23 );
		jarFileField.setEditable( false );
		this.add( jarFileField );

		JButton saveJarButton = new JButton( "..." );
		saveJarButton.setBounds( 408, 85, 25, 23 );
		this.add( saveJarButton );

		chooseExeButton.addActionListener( e ->
		{
			JFileChooser nfileChooser = new NativeJFileChooser( System.getProperty( "user.home" ) );
			nfileChooser.setFileFilter( new FileNameExtensionFilter( "Launch4j Executable", "exe" ) );
			int option = nfileChooser.showOpenDialog( null );
			if ( option == NativeJFileChooser.APPROVE_OPTION )
			{
				exePathField.setText( nfileChooser.getSelectedFile().getAbsolutePath() );
			}
		} );

		saveJarButton.addActionListener( e ->
		{
			JFileChooser nfileChooser = new NativeJFileChooser( System.getProperty( "user.home" ) );
			nfileChooser.setFileFilter( new FileNameExtensionFilter( "Java Executable", "jar" ) );
			int option = nfileChooser.showSaveDialog( null );
			if ( option == NativeJFileChooser.APPROVE_OPTION )
			{
				jarFileField.setText( nfileChooser.getSelectedFile().getAbsolutePath() );
			}
		} );

		extractButton.addActionListener( e ->
		{
			if ( exePathField.getText().isEmpty() || jarFileField.getText().isEmpty() )
			{
				JOptionPane.showMessageDialog( this, "select files first", Bootstrap.TITLE, JOptionPane.ERROR_MESSAGE );
				return;
			}
			File i = new File( exePathField.getText() ), o = new File( jarFileField.getText() );
			try
			{
				Extractor.INSTANCE.extract( i, o );
				JOptionPane.showMessageDialog( this, String.format( "successfully extracted %s to %s", i.getName(), o.getName() ), Bootstrap.TITLE, JOptionPane.PLAIN_MESSAGE );
			} catch ( ExecutionException ex )
			{
				JOptionPane.showMessageDialog( this, ex.getMessage(), Bootstrap.TITLE, JOptionPane.ERROR_MESSAGE );
			}
		} );
	}

	private void setIcon( String resourceName )
	{
		try
		{
			this.setIconImage( ImageIO.read( getClass().getClassLoader().getResource( resourceName ) ) );
		} catch ( IOException e )
		{
			e.printStackTrace();
		}
	}

}
