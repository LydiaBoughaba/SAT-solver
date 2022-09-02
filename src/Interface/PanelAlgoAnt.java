package Interface;
import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;

import javax.swing.JButton;
import javax.swing.JFileChooser;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.filechooser.FileFilter;
import javax.swing.filechooser.FileNameExtensionFilter;
import javax.swing.table.DefaultTableModel;


import AntSys.ACS;
import AntSys.Affichage;
import AntSys.Parametre;

import javax.swing.JTable;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import java.awt.Font;

public class PanelAlgoAnt extends JPanel {
	
	private JTextField txtAnt;
	private JTextField alpha;
	private JTextField beta;
	private JTextField ro;
	private JTextField antnbr;
	private JTextField phinit;
	private JTextField q;
	private JTextField iter;
	private JTextField textField_7;
	private JTextField textField_8;
	static File [] f;
	private JTable table;
	
	/**
	 * Create the panel.
	 */
	public PanelAlgoAnt() {
		setBounds(0,0,731,499);
		setBackground(new Color(255, 255, 255));
		setLayout(null);
		
		JPanel panel = new JPanel();
		panel.setToolTipText("");
		panel.setBackground(new Color(47, 79, 79));
		panel.setBounds(0, 0, 731, 72);
		add(panel);
		
		JLabel lblSatsolver = new JLabel("Sat_SOLVER");
		lblSatsolver.setForeground(Color.CYAN);
		lblSatsolver.setFont(new Font("Sylfaen", Font.ITALIC, 43));
		panel.add(lblSatsolver);
		
		JPanel panel_2 = new JPanel();
		panel_2.setBackground(new Color(240, 248, 255));
		panel_2.setBounds(0, 77, 731, 165);
		add(panel_2);
		panel_2.setLayout(null);
		
		JLabel lblNewLabel1 = new JLabel("Parametres :");
		lblNewLabel1.setBounds(10, 11, 94, 14);
		panel_2.add(lblNewLabel1);
		
		JLabel lblNewLabel_1 = new JLabel("Alpha");
		lblNewLabel_1.setBounds(10, 36, 43, 14);
		panel_2.add(lblNewLabel_1);
		
		JLabel lblNewLabel_2 = new JLabel("betha");
		lblNewLabel_2.setBounds(10, 61, 82, 14);
		panel_2.add(lblNewLabel_2);
		
		JLabel lblNewLabel_3 = new JLabel("\u03C1  evaporation rate");
		lblNewLabel_3.setBounds(10, 82, 82, 14);
		panel_2.add(lblNewLabel_3);
		
		JLabel lblNewLabel_4 = new JLabel("Ant number");
		lblNewLabel_4.setBounds(10, 107, 82, 14);
		panel_2.add(lblNewLabel_4);
		
		alpha = new JTextField();
		alpha.setBounds(102, 36, 86, 20);
		panel_2.add(alpha);
		alpha.setColumns(10);
		
		beta = new JTextField();
		beta.setBounds(102, 58, 86, 20);
		panel_2.add(beta);
		beta.setColumns(10);
		
		ro = new JTextField();
		ro.setBounds(102, 79, 86, 20);
		panel_2.add(ro);
		ro.setColumns(10);
		
		antnbr = new JTextField();
		antnbr.setBounds(102, 104, 86, 20);
		panel_2.add(antnbr);
		antnbr.setColumns(10);
		
		JLabel lblNewLabel_9 = new JLabel("pheromone init");
		lblNewLabel_9.setBounds(224, 36, 155, 14);
		panel_2.add(lblNewLabel_9);
		
		phinit = new JTextField();
		phinit.setBounds(374, 33, 86, 20);
		panel_2.add(phinit);
		phinit.setColumns(10);
		
		JLabel lblNewLabel_10 = new JLabel("Q0");
		lblNewLabel_10.setBounds(224, 61, 46, 14);
		panel_2.add(lblNewLabel_10);
		
		JLabel lblNewLabel_11 = new JLabel("Iteration");
		lblNewLabel_11.setBounds(224, 82, 140, 14);
		panel_2.add(lblNewLabel_11);
		
		q = new JTextField();
		q.setBounds(374, 58, 86, 20);
		panel_2.add(q);
		q.setColumns(10);
		
		iter = new JTextField();
		iter.setBounds(374, 79, 86, 20);
		panel_2.add(iter);
		iter.setColumns(10);
		
		JButton executer = new JButton("Execution");
		executer.setBounds(531, 127, 190, 38);
		panel_2.add(executer);
		
		JButton btnNewButton = new JButton("Selection des fichiers");
		btnNewButton.setBounds(284, 127, 188, 38);
		panel_2.add(btnNewButton);
		btnNewButton.setForeground(new Color(105, 105, 105));
		
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				 textField_8.setText("veilez patienter lors de l'excution affichage prend quelque minute");		
						
			JFileChooser chooser=new JFileChooser();
			chooser.setDialogTitle("selection des fichier ");
			chooser.setAcceptAllFileFilterUsed(false);
			chooser.addChoosableFileFilter(new FileNameExtensionFilter("Conjunctive Normal Form(.cnf)","cnf"));
			chooser.setMultiSelectionEnabled(true);
			int res=chooser.showDialog(null, "importer");
			if(res==JFileChooser.APPROVE_OPTION){
				
				f=chooser.getSelectedFiles();
								
			}
			
}
		});
		
		JLabel lblNewLabel_7 = new JLabel("");
		lblNewLabel_7.setBounds(109, 439, 46, 14);
		add(lblNewLabel_7);
		
		JLabel lblNewLabel_8 = new JLabel("");
		lblNewLabel_8.setBounds(109, 474, 46, 14);
		add(lblNewLabel_8);
		
		JPanel panel_1 = new JPanel();
		panel_1.setLayout(null);
		panel_1.setBackground(new Color(240, 248, 255));
		panel_1.setBounds(0, 253, 731, 237);
		add(panel_1);
		
		JLabel lblNewLabel = new JLabel("R\u00E9sultat :");
		lblNewLabel.setBounds(10, 11, 94, 14);
		panel_1.add(lblNewLabel);
		
		textField_8 = new JTextField();
		textField_8.setColumns(10);
		textField_8.setBounds(259, 8, 379, 20);
		panel_1.add(textField_8);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(20, 36, 701, 190);
		panel_1.add(scrollPane);
		JTextArea textArea = new JTextArea();
		scrollPane.setViewportView(textArea);
		textArea.setForeground(Color.BLACK);
		textArea.setBackground(Color.LIGHT_GRAY);
		
		
		
		
		 executer.addActionListener(new ActionListener() {
				
			 public void actionPerformed(ActionEvent e) {
					
					double al=Double.parseDouble(alpha.getText());
					double be=Double.parseDouble(beta.getText());
					double rate=Double.parseDouble(ro.getText());
					double q00=Double.parseDouble(q.getText());
					double phi=Double.parseDouble(phinit.getText());
					int anbr =Integer.parseInt(antnbr.getText());
					int it=Integer.parseInt(iter.getText());
					
					Parametre p=new Parametre(al,be,rate,phi,it,anbr,q00);
					try {
						

						String ss="";
					for(int h=0;h<f.length;h++){
						String chemin=f[h].getPath();
						chemin=chemin.replaceAll("//", "\\");
						Affichage s=ACS.AC_S(chemin, p);
						ss=ss+"Instance:"+h+ "   Time:"+s.getTemps()+"   Number clause satisfied:"+s.getBest()+"\n";
						
					}
					textArea.setText(ss);
					
							
				
				} catch (IOException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
				
				}});
		
		
}	
}
