package Repository;

import java.sql.Connection;
import DataBase.MysqlConection;
import Model.Aluno;
import Model.Avaliacao;
import Model.Disciplina;

import java.sql.Connection;
import java.sql.Date;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class AvaliacaoRepository {
	private final Connection _connection;
	
	public AvaliacaoRepository() throws SQLException {
        this._connection = MysqlConection.getConnection();
    }
	
	public Avaliacao getById(Integer id) throws SQLException{
        var sql = "SELECT * FROM avaliacao WHERE codaluno = ? ";
        var ps = _connection.prepareStatement(sql);
        ps.setInt(1, id);
        var rs = ps.executeQuery();

        rs.next();
        var avaliacao = new Avaliacao();
        avaliacao.setCodAluno(rs.getInt("codaluno"));
        avaliacao.setCodDisciplina(rs.getInt("coddisciplina"));
        avaliacao.setNumAvaliacao(rs.getInt("nravaliacao"));
        avaliacao.setValorNota(rs.getDouble("vlrnota"));
        return avaliacao;
    }
	
	public List<Avaliacao> getAll() throws SQLException{
        List<Avaliacao> avaliacoes = new ArrayList<Avaliacao>();

        var sql = "SELECT * FROM avaliacao";
        var ps = _connection.prepareStatement(sql);
        var rs = ps.executeQuery();

        while (rs.next()){
            var avaliacao = new Avaliacao();
            avaliacao.setCodAluno(rs.getInt("codaluno"));
            avaliacao.setCodDisciplina(rs.getInt("coddisciplina"));
            avaliacao.setNumAvaliacao(rs.getInt("nravaliacao"));
            avaliacao.setValorNota(rs.getDouble("vlrnota"));
            avaliacoes.add(avaliacao);
        }
        return avaliacoes;
    }
	
	public void create(Avaliacao avaliacao) throws SQLException{
        var sql = "INSERT INTO avaliacao (codaluno, coddisciplina, " +
                "nravaliacao, vlrnota) VALUES (?,?,?,?)";
        var ps = _connection.prepareStatement(sql);
        ps.setInt(1, avaliacao.getCodAluno());
        ps.setInt(2, avaliacao.getCodDisciplina());
        ps.setInt(3, avaliacao.getNumAvaliacao());
        ps.setDouble(4, avaliacao.getValorNota());
        ps.execute();
    }
	
	public void update(Avaliacao avaliacao) throws SQLException{
        var sql = "UPDATE avaliacao set coddisciplina = ?, " +
                "nravaliacao = ?, vlrnota = ? WHERE codaluno = ?";
        var ps = _connection.prepareStatement(sql);
        ps.setInt(1, avaliacao.getCodDisciplina());
        ps.setInt(2, avaliacao.getNumAvaliacao());
        ps.setDouble(3, avaliacao.getValorNota());
        ps.setInt(4, avaliacao.getCodAluno());
        ps.execute();
    }
	
	public void delete (Integer id) throws SQLException{
        var sql = "DELETE FROM avaliacao WHERE codaluno = ?";
        var ps = _connection.prepareStatement(sql);
        ps.setInt(1, id);
        ps.execute();
    }
}
