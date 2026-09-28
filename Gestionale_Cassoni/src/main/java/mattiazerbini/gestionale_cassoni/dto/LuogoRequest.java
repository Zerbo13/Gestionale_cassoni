package mattiazerbini.gestionale_cassoni.dto;

public class LuogoRequest {

    private String nome;
    private String indirizzo;
    private String tipologia;

    public LuogoRequest() {
    }

    public LuogoRequest(String nome, String indirizzo, String tipologia) {
        this.nome = nome;
        this.indirizzo = indirizzo;
        this.tipologia = tipologia;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getIndirizzo() {
        return indirizzo;
    }

    public void setIndirizzo(String indirizzo) {
        this.indirizzo = indirizzo;
    }

    public String getTipologia() {
        return tipologia;
    }

    public void setTipologia(String tipologia) {
        this.tipologia = tipologia;
    }
}