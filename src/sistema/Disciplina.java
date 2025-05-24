package sistema;

import java.util.ArrayList;

public class Disciplina {
    private String nome;
    private String codigo;
    private int cargaHoraria;
    private ArrayList<String> preRequisitos;

    public Disciplina(String nome, String codigo, int cargaHoraria) {
        this.nome = nome;
        this.codigo = codigo;
        this.cargaHoraria = cargaHoraria;
        this.preRequisitos = new ArrayList<>();
    }

    public String getNome() {
        return nome;
    }

    public String getCodigo() {
        return codigo;
    }

    public int getCargaHoraria() {
        return cargaHoraria;
    }

    public ArrayList<String> getPreRequisitos() {
        return preRequisitos;
    }

    public void adicionarPreRequisito(String codDisciplina) {
        preRequisitos.add(codDisciplina);
    }

    @Override
    public String toString() {
        return "Disciplina: " + nome + " (" + codigo + "), Carga Horária: " + cargaHoraria +
               ", Pré-requisitos: " + preRequisitos;
    }
}
