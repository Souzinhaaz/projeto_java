package models;


import java.util.List;

public class Boletim {
    private int codigoBoletim;
    private int matricula;
    private float nota1;
    private float nota2;
    private float nota3;
    private float nota4;
    private int quantFaltas;
    private String situacao;

    public int getCodigoBoletim() {return codigoBoletim;}

    public void setCodigoBoletim(int codigoBoletim) {
        this.codigoBoletim = codigoBoletim;
    }

    public int getMatricula() {
        return matricula;
    }

    public void setMatricula(int matricula) {
        this.matricula = matricula;
    }

    public float getNota1() {
        return nota1;
    }

    public void setNota1(float nota1) {
        this.nota1 = nota1;
    }

    public float getNota2() {
        return nota2;
    }

    public void setNota2(float nota2) {
        this.nota2 = nota2;
    }

    public float getNota3() {
        return nota3;
    }

    public void setNota3(float nota3) {
        this.nota3 = nota3;
    }

    public float getNota4() {
        return nota4;
    }

    public void setNota4(float nota4) {
        this.nota4 = nota4;
    }

    public int getQuantFaltas() {
        return this.quantFaltas;
    }

    public void setQuantFaltas(int quantFaltas) {
        this.quantFaltas = quantFaltas;
    }

    public String getSituacao() {
        return this.situacao;
    }

    public void setSituacao(String situacao) {
        this.situacao = situacao;
    }

}
