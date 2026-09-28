package mattiazerbini.gestionale_cassoni.entities;

import jakarta.persistence.*;

@Entity
@Table(name="mezzi")
public class Mezzo {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, unique = true)
    private String targa;

    private String modello;

    private String tipologia;

    private Boolean attivo=true;

    public Mezzo(String targa, String modello, String tipologia, Boolean attivo) {
        this.targa = targa;
        this.modello = modello;
        this.tipologia = tipologia;
        this.attivo = attivo;
    }

    public Mezzo(){
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
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

    public String getTipologia() {
        return tipologia;
    }

    public void setTipologia(String tipologia) {
        this.tipologia = tipologia;
    }

    public Boolean getAttivo() {
        return attivo;
    }

    public void setAttivo(Boolean attivo) {
        this.attivo = attivo;
    }
}
