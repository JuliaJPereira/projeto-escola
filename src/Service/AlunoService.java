package Service;

import Model.Aluno;
import Repository.AlunoRepository;

import java.sql.SQLException;
import java.util.List;

public class AlunoService {
    private AlunoRepository _repository;

    //construtor
    public AlunoService() throws SQLException {
        _repository = new AlunoRepository();
    }

    public boolean create(Aluno aluno){
        try{
            _repository.create(aluno);
            return true;
        } catch (SQLException e){
            e.printStackTrace();
            return false;
        }
    }

    public boolean update(Aluno aluno){
        try{
            _repository.update(aluno);
            return true;
        } catch (SQLException e){
            e.printStackTrace();
            return false;
        }
    }

    public boolean delete(Aluno aluno){
        try{
            _repository.delete(aluno.getCodAluno());
            return true;
        } catch (Exception e){
            e.printStackTrace();
            return false;
        }
    }

    public List<Aluno> getAll() throws SQLException{
        return _repository.getAll();
    }

    public Aluno getById(Integer id) throws SQLException{
        return _repository.getById(id);
    }
}
