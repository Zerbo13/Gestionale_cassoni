package mattiazerbini.gestionale_cassoni.dto;

import jakarta.validation.constraints.NotBlank;

public class LuogoRequest {

    @NotBlank(message = "Il nome è obbligatorio")
    private String nome;

    @NotBlank(message = "L'indirizzo è obbligatorio")
    private String indirizzo;

    @NotBlank(message = "La tipologia è obbligatoria")
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