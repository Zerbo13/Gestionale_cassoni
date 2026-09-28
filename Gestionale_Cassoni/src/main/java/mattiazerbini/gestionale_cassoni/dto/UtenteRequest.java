package mattiazerbini.gestionale_cassoni.dto;

public class UtenteRequest {

    private String nome;
    private String cognome;
    private String nickname;
    private String password;
    private String ruolo;
    private Long mezzoId;

    public UtenteRequest() {
    }

    public UtenteRequest(String nome, String cognome, String nickname,
                         String password, String ruolo, Long mezzoId) {
        this.nome = nome;
        this.cognome = cognome;
        this.nickname = nickname;
        this.password = password;
        this.ruolo = ruolo;
        this.mezzoId = mezzoId;
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

    public String getNickname() {
        return nickname;
    }

    public void setNickname(String nickname) {
        this.nickname = nickname;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getRuolo() {
        return ruolo;
    }

    public void setRuolo(String ruolo) {
        this.ruolo = ruolo;
    }

    public Long getMezzoId() {
        return mezzoId;
    }

    public void setMezzoId(Long mezzoId) {
        this.mezzoId = mezzoId;
    }
}