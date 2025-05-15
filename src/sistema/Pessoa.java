package sistema;

public class Pessoa {
	private String nome;
	private String cpf;
	private String email;
	
	public Pessoa(String nome, String cpf, String email) {
		this.nome = nome;
		this.cpf = cpf;
		this.email = email;
	}
	public String getNome() {return nome; }
	public String getCPF() {return cpf; }
	public String getEmail() {return email; }
	
	public void setNome(String nome) {this.nome = nome;} 
	public void setgCpf(String cpf) {this.cpf = cpf; }
	public void setEmail(String email) {this.email = email; }
	
	@Override
	public String toString() {
		return "Nome: " + nome + ", CPF: " + cpf + ", Email: " + email;
	}
}
