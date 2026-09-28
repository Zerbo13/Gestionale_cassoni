package mattiazerbini.gestionale_cassoni.dto;

public class ViaggioRequest {

    private Long cassoneId;
    private Long partenzaId;
    private Long destinazioneId;
    private String note;

    public ViaggioRequest() {
    }

    public ViaggioRequest(Long cassoneId, Long partenzaId,
                          Long destinazioneId, String note) {
        this.cassoneId = cassoneId;
        this.partenzaId = partenzaId;
        this.destinazioneId = destinazioneId;
        this.note = note;
    }

    public Long getCassoneId() {
        return cassoneId;
    }

    public void setCassoneId(Long cassoneId) {
        this.cassoneId = cassoneId;
    }

    public Long getPartenzaId() {
        return partenzaId;
    }

    public void setPartenzaId(Long partenzaId) {
        this.partenzaId = partenzaId;
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