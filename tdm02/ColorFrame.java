package tdm02;
import java.awt.Color;

import javax.swing.*;

public class ColorFrame
{
	public static void main(String[] args) throws Exception
	{
		ColorFrame c = new ColorFrame();
		c.execute();
	}
	
	private void execute() throws Exception
	{
		JFrame frame = new JFrame("J'adore le sex");
		frame.setSize(300,300);
		
		//
		frame.getContentPane().setBackground(Color.GREEN);
		frame.setVisible(true);
		Thread.sleep(2000);
		
		
		//
		frame.getContentPane().setBackground(Color.RED);
		frame.setVisible(true);
		Thread.sleep(2000);
		
		
		frame.getContentPane().setBackground(Color.GREEN);
		frame.setVisible(true);
		Thread.sleep(2000);
		
		frame.dispose();
		
	}
}