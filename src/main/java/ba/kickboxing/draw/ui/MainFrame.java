package ba.kickboxing.draw.ui;

import java.awt.Component;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JComboBox;
import javax.swing.JOptionPane;
import javax.swing.JTextField;

import ba.kickboxing.draw.business.TournamentManager;
import ba.kickboxing.draw.common.AgeCategory;
import ba.kickboxing.draw.common.Discipline;
import ba.kickboxing.draw.common.Player;
import ba.kickboxing.draw.common.Sex;
import ba.kickboxing.draw.common.WeightCategory;

import jxl.read.biff.BiffException;
import jxl.write.WriteException;

/**
*
* @author hosmanag
*/
public class MainFrame extends javax.swing.JFrame implements ActionListener {

	private static final long serialVersionUID = 5495625702638704722L;
	private TournamentManager tournamentManager;
	private String xlsPath = "zrijeb.xls";
	private List<Component> clearableComponents = new ArrayList<Component>();

	/**
	 * Creates new form MainFrame
	 */
	public MainFrame(TournamentManager tournamentManager) {
		this.tournamentManager = tournamentManager;
		
		initComponents();
		
		setVisible(true);
		
	}

  

/**
    * This method is called from within the constructor to initialize the form.
    * WARNING: Do NOT modify this code. The content of this method is always
    * regenerated by the Form Editor.
    */
   
