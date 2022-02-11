package Controller;
import Model.Aluno;
import Service.AlunoService;
import com.google.gson.JsonParser;
import spark.Request;
import spark.Response;

import java.sql.SQLException;
import java.time.LocalDate;

import static spark.Spark.*;

public class AlunoController {
    private AlunoService _service;

    public AlunoController() throws SQLException {
        _service = new AlunoService();

        get("/aluno", (req, res) -> getAll(req, res));
        get("/aluno/:id", (req, res) -> getById(req, res));
        delete("/aluno/:id", (req, res) -> deletar(req, res));
        post("/aluno", (req, res) -> create(req, res));
        put("/aluno/:id", (req, res) -> update(req, res));
    }

    public Object getAll(Request request, Response response) throws SQLException{
        var alunos = _service.getAll();
        return alunos;
    }

    public Object getById(Request request, Response response) throws SQLException{
        var Id = request.params("id");
        var aluno = _service.getById(Integer.valueOf(Id));
        System.out.println(Id);
        return aluno;
    }

    public Object deletar(Request request, Response response) throws SQLException{
        var Id = request.params("id");
        var aluno = new Aluno();
        aluno.setCodAluno(3);
        _service.delete(aluno);
        System.out.println(Id);
        return "apagado";
    }

    public Object create(Request request, Response response) throws SQLException{
        var json = JsonParser.parseString(request.body()).getAsJsonObject();
        var aluno = new Aluno();
        aluno.setNomeAluno(json.get("nome").getAsString());
        aluno.setSitAluno(json.get("situacao").getAsString());
        aluno.setMediaAluno(json.get("media").getAsDouble());
        aluno.setNomeMae(json.get("nome_mae").getAsString());
        aluno.setNomePai(json.get("nome_pai").getAsString());
        aluno.setDataNasc(LocalDate.parse(json.get("data_nasc").getAsString()));
        _service.create(aluno);
        return "ok";
    }

    public Object update(Request request, Response response) throws SQLException{
        var id = Integer.valueOf(request.params("id"));
        var json = JsonParser.parseString(request.body()).getAsJsonObject();

        var aluno = _service.getById(id);
        if(json.has("nome")){
            aluno.setNomeAluno(json.get("nome").getAsString());
        }

        if(json.has("situacao")){
            aluno.setSitAluno(json.get("situacao").getAsString());
        }

        if(json.has("media")){
            aluno.setMediaAluno(json.get("media").getAsDouble());
        }

        if(json.has("nome_mae")){
            aluno.setNomeMae(json.get("nome_mae").getAsString());
        }

        if(json.has("nome_pai")){
            aluno.setNomePai(json.get("nome_pai").getAsString());
        }

        if(json.has("data_nasc")){
            aluno.setDataNasc(LocalDate.parse(json.get("data_nasc").getAsString()));
        }

        _service.update(aluno);
        return "ok";
    }
}
