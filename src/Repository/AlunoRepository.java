package Repository;

import DataBase.MysqlConection;
import Model.Aluno;

import java.sql.Connection;
import java.sql.Date;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class AlunoRepository {

    private final Connection _connection;

    public AlunoRepository() throws SQLException {
        this._connection = MysqlConection.getConnection();
    }

    public Aluno getById(Integer id) throws SQLException{
        var sql = "SELECT * FROM aluno WHERE codaluno = ? ";
        var ps = _connection.prepareStatement(sql);
        ps.setInt(1, id);
        var rs = ps.executeQuery();

        rs.next();
        var aluno = new Aluno();
        aluno.setCodAluno(rs.getInt("codaluno"));
        aluno.setNomeAluno(rs.getString("nomaluno"));
        aluno.setNomeMae(rs.getString("nommae"));
        aluno.setNomePai(rs.getString("nompai"));
        aluno.setDataNasc(rs.getDate("datanasc").toLocalDate());
        aluno.setMediaAluno(rs.getDouble("medaluno"));
        aluno.setSitAluno(rs.getString("sitaluno"));
        return aluno;
    }

    public List<Aluno> getAll() throws SQLException{
        List<Aluno> alunos = new ArrayList<>();

        var sql = "SELECT * FROM aluno";
        var ps = _connection.prepareStatement(sql);
        var rs = ps.executeQuery();

        while(rs.next()){
            var aluno = new Aluno();
            aluno.setCodAluno(rs.getInt("codaluno"));
            aluno.setNomeAluno(rs.getString("nomaluno"));
            aluno.setNomeMae(rs.getString("nommae"));
            aluno.setNomePai(rs.getString("nompai"));
            aluno.setDataNasc(rs.getDate("datanasc").toLocalDate());
            aluno.setMediaAluno(rs.getDouble("medaluno"));
            aluno.setSitAluno(rs.getString("sitaluno"));
            alunos.add(aluno);
        }
        return alunos;
    }

    public void create(Aluno aluno) throws SQLException{
        var sql = "INSERT INTO aluno(nomaluno, nommae, nompai, datanasc, medaluno, sitaluno) " +
                "VALUES (?,?,?,?,?,?)";
        var ps = _connection.prepareStatement(sql);
        ps.setString(1, aluno.getNomeAluno());
        ps.setString(2, aluno.getNomeMae());
        ps.setString(3, aluno.getNomePai());
        ps.setDate(4, Date.valueOf(aluno.getDataNasc()));
        ps.setDouble(5, aluno.getMediaAluno());
        ps.setString(6, aluno.getSitAluno());
        ps.execute();
    }

    public void update(Aluno aluno) throws SQLException{
        var sql = "UPDATE aluno set nomaluno = ?, nommae = ?, nompai = ?, datanasc = ?, " +
                "medaluno = ?, sitaluno = ? WHERE codaluno = ?";
        var ps = _connection.prepareStatement(sql);
        ps.setString(1, aluno.getNomeAluno());
        ps.setString(2, aluno.getNomeMae());
        ps.setString(3, aluno.getNomePai());
        ps.setDate(4, Date.valueOf(aluno.getDataNasc()));
        ps.setDouble(5, aluno.getMediaAluno());
        ps.setString(6, aluno.getSitAluno());
        ps.setInt(7, aluno.getCodAluno());
        ps.execute();
    }

    public void delete(Integer id) throws SQLException{
        var sql = "DELETE FROM aluno WHERE codaluno = ?";
        var ps = _connection.prepareStatement(sql);
        ps.setInt(1, id);
        ps.execute();
    }
}
