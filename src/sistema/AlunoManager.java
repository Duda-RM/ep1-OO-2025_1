package sistema;

import java.io.*;
import java.util.ArrayList;

public class AlunoManager {
    private ArrayList<Aluno> alunos;

    public AlunoManager() {
        this.alunos = new ArrayList<>();
    }

    public boolean adicionarAluno(Aluno aluno) {
        if (buscarAlunoPorMatricula(aluno.getMatricula()) != null) {
            System.out.println("Já existe um aluno com essa matrícula.");
            return false;
        }
        alunos.add(aluno);
        System.out.println("Aluno cadastrado com sucesso.");
        return true;
    }

    public Aluno buscarAlunoPorMatricula(String matricula) {
        for (Aluno a : alunos) {
            if (a.getMatricula().equals(matricula)) {
                return a;
            }
        }
        return null;
    }

    public void listarAlunos() {
        if (alunos.isEmpty()) {
            System.out.println("Nenhum aluno cadastrado.");
        } else {
            for (Aluno a : alunos) {
                System.out.println(a);
            }
        }
    }

    public void salvarAlunosEmArquivo(String caminho) {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(caminho))) {
            for (Aluno a : alunos) {
                writer.write(
                    a.getNome() + "," +
                    a.getCPF() + "," +
                    a.getEmail() + "," +
                    a.getMatricula() + "," +
                    a.getCurso() + "," +
                    a.getFrequencia() + "," +
                    (a instanceof AlunoEspecial ? "especial" : "normal")
                );
                writer.newLine();
            }
            System.out.println("Alunos salvos com sucesso.");
        } catch (IOException e) {
            System.out.println("Erro ao salvar alunos: " + e.getMessage());
        }
    }

    public void carregarAlunosDoArquivo(String caminho) {
        alunos.clear();
        try (BufferedReader reader = new BufferedReader(new FileReader(caminho))) {
            String linha;
            while ((linha = reader.readLine()) != null) {
                String[] dados = linha.split(",");
                if (dados.length >= 7) {
                    String tipo = dados[6];
                    if (tipo.equals("especial")) {
                        AlunoEspecial a = new AlunoEspecial(dados[0], dados[1], dados[2], dados[3], dados[4]);
                        a.setFrequencia(Double.parseDouble(dados[5]));
                        alunos.add(a);
                    } else {
                        Aluno a = new Aluno(dados[0], dados[1], dados[2], dados[3], dados[4]);
                        a.setFrequencia(Double.parseDouble(dados[5]));
                        alunos.add(a);
                    }
                }
            }
            System.out.println("Alunos carregados com sucesso.");
        } catch (IOException e) {
            System.out.println("Erro ao carregar alunos: " + e.getMessage());
        }
    }
}
