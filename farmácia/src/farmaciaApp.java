import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class farmaciaApp{
	
	static Connection connection;
	static farmaciaApp farmaciaApp = new farmaciaApp();
	
	public static void main(String[] arge) {
		
		Scanner imput = new Scanner(System.in); 
		
		 try {
	            connection = DriverManager.getConnection("jdbc:mysql://localhost/farmacia", "root", "12345");
	 
	            System.out.println("Bem vindo a nossa farmâcia ");
                System.out.println("O que você gostaria de fazer? ");
                System.out.println("Temos as seguintes opções: ");
                System.out.println("0 - Sair da farmâcia");
                System.out.println("1 - Inserir um rémedio ao estoque:");
                System.out.println("2 - Remover item do estoque:");
                System.out.println("3 - Rémedios restantes:");
                System.out.println("4 - Atualizar dados");
               
                int op = imput.nextInt();
	            do {
	               
	                switch(op) {
	                case 1:
	                    farmaciaApp.insert(imput);
	                    break;
	                case 2:
	                    farmaciaApp.delete(imput);
	                    break;
	                case 3:
	                    farmaciaApp.listar(imput);
	                    break;
	                case 4:
	                    farmaciaApp.update(imput);
	                    break;
	                }
	                System.out.println("0 - Sair da farmâcia");
	                System.out.println("1 - Adicionar algum medicamento");
	                System.out.println("2 - Deletar algum medicamento");
	                System.out.println("3 - exibir a lista de medicamentos");
	                System.out.println("4 - Atualizar os dados de um medicamento");
	               
	                op = imput.nextInt();
	            }while(op != 0);
	                       
	        } catch (SQLException e) {
	            e.printStackTrace();
	        }
	}
	
	public void insert(Scanner imput) throws SQLException {
		System.out.println("Digite o nome do remédio: ");
		String nome = imput.next();
		System.out.println("Digite o preço do produto: ");
		float preco = imput.nextFloat();
		System.out.println("Original ou genérico");
		String tipo_remedio = imput.next();
		
		System.out.println("Produto cadastrado com sucesso!");
				
		
		String sqlInsert = "INSERT INTO remedio (nome, preco, tipo_remedio) VALUES (?, ?, ?)";
		
		PreparedStatement stmt = connection.prepareStatement(sqlInsert);
		stmt.setString(1, nome);
		stmt.setFloat(2, preco);
		stmt.setString(3, tipo_remedio);
		
		stmt.execute();
		
		stmt.close();
		connection.close();
	}
	
	public void delete(Scanner imput) throws SQLException {
		System.out.println("Digite o nome do remédio que será excluido!");
		String nome = imput.nextLine();
		
		System.out.println("O medicamento foi removido do estoque!");
		
		String sqlDelete = "DELETE FROM remedio WHERE nome = ?";
		
		PreparedStatement stmt = connection.prepareStatement(sqlDelete);
		stmt.setString(1, nome);
		
		stmt.execute();
		
		stmt.close();
		connection.close();
	}
	
	public void listar(Scanner imput) throws SQLException{
		String sql = "SELECT * FROM remedio";
		
		PreparedStatement stmt = connection.prepareStatement(sql);
		ResultSet rs = stmt.executeQuery(sql);
		
		List<Remedio> listaRemedio = new ArrayList<Remedio>();
		
		while(rs.next()) {
			Remedio remedio = new Remedio();
			remedio.setNome(rs.getString("nome"));
			remedio.setPreco(rs.getFloat("preco"));
			remedio.setTipo_remedio(rs.getString("tipo_remedio"));
			
			listaRemedio.add(remedio);
		}
		
		for(Remedio remedio : listaRemedio) {
			System.out.println("Nome: " + remedio.getNome()); 
			System.out.println("Valor: " + "R$ " + remedio.getPreco() + " reais"); 
			System.out.println("Tipo: " + remedio.getTipo_remedio());
			System.out.println("-- -- -- -- -- -- -- -- -- -- -- -- -- -- -- -- -- -- -- -- -- ");
		}

		rs.close();
		stmt.close();
	}
	
	public void update(Scanner imput) throws SQLException{
		System.out.println("Digite o nome novo do remédio: ");
		String nomeNovo = imput.next();
		System.out.println("Digite o novo preço do remédio: ");
		float precoNovo = imput.nextFloat();
		System.out.println("Original ou genérico: ");
		String tipoNovo = imput.next();
		System.out.println("Digite o id do produto: ");
		int novoId = imput.nextInt();
		
		System.out.println("Dados alterados com sucesso!");
		
		String sqlupdate = "UPDATE remedio SET  nome = ?, preco = ?, tipo_remedio = ? WHERE ID = ? ";
		PreparedStatement stmt = connection.prepareStatement(sqlupdate);
		stmt.setString(1, nomeNovo);
		stmt.setFloat(2, precoNovo);
		stmt.setString(3, tipoNovo);
		stmt.setInt(4, novoId);
		
		stmt.execute();
		
		stmt.close();
	}
}