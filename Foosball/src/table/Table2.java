package table;

import java.awt.*;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

import javax.swing.*;

public class Table2 extends JFrame {
	private void createAndDisplayGUI() {

		GamePanel gp = new GamePanel();
		gp.createGUI();

		JPanel rightPanel = new JPanel();
		rightPanel.setLayout(new BorderLayout());
		PreviewPanel pp = new PreviewPanel();
		pp.createGUI();

		JPanel pointsPanel = new JPanel();
		pointsPanel.setOpaque(true);
		pointsPanel.setBackground(Color.DARK_GRAY);
		JLabel pointsLabel = new JLabel("Points");
		pointsPanel.add(pointsLabel);
		rightPanel.add(pp, BorderLayout.PAGE_END);
		rightPanel.add(pointsPanel, BorderLayout.CENTER);

		getContentPane().add(gp, BorderLayout.CENTER);
		getContentPane().add(rightPanel, BorderLayout.LINE_END);

		pack();
		setLocationByPlatform(true);
		setVisible(true);

		// Window Listeners
		addWindowListener(new WindowAdapter() {
			public void windowClosing(WindowEvent e) {
				System.exit(0);
			} // windowClosing
		});
	}

	/*public static void main(String... args) {
		SwingUtilities.invokeLater(new Runnable() {
			public void run() {
				new Table2().createAndDisplayGUI();
			}
		});
	}*/
}

class GamePanel extends JPanel {
	public Dimension getPreferredSize() {
		return (new Dimension(900, 600));
	}

	protected void createGUI() {
		setOpaque(true);
		setBackground(Color.BLUE);
	}
}

class PreviewPanel extends JPanel {
	public Dimension getPreferredSize() {
		return (new Dimension(200, 200));
	}

	protected void createGUI() {
		setOpaque(true);
		setBackground(Color.WHITE);
		JLabel label = new JLabel("Preview of the next Block.");
	}
}