package mattiazerbini.gestionale_cassoni.dto;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

public class ViaggioRequest {

    @NotNull(message = "Il mezzo è obbligatorio")
    private Long mezzoId;

    @NotNull(message = "Il cassone è obbligatorio")
    private Long cassoneId;

    @NotNull(message = "La destinazione è obbligatoria")
    private Long destinazioneId;

    @Size(max = 500, message = "Le note non possono superare 500 caratteri")
    private String note;

    public ViaggioRequest() {
    }

    public ViaggioRequest(
            Long mezzoId,
            Long cassoneId,
            Long destinazioneId,
            String note
    ) {
        this.mezzoId = mezzoId;
        this.cassoneId = cassoneId;
        this.destinazioneId = destinazioneId;
        this.note = note;
    }

    public Long getMezzoId() {
        return mezzoId;
    }

    public void setMezzoId(Long mezzoId) {
        this.mezzoId = mezzoId;
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