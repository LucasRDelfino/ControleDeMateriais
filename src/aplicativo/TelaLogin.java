package aplicativo;

import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.table.DefaultTableModel;

public class TelaLogin extends JFrame{
	JLabel lb1,lb2,lb3,lb4;
	JTextField email,senha;
	JButton logar;
	private PreparedStatement st;
	private ResultSet rs;
	private int rs2;
	private BD bd;
	materiaisDAO dao;
	String sql = "SELECT * FROM limateriais";

public TelaLogin() {
		
		Componentes();
		Eventos();
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setSize(500, 300);
		setLocationRelativeTo(null);
		setResizable(false);
		setVisible(true);
		
		
	
	}
public void Componentes() {
	setLayout(null);
	
	Color verde = new Color( 234, 255, 234);
	getContentPane().setBackground(verde);
	email = new JTextField();
	email.setBounds(75,112, 320, 25);
	email.setFont(new Font("Arial", Font.BOLD, 16));
	email.setForeground(Color.BLACK);
	add(email);	
	senha = new JTextField();
	senha.setBounds(75,182, 320, 25);
	senha.setFont(new Font("Arial", Font.BOLD, 16));
	senha.setForeground(Color.BLACK);
	add(senha);	
	
	
	logar = new JButton("Logar");
	logar.setBounds(200, 220, 95, 30);
	logar.setFont( new Font("Lucida Bright Demibold", Font.BOLD, 15) );
	logar.setBackground(Color.decode("#1E5128"));
	logar.setForeground(Color.white);
	add(logar);
	
	
	lb1 = new JLabel("Tela de Login");
	lb1.setBounds(171,24,500,50);
    lb1.setFont( new Font("Lucida Bright Demibold", Font.BOLD,20) );
    lb1.setForeground(Color.black);
    lb1.setForeground(Color.decode("#1E5128"));
	add(lb1);
	lb3 = new JLabel("Email:");
	lb3.setBounds(15,100,500,50);
    lb3.setFont( new Font("Lucida Bright Demibold", Font.BOLD, 15) );
    lb3.setForeground(Color.black);
    lb3.setForeground(Color.decode("#1E5128"));
	add(lb3);
	lb4 = new JLabel("Senha:");
	lb4.setBounds(15,170,500,50);
    lb4.setFont( new Font("Lucida Bright Demibold", Font.BOLD, 15) );
    lb4.setForeground(Color.black);
    lb4.setForeground(Color.decode("#1E5128"));
	add(lb4);
	
	dao = new materiaisDAO();
	if (!dao.bd.getConnection()) {
		System.out.println("Falha na conexão");
		System.exit(0);
		
	
		
	
	}
	
		
	
		
	
	}
	

public void Eventos() {
	logar.addActionListener(new ActionListener() {
		public void actionPerformed(ActionEvent e) {
			  String logar = "select email,senha from login where email ='"+email.getText()+"' and senha ='"+senha.getText()+"'";
			  try {
	     			st = dao.bd.c.prepareStatement(logar);
						rs= st.executeQuery();
						System.out.println();
						if(rs.first()==true){
						   new TelaDeInicio().setVisible(true);				
							  setVisible(false);
							
						}else {
							JOptionPane.showMessageDialog(null,"Usuário Inválido");
						}
						
					} catch (SQLException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					
	              
	             }
				
              System.out.println(logar);
                           
              
		
			
		}
	});
	

}


	
      
	public static void main(String args[]){
		new TelaLogin();
		}
}

