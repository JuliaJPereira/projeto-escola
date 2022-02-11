package Service;

import Model.Disciplina;
import Repository.DisciplinaRepository;

import java.sql.SQLException;
import java.util.List;

public class DisciplinaService {
    private DisciplinaRepository _repository;

    //construtor
    public DisciplinaService() throws SQLException {
        _repository = new DisciplinaRepository();
    }

    public boolean create(Disciplina disciplina){
        try{
            if(disciplina.getAvaliacoes()<4){
                throw new Exception("Não é possível cadastrar disciplinas com menos de 4 avaliações");
            }
            _repository.create(disciplina);
            return true;
        } catch (SQLException e){
         e.printStackTrace();
         return false;
        } catch (Exception e){
            e.printStackTrace();
            return false; }
    }

    public boolean update (Disciplina disciplina){
        try{
            _repository.update(disciplina);
            return true;
        } catch (SQLException e){
            e.printStackTrace();
            return false;
        }
    }

    public boolean delete(Disciplina disciplina){
        try{
            _repository.delete(disciplina.getId());
            return true;
        } catch (Exception e){
            e.printStackTrace();
            return false;
        }
    }

    public List<Disciplina> getAll() throws SQLException{
        return _repository.getAll();
    }

    public Disciplina getById(Integer id) throws SQLException{
        return  _repository.getById(id);
    }
}
