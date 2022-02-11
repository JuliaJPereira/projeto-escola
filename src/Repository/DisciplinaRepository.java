package Repository;
// SEMPRE VAMOS SEGUIR O PRINCIPIO DE CRUD DENTRO DE REPOSITORIO
// CRUD = CREATE, READ, UPDATE AND DELETE
// METODOS QUE VAMOS CRIAR:
// getById, getAll, create, update, delete

import DataBase.MysqlConection;
import Model.Disciplina;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class DisciplinaRepository {
    private Connection _connection;

    // construtor
    public DisciplinaRepository() throws SQLException {
        this._connection = MysqlConection.getConnection();
    }

    public Disciplina getById(Integer id) throws SQLException {
        var sql = "SELECT * FROM disciplina WHERE coddisciplina = ?";
        var ps = _connection.prepareStatement(sql);
        ps.setInt(1, id);
        var rs = ps.executeQuery();

        rs.next();
        var disciplina = new Disciplina();
        disciplina.setId(rs.getInt("coddisciplina"));
        disciplina.setName(rs.getString("nomDisciplina"));
        disciplina.setProfessor(rs.getString("nomProfessor"));
        disciplina.setAvaliacoes(rs.getInt("qtdAvaliacoes"));
        return disciplina;
    }

    public List<Disciplina> getAll() throws SQLException{
        List<Disciplina> disciplinas = new ArrayList<Disciplina>();

        var sql = "SELECT * FROM disciplina";
        var ps = _connection.prepareStatement(sql);
        var rs = ps.executeQuery();

        while (rs.next()){
            var disciplina = new Disciplina();
            disciplina.setId(rs.getInt("coddisciplina"));
            disciplina.setName(rs.getString("nomDisciplina"));
            disciplina.setProfessor(rs.getString("nomProfessor"));
            disciplina.setAvaliacoes(rs.getInt("qtdAvaliacoes"));
            disciplinas.add(disciplina);
        }
        return disciplinas;
    }

    public void create(Disciplina disciplina) throws SQLException{
        var sql = "INSERT INTO disciplina (coddisciplina, nomDisciplina, " +
                "nomprofessor, qtdavaliacoes) VALUES (?,?,?,?)";
        var ps = _connection.prepareStatement(sql);
        ps.setInt(1, disciplina.getId());
        ps.setString(2, disciplina.getName());
        ps.setString(3, disciplina.getProfessor());
        ps.setInt(4, disciplina.getAvaliacoes());
        ps.execute();
    }

    public void update(Disciplina disciplina) throws SQLException{
        var sql = "UPDATE disciplina set nomDisciplina = ?, " +
                "nomprofessor = ?, qtdavaliacoes = ? WHERE coddisciplina = ?";
        var ps = _connection.prepareStatement(sql);
        ps.setString(1, disciplina.getName());
        ps.setString(2, disciplina.getProfessor());
        ps.setInt(3, disciplina.getAvaliacoes());
        ps.setInt(4, disciplina.getId());
        ps.execute();
    }

    public void delete (Integer id) throws SQLException{
        var sql = "DELETE FROM disciplina WHERE coddisciplina = ?";
        var ps = _connection.prepareStatement(sql);
        ps.setInt(1, id);
        ps.execute();
    }

}
