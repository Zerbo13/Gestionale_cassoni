package mattiazerbini.gestionale_cassoni.dto;

import mattiazerbini.gestionale_cassoni.entities.StatoViaggio;

import java.time.LocalDateTime;

public class ViaggioResponse {

    private Long id;
    private String operaio;
    private String mezzo;
    private String cassone;
    private String partenza;
    private String destinazione;
    private LocalDateTime dataOraInizio;
    private LocalDateTime dataOraFine;
    private StatoViaggio stato;
    private String note;
    private String fotoUrl;

    public ViaggioResponse() {
    }

    public ViaggioResponse(
            Long id,
            String operaio,
            String mezzo,
            String cassone,
            String partenza,
            String destinazione,
            LocalDateTime dataOraInizio,
            LocalDateTime dataOraFine,
            StatoViaggio stato,
            String note,
            String fotoUrl
    ) {
        this.id = id;
        this.operaio = operaio;
        this.mezzo = mezzo;
        this.cassone = cassone;
        this.partenza = partenza;
        this.destinazione = destinazione;
        this.dataOraInizio = dataOraInizio;
        this.dataOraFine = dataOraFine;
        this.stato = stato;
        this.note = note;
        this.fotoUrl = fotoUrl;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getOperaio() {
        return operaio;
    }

    public void setOperaio(String operaio) {
        this.operaio = operaio;
    }

    public String getMezzo() {
        return mezzo;
    }

    public void setMezzo(String mezzo) {
        this.mezzo = mezzo;
    }

    public String getCassone() {
        return cassone;
    }

    public void setCassone(String cassone) {
        this.cassone = cassone;
    }

    public String getPartenza() {
        return partenza;
    }

    public void setPartenza(String partenza) {
        this.partenza = partenza;
    }

    public String getDestinazione() {
        return destinazione;
    }

    public void setDestinazione(String destinazione) {
        this.destinazione = destinazione;
    }

    public LocalDateTime getDataOraInizio() {
        return dataOraInizio;
    }

    public void setDataOraInizio(LocalDateTime dataOraInizio) {
        this.dataOraInizio = dataOraInizio;
    }

    public LocalDateTime getDataOraFine() {
        return dataOraFine;
    }

    public void setDataOraFine(LocalDateTime dataOraFine) {
        this.dataOraFine = dataOraFine;
    }

    public StatoViaggio getStato() {
        return stato;
    }

    public void setStato(StatoViaggio stato) {
        this.stato = stato;
    }

    public String getNote() {
        return note;
    }

    public void setNote(String note) {
        this.note = note;
    }

    public String getFotoUrl() {
        return fotoUrl;
    }

    public void setFotoUrl(String fotoUrl) {
        this.fotoUrl = fotoUrl;
    }
}