package mattiazerbini.gestionale_cassoni.dto;

public class MezzoRequest {

    private String targa;
    private String modello;
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