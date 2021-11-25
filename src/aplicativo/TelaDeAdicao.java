package aplicativo;


import java.awt.event.*;
import java.awt.*;
import javax.swing.*;
import javax.swing.border.TitledBorder;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;

import java.sql.*;

public class TelaDeAdicao extends JFrame {
	private JScrollPane scrollTable;
	private JTable table;
	private BD bd;
	private PreparedStatement st;
	private ResultSet rs;
	materiaisDAO dao;
	Color verdeEscuro = new Color(43, 87, 38);
	JButton adicionar,atualizar,inicio;
	JLabel lb1,lb2,lb3;
	JTextField nome,qtd;
	String sql = "SELECT * FROM limateriais";
	private int rs2;

	

	public TelaDeAdicao() {
		
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
		
		lb1 = new JLabel("Tela de Adição");
		lb1.setBounds(135,25,500,50);
	    lb1.setFont( new Font("Lucida Bright Demibold", Font.BOLD, 40) );
	    lb1.setForeground(Color.decode("#1E5128"));;
		add(lb1);
		
		lb2 = new JLabel("Quantidade:");
		lb2.setBounds(295,100,500,50);
	    lb2.setFont( new Font("Lucida Bright Demibold", Font.BOLD, 15) );
	    lb2.setForeground(Color.decode("#1E5128"));
		add(lb2);
		
		lb3 = new JLabel("Nome do Produto:");
		lb3.setBounds(15,100,500,50);
	    lb3.setFont( new Font("Lucida Bright Demibold", Font.BOLD, 15) );
	    lb3.setForeground(Color.decode("#1E5128"));
		add(lb3);
		
		nome = new JTextField();
		nome.setBounds(160,110, 120, 30);
		nome.setFont(new Font("Arial", Font.BOLD, 16));
		nome.setForeground(Color.BLACK);
		add(nome);	
		
		qtd = new JTextField();
		qtd.setBounds(400,110, 120, 30);
		qtd.setFont(new Font("Arial", Font.BOLD, 16));
		qtd.setForeground(Color.BLACK);
		add(qtd);	
		
		adicionar = new JButton("Adicionar");
		adicionar.setBounds(70, 350, 125, 35);
		adicionar.setFont( new Font("Lucida Bright Demibold", Font.BOLD, 15) );
		add(adicionar);
		adicionar.setBackground(Color.decode("#1E5128"));
		adicionar.setForeground(Color.white);
		
		inicio = new JButton("Inicio");
		inicio.setBounds(390, 350, 125, 35);
		inicio.setFont( new Font("Lucida Bright Demibold", Font.BOLD, 15) );
		add(inicio);
		inicio.setBackground(Color.decode("#1E5128"));
		inicio.setForeground(Color.white);
		
		
		
		atualizar = new JButton("Atualizar");
		atualizar.setBounds(230, 350, 125, 35);
		atualizar.setFont( new Font("Lucida Bright Demibold", Font.BOLD, 15) );
		add(atualizar);
		atualizar.setBackground(Color.decode("#1E5128"));
		atualizar.setForeground(Color.white);
	    
		
		
		
		setFont(new Font("Arial", Font.PLAIN, 12));
		scrollTable = new JScrollPane();
		scrollTable.setBounds(15, 155, 515, 183);
		add(scrollTable);
		
		dao = new materiaisDAO();
		if (!dao.bd.getConnection()) {
			System.out.println("Falha na conexão");
			System.exit(0);
			
		
			
		
		}
		executarTabela(sql);
	}
		public void executarTabela(String sql ) {
	        try {
	            st = dao.bd.c.prepareStatement(sql);
	            rs= st.executeQuery();
	            DefaultTableModel tableModel = new DefaultTableModel(new String[] { "Nome","Quantidade" }, 0) {
	            	
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

	
	public void Eventos() {
		adicionar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e){
                 String adicionar = "insert into limateriais(nome,quantidade) values ('"+nome.getText()+"',"+qtd.getText()+")";
                 System.out.println(adicionar);
                              
                 try {
        			st = dao.bd.c.prepareStatement(adicionar);
					rs2= st.executeUpdate();
					executarTabela(sql);
				} catch (SQLException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				
                 
                }
            }
		});
		atualizar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e){
                 String atualizar = "update limateriais set quantidade="+qtd.getText()+" where nome='"+nome.getText()+"'";
                 System.out.println(atualizar);
                              
                 try {
        			st = dao.bd.c.prepareStatement(atualizar);
					rs2= st.executeUpdate();
					executarTabela(sql);
				} catch (SQLException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				
                 
                }
            }
		});
		inicio.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e){
				new TelaDeInicio().setVisible(true);				
				  setVisible(false);
				 
            }
		});
		
		
		
		
		
		
		
	}

		public static void main(String args[]){
			new TelaDeAdicao();
			}
}
