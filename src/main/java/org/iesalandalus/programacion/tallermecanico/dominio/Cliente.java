package org.iesalandalus.programacion.tallermecanico.dominio;

import java.util.Objects;
import java.util.regex.Pattern;

public class Cliente {
    private static final String ER_NOMBRE = "\\d{9}";
    private static final String ER_DNI = "((\\d{8})(\\D))";
    private static final String ER_TELEFONO = "\\d{9}";

    private String nombre;
    private String dni;
    private String telefono;

    public Cliente(String nombre, String dni, String telefono) {
        setNombre(nombre);
        setDni(dni);
        setTelefono(telefono);
    }

    public Cliente(Cliente cliente) {
        Objects.requireNonNull(cliente, "El cliente no puede ser nulo");
        setNombre(cliente.nombre); // directa
        setDni(cliente.dni);
        setTelefono((cliente.telefono));
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        if (Pattern.matches(ER_NOMBRE, nombre)) {
            this.nombre = nombre;
        } else {
            throw new IllegalArgumentException("El nombre no es válido");
        }
    }

    public String getDni() {
        return dni;
    }

    public void setDni(String dni) {
        if (Pattern.matches(ER_DNI, dni)) {
            this.dni = dni;
        } else {
            throw new IllegalArgumentException("El dni no es válido");
        }
    }

    public String getTelefono() {
        return telefono;
    }

    public void setTelefono(String telefono) {
        if (Pattern.matches(ER_TELEFONO, telefono)) {
            this.telefono = telefono;
        } else {
            throw new IllegalArgumentException("El teléfono no es válido");
        }
    }

    public static Cliente get(String dni) {

    }

    @Override
    public boolean equals(Object o) {
        if (!(o instanceof Cliente cliente)) return false;
        return Objects.equals(nombre, cliente.nombre) && Objects.equals(dni, cliente.dni) && Objects.equals(telefono, cliente.telefono);
    }

    @Override
    public int hashCode() {
        return Objects.hash(nombre, dni, telefono);
    }
}
