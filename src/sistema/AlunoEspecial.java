package sistema;

public class AlunoEspecial extends Aluno {
    private int disciplinasMatriculadas;

    public AlunoEspecial(String nome, String cpf, String email, String matricula, String curso) {
        super(nome, cpf, email, matricula, curso);
        this.disciplinasMatriculadas = 0;
    }

    public int getDisciplinasMatriculadas() {
        return disciplinasMatriculadas;
    }

    public boolean podeMatricular() {
        return disciplinasMatriculadas < 2;
    }

    public void matricularDisciplina() {
        if (podeMatricular()) {
            disciplinasMatriculadas++;
        } else {
            System.out.println("Aluno Especial só pode cursar no máximo 2 disciplinas.");
        }
    }

    @Override
    public void adicionarNota(double nota) {
        System.out.println("Aluno Especial não pode receber notas. Apenas presença é registrada.");
    }

    @Override
    public double calcularMedia() {
        return 0.0;
    }

    @Override
    public String toString() {
        return super.toString() + " [Aluno Especial - só presença, sem nota]";
    }
}
