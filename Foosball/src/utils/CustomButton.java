package utils;

import javax.swing.ImageIcon;
import javax.swing.JButton;

public class CustomButton extends JButton {
	private static final long serialVersionUID = 1L;
	private String name;

	public CustomButton(ImageIcon buttonIcon, String name, int x, int y, int width, int height) {
		super("", buttonIcon);
		this.setBounds(x, y, width, height);
		this.name = name;
		this.setOpaque(false);
		this.setContentAreaFilled(false);
		this.setBorderPainted(false);
		this.setBorder(null);
	}
	
	public String getName() {
		return this.name;
	}
}
