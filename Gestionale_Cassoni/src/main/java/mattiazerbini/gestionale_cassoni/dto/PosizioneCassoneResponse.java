package mattiazerbini.gestionale_cassoni.dto;

public class PosizioneCassoneResponse {

    private String cassone;
    private String posizione;
    private long giorniFermo;

    private PosizioneCassoneResponse(){

    }

    public PosizioneCassoneResponse(String cassone, String posizione, long giorniFermo){
        this.cassone = cassone;
        this.posizione = posizione;
        this.giorniFermo = giorniFermo;
    }

    public String getCassone() {
        return cassone;
    }

    public void setCassone(String cassone) {
        this.cassone = cassone;
    }

    public String getPosizione() {
        return posizione;
    }

    public void setPosizione(String posizione) {
        this.posizione = posizione;
    }

    public long getGiorniFermo() {
        return giorniFermo;
    }

    public void setGiorniFermo(long giorniFermo) {
        this.giorniFermo = giorniFermo;
    }
}
