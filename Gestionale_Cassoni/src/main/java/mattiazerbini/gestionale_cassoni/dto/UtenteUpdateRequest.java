package mattiazerbini.gestionale_cassoni.dto;

import jakarta.validation.constraints.NotBlank;

public class UtenteUpdateRequest {

    @NotBlank(message = "Il nome è obbligatorio")
    private String nome;

    @NotBlank(message = "Il cognome è obbligatorio")
    private String cognome;

    @NotBlank(message = "Il nickname è obbligatorio")
    private String nickname;

    @NotBlank(message = "Il ruolo è obbligatorio")
    private String ruolo;

    private Long mezzoId;

    public UtenteUpdateRequest() {
    }

    public UtenteUpdateRequest(String nome, String cognome, String nickname, String ruolo, Long mezzoId) {

        this.nome = nome;
        this.cognome = cognome;
        this.nickname = nickname;
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