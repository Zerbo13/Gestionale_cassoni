package mattiazerbini.gestionale_cassoni.dto;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

public class ViaggioRequest {

    @NotNull(message = "Il cassone è obbligatorio")
    private Long cassoneId;

    @NotNull(message = "La destinazione è obbligatoria")
    private Long destinazioneId;

    @Size(
            max = 200,
            message = "Le note non possono superare 200 caratteri"
    )
    private String note;

    public ViaggioRequest() {
    }

    public ViaggioRequest(Long cassoneId, Long destinazioneId, String note) {
        this.cassoneId = cassoneId;
        this.destinazioneId = destinazioneId;
        this.note = note;
    }

    public Long getCassoneId() {
        return cassoneId;
    }

    public void setCassoneId(Long cassoneId) {
        this.cassoneId = cassoneId;
    }

    public Long getDestinazioneId() {
        return destinazioneId;
    }

    public void setDestinazioneId(Long destinazioneId) {
        this.destinazioneId = destinazioneId;
    }

    public String getNote() {
        return note;
    }

    public void setNote(String note) {
        this.note = note;
    }
}