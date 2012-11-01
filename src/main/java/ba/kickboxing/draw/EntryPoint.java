package ba.kickboxing.draw;

import javax.swing.UIManager;
import javax.swing.UnsupportedLookAndFeelException;

public class EntryPoint {
	public static void main(String[] args) throws Exception {
		setLookAndFeel();

		new UI();

	}

	private static void setLookAndFeel() {
		try {
			UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
		} catch (UnsupportedLookAndFeelException e) {
			// handle exception
		} catch (ClassNotFoundException e) {
			// handle exception
		} catch (InstantiationException e) {
			// handle exception
		} catch (IllegalAccessException e) {
			// handle exception
		}
	}

}
