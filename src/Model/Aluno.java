package Model;

import java.time.LocalDate;

public class Aluno {
    Integer codAluno;
    String nomeAluno;
    String nomeMae;
    String nomePai;
    LocalDate dataNasc;
    Double mediaAluno;
    String sitAluno;

    public Integer getCodAluno() {
        return codAluno;
    }

    public void setCodAluno(Integer codAluno) {
        this.codAluno = codAluno;
    }

    public String getNomeAluno() {
        return nomeAluno;
    }

    public void setNomeAluno(String nomeAluno) {
        this.nomeAluno = nomeAluno;
    }

    public String getNomeMae() {
        return nomeMae;
    }

    public void setNomeMae(String nomeMae) {
        this.nomeMae = nomeMae;
    }

    public String getNomePai() {
        return nomePai;
    }

    public void setNomePai(String nomePai) {
        this.nomePai = nomePai;
    }

    public LocalDate getDataNasc() {
        return dataNasc;
    }

    public void setDataNasc(LocalDate dataNasc) {
        this.dataNasc = dataNasc;
    }

    public Double getMediaAluno() {
        return mediaAluno;
    }

    public void setMediaAluno(Double mediaAluno) {
        this.mediaAluno = mediaAluno;
    }

    public String getSitAluno() {
        return sitAluno;
    }

    public void setSitAluno(String sitAluno) {
        this.sitAluno = sitAluno;
    }
}
