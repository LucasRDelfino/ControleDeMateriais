package aplicativo;

import java.awt.event.*;
import java.awt.*;
import javax.swing.*;

public class TelaDeInicio extends JFrame {
	private JLabel bemvindo,lb1;
	private JButton adicionar,buscar,remover;
	ImageIcon logo;

	public TelaDeInicio() {
		Componentes();
		Eventos();
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setSize(1080, 720);
		setLocationRelativeTo(null);
		setResizable(false);
		setVisible(true);
		
		
	}	
	public void Componentes() {	
		setLayout(null);
		Color verde = new Color( 234, 255, 234);
		getContentPane().setBackground(verde);
		
		logo = new ImageIcon("logo.png");			
		lb1 = new JLabel (logo);
		lb1.setBounds(280, 50 ,500,500);
		add(lb1);
		
		
		adicionar = new JButton("Adicionar");
		adicionar.setFont(new Font("Roboto", Font.PLAIN, 24));
		adicionar.setBackground(Color.decode("#1E5128"));
		adicionar.setForeground(Color.white);
		
		remover = new JButton("Remover");
		remover.setFont(new Font("Roboto", Font.PLAIN, 24));
		remover.setBackground(Color.decode("#1E5128"));
		remover.setForeground(Color.white);
		
		buscar = new JButton("Buscar");
		buscar.setFont(new Font("Roboto", Font.PLAIN, 24));
		buscar.setBackground(Color.decode("#1E5128"));
		buscar.setForeground(Color.white);
		
		bemvindo = new JLabel("Bem-Vindo");
		bemvindo.setFont(new Font("Roboto", Font.PLAIN, 48));
		bemvindo.setForeground(Color.decode("#1E5128"));
		
		

		
		adicionar.setBounds(250, 550, 150, 50);
		remover.setBounds(450, 550, 150, 50);
		buscar.setBounds(650, 550, 150, 50);
		bemvindo.setBounds(420, 10, 400, 50);
		
		
		add(buscar);
		add(adicionar);
		add(remover);
		add(bemvindo);
		
		
	}
	public void Eventos() {
	adicionar.addActionListener(new ActionListener() {
		public void actionPerformed(ActionEvent e) {
			 //Muda de Tela
			  new TelaDeAdicao().setVisible(true);				
			  setVisible(false);
			 
		
			
		}
	});
	remover.addActionListener(new ActionListener() {
		public void actionPerformed(ActionEvent e) {
			 //Muda de Tela
			  new TelaDeRemocao().setVisible(true);				
			  setVisible(false);
			 
		
			
		}
	});
	buscar.addActionListener(new ActionListener() {
		public void actionPerformed(ActionEvent e) {
			 //Muda de Tela
			  new TelaDeBusca().setVisible(true);				
			  setVisible(false);
			 
		
			
		}
	});
	}

	public static void main(String[] args) {
		new TelaDeInicio();	
	}
}