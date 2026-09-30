package mattiazerbini.gestionale_cassoni.dto;

import jakarta.validation.constraints.NotBlank;

public class MezzoRequest {

    @NotBlank(message = "La targa è obbligatoria")
    private String targa;

    @NotBlank(message = "Il modello è obbligatorio")
    private String modello;

    @NotBlank(message = "Il tipo è obbligatorio")
    private String tipo;

    public MezzoRequest() {
    }

    public MezzoRequest(String targa, String modello, String tipo) {
        this.targa = targa;
        this.modello = modello;
        this.tipo = tipo;
    }

    public String getTarga() {
        return targa;
    }

    public void setTarga(String targa) {
        this.targa = targa;
    }

    public String getModello() {
        return modello;
    }

    public void setModello(String modello) {
        this.modello = modello;
    }

    public String getTipo() {
        return tipo;
    }

    public void setTipo(String tipo) {
        this.tipo = tipo;
    }
}