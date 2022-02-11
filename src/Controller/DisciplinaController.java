package Controller;

import Model.Disciplina;
import Service.DisciplinaService;
import com.google.gson.JsonParser;
import spark.Request;
import spark.Response;

import java.sql.SQLException;

import static spark.Spark.*;

public class DisciplinaController {
    private DisciplinaService _service;

    public DisciplinaController() throws SQLException{
        _service = new DisciplinaService();

        get("/disciplina", (req,res) -> getAll(req, res));
        get("/disciplina/:id", (req, res) -> getById(req, res));
        delete("/disciplina/", (req, res) -> deletar(req, res));
        post("/disciplina/:id", (req, res) -> create(req, res));
        put("/disciplina/:id", (req, res) -> update(req, res));

    }

    public Object getAll(Request request, Response response) throws SQLException{
        var disciplinas = _service.getAll();
        return disciplinas;
    }

    public Object getById(Request request, Response response) throws SQLException{
        var Id = request.params("id");
        var disciplina = _service.getById(Integer.valueOf(Id));
        System.out.println(Id);
        return disciplina;
    }

    public Object deletar(Request request, Response response) throws SQLException{
        var Id = request.params("id");
        var disciplina = new Disciplina();
        disciplina.setId(333);
        _service.delete(disciplina);
        System.out.println(Id);
        return "Disciplina apagada.";
    }

    public Object create(Request request, Response response) throws SQLException{
        var json = JsonParser.parseString(request.body()).getAsJsonObject();
        var disciplina = new Disciplina();
        disciplina.setName(json.get("nome_disciplina").getAsString());
        disciplina.setProfessor(json.get("nome_prof").getAsString());
        disciplina.setAvaliacoes(json.get("qtd_avaliacoes").getAsInt());
        _service.create(disciplina);
        return "Disciplina criada.";
    }

    public Object update(Request request, Response response) throws SQLException{
        var id = Integer.valueOf(request.params("id"));
        var json = JsonParser.parseString(request.body()).getAsJsonObject();

        var disciplina = _service.getById(id);

        if(json.has("nome_disciplina")){
            disciplina.setName(json.get("nome_disciplina").getAsString());
        }

        if(json.has("nome_prof")){
            disciplina.setProfessor(json.get("nome_prof").getAsString());
        }

        if(json.has("qtd_avaliacoes")){
            disciplina.setAvaliacoes(json.get("qtd_avaliacoes").getAsInt());
        }

        _service.update(disciplina);
        return "Disciplina atualiada.";

    }


}
