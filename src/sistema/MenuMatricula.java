package sistema;

import java.util.ArrayList;
import java.util.Scanner;

public class MenuMatricula {
    private ArrayList<Aluno> alunos;
    private ArrayList<Turma> turmas;
    private Scanner sc;

    public MenuMatricula(ArrayList<Aluno> alunos, ArrayList<Turma> turmas) {
        this.alunos = alunos;
        this.turmas = turmas;
        this.sc = new Scanner(System.in);
    }

    public void iniciar() {
        System.out.print("Digite a matrícula do aluno: ");
        String matricula = sc.nextLine();

        Aluno aluno = null;
        for (Aluno a : alunos) {
            if (a.getMatricula().equals(matricula)) {
                aluno = a;
                break;
            }
        }

        if (aluno == null) {
            System.out.println("Aluno não encontrado!");
            return;
        }

        System.out.println("Turmas disponíveis:");
        for (Turma t : turmas) {
            System.out.println(t);
        }

        System.out.print("Digite o código da turma para matrícula: ");
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

        // Verificar capacidade
        if (turmaEscolhida.getAlunosMatriculados().size() >= turmaEscolhida.getCapacidadeMaxima()) {
            System.out.println("Turma cheia! Matrícula não realizada.");
            return;
        }

        // Verificar pré-requisitos
        ArrayList<String> preRequisitos = turmaEscolhida.getDisciplina().getPreRequisitos();
        boolean temPreRequisitos = true;
        for (String codPre : preRequisitos) {
            boolean encontrado = false;
            for (Turma t : turmas) {
                if (t.getDisciplina().getCodigo().equals(codPre) && t.getAlunosMatriculados().contains(aluno)) {
                    encontrado = true;
                    break;
                }
            }
            if (!encontrado) {
                temPreRequisitos = false;
                break;
            }
        }

        if (!temPreRequisitos) {
            System.out.println("Aluno não possui os pré-requisitos para esta turma.");
            return;
        }

        // Verificar restrição do Aluno Especial
        if (aluno instanceof AlunoEspecial alunoEsp) {
            if (!alunoEsp.podeMatricular()) {
                System.out.println("Aluno Especial não pode cursar mais de 2 disciplinas!");
                return;
            } else {
                alunoEsp.matricularDisciplina();
            }
        }

        // Matrícula
        if (turmaEscolhida.matricularAluno(aluno)) {
            System.out.println("Aluno matriculado com sucesso!");
        } else {
            System.out.println("Erro ao matricular o aluno!");
        }
    }
}
