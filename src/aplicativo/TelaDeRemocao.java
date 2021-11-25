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
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

public class TelaDeRemocao extends JFrame{

	private JScrollPane scrollTable;
	private JTable table;
	private BD bd;
	private PreparedStatement st;
	private ResultSet rs;
	private int rs2;
	materiaisDAO dao;
	Color verdeEscuro = new Color(43, 87, 38);
	JButton deletar;
	JLabel lb1,lb2,lb3;

	

	public TelaDeRemocao() {
		
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
		lb1 = new JLabel("Tela de Deletar");
		lb1.setBounds(135,25,500,50);
	    lb1.setFont( new Font("Lucida Bright Demibold", Font.BOLD, 40) );
	    lb1.setForeground(Color.decode("#1E5128"));
		add(lb1);
		
		
		deletar = new JButton("DELETAR");
		deletar.setBounds(220, 294, 125, 50);
		deletar.setFont( new Font("Lucida Bright Demibold", Font.BOLD, 15) );
		add(deletar);
		deletar.setBackground(Color.decode("#1E5128"));
		deletar.setForeground(Color.white);
		
		
		
		setFont(new Font("Arial", Font.PLAIN, 12));
		scrollTable = new JScrollPane();
		scrollTable.setBounds(15, 100, 515, 183);
		add(scrollTable);
		dao = new materiaisDAO();
		if (!dao.bd.getConnection()) {
			System.out.println("Falha na conexão");
			System.exit(0);
			
		
			
		
		}
		executarTabela();

	}
	public void Eventos() {
		deletar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e){
                int[] linhas = table.getSelectedRows();
                DefaultTableModel dtm = (DefaultTableModel) table.getModel();
                 String delete = "delete from limateriais where id="+(linhas[0]+1);
                 System.out.println(linhas[0]);
                 
                 System.out.println(delete);
                 try {
        			st = dao.bd.c.prepareStatement(delete);
					rs2= st.executeUpdate();
					executarTabela();
				} catch (SQLException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				
                 
                }
            }
		});
		

	}


		
		public void executarTabela() {
	        try {
	            String sql = "SELECT * FROM limateriais";
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
	            System.out.println("ERRO");
	        }
	    }

		public static void main(String args[]){
			new TelaDeRemocao();
			}
}


