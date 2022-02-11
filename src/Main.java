import Controller.AlunoController;
import Controller.DisciplinaController;
import Model.Aluno;
import Model.Avaliacao;
import Model.Disciplina;
import Model.Matricula;
import Service.AlunoService;
import Service.AvaliacaoService;
import Service.DisciplinaService;
import Service.MatriculaService;
import com.google.gson.Gson;

import java.sql.Date;
import java.sql.SQLException;
import java.time.LocalDate;
import java.time.Month;
import java.util.List;

import static spark.Spark.*;

public class Main {
    private static final Gson gson = new Gson();

    public static void main(String[] args) {
        try{
            before((req, res) -> {
                res.type("application/json");
            });

            defaultResponseTransformer(gson::toJson);

            new AlunoController();
            new DisciplinaController();
        } catch (SQLException e){
            e.printStackTrace();
        }

    }

    // Matricula

    private static void CriaMatricula(){
        try{
            var service = new MatriculaService();
            var matricula = new Matricula();
            matricula.setCodAluno(4);
            matricula.setCodDisciplina(5678);
            matricula.setDataMatricula(LocalDate.of(1996, Month.JUNE, 28));
            matricula.setStatusMatricula("C");

            service.create(matricula);
        } catch (SQLException e){
            e.printStackTrace();
        }
    }

    private static void ConsultaTodasMatriculas(){
        try{
            var service = new MatriculaService();
            var matriculas = service.getAll();
            System.out.println(matriculas);
        } catch (SQLException e){
            e.printStackTrace();
        }
    }

    private static void ConsultaMatriculaPeloId(){
        try{
            var service = new MatriculaService();
            var matricula = service.getById(3);
            System.out.println(matricula);
        } catch (SQLException e){
            e.printStackTrace();
        }
    }

    private static void DeletarMatricula(){
        try{
            var service = new MatriculaService();
            var matricula = new Matricula();
            matricula.setCodAluno(2);
            service.delete(matricula);
        } catch (SQLException e){
            e.printStackTrace();
        }
    }

    // Avaliacao

    private static void CriaAvaliacao(){
        try{
            var service = new AvaliacaoService();
            var avaliacao = new Avaliacao();
            avaliacao.setCodAluno(3);
            avaliacao.setCodDisciplina(1268);
            avaliacao.setNumAvaliacao(2);
            avaliacao.setValorNota(09.55);

            service.create(avaliacao);
        } catch (SQLException e){
            e.printStackTrace();
        }
    }

    private static void ConsultaTodasAvaliacoes(){
        try{
            var service = new AvaliacaoService();
            var avaliacaos = service.getAll();
            System.out.println(avaliacaos);
        } catch (SQLException e){
            e.printStackTrace();
        }
    }

    private static void ConsultaAvaliacaoPeloId(){
        try{
            var service = new AvaliacaoService();
            var avaliacao = service.getById(3);
            System.out.println(avaliacao);
        } catch (SQLException e){
            e.printStackTrace();
        }
    }

    // Aluno

    private static void CriaAluno(){
        try{
            var service = new AlunoService();
            var aluno = new Aluno();
            aluno.setCodAluno(1);
            aluno.setNomeAluno("Lilo");
            aluno.setNomeMae("Ratinha");
            aluno.setNomePai("Ratinho");
            aluno.setDataNasc(LocalDate.of(1996, Month.JUNE, 28));
            aluno.setMediaAluno(07.85);
            aluno.setSitAluno("A");

            service.create(aluno);
        } catch (SQLException e){
            e.printStackTrace();
        }
    }

    private static void ConsultaTodosAlunos(){
        try{
            var service = new AlunoService();
            var alunos = service.getAll();
            System.out.println(alunos);
        } catch (SQLException e){
            e.printStackTrace();
        }
    }

    private static void ConsultaAlunoPeloId(){
        try{
            var service = new AlunoService();
            var aluno = service.getById(1);
            System.out.println(aluno);
        } catch (SQLException e){
            e.printStackTrace();
        }
    }

    private static void DeletarAluno(){
        try{
            var service = new AlunoService();
            var aluno = new Aluno();
            aluno.setCodAluno(2);
            service.delete(aluno);
        } catch (SQLException e){
            e.printStackTrace();
        }
    }

    private static void AtualizarAluno(){
        try {
            var service = new AlunoService();
            var aluno = service.getById(1);
            aluno.setNomeAluno("Blanquita");
            service.update(aluno);
        } catch (SQLException e){
            e.printStackTrace();
        }

    }
    // Disciplina

    private static void CriaDisciplina(){
        try{
            var service = new DisciplinaService();
            var disciplina = new Disciplina();
            disciplina.setName("SQL");
            disciplina.setProfessor("Julita");
            disciplina.setId(22);
            disciplina.setAvaliacoes(1);

            service.create(disciplina);

        } catch (SQLException e){
            e.printStackTrace();
        }
    }

    private static void ConsultaTodasDisciplinas(){
        try{
            var service = new DisciplinaService();
            var disciplinas = service.getAll();
            System.out.println(disciplinas);
        } catch (SQLException e){
            e.printStackTrace();
        }
    }

    private static void ConsultaPeloId(){
        try{
            var service = new DisciplinaService();
            var disciplina = service.getById(333);
            System.out.println(disciplina);
        } catch (SQLException e){
            e.printStackTrace();
        }
    }

    private static void DeletarDisciplina(){
        try{
            var service = new DisciplinaService();
            var disciplina = new Disciplina();
            disciplina.setId(2);
            service.delete(disciplina);
        } catch (SQLException e){
            e.printStackTrace();
        }
    }
}
