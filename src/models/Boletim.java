package models;


import java.util.List;

public class Boletim {
    private int codigoBoletim;
    private List<Float> notas;
    private int quantFaltas;
    private boolean aprovado;

    public int getCodigoBoletim() {return codigoBoletim;}

    public void setCodigoBoletim(int codigoBoletim) {
        this.codigoBoletim = codigoBoletim;
    }

    public List<Float> getNotas() {
        return this.notas;
    }

    public void setNotas(List<Float> notas) {
        this.notas = notas;
    }

    public int getQuantFaltas() {
        return this.quantFaltas;
    }

    public void setQuantFaltas(int quantFaltas) {
        this.quantFaltas = quantFaltas;
    }

    public boolean getAprovado() {
        return this.aprovado;
    }

    public void setAprovado(boolean aprovado) {
        this.aprovado = aprovado;
    }

}
