package Interface;
import java.awt.Color;


import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.JButton;
import javax.swing.JFileChooser;
import javax.swing.JLabel;
import AlgoPA.Algos;
import AlgoPA.Clause;
import AlgoPA.Litteral;
import AlgoPA.Load;
import AlgoPA.Sat;
import AlgoPA.Solution;
import java.io.*;
import java.util.ArrayList;
import java.util.List;
import java.util.ArrayList;
import java.util.List;

import javax.swing.SwingConstants;
import javax.swing.filechooser.FileNameExtensionFilter;
import java.awt.Font;
		
public class PanelAlgoAveugleHeuri extends JPanel {
	
	
    
    /**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	public static Sat sat;

	public PanelAlgoAveugleHeuri() {
		setBounds(0,0,731,499);
		setBackground(new Color(255, 255, 255));
		setLayout(null);
	
		JPanel panel = new JPanel();
		panel.setBounds(0, 0, 731, 72);
		panel.setBackground(new Color(47, 79, 79));
		add(panel);
		
		JLabel lblSatsolver = new JLabel("Sat_SOLVER");
		lblSatsolver.setForeground(Color.CYAN);
		lblSatsolver.setFont(new Font("Sylfaen", Font.ITALIC, 43));
		panel.add(lblSatsolver);
		
		JPanel panel_1 = new JPanel();
		panel_1.setBackground(new Color(240, 248, 255));
		panel_1.setBounds(0, 98, 731, 206);
		add(panel_1);
		panel_1.setLayout(null);
		
		JLabel lblNewLabel1 = new JLabel("Algorithms :");
		lblNewLabel1.setBounds(21, 32, 94, 14);
		panel_1.add(lblNewLabel1);
		
		JLabel lblNewLabel = new JLabel("Recherche en profondeur d'abord :");
		lblNewLabel.setBounds(21, 86, 220, 14);
		panel_1.add(lblNewLabel);
		
		JLabel lblNewLabel_1 = new JLabel("Algorithme A* :");
		lblNewLabel_1.setBounds(21, 144, 94, 14);
		panel_1.add(lblNewLabel_1);
		
		JLabel lblNewLabel_6 = new JLabel("clauses satisfaites :");
		lblNewLabel_6.setBounds(516, 32, 118, 14);
		panel_1.add(lblNewLabel_6);
		
		
		JLabel lblNewLabel_5 = new JLabel("temps d'execution");
		lblNewLabel_5.setBounds(286, 32, 131, 14);
		panel_1.add(lblNewLabel_5);
		
		JLabel Satisfiedclauses = new JLabel("");
		Satisfiedclauses.setBounds(516, 86, 94, 14);
		panel_1.add(Satisfiedclauses);
		
		JLabel SatisclauseAetoil = new JLabel("");
		SatisclauseAetoil.setBounds(516, 144, 94, 14);
		panel_1.add(SatisclauseAetoil);
		
		JLabel temps = new JLabel("temps fix\u00E9 a 2000 ms");
		temps.setBounds(286, 86, 154, 14);
		panel_1.add(temps);
		
		JLabel lblNewLabel_3 = new JLabel("temps fix\u00E9 a 2000 ms");
		lblNewLabel_3.setBounds(286, 144, 154, 14);
		panel_1.add(lblNewLabel_3);
		
		JLabel choixfile = new JLabel("choisiser un fichier");
		choixfile.setBackground(Color.LIGHT_GRAY);
		choixfile.setForeground(Color.BLACK);
		choixfile.setHorizontalAlignment(SwingConstants.CENTER);
		choixfile.setBounds(42, 330, 469, 28);
		add(choixfile);
		JButton importbutton = new JButton("Importer");
		

		importbutton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				JFileChooser fileChooser = new JFileChooser();
				fileChooser.setAcceptAllFileFilterUsed(false);
				fileChooser.addChoosableFileFilter(new FileNameExtensionFilter("Conjunctive Normal Form (.cnf)", "cnf"));
				int result = fileChooser.showOpenDialog(null);
				if(result==JFileChooser.APPROVE_OPTION)
				{
					String file  = fileChooser.getSelectedFile().getPath().toString();
					choixfile.setText(file);
					
				}
				
			}});
		
		
		importbutton.setBounds(569, 333, 89, 23);
		add(importbutton);
		
		JButton btnNewButton = new JButton("Executer");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String choice = choixfile.getText();
				Algos Algos = new Algos();
				Load.sat = Load.LoadSat(choice);
		        //float t = Load.A_STAR_TOTAL_TIME; 
		       int Profondeur = Algos.depthFirstAlgorithm();
		        Satisfiedclauses.setText(""+Profondeur);
		       int A = Algos.aStarAlgorithm();
		       	SatisclauseAetoil.setText(""+A);
		       	
		       	 
	
				
			}});
		btnNewButton.setBackground(new Color(240, 255, 255));
		btnNewButton.setBounds(280, 396, 129, 53);
		add(btnNewButton);
		
		
		

		}
	
	
	
	
}
