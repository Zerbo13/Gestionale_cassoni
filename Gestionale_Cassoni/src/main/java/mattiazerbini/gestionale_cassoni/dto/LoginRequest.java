package mattiazerbini.gestionale_cassoni.dto;

import jakarta.validation.constraints.NotBlank;

public class LoginRequest {

    @NotBlank(message = "Il nickname è obbligatorio")
    private String nickname;

    @NotBlank(message = "La password è obbligatoria")
    private String password;

    public LoginRequest() {
    }

    public LoginRequest(String nickname, String password) {
        this.nickname = nickname;
        this.password = password;
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
}