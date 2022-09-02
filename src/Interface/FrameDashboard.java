package Interface;
import java.awt.EventQueue;
import java.awt.Image;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JPanel;
import java.awt.Color;
import javax.swing.border.LineBorder;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.Font;
import javax.swing.SwingConstants;

public class FrameDashboard extends JFrame {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private JPanel contentPane;
	private Image home = new ImageIcon(FrameDashboard.class.getResource("home1.png")).getImage().getScaledInstance(90, 90, Image.SCALE_SMOOTH);
	private PanelAlgoAveugleHeuri PanelAlgoAvH ;
	private PanelAlgoGenet PanelAlgoG ;
	private PanelAlgoAnt PanelAlgoAnt ;

	

	
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					FrameDashboard frame = new FrameDashboard();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	
	public FrameDashboard() {
		setBackground(new Color(0, 139, 139));
		setLocationRelativeTo(null);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 990, 521);
		setUndecorated(true);
		contentPane = new JPanel();
		contentPane.setBackground(new Color(255, 255, 255));
;
		contentPane.setBorder(new LineBorder(new Color(0, 0, 128), 2));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		PanelAlgoAvH = new PanelAlgoAveugleHeuri();
		PanelAlgoG = new PanelAlgoGenet();
		PanelAlgoAnt = new PanelAlgoAnt();
		
		JPanel panelMenu = new JPanel();
		panelMenu.setBackground(new Color(47, 79, 79));
		panelMenu.setBounds(0, 0, 239, 522);
		contentPane.add(panelMenu);
		panelMenu.setLayout(null);
		
		JLabel lblNewLabel = new JLabel();
		lblNewLabel.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel.setLabelFor(lblNewLabel);
		lblNewLabel.setBounds(0, 11, 239, 129);
		lblNewLabel.setIcon(new ImageIcon(home));
		panelMenu.add(lblNewLabel);
		
		
		
		
		
		JPanel panelprofA = new JPanel();
		panelprofA.addMouseListener(new PanelButtonMouseAdapter(panelprofA){
			@Override
			public void mouseClicked (MouseEvent e) {
				MenuClicked(PanelAlgoAvH);
			}
		} );
		panelprofA.setBackground(new Color(47, 79, 79));
		panelprofA.setLayout(null);
		panelprofA.setBounds(10, 230, 219, 53);
		panelMenu.add(panelprofA);
		
		JLabel lblNewLabel_1 = new JLabel("Algo aveugle et heuristique");
		lblNewLabel_1.setIcon(new ImageIcon(FrameDashboard.class.getResource("/Interface/Astar.png")));
		lblNewLabel_1.setBounds(0, 11, 229, 31);
		panelprofA.add(lblNewLabel_1);
		lblNewLabel_1.setFont(new Font("Viner Hand ITC", Font.BOLD, 15));
		lblNewLabel_1.setForeground(new Color(255, 255, 255));
		
		JPanel panelGenet = new JPanel();
		panelGenet.addMouseListener(new PanelButtonMouseAdapter(panelGenet){
			@Override
			public void mouseClicked (MouseEvent e) {
				MenuClicked(PanelAlgoG);
			}
		} );
		panelGenet.setBackground(new Color(47, 79, 79));
		panelGenet.setLayout(null);
		panelGenet.setBounds(10, 294, 219, 53);
		panelMenu.add(panelGenet);
		
		JLabel lblAlgoGenetic = new JLabel("Algo Genetic");
		lblAlgoGenetic.setIcon(new ImageIcon(FrameDashboard.class.getResource("/Interface/icons8_Contacts_25px.png")));
		lblAlgoGenetic.setForeground(Color.WHITE);
		lblAlgoGenetic.setFont(new Font("Viner Hand ITC", Font.BOLD, 15));
		lblAlgoGenetic.setBounds(57, 11, 110, 31);
		panelGenet.add(lblAlgoGenetic);
		
		JPanel panelAnt = new JPanel();
		panelAnt.addMouseListener(new PanelButtonMouseAdapter(panelAnt){
			@Override
			public void mouseClicked (MouseEvent e) {
				MenuClicked(PanelAlgoAnt);
			}
		} );
		panelAnt.setBackground(new Color(47, 79, 79));
		panelAnt.setLayout(null);
		panelAnt.setBounds(10, 363, 219, 53);
		panelMenu.add(panelAnt);
		
		JLabel lblColonieDeFourmis = new JLabel("Colonie de fourmis");
		lblColonieDeFourmis.setIcon(new ImageIcon(FrameDashboard.class.getResource("/Interface/ant1.png")));
		lblColonieDeFourmis.setForeground(Color.WHITE);
		lblColonieDeFourmis.setFont(new Font("Viner Hand ITC", Font.BOLD, 15));
		lblColonieDeFourmis.setBounds(38, 11, 153, 31);
		panelAnt.add(lblColonieDeFourmis);
		
		JLabel lblNewLabel_2 = new JLabel("X");			
		lblNewLabel_2.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				FrameDashboard.this.dispose();
			}
		});
		
		lblNewLabel_2.setForeground(Color.RED);
		lblNewLabel_2.setFont(new Font("Tahoma", Font.PLAIN, 20));
		lblNewLabel_2.setBounds(968, 11, 12, 25);
		contentPane.add(lblNewLabel_2);
		
		JPanel panelmain = new JPanel();
		panelmain.setBounds(249, 11, 731, 499);
		contentPane.add(panelmain);
		panelmain.setLayout(null);

		panelmain.add(PanelAlgoAvH);
		panelmain.add(PanelAlgoG);
		panelmain.add(PanelAlgoAnt);
		
		MenuClicked(PanelAlgoAvH);
	}
	
	public void MenuClicked(JPanel panel) {
		PanelAlgoG.setVisible(false);
		PanelAlgoAvH.setVisible(false);
		PanelAlgoAnt.setVisible(false);
		
		panel.setVisible(true);
	}
	
	private class PanelButtonMouseAdapter extends MouseAdapter { 
		JPanel panel;
		 public PanelButtonMouseAdapter (JPanel panel) {
			 this.panel = panel ; 
		 }
		@Override 
		public void mouseEntered (MouseEvent e) {
			
			 panel.setBackground(new Color(112, 128, 144));

		}
		
		@Override
		public void mouseExited (MouseEvent e) {
			panel.setBackground(new Color(47, 79, 79));
			//(0, 139, 139));

			
		}
		@Override
		public void mousePressed (MouseEvent e) {
			panel.setBackground(new Color(60, 170, 113));
			
		}
		@Override
		public void mouseReleased (MouseEvent e) {
			panel.setBackground(new Color(112, 128, 144));
			
		}
		

		@Override
		public void mouseClicked(MouseEvent e) {
			
			
		}
	}
}
