package mattiazerbini.gestionale_cassoni.dto;

public class CassoneRequest {

    private String codiceCassone;
    private String colore;
    private String misura;
    private String tipologia;
    private Long posizioneInizialeId;

    public CassoneRequest() {
    }

    public CassoneRequest(String codiceCassone, String colore,
                          String misura, String tipologia,
                          Long posizioneInizialeId) {
        this.codiceCassone = codiceCassone;
        this.colore = colore;
        this.misura = misura;
        this.tipologia = tipologia;
        this.posizioneInizialeId = posizioneInizialeId;
    }

    public String getCodiceCassone() {
        return codiceCassone;
    }

    public void setCodiceCassone(String codiceCassone) {
        this.codiceCassone = codiceCassone;
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

    public String getTipologia() {
        return tipologia;
    }

    public void setTipologia(String tipologia) {
        this.tipologia = tipologia;
    }

    public Long getPosizioneInizialeId() {
        return posizioneInizialeId;
    }

    public void setPosizioneInizialeId(Long posizioneInizialeId) {
        this.posizioneInizialeId = posizioneInizialeId;
    }
}