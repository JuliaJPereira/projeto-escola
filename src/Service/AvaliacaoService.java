package Service;

import java.sql.SQLException;
import java.util.List;

import Model.Avaliacao;
import Model.Disciplina;
import Repository.AvaliacaoRepository;
import Repository.DisciplinaRepository;

public class AvaliacaoService {
	private AvaliacaoRepository _repository;
	
	 //construtor
    public AvaliacaoService() throws SQLException {
        _repository = new AvaliacaoRepository();
    }
    
    public boolean create(Avaliacao avaliacao){
        try{
            _repository.create(avaliacao);
            return true;
        } catch (SQLException e){
         e.printStackTrace();
         return false;
        } catch (Exception e){
            e.printStackTrace();
            return false; }
    }
    
    public boolean update (Avaliacao avaliacao){
        try{
            _repository.update(avaliacao);
            return true;
        } catch (SQLException e){
            e.printStackTrace();
            return false;
        }
    }
    
    public boolean delete(Avaliacao avaliacao){
        try{
            _repository.delete(avaliacao.getCodAluno());
            return true;
        } catch (Exception e){
            e.printStackTrace();
            return false;
        }
    }
    
    public List<Avaliacao> getAll() throws SQLException{
        return _repository.getAll();
    }
    
    public Avaliacao getById(Integer id) throws SQLException{
        return  _repository.getById(id);
    }
}
