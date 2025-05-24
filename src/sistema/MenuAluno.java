package sistema;

import java.util.Scanner;

public class MenuAluno {
    private AlunoManager alunoManager;
    private Scanner sc;
    private String caminhoArquivo = "alunos.txt";

    public MenuAluno() {
        this.alunoManager = new AlunoManager();
        this.sc = new Scanner(System.in);
    }

    public void iniciar() {
        int opcao;

        do {
            System.out.println("\n===== MODO ALUNO =====");
            System.out.println("1. Cadastrar Aluno Normal");
            System.out.println("2. Cadastrar Aluno Especial");
            System.out.println("3. Listar Alunos");
            System.out.println("4. Salvar em Arquivo");
            System.out.println("5. Carregar de Arquivo");
            System.out.println("0. Sair");
            System.out.print("Escolha uma opção: ");
            opcao = sc.nextInt();
            sc.nextLine(); // limpa buffer

            switch (opcao) {
                case 1 -> cadastrarAluno(false);
                case 2 -> cadastrarAluno(true);
                case 3 -> alunoManager.listarAlunos();
                case 4 -> alunoManager.salvarAlunosEmArquivo(caminhoArquivo);
                case 5 -> alunoManager.carregarAlunosDoArquivo(caminhoArquivo);
                case 0 -> System.out.println("Encerrando Modo Aluno...");
                default -> System.out.println("Opção inválida.");
            }
        } while (opcao != 0);
    }

    private void cadastrarAluno(boolean especial) {
        System.out.print("Nome: ");
        String nome = sc.nextLine();
        System.out.print("CPF: ");
        String cpf = sc.nextLine();
        System.out.print("Email: ");
        String email = sc.nextLine();
        System.out.print("Matrícula: ");
        String matricula = sc.nextLine();
        System.out.print("Curso: ");
        String curso = sc.nextLine();

        if (especial) {
            AlunoEspecial aluno = new AlunoEspecial(nome, cpf, email, matricula, curso);
            alunoManager.adicionarAluno(aluno);
        } else {
            Aluno aluno = new Aluno(nome, cpf, email, matricula, curso);
            alunoManager.adicionarAluno(aluno);
        }
    }
}
