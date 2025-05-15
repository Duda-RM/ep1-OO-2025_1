package sistema;

import java.util.ArrayList;
	
public class Aluno extends Pessoa {
		private String matricula;
		private String curso;
		private ArrayList<Double> notas;
		private double frequencia;
		
		public Aluno(String nome, String cpf, String email, String matricula, String curso) {
			super(nome, cpf, email);
			this.matricula = matricula;
			this.curso = curso;
			this.notas = new ArrayList<>();
			this.frequencia = 0.0;
			
		}
		
		public String getMatricula() {
			return matricula;
			}
		
		public String getCurso() {
			return curso;
		}
		
		public void setMatricula(String matricula) {
			this.matricula = matricula;
		}
		
		public void setCurso(String curso) {
			this.curso = curso;
		}
		
		public ArrayList<Double> getNotas() {
			return notas;
		}
		
		public void adicionarNota(double nota) {
			notas.add(nota);
		}
		
		public double calcularMedia() {
			if (notas.isEmpty()) return 0.0;
			double soma = 0;
			for (double nota : notas) {
				soma += nota;
			}
			return soma/notas.size();
			
		}
		
		@Override
		public String toString() {
			return super.toString()+
					", Matricula: " + matricula +
					", Curso: " + curso + 
					", Média: " + calcularMedia() +
					", Frequência: " + frequencia + "%";
		}
			}

		