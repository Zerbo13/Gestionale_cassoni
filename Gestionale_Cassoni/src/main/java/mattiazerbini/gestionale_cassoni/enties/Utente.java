package mattiazerbini.gestionale_cassoni.enties;
import jakarta.persistence.*;

@Entity
@Table(name = "utenti")
public class Utente {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String nome;

    private String cognome;

    @Column(nullable = false, unique = true)
    private String nickname;

    @Column(name = "password_hash", nullable = false)
    private String password;

    private String ruolo;

    private Boolean attivo = true;

    @ManyToOne
    @JoinColumn(name = "mezzo_id")
    private Mezzo mezzo;

    public Utente(){
    }

    public Utente(String nome, String cognome, String nickname,
                  String password, String ruolo,
                  Boolean attivo, Mezzo mezzo) {
        this.nome = nome;
        this.cognome = cognome;
        this.nickname = nickname;
        this.password = password;
        this.ruolo = ruolo;
        this.attivo = attivo;
        this.mezzo = mezzo;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getCognome() {
        return cognome;
    }

    public void setCognome(String cognome) {
        this.cognome = cognome;
    }

    public String getNickname() {
        return nickname;
    }

    public void setNickname(String nickname) {
        this.nickname = nickname;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getRuolo() {
        return ruolo;
    }

    public void setRuolo(String ruolo) {
        this.ruolo = ruolo;
    }

    public Mezzo getMezzo() {
        return mezzo;
    }

    public void setMezzo(Mezzo mezzo) {
        this.mezzo = mezzo;
    }

    public Boolean getAttivo() {
        return attivo;
    }

    public void setAttivo(Boolean attivo) {
        this.attivo = attivo;
    }

}
