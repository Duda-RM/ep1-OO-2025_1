package sistema;

import java.util.ArrayList;
import java.util.Scanner;

public class MenuAvaliacao {
    private ArrayList<Turma> turmas;
    private Scanner sc;

    public MenuAvaliacao(ArrayList<Turma> turmas) {
        this.turmas = turmas;
        this.sc = new Scanner(System.in);
    }

    public void iniciar() {
        int opcao;

        do {
            System.out.println("\n===== MODO AVALIAÇÃO/FREQUÊNCIA =====");
            System.out.println("1. Lançar notas e presença");
            System.out.println("2. Gerar relatórios");
            System.out.println("3. Exibir boletim por aluno");
            System.out.println("0. Sair");
            System.out.print("Escolha uma opção: ");
            opcao = sc.nextInt();
            sc.nextLine();

            switch (opcao) {
                case 1 -> lancarNotasEPresenca();
                case 2 -> gerarRelatorios();
                case 3 -> exibirBoletim();
                case 0 -> System.out.println("Saindo do Modo Avaliação...");
                default -> System.out.println("Opção inválida!");
            }
        } while (opcao != 0);
    }

    private void lancarNotasEPresenca() {
        System.out.print("Digite o código da turma para lançar notas/presença: ");
        String codTurma = sc.nextLine();

        Turma turmaEscolhida = null;
        for (Turma t : turmas) {
            if (t.getCodigoTurma().equals(codTurma)) {
                turmaEscolhida = t;
                break;
            }
        }

        if (turmaEscolhida == null) {
            System.out.println("Turma não encontrada!");
            return;
        }

        System.out.println("Alunos matriculados:");
        for (Aluno a : turmaEscolhida.getAlunosMatriculados()) {
            System.out.println("- " + a.getMatricula() + " | " + a.getNome());
        }

        System.out.print("Digite a matrícula do aluno para lançar notas/presença: ");
        String matriculaAluno = sc.nextLine();

        Aluno alunoEscolhido = null;
        for (Aluno a : turmaEscolhida.getAlunosMatriculados()) {
            if (a.getMatricula().equals(matriculaAluno)) {
                alunoEscolhido = a;
                break;
            }
        }

        if (alunoEscolhido == null) {
            System.out.println("Aluno não encontrado nessa turma!");
            return;
        }

        System.out.println("Digite as notas:");
        System.out.print("P1: ");
        double p1 = sc.nextDouble();
        System.out.print("P2: ");
        double p2 = sc.nextDouble();
        System.out.print("P3: ");
        double p3 = sc.nextDouble();
        System.out.print("Listas de exercícios (L): ");
        double l = sc.nextDouble();
        System.out.print("Seminário (S): ");
        double s = sc.nextDouble();

        System.out.print("Digite a frequência do aluno (%): ");
        double frequencia = sc.nextDouble();
        sc.nextLine();

        alunoEscolhido.setFrequencia(frequencia);

        double mediaFinal = 0.0;
        if (turmaEscolhida.getFormaAvaliacao().equals("1")) {
            mediaFinal = (p1 + p2 + p3 + l + s) / 5;
        } else {
            mediaFinal = (p1 + (p2 * 2) + (p3 * 3) + l + s) / 8;
        }

        System.out.println("Média final: " + mediaFinal);

        if (frequencia < 75) {
            System.out.println("Reprovado por falta!");
        } else if (mediaFinal >= 5) {
            System.out.println("Aprovado!");
        } else {
            System.out.println("Reprovado por nota!");
        }
    }

    public void gerarRelatorios() {
        System.out.println("\n===== RELATÓRIOS =====");
        System.out.println("1. Relatório por Turma");
        System.out.println("2. Relatório por Disciplina");
        System.out.println("3. Relatório por Professor");
        System.out.print("Escolha uma opção: ");
        int opcao = sc.nextInt();
        sc.nextLine();

        switch (opcao) {
            case 1 -> relatorioPorTurma();
            case 2 -> relatorioPorDisciplina();
            case 3 -> relatorioPorProfessor();
            default -> System.out.println("Opção inválida!");
        }
    }

    private void relatorioPorTurma() {
        System.out.print("Digite o código da turma: ");
        String codTurma = sc.nextLine();

        for (Turma t : turmas) {
            if (t.getCodigoTurma().equals(codTurma)) {
                System.out.println("Alunos matriculados na turma " + t.getCodigoTurma() + ":");
                for (Aluno a : t.getAlunosMatriculados()) {
                    System.out.println("- " + a.getMatricula() + " | " + a.getNome());
                }
                return;
            }
        }
        System.out.println("Turma não encontrada!");
    }

    private void relatorioPorDisciplina() {
        System.out.print("Digite o código da disciplina: ");
        String codDisciplina = sc.nextLine();

        for (Turma t : turmas) {
            if (t.getDisciplina().getCodigo().equals(codDisciplina)) {
                System.out.println("Turma " + t.getCodigoTurma() + " da disciplina " + codDisciplina + ":");
                for (Aluno a : t.getAlunosMatriculados()) {
                    System.out.println("- " + a.getMatricula() + " | " + a.getNome());
                }
            }
        }
    }

    private void relatorioPorProfessor() {
        System.out.print("Digite o nome do professor: ");
        String nomeProfessor = sc.nextLine();

        for (Turma t : turmas) {
            if (t.getProfessor().equalsIgnoreCase(nomeProfessor)) {
                System.out.println("Turma " + t.getCodigoTurma() + " do professor " + nomeProfessor + ":");
                for (Aluno a : t.getAlunosMatriculados()) {
                    System.out.println("- " + a.getMatricula() + " | " + a.getNome());
                }
            }
        }
    }

    public void exibirBoletim() {
        System.out.print("Digite a matrícula do aluno: ");
        String matricula = sc.nextLine();

        for (Turma t : turmas) {
            for (Aluno a : t.getAlunosMatriculados()) {
                if (a.getMatricula().equals(matricula)) {
                    System.out.println("\nBoletim do aluno: " + a.getNome());
                    System.out.println("- Turma: " + t.getCodigoTurma());
                    System.out.println("- Disciplina: " + t.getDisciplina().getNome());
                    System.out.println("- Professor: " + t.getProfessor());
                    System.out.println("- Semestre: " + t.getSemestre());
                    System.out.println("- Frequência: " + a.getFrequencia() + "%");
                }
            }
        }
    }
}
