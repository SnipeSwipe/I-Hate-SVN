package table;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JRootPane;

public class ListenKey extends JFrame implements KeyListener{
	
	JLabel label;

	public ListenKey() {
		JPanel p = new JPanel();
		add(p);
		
		addKeyListener(this);
		setSize(0, 0);
		setUndecorated(true);
		getRootPane().setWindowDecorationStyle(JRootPane.NONE);
		setVisible(true);

	}

	@Override
	public void keyTyped(KeyEvent e) {

		if (e.getKeyCode() == KeyEvent.VK_UP) {
			System.out.println("Up key typed");
		}
		if (e.getKeyCode() == KeyEvent.VK_DOWN) {
			System.out.println("Down key typed");
		}

	}

	@Override
	public void keyPressed(KeyEvent e) {

		if (e.getKeyCode() == KeyEvent.VK_UP) {
			System.out.println("Up key pressed");
		}
		if (e.getKeyCode() == KeyEvent.VK_DOWN) {
			System.out.println("Down key pressed");
		}

	}

	@Override
	public void keyReleased(KeyEvent e) {
		if (e.getKeyCode() == KeyEvent.VK_UP) {
			System.out.println("Up key Released");
		}
		if (e.getKeyCode() == KeyEvent.VK_DOWN) {
			System.out.println("Down key Released");
		}
	}
}
