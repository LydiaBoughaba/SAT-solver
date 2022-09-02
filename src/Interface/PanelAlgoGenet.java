package Interface;
import java.awt.Color;

import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.filechooser.FileNameExtensionFilter;

import java.awt.Font;
import javax.swing.JButton;
import javax.swing.JFileChooser;

import java.awt.SystemColor;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;


import GA.Main;
import GA.Affichage;

public class PanelAlgoGenet extends JPanel {
	private JTextField tp;
	private JTextField ts;
	private JTextField pc;
	private JTextField pm;
   static File[]f;
	
	public PanelAlgoGenet() {
		setBounds(0,0,731,499);
		setBackground(new Color(255, 255, 255));
		setLayout(null);
		
		JPanel panel = new JPanel();
		panel.setBackground(new Color(47, 79, 79));
		panel.setBounds(0, 0, 731, 72);
		add(panel);
		
		JLabel lblSatsolver = new JLabel("Sat_SOLVER");
		lblSatsolver.setForeground(Color.CYAN);
		lblSatsolver.setFont(new Font("Sylfaen", Font.ITALIC, 43));
		panel.add(lblSatsolver);
		
		JPanel panel_1 = new JPanel();
		panel_1.setBackground(new Color(240, 248, 255));
		panel_1.setBounds(0, 271, 731, 183);
		add(panel_1);
		panel_1.setLayout(null);
		
		JLabel lblNewLabel = new JLabel("R\u00E9sultat :");
		lblNewLabel.setBounds(21, 32, 94, 14);
		panel_1.add(lblNewLabel);
		
		JPanel panel_2 = new JPanel();
		panel_2.setBackground(new Color(240, 248, 255));
		panel_2.setBounds(0, 95, 731, 165);
		add(panel_2);
		panel_2.setLayout(null);
		
		JLabel lblNewLabel1 = new JLabel("Parametres :");
		lblNewLabel1.setBounds(10, 32, 94, 14);
		panel_2.add(lblNewLabel1);
		
		JLabel lblNewLabel_1 = new JLabel("Taille de la population");
		lblNewLabel_1.setBounds(10, 57, 130, 14);
		panel_2.add(lblNewLabel_1);
		
		JLabel lblNewLabel_2 = new JLabel("Taille de la selection");
		lblNewLabel_2.setBounds(10, 82, 130, 14);
		panel_2.add(lblNewLabel_2);
		
		JLabel lblNewLabel_3 = new JLabel("Probabilite de croissement");
		lblNewLabel_3.setBounds(10, 107, 130, 14);
		panel_2.add(lblNewLabel_3);
		
		JLabel mm = new JLabel("Probabilite de mutation");
		mm.setBounds(10, 132, 130, 14);
		panel_2.add(mm);
		
		tp = new JTextField("60");
		tp.setBounds(170, 54, 86, 20);
		panel_2.add(tp);
		tp.setColumns(10);
		
		ts = new JTextField("40");
		ts.setBounds(170, 79, 86, 20);
		panel_2.add(ts);
		ts.setColumns(10);
		
		pc = new JTextField("0.9");
		pc.setBounds(170, 104, 86, 20);
		panel_2.add(pc);
		pc.setColumns(10);
		
		pm = new JTextField("0.05");
		pm.setBounds(170, 129, 86, 20);
		panel_2.add(pm);
		pm.setColumns(10);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(20, 36, 701, 190);
		panel_1.add(scrollPane);
		JTextArea textArea = new JTextArea();
		scrollPane.setViewportView(textArea);
		textArea.setForeground(Color.BLACK);
		textArea.setBackground(Color.LIGHT_GRAY);
		
		JButton up = new JButton("Selection des fichiers");
		up.setForeground(SystemColor.controlDkShadow);
		up.setBounds(533, 28, 188, 38);
		panel_2.add(up);
        up.addActionListener(new ActionListener() {
			
		 public void actionPerformed(ActionEvent e) {

				double Prob_croi =Double.parseDouble(pc.getText());
				double Prob_mut=Double.parseDouble(pm.getText());
				int taille_p=Integer.parseInt(tp.getText());
				int taille_s=Integer.parseInt(ts.getText());
				Main.crossOverChance = Prob_croi;
				Main.mutationChance = Prob_mut;
				Main.populationSize = taille_p;
				Main.selectionSize = taille_s;
				
				
				String ss="";
				for(int h=0;h<f.length;h++){
					String chemin=f[h].getPath();
					chemin = chemin.replaceAll("//", "\\");
					Affichage s = Main.GA(chemin);
			        ss=ss+"Instance:"+h+ "   Temps : "+s.getTemps()+"   Nombre de clauses satisfaites : "+s.getBest()+" Ram utilisé : "+s.getRam()+""+"\n";
			        
				}
				textArea.setText(ss);
			}});
		
		
		JButton ex = new JButton("Execution");
		ex.setBounds(531, 103, 190, 38);
		panel_2.add(ex);
		up.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				
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

	}
}