	 // <editor-fold defaultstate="collapsed" desc="Generated Code">
	 private void initComponents() {

	        jMenuBar1 = new javax.swing.JMenuBar();
	        jMenu1 = new javax.swing.JMenu();
	        jMenu2 = new javax.swing.JMenu();
	        buttonGroup1 = new javax.swing.ButtonGroup();
	        jLabel1 = new javax.swing.JLabel();
	        jTextField1 = new javax.swing.JTextField();
	        jLabel2 = new javax.swing.JLabel();
	        jComboBox1 = new javax.swing.JComboBox();
	        jLabel3 = new javax.swing.JLabel();
	        jComboBox2 = new javax.swing.JComboBox();
	        jLabel4 = new javax.swing.JLabel();
	        jComboBox3 = new javax.swing.JComboBox();
	        jLabel5 = new javax.swing.JLabel();
	        jTextField2 = new javax.swing.JTextField();
	        jButton1 = new javax.swing.JButton();
	        jButton2 = new javax.swing.JButton();
	        jRadioButton2 = new javax.swing.JRadioButton();
	        jRadioButton3 = new javax.swing.JRadioButton();
	        jLabel6 = new javax.swing.JLabel();
	        jButton3 = new javax.swing.JButton();
	        jMenuBar2 = new javax.swing.JMenuBar();
	        jMenu3 = new javax.swing.JMenu();
	        jMenuItem1 = new javax.swing.JMenuItem();
	        jMenuItem2 = new javax.swing.JMenuItem();

	        jMenu1.setText("File");
	        jMenuBar1.add(jMenu1);

	        jMenu2.setText("Edit");
	        jMenuBar1.add(jMenu2);

	        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

	        jLabel1.setText("Ime i prezime");

	        jTextField1.setColumns(30);
	        clearableComponents.add(jTextField1);
	        
	        jLabel2.setText("Disciplina");

	        jComboBox1.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "Semi contact", "Light contact", "Full contact", "Low kick", "K1 rules" }));
	        clearableComponents.add(jComboBox1);

	        jLabel3.setText("Tezisna kategorija");

	        jComboBox2.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "-81", "+81" }));
	        clearableComponents.add(jComboBox2);
	        
	        jLabel4.setText("Uzrast");

	        jComboBox3.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "Kadet", "Junior", "Senior" }));
	        clearableComponents.add(jComboBox3);
	        
	        jLabel5.setText("Naziv kluba");

	        jTextField2.setColumns(30);
	        clearableComponents.add(jTextField2);
	        
	        jButton1.setText("Sacuvaj");
	        jButton1.addActionListener(this);

	        jButton2.setText("Ocisti");
	        jButton2.addActionListener(this);

	        buttonGroup1.add(jRadioButton2);
	        jRadioButton2.setText("Muski");

	        buttonGroup1.add(jRadioButton3);
	        jRadioButton3.setText("Zenski");

	        jLabel6.setText("Spol");

	        jButton3.setText("Generisi zrijeb!");
	        jButton3.addActionListener(this);

	        jMenu3.setText("Akcije");

	        jMenuItem1.setText("Ucitaj turnir...");
	        jMenu3.add(jMenuItem1);

	        jMenuItem2.setText("Sacuvaj turnir");
	        jMenuItem2.addActionListener(new java.awt.event.ActionListener() {
	            public void actionPerformed(java.awt.event.ActionEvent evt) {
	                jMenuItem2ActionPerformed(evt);
	            }
	        });
	        jMenu3.add(jMenuItem2);

	        jMenuBar2.add(jMenu3);

	        setJMenuBar(jMenuBar2);

	        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
	        getContentPane().setLayout(layout);
	        layout.setHorizontalGroup(
	            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
	            .addGroup(layout.createSequentialGroup()
	                .addContainerGap()
	                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
	                    .addGroup(layout.createSequentialGroup()
	                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
	                            .addGroup(layout.createSequentialGroup()
	                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
	                                    .addComponent(jLabel1)
	                                    .addComponent(jLabel2)
	                                    .addComponent(jLabel3)
	                                    .addComponent(jLabel4)
	                                    .addComponent(jLabel5)
	                                    .addComponent(jLabel6))
	                                .addGap(41, 41, 41)
	                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
	                                    .addComponent(jTextField2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
	                                    .addComponent(jComboBox2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
	                                    .addComponent(jComboBox1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
	                                    .addComponent(jTextField1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
	                                    .addComponent(jComboBox3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
	                                    .addGroup(layout.createSequentialGroup()
	                                        .addGap(128, 128, 128)
	                                        .addComponent(jRadioButton3))
	                                    .addComponent(jRadioButton2))
	                                .addGap(0, 0, Short.MAX_VALUE))
	                            .addGroup(layout.createSequentialGroup()
	                                .addComponent(jButton1)
	                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
	                                .addComponent(jButton2)))
	                        .addContainerGap())
	                    .addGroup(layout.createSequentialGroup()
	                        .addComponent(jButton3)
	                        .addContainerGap())))
	        );
	        layout.setVerticalGroup(
	            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
	            .addGroup(layout.createSequentialGroup()
	                .addContainerGap()
	                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
	                    .addComponent(jLabel1)
	                    .addComponent(jTextField1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
	                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
	                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
	                    .addComponent(jLabel2)
	                    .addComponent(jComboBox1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
	                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
	                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
	                    .addComponent(jLabel3)
	                    .addComponent(jComboBox2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
	                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
	                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
	                    .addComponent(jLabel4)
	                    .addComponent(jComboBox3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
	                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
	                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
	                    .addComponent(jLabel5)
	                    .addComponent(jTextField2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
	                .addGap(7, 7, 7)
	                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
	                    .addGroup(layout.createSequentialGroup()
	                        .addGap(4, 4, 4)
	                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
	                            .addComponent(jRadioButton2)
	                            .addComponent(jLabel6)))
	                    .addComponent(jRadioButton3))
	                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
	                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
	                    .addComponent(jButton1)
	                    .addComponent(jButton2))
	                .addGap(18, 18, 18)
	                .addComponent(jButton3)
	                .addContainerGap(44, Short.MAX_VALUE))
	        );

	        pack();
	    }// </editor-fold>

   private void jMenuItem2ActionPerformed(java.awt.event.ActionEvent evt) {
       // TODO add your handling code here:
   }

   @Override
	public void actionPerformed(ActionEvent e) {
		if (e.getSource() == jButton1) {
			try {
				savePlayer();
				showMessage("Ucesnik uspjesno dodat!", true);
			} catch (Exception ex) {
				showMessage("Desila se greska prilikom dodavanja ucesnika:\n" + ex.getMessage(), false);
			}
			
		} else if (e.getSource() == jButton3) {
			try {
				draw();
				showMessage("Zrijeb uspjesno generisan!", true);
			} catch (Exception ex) {
				showMessage("Desila se greska prilikom generisanja zrijeba:\n" + ex.getMessage(), false);
			}
		} else if (e.getSource() == jButton2) {
			clearComponents();
		}
	}

	private void clearComponents() {
		for (Component c : clearableComponents) {
			if (c instanceof JTextField) {
				((JTextField) c).setText("");
			} else if (c instanceof JComboBox) {
				((JComboBox) c).setSelectedIndex(0);
			}
		}
	}



	private void draw() throws WriteException, IOException, BiffException {
		tournamentManager.drawAndSave(xlsPath);
	}

	private void showMessage(String message, boolean isSuccessMsg) {
		JOptionPane.showMessageDialog(this, message, 
				isSuccessMsg ? "Uspjeh" : "Greska", 
				isSuccessMsg ? JOptionPane.INFORMATION_MESSAGE : JOptionPane.ERROR_MESSAGE);
	}



	private void savePlayer() {
		String nameSurname = jTextField1.getText();
		String discipline = (String) jComboBox1.getSelectedItem();
		String weightCategory = (String) jComboBox2.getSelectedItem();
		String ageCategory = (String) jComboBox3.getSelectedItem();
		String clubName = jTextField2.getText();		
		Sex s = jRadioButton2.isSelected() ? Sex.M : Sex.F;
		
		Player p = new Player(nameSurname, Discipline.getEnum(discipline.toUpperCase()), 
				WeightCategory.getEnum(weightCategory), AgeCategory.getEnum(ageCategory.toUpperCase()), s, clubName);
		tournamentManager.savePlayer(p);
		
	}
	
	// Variables declaration - do not modify
	private javax.swing.ButtonGroup buttonGroup1;
	private javax.swing.JButton jButton1;
	private javax.swing.JButton jButton2;
	private javax.swing.JButton jButton3;
	private javax.swing.JComboBox jComboBox1;
	private javax.swing.JComboBox jComboBox2;
	private javax.swing.JComboBox jComboBox3;
	private javax.swing.JLabel jLabel1;
	private javax.swing.JLabel jLabel2;
	private javax.swing.JLabel jLabel3;
	private javax.swing.JLabel jLabel4;
	private javax.swing.JLabel jLabel5;
	private javax.swing.JLabel jLabel6;
	private javax.swing.JMenu jMenu1;
	private javax.swing.JMenu jMenu2;
	private javax.swing.JMenu jMenu3;
	private javax.swing.JMenuBar jMenuBar1;
	private javax.swing.JMenuBar jMenuBar2;
	private javax.swing.JMenuItem jMenuItem1;
	private javax.swing.JMenuItem jMenuItem2;
	private javax.swing.JRadioButton jRadioButton2;
	private javax.swing.JRadioButton jRadioButton3;
	private javax.swing.JTextField jTextField1;
	private javax.swing.JTextField jTextField2;
	// End of variables declaration
	
}
