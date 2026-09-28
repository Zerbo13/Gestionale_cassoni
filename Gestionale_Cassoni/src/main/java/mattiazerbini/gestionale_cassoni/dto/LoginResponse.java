package mattiazerbini.gestionale_cassoni.dto;

public class LoginResponse {

    private String token;
    private String nome;
    private String cognome;
    private String ruolo;

    public LoginResponse() {
    }

    public LoginResponse(String token, String nome, String cognome, String ruolo) {
        this.token = token;
        this.nome = nome;
        this.cognome = cognome;
        this.ruolo = ruolo;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getCognome() {
        return cognome;
    }

    public void setCognome(String cognome) {
        this.cognome = cognome;
    }

    public String getRuolo() {
        return ruolo;
    }

    public void setRuolo(String ruolo) {
        this.ruolo = ruolo;
    }
}