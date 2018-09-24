package com.example.enrique.solucion_ex_unidad_1;

public class modelItemnewslist {
    private String hora;
    private String titulo;
    private String urlImagen;
    private String link;
    private byte carruselImage;

    public modelItemnewslist() {
    }

    public modelItemnewslist(String hora, String titulo, String urlImagen, String link, byte carruselImage) {
        this.hora = hora;
        this.titulo = titulo;
        this.urlImagen = urlImagen;
        this.link = link;
        this.carruselImage = carruselImage;
    }

    public String getHora() {
        return hora;
    }

    public void setHora(String hora) {
        this.hora = hora;
    }

    public String getTitulo() {
        return titulo;
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    public String getImagen() {
        return urlImagen;
    }

    public void setImagen(String urlImagen) {
        this.urlImagen = urlImagen;
    }

    public String getLink() { return link; }

    public void setLink(String link) { this.link = link; }

    public byte getCarruselImage() { return carruselImage; }

    public void setCarruselImage(byte carruselImage) { this.carruselImage = carruselImage; }
}
