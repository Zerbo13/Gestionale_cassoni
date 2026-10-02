package mattiazerbini.gestionale_cassoni.entities;

import jakarta.persistence.*;

import java.time.LocalDateTime;

@Entity
@Table(name = "Cassoni")
public class Cassone {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(
            name = "codice_cassone",
            nullable = false,
            unique = true
    )
    private String codiceCassone;

    private String tipologia;

    private String colore;

    private String misura;

    private String capacità;

    private Boolean attivo = true;

    private LocalDateTime dataPosizioneIniziale;

    @ManyToOne
    @JoinColumn(name = "posizione_iniziale_id")
    private Luogo posizioneIniziale;

    public Cassone() {
    }

    public Cassone(
            String codiceCassone,
            String tipologia,
            String colore,
            String misura,
            Boolean attivo,
            String capacità,
            Luogo posizioneIniziale,
            LocalDateTime dataPosizioneIniziale
    ) {
        this.codiceCassone = codiceCassone;
        this.tipologia = tipologia;
        this.colore = colore;
        this.misura = misura;
        this.attivo = attivo;
        this.capacità = capacità;
        this.posizioneIniziale = posizioneIniziale;
        this.dataPosizioneIniziale = dataPosizioneIniziale;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getCodiceCassone() {
        return codiceCassone;
    }

    public void setCodiceCassone(String codiceCassone) {
        this.codiceCassone = codiceCassone;
    }

    public String getTipologia() {
        return tipologia;
    }

    public void setTipologia(String tipologia) {
        this.tipologia = tipologia;
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

    public String getCapacità() {
        return capacità;
    }

    public void setCapacità(String capacità) {
        this.capacità = capacità;
    }

    public Boolean getAttivo() {
        return attivo;
    }

    public void setAttivo(Boolean attivo) {
        this.attivo = attivo;
    }

    public Luogo getPosizioneIniziale() {
        return posizioneIniziale;
    }

    public void setPosizioneIniziale(Luogo posizioneIniziale) {
        this.posizioneIniziale = posizioneIniziale;
    }

    public LocalDateTime getDataPosizioneIniziale() {
        return dataPosizioneIniziale;
    }

    public void setDataPosizioneIniziale(
            LocalDateTime dataPosizioneIniziale
    ) {
        this.dataPosizioneIniziale = dataPosizioneIniziale;
    }
}