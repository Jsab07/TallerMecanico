package org.iesalandalus.programacion.tallermecanico.dominio;

public record Vehiculo(String marca, String modelo, String matricula) {
    private static final String ER_MARCA = '';
    private static final String ER_MARCA = '';

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
