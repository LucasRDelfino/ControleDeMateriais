package aplicativo;

import java.awt.event.*;
import java.awt.*;
import javax.swing.*;

public class TelaDeInicio extends JFrame implements ActionListener{
	
	private JLabel bemvindo, icone;
	private JButton entrar;
	
	public TelaDeInicio() {
		setLayout(null);
		setSize(1080, 720);
		setTitle("Início");
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		setLocationRelativeTo(null);
		//getContentPane().setBackground(Color.decode("#1E5128"));
		setResizable(false);
		setVisible(true);
		
		entrar = new JButton("Entrar");
		entrar.setBounds(400, 400, 350, 50);
		entrar.setForeground(Color.decode("#1E5128"));
		entrar.addActionListener(this);
	}
	
	public void actionPerformed(ActionEvent e) {
		
	}

	public static void main(String[] args) {
		new TelaDeInicio();	
	}
}
