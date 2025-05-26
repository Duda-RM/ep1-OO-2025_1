package sistema;

import java.util.ArrayList;
import java.util.Scanner;

public class MenuDisciplinaTurma {
    private ArrayList<Disciplina> disciplinas;
    private ArrayList<Turma> turmas;
    private Scanner sc;

    public MenuDisciplinaTurma() {
        this.disciplinas = new ArrayList<>();
        this.turmas = new ArrayList<>();
        this.sc = new Scanner(System.in);
    }

    public void iniciar() {
        int opcao;

        do {
            System.out.println("\n===== MODO DISCIPLINA/TURMA =====");
            System.out.println("1. Cadastrar Disciplina");
            System.out.println("2. Cadastrar Turma");
            System.out.println("3. Listar Disciplinas");
            System.out.println("4. Listar Turmas");
            System.out.println("5. Fazer matrícula de aluno"); 
            System.out.println("6. Lançar notas e presença (Modo Avaliação)");
            System.out.println("0. Sair");
            System.out.print("Escolha uma opção: ");
            opcao = sc.nextInt();
            sc.nextLine();

            switch (opcao) {
                case 1 -> cadastrarDisciplina();
                case 2 -> cadastrarTurma();
                case 3 -> listarDisciplinas();
                case 4 -> listarTurmas();
                case 5 -> fazerMatricula(); 
                case 6 -> iniciarMenuAvaliacao();
                case 0 -> System.out.println("Saindo do Modo Disciplina/Turma...");
                default -> System.out.println("Opção inválida!");
            }
        } while (opcao != 0);
    }

    private void cadastrarDisciplina() {
        System.out.print("Nome: ");
        String nome = sc.nextLine();
        System.out.print("Código: ");
        String codigo = sc.nextLine();
        System.out.print("Carga horária: ");
        int cargaHoraria = sc.nextInt();
        sc.nextLine();

        Disciplina d = new Disciplina(nome, codigo, cargaHoraria);

        System.out.print("Quantos pré-requisitos? ");
        int n = sc.nextInt();
        sc.nextLine();
        for (int i = 0; i < n; i++) {
            System.out.print("Código do pré-requisito " + (i + 1) + ": ");
            String codPre = sc.nextLine();
            d.adicionarPreRequisito(codPre);
        }

        disciplinas.add(d);
        System.out.println("Disciplina cadastrada com sucesso!");
    }

    private void cadastrarTurma() {
        if (disciplinas.isEmpty()) {
            System.out.println("Nenhuma disciplina cadastrada ainda!");
            return;
        }

        System.out.print("Código da Turma: ");
        String codTurma = sc.nextLine();

        System.out.println("Escolha a disciplina (código):");
        for (Disciplina d : disciplinas) {
            System.out.println("- " + d.getCodigo() + ": " + d.getNome());
        }
        String codDisciplina = sc.nextLine();

        Disciplina disciplinaEscolhida = null;
        for (Disciplina d : disciplinas) {
            if (d.getCodigo().equals(codDisciplina)) {
                disciplinaEscolhida = d;
                break;
            }
        }

        if (disciplinaEscolhida == null) {
            System.out.println("Disciplina não encontrada!");
            return;
        }

        System.out.print("Professor responsável: ");
        String professor = sc.nextLine();
        System.out.print("Semestre: ");
        String semestre = sc.nextLine();
        System.out.print("Forma de avaliação: ");
        String formaAvaliacao = sc.nextLine();
        System.out.print("É presencial? (true/false): ");
        boolean presencial = sc.nextBoolean();
        sc.nextLine();
        String sala = presencial ? sc.nextLine() : "Remoto";
        if (presencial) {
            System.out.print("Sala: ");
            sala = sc.nextLine();
        }
        System.out.print("Horário: ");
        String horario = sc.nextLine();
        System.out.print("Capacidade máxima: ");
        int capacidade = sc.nextInt();
        sc.nextLine();

        Turma t = new Turma(codTurma, disciplinaEscolhida, professor, semestre,
                             formaAvaliacao, presencial, sala, horario, capacidade);
        turmas.add(t);
        System.out.println("Turma cadastrada com sucesso!");
    }

    private void listarDisciplinas() {
        if (disciplinas.isEmpty()) {
            System.out.println("Nenhuma disciplina cadastrada.");
        } else {
            for (Disciplina d : disciplinas) {
                System.out.println(d);
            }
        }
    }

    private void listarTurmas() {
        if (turmas.isEmpty()) {
            System.out.println("Nenhuma turma cadastrada.");
        } else {
            for (Turma t : turmas) {
                System.out.println(t);
            }
        }
    
    }
    
    private void fazerMatricula() {
        MenuMatricula menuMatricula = new MenuMatricula(
            obterAlunos(),
            turmas
        );
        menuMatricula.iniciar();
    }
    
    private ArrayList<Aluno> obterAlunos() {
        AlunoManager am = new AlunoManager();
        am.carregarAlunosDoArquivo("alunos.txt");
        return am.getAlunos();
    }
    
    private void iniciarMenuAvaliacao() {
        MenuAvaliacao menuAvaliacao = new MenuAvaliacao(turmas);
        menuAvaliacao.iniciar();
    }

}
