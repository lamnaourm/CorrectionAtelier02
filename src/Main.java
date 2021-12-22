import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import javax.swing.JButton;
import java.awt.Font;
import javax.swing.border.LineBorder;
import java.awt.Color;
import javax.swing.SwingConstants;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class Main extends JFrame {

	private JPanel contentPane;
	private JTextField txt_unit;
	private JTextField txt_qte;
	private JTextField txt_tva;
	private JTextField txt_ht;
	private JTextField txt_ttc;
	private JButton btn_quitter;
	private JButton btn_init;
	private JButton btnCalculer;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Main frame = new Main();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	public Main() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 480, 352);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel lblNewLabel = new JLabel("Facture");
		lblNewLabel.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel.setHorizontalTextPosition(SwingConstants.CENTER);
		lblNewLabel.setBorder(new LineBorder(Color.PINK, 3, true));
		lblNewLabel.setFont(new Font("Tahoma", Font.BOLD, 20));
		lblNewLabel.setBounds(35, 22, 395, 33);
		contentPane.add(lblNewLabel);
		
		JLabel lblNewLabel_1 = new JLabel("Prix Unitaire :");
		lblNewLabel_1.setBounds(35, 81, 81, 14);
		contentPane.add(lblNewLabel_1);
		
		JLabel lblNewLabel_2 = new JLabel("Qnantite commandee :");
		lblNewLabel_2.setBounds(35, 120, 123, 14);
		contentPane.add(lblNewLabel_2);
		
		JLabel lblNewLabel_3 = new JLabel("Taux TVA :");
		lblNewLabel_3.setBounds(35, 161, 123, 14);
		contentPane.add(lblNewLabel_3);
		
		txt_unit = new JTextField();
		txt_unit.setBounds(177, 75, 131, 20);
		contentPane.add(txt_unit);
		txt_unit.setColumns(10);
		txt_unit.setDocument(new ControleDecimal());
		
		txt_qte = new JTextField();
		txt_qte.setBounds(177, 114, 131, 20);
		contentPane.add(txt_qte);
		txt_qte.setDocument(new ControleEntier());
		txt_qte.setColumns(10);
		
		txt_tva = new JTextField();
		txt_tva.setBounds(177, 155, 131, 20);
		contentPane.add(txt_tva);
		txt_tva.setDocument(new ControleEntier());
		txt_tva.setColumns(10);
		
		txt_ht = new JTextField();
		txt_ht.setEditable(false);
		txt_ht.setBounds(177, 225, 86, 20);
		contentPane.add(txt_ht);
		txt_ht.setColumns(10);
		
		JLabel lblNewLabel_4 = new JLabel("Prix HT :");
		lblNewLabel_4.setBounds(88, 228, 70, 14);
		contentPane.add(lblNewLabel_4);
		
		JLabel lblNewLabel_5 = new JLabel("Prix TTC :");
		lblNewLabel_5.setBounds(88, 266, 70, 14);
		contentPane.add(lblNewLabel_5);
		
		txt_ttc = new JTextField();
		txt_ttc.setEditable(false);
		txt_ttc.setBounds(177, 263, 86, 20);
		contentPane.add(txt_ttc);
		txt_ttc.setColumns(10);
		
		btnCalculer = new JButton("Calculer");
		btnCalculer.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				if(txt_unit.getText().isEmpty()) {
					JOptionPane.showMessageDialog(null, "Prix Unitaire vide");
					txt_unit.requestFocus();
					return;
				}
				if(txt_qte.getText().isEmpty()) {
					JOptionPane.showMessageDialog(null, "Quantite vide vide");
					txt_qte.requestFocus();
					return;
				}if(txt_tva.getText().isEmpty()) {
					JOptionPane.showMessageDialog(null, "TVA vide");
					txt_tva.requestFocus();
					return;
				}
				double prix = Double.parseDouble(txt_unit.getText());
				int qte = Integer.parseInt(txt_qte.getText());
				int tva = Integer.parseInt(txt_tva.getText());
				
				double ht = prix * qte;
				double ttc = ht * (1+(double)tva/100);
				
				txt_ht.setText(String.format("%.2f", ht));
				txt_ttc.setText(String.format("%.2f", ttc));
				
			}
		});
		btnCalculer.setBounds(341, 92, 89, 23);
		contentPane.add(btnCalculer);
		
		btn_init = new JButton("Initialiser");
		btn_init.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				txt_ht.setText("");
				txt_ttc.setText("");
				txt_unit.setText("");
				txt_tva.setText("");
				txt_qte.setText("");
				txt_unit.requestFocus();
			}
		});
		btn_init.setBounds(341, 135, 89, 23);
		contentPane.add(btn_init);
		
		btn_quitter = new JButton("Quitter");
		btn_quitter.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				closeWindow();
			}
		});
		btn_quitter.setBounds(341, 228, 89, 23);
		contentPane.add(btn_quitter);
	}
	
	void closeWindow() {
		int choix = JOptionPane.showConfirmDialog(null, "Voulez-vous quitter app ?");
		
		if(choix==JOptionPane.OK_OPTION)
			this.dispose();
	}
}

