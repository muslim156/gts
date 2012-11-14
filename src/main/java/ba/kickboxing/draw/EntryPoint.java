package ba.kickboxing.draw;

import java.awt.SplashScreen;

import javax.swing.UIManager;

import ba.kickboxing.draw.business.TournamentManagerImpl;
import ba.kickboxing.draw.persistence.ApacheIo;
import ba.kickboxing.draw.persistence.FileDao;
import ba.kickboxing.draw.ui.MainFrame;


public class EntryPoint {
	private static final String APPLICATION_STORAGE_PATH = "tmp.gts";
	
	// SplashScreen-Image: src/main/resources/git.png
	public static void main(String[] args) throws Exception {
//		test();
		
		
		SplashScreen sc = SplashScreen.getSplashScreen();

		if (sc == null) {
			System.out.println("splash screen is null");
		}
//		Thread.sleep(3000);
		setLookAndFeel();

		// new UI();
		new MainFrame(new TournamentManagerImpl(new FileDao(
				APPLICATION_STORAGE_PATH, true)));

	}

	private static void test() {
		try {
			ApacheIo.test();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			System.exit(0);
		}
	}

	private static void setLookAndFeel() {
		/*
		 * Set the Nimbus look and feel
		 */
		// <editor-fold defaultstate="collapsed"
		// desc=" Look and feel setting code (optional) ">
		/*
		 * If Nimbus (introduced in Java SE 6) is not available, stay with the
		 * default look and feel. For details see
		 * http://download.oracle.com/javase
		 * /tutorial/uiswing/lookandfeel/plaf.html
		 */
		try {
			UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
		} catch (ClassNotFoundException ex) {
			java.util.logging.Logger.getLogger(MainFrame.class.getName()).log(
					java.util.logging.Level.SEVERE, null, ex);
		} catch (InstantiationException ex) {
			java.util.logging.Logger.getLogger(MainFrame.class.getName()).log(
					java.util.logging.Level.SEVERE, null, ex);
		} catch (IllegalAccessException ex) {
			java.util.logging.Logger.getLogger(MainFrame.class.getName()).log(
					java.util.logging.Level.SEVERE, null, ex);
		} catch (javax.swing.UnsupportedLookAndFeelException ex) {
			java.util.logging.Logger.getLogger(MainFrame.class.getName()).log(
					java.util.logging.Level.SEVERE, null, ex);
		}
		// </editor-fold>
	}

}
