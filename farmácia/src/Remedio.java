public class Remedio {
	
	private String nome;
	private String id;
	private float preco;
	private String tipo_remedio;
	public String getNome() {
		return nome;
	}
	public void setNome(String nome) {
		this.nome = nome;
	}
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public float getPreco() {
		return preco;
	}
	public void setPreco(float preco) {
		this.preco = preco;
	}
	public String getTipo_remedio() {
		return tipo_remedio;
	}
	public void setTipo_remedio(String tipo_remedio) {
		this.tipo_remedio = tipo_remedio;
	}	
	
	public Remedio() {
		// TODO Auto-generated constructor stub
	}
	public Remedio(String nome, String id, float preco, String tipo_remedio) {
		super();
		this.nome = nome;
		this.id = id;
		this.preco = preco;
		this.tipo_remedio = tipo_remedio;
	}
	
	
	
}
