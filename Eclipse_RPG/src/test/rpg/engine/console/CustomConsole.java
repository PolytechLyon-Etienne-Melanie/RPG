package test.rpg.engine.console;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.PrintStream;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import test.rpg.engine.console.printer.CapturePane;
import test.rpg.engine.console.printer.JConsole;
import test.rpg.engine.console.printer.PrintColorWriter;

public class CustomConsole extends JFrame
{

	private JPanel contentPane;

	/**
	 * Create the frame.
	 */
	public CustomConsole(JConsole console)
	{
		setBackground(Color.BLACK);
		initComponents(console);
	}

	private void initComponents(JConsole console)
	{
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 640, 480);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(new BorderLayout(0, 0));
		contentPane.add(console, BorderLayout.CENTER);
	}

}
