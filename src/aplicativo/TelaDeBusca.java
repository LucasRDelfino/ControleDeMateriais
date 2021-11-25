package aplicativo;
import java.awt.event.*;
import java.awt.*;
import javax.swing.*;
import javax.swing.border.TitledBorder;
import javax.swing.table.DefaultTableModel;

import java.sql.*;

public class TelaDeBusca extends JFrame {
	private JScrollPane scrollTable;
	private JTable table;
	private BD bd;
	private PreparedStatement st;
	private ResultSet rs;
	materiaisDAO dao;
	Color verdeEscuro = new Color(43, 87, 38);
	JButton adicionar,buscar;
	JLabel lb1,lb2,lb3,lb4;
	JTextField nome;
	String sql = "SELECT * FROM limateriais";
	

	public TelaDeBusca() {
		
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setSize(560, 430);
		setLocationRelativeTo(null);
		setResizable(false);
		setVisible(true);
		Componentes();
		Eventos();
		setBackground(Color.RED);

	}

	public void Componentes() {
		setLayout(null);
		Color verde = new Color( 234, 255, 234);
		getContentPane().setBackground(verde);
		
		lb4 = new JLabel("Digite o nome do produto:");
		lb4.setBounds(25,90,500,50);
	    lb4.setFont( new Font("Lucida Bright Demibold", Font.BOLD, 16) );
	    lb4.setForeground(Color.decode("#1E5128"));
		add(lb4);
		nome = new JTextField();
		nome.setBounds(255,100, 120, 30);
		nome.setFont(new Font("Arial", Font.BOLD, 16));
		nome.setForeground(Color.decode("#1E5128"));
		add(nome);	
		buscar = new JButton("Buscar");
		buscar.setBounds(420, 100, 95, 30);
		buscar.setFont( new Font("Lucida Bright Demibold", Font.BOLD, 15) );
		add(buscar);
		buscar.setBackground(Color.decode("#1E5128"));
		buscar.setForeground(Color.white);
		
		
		lb1 = new JLabel("Tela de buscas");
		lb1.setBounds(135,5,500,50);
	    lb1.setFont( new Font("Lucida Bright Demibold", Font.BOLD, 40) );
	    lb1.setForeground(Color.decode("#1E5128"));
		add(lb1);
		lb2 = new JLabel("Obs: não encontrado.");
		lb2.setBounds(50,110,500,500);
		lb2.setFont( new Font("Lucida Bright Demibold", Font.BOLD, 20) );
		lb2.setForeground(Color.decode("#1E5128"));
	    add(lb2);
	    
		adicionar = new JButton("<html><u>Adicionar</u>");
		adicionar.setBounds(270, 323, 75, 75);
		adicionar.setContentAreaFilled(false);
		adicionar.setFont( new Font("Lucida Bright Demibold", Font.BOLD, 15) );
		adicionar.setFocusable(false);
	    adicionar.setBorder(null);
	    adicionar.setForeground(Color.decode("#1E5128"));
		add(adicionar);
		
		
		setFont(new Font("Arial", Font.PLAIN, 12));
		scrollTable = new JScrollPane();
		scrollTable.setBounds(15, 150, 515, 183);
		add(scrollTable);
		dao = new materiaisDAO();
		if (!dao.bd.getConnection()) {
			System.out.println("Falha na conexão");
			System.exit(0);
			
		
			
		
		}
		executarTabela(sql);

	}
	public void Eventos() {
		buscar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String nometf = nome.getText();
				sql = "SELECT * FROM limateriais WHERE nome='"+nometf+"'";
				executarTabela(sql);
			
				
			}
		});
		
		adicionar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				 //Muda de Tela
				  new TelaDeAdicao().setVisible(true);				
				  setVisible(false);
				 
			
				
			}
		});
		

	}


		
		public void executarTabela(String sql) {
	        try {
	           
	            st = dao.bd.c.prepareStatement(sql);
	            rs= st.executeQuery();
	            DefaultTableModel tableModel = new DefaultTableModel(new String[] { "Id", "Nome","Quantidade" }, 0) {
	                public boolean isCellEditable(int row, int col) {
	                    return false;
	                }
	            };
	            int qtdeColunas = rs.getMetaData().getColumnCount();
	            for (int indice = 1; indice <= qtdeColunas; indice++) {
//	                tableModel.addColumn(resultado.getMetaData().getColumnName(indice));
	            }
	            table = new JTable(tableModel);
	            DefaultTableModel dtm = (DefaultTableModel) table.getModel();

	            while (rs.next()) {
	                try {
	                    String[] dados = new String[qtdeColunas];
	                    for (int i = 1; i <= qtdeColunas; i++) {
	                        dados[i - 1] = rs.getString(i);
	                    }
	                    dtm.addRow(dados);
	                    System.out.print("");
	                } catch (SQLException erro) {
	                    System.out.println(erro);
	                }
	                scrollTable.setViewportView(table);
	            }

	            rs.close();
	            st.close();
	        } catch (SQLException erro) {
	            System.out.println(erro);
	        }
	    }

		public static void main(String args[]){
			new TelaDeBusca();
			}
}

