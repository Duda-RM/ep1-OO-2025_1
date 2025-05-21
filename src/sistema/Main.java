package sistema;

public class Main {
    public static void main(String[] args) {
        Aluno aluno = new Aluno(
        		"Duda Rodrigues",
        		"123.456.789-00",
        		"duda@email.com",
        		"20250123",
        		"Engenharia de Software"
        		);
        
        aluno.adicionarNota(8.5);
        aluno.adicionarNota(7.0);
        aluno.adicionarNota(9.2);
        
        aluno.setFrequencia(85.0);
        
        System.out.println("----- DADOS DO ALUNO -----");
        System.out.println(aluno);
        		
    }
}
