package Repository;

import DataBase.MysqlConection;
import Model.Aluno;
import Model.Matricula;

import java.sql.Connection;
import java.sql.Date;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class MatriculaRepository {
    private final Connection _connection;

    public MatriculaRepository() throws SQLException {
        this._connection = MysqlConection.getConnection();
    }

    public Matricula getById(Integer id) throws SQLException{
        var sql = "SELECT * FROM matricula WHERE codaluno = ? ";
        var ps = _connection.prepareStatement(sql);
        ps.setInt(1, id);
        var rs = ps.executeQuery();

        rs.next();
        var matricula = new Matricula();
        matricula.setCodAluno(rs.getInt("codaluno"));
        matricula.setCodDisciplina(rs.getInt("coddisciplina"));
        matricula.setDataMatricula(rs.getDate("dtmatricula").toLocalDate());
        matricula.setStatusMatricula(rs.getString("statusmatricula"));
        return matricula;
    }

    public List<Matricula> getAll() throws SQLException{
        List<Matricula> matriculas = new ArrayList<>();

        var sql = "SELECT * FROM matricula";
        var ps = _connection.prepareStatement(sql);
        var rs = ps.executeQuery();

        while(rs.next()){
            var matricula = new Matricula();
            matricula.setCodAluno(rs.getInt("codaluno"));
            matricula.setCodDisciplina(rs.getInt("coddisciplina"));
            matricula.setDataMatricula(rs.getDate("dtmatricula").toLocalDate());
            matricula.setStatusMatricula(rs.getString("statusmatricula"));
            matriculas.add(matricula);
        }
        return matriculas;
    }

    public void create(Matricula matricula) throws SQLException{
        var sql = "INSERT INTO matricula(codaluno, coddisciplina, dtmatricula, statusmatricula) " +
                "VALUES (?,?,?,?)";
        var ps = _connection.prepareStatement(sql);
        ps.setInt(1, matricula.getCodAluno());
        ps.setInt(2, matricula.getCodDisciplina());
        ps.setDate(3, Date.valueOf(matricula.getDataMatricula()));
        ps.setString(4, matricula.getStatusMatricula());
        ps.execute();
    }

    public void update(Matricula matricula) throws SQLException{
        var sql = "UPDATE matricula set coddisciplina = ?, dtmatricula = ?, statusmatricula = ?, " +
                "WHERE codaluno = ?";
        var ps = _connection.prepareStatement(sql);
        ps.setInt(1, matricula.getCodDisciplina());
        ps.setDate(2, Date.valueOf(matricula.getDataMatricula()));
        ps.setString(3, matricula.getStatusMatricula());
        ps.setInt(4, matricula.getCodAluno());
        ps.execute();
    }

    public void delete(Integer id) throws SQLException{
        var sql = "DELETE FROM matricula WHERE codaluno = ?";
        var ps = _connection.prepareStatement(sql);
        ps.setInt(1, id);
        ps.execute();
    }
}
