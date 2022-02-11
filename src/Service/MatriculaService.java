package Service;

import Model.Aluno;
import Model.Matricula;
import Repository.AlunoRepository;
import Repository.MatriculaRepository;

import java.sql.SQLException;
import java.util.List;

public class MatriculaService {
    private MatriculaRepository _repository;

    //construtor
    public MatriculaService() throws SQLException {
        _repository = new MatriculaRepository();
    }

    public boolean create(Matricula matricula){
        try{
            _repository.create(matricula);
            return true;
        } catch (SQLException e){
            e.printStackTrace();
            return false;
        }
    }

    public boolean update(Matricula matricula){
        try{
            _repository.update(matricula);
            return true;
        } catch (SQLException e){
            e.printStackTrace();
            return false;
        }
    }

    public boolean delete(Matricula matricula){
        try{
            _repository.delete(matricula.getCodAluno());
            return true;
        } catch (Exception e){
            e.printStackTrace();
            return false;
        }
    }

    public List<Matricula> getAll() throws SQLException{
        return _repository.getAll();
    }

    public Matricula getById(Integer id) throws SQLException{
        return _repository.getById(id);
    }
}
