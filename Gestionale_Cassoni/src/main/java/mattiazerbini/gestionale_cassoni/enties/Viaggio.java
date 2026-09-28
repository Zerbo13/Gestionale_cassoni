package mattiazerbini.gestionale_cassoni.entities;

import jakarta.persistence.*;
import mattiazerbini.gestionale_cassoni.enties.*;

import java.time.LocalDateTime;

@Entity
@Table(name = "viaggi")
public class Viaggio {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "utente_id", nullable = false)
    private Utente utente;

    @ManyToOne
    @JoinColumn(name = "mezzo_id", nullable = false)
    private Mezzo mezzo;

    @ManyToOne
    @JoinColumn(name = "cassone_id", nullable = false)
    private Cassone cassone;

    @ManyToOne
    @JoinColumn(name = "partenza_id", nullable = false)
    private Luogo partenza;

    @ManyToOne
    @JoinColumn(name = "destinazione_id", nullable = false)
    private Luogo destinazione;

    @Column(name = "data_ora_inizio", nullable = false)
    private LocalDateTime dataOraInizio;

    @Column(name = "data_ora_fine")
    private LocalDateTime dataOraFine;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private StatoViaggio stato;

    private String note;

    public Viaggio() {
    }

    public Viaggio(
            Utente utente,
            Mezzo mezzo,
            Cassone cassone,
            Luogo partenza,
            Luogo destinazione,
            LocalDateTime dataOraInizio,
            LocalDateTime dataOraFine,
            StatoViaggio stato,
            String note
    ) {
        this.utente = utente;
        this.mezzo = mezzo;
        this.cassone = cassone;
        this.partenza = partenza;
        this.destinazione = destinazione;
        this.dataOraInizio = dataOraInizio;
        this.dataOraFine = dataOraFine;
        this.stato = stato;
        this.note = note;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Utente getUtente() {
        return utente;
    }

    public void setUtente(Utente utente) {
        this.utente = utente;
    }

    public Mezzo getMezzo() {
        return mezzo;
    }

    public void setMezzo(Mezzo mezzo) {
        this.mezzo = mezzo;
    }

    public Cassone getCassone() {
        return cassone;
    }

    public void setCassone(Cassone cassone) {
        this.cassone = cassone;
    }

    public Luogo getPartenza() {
        return partenza;
    }

    public void setPartenza(Luogo partenza) {
        this.partenza = partenza;
    }

    public Luogo getDestinazione() {
        return destinazione;
    }

    public void setDestinazione(Luogo destinazione) {
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
}