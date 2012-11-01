package ba.kickboxing.draw;

import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.IOException;
import java.util.Date;
import java.util.List;

import javax.swing.JButton;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;

import jxl.write.WriteException;

/* TopLevelDemo.java requires no other files. */
public class UI implements ActionListener {
	private JFrame frame;

	private JButton openButton;

	private JFileChooser fc;

	/**
	 * Create the GUI and show it. For thread safety, this method should be
	 * invoked from the event-dispatching thread.
	 */
	private void createAndShowGUI() {
		// Create and set up the window.
		frame = createFrame();

		fc = new JFileChooser();

		openButton = new JButton("Open a File...");
		openButton.addActionListener(this);

		JPanel buttonPanel = new JPanel(new FlowLayout()); // use FlowLayout
		buttonPanel.add(openButton);

		frame.add(buttonPanel);
		// Display the window.
		// frame.pack();
		frame.setVisible(true);
	}

	private JFrame createFrame() {
		JFrame frame = new JFrame("Gazija Tournament Software");
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setSize(300, 300);
		
		return frame;
	}

	public UI() {
		// Schedule a job for the event-dispatching thread:
		// creating and showing this application's GUI.
		javax.swing.SwingUtilities.invokeLater(new Runnable() {
			public void run() {
				createAndShowGUI();
			}
		});
	}

	public void actionPerformed(ActionEvent e) {

		// Handle open button action.
		if (e.getSource() == openButton) {
			int returnVal = fc.showOpenDialog(frame);

			if (returnVal == JFileChooser.APPROVE_OPTION) {
				File file = fc.getSelectedFile();
				// This is where a real application would open the file.
				String inputFilePath = file.getAbsolutePath();

				List<Player> players = null;
				
				try {
					players = IO.readFromTxt(inputFilePath);
				} catch (IOException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
				
				Drawing drawing = new Drawing(players);
				drawing.draw();

				String outputPath = getOutputFilePath();
				try {
					IO.writeToXls(outputPath, drawing.getCategoryMap());

					JOptionPane.showMessageDialog(frame, "Žrijeb je kreiran, pogledajte u fajl " + outputPath);
				} catch (WriteException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				} catch (IOException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
			}

		}

	}

	private String getOutputFilePath() {
		return "output" + new Date().getTime() + ".xls";
	}

}