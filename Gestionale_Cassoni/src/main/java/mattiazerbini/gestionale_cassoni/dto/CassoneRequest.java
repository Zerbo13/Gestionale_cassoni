package mattiazerbini.gestionale_cassoni.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public class CassoneRequest {

    @NotBlank(message = "Il codice cassone è obbligatorio")
    private String codiceCassone;

    @NotBlank(message = "Il colore è obbligatorio")
    private String colore;

    @NotBlank(message = "La misura è obbligatoria")
    private String misura;

    @NotBlank(message = "La tipologia è obbligatoria")
    private String tipologia;

    @NotBlank(message = "La capacità è obbligatoria")
    private String capacità;

    @NotNull(message = "La posizione iniziale è obbligatoria")
    private Long posizioneInizialeId;

    public CassoneRequest() {
    }

    public CassoneRequest(String codiceCassone, String colore, String misura, String tipologia, String capacità, Long posizioneInizialeId) {
        this.codiceCassone = codiceCassone;
        this.colore = colore;
        this.misura = misura;
        this.tipologia = tipologia;
        this.capacità = capacità;
        this.posizioneInizialeId = posizioneInizialeId;
    }

    public String getCodiceCassone() {
        return codiceCassone;
    }

    public void setCodiceCassone(String codiceCassone) {
        this.codiceCassone = codiceCassone;
    }

    public String getColore() {
        return colore;
    }

    public void setColore(String colore) {
        this.colore = colore;
    }

    public String getMisura() {
        return misura;
    }

    public void setMisura(String misura) {
        this.misura = misura;
    }

    public String getTipologia() {
        return tipologia;
    }

    public void setTipologia(String tipologia) {
        this.tipologia = tipologia;
    }

    public String getCapacità() {
        return capacità;
    }

    public void setCapacità(String capacità) {
        this.capacità = capacità;
    }

    public Long getPosizioneInizialeId() {
        return posizioneInizialeId;
    }

    public void setPosizioneInizialeId(Long posizioneInizialeId) {
        this.posizioneInizialeId = posizioneInizialeId;
    }
}