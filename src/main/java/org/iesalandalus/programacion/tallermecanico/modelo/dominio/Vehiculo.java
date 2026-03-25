package org.iesalandalus.programacion.tallermecanico.modelo.dominio;

public record Vehiculo(String marca, String modelo, String matricula) {
    private static final String ER_MARCA = "h";
    private static final String ER_MATRICULA = "h";

    public Vehiculo(String marca, String modelo, String matricula) {
        this.marca = marca;
        this.modelo = modelo;
        this.matricula = matricula;
    }

    private void validarMarca(String marca) {

    }

    private void validarModelo(String modelo) {

    }

    private void validarMatricula(String matricula) {

    }
}
