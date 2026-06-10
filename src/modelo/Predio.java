package modelo;

public class Predio {

    private String npn;
    private String municipio;
    private String direccion;
    private String ficha;

    public Predio(String npn, String municipio, String direccion, String ficha) {
        this.npn = npn;
        this.municipio = municipio;
        this.direccion = direccion;
        this.ficha = ficha;
    }

    public String getNpn() {
        return npn;
    }

    public void setNpn(String npn) {
        this.npn = npn;
    }

    public String getMunicipio() {
        return municipio;
    }

    public void setMunicipio(String municipio) {
        this.municipio = municipio;
    }

    public String getDireccion() {
        return direccion;
    }

    public void setDireccion(String direccion) {
        this.direccion = direccion;
    }

    public String getFicha() {
        return ficha;
    }

    public void setFicha(String ficha) {
        this.ficha = ficha;
    }
}
