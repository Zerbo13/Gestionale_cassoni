package mattiazerbini.gestionale_cassoni.enties;

import jakarta.persistence.*;

@Entity
@Table(name = "luoghi")
public class Luogo {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String nome;

    private String indirizzo;

    private String tipologia;

    private Boolean attivo=true;

    public Luogo(String nome, String indirizzo, String tipologia, Boolean attivo) {
        this.nome = nome;
        this.indirizzo = indirizzo;
        this.tipologia = tipologia;
        this.attivo = attivo;
    }

    public Luogo() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getIndirizzo() {
        return indirizzo;
    }

    public void setIndirizzo(String indirizzo) {
        this.indirizzo = indirizzo;
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
