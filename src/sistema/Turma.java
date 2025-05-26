package sistema;

import java.util.ArrayList;

public class Turma {
    private String codigoTurma;
    private Disciplina disciplina;
    private String professor;
    private String semestre;
    private String formaAvaliacao;
    private boolean presencial;
    private String sala;
    private String horario;
    private int capacidadeMaxima;
    private ArrayList<Aluno> alunosMatriculados;

    public Turma(String codigoTurma, Disciplina disciplina, String professor,
                  String semestre, String formaAvaliacao, boolean presencial,
                  String sala, String horario, int capacidadeMaxima) {
        this.codigoTurma = codigoTurma;
        this.disciplina = disciplina;
        this.professor = professor;
        this.semestre = semestre;
        this.formaAvaliacao = formaAvaliacao;
        this.presencial = presencial;
        this.sala = presencial ? sala : "Remoto";
        this.horario = horario;
        this.capacidadeMaxima = capacidadeMaxima;
        this.alunosMatriculados = new ArrayList<>();
    }

    public String getCodigoTurma() { return codigoTurma; }
    public Disciplina getDisciplina() { return disciplina; }
    public String getProfessor() { return professor; }
    public String getSemestre() { return semestre; }
    public String getFormaAvaliacao() { return formaAvaliacao; }
    public boolean isPresencial() { return presencial; }
    public String getSala() { return sala; }
    public String getHorario() { return horario; }
    public int getCapacidadeMaxima() { return capacidadeMaxima; }
    public ArrayList<Aluno> getAlunosMatriculados() { return alunosMatriculados; }

    public boolean matricularAluno(Aluno aluno) {
        if (alunosMatriculados.size() < capacidadeMaxima) {
            alunosMatriculados.add(aluno);
            return true;
        } else {
            System.out.println("Turma cheia!");
            return false;
        }
    }

    @Override
    public String toString() {
        return "Turma: " + codigoTurma + ", Disciplina: " + disciplina.getNome() +
                ", Professor: " + professor + ", Semestre: " + semestre +
                ", Avaliação: " + formaAvaliacao + ", Modalidade: " +
                (presencial ? "Presencial, Sala: " + sala : "Remota") +
                ", Horário: " + horario +
                ", Vagas: " + capacidadeMaxima +
                ", Alunos matriculados: " + alunosMatriculados.size();
    }
}
