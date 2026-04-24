package org.iesalandalus.programacion.tallermecanico.modelo.negocio;

import org.iesalandalus.programacion.tallermecanico.modelo.TallerMecanicoExcepcion;
import org.iesalandalus.programacion.tallermecanico.modelo.dominio.Cliente;
import org.iesalandalus.programacion.tallermecanico.modelo.dominio.Trabajo;
import org.iesalandalus.programacion.tallermecanico.modelo.dominio.Vehiculo;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class Revisiones {
    private final List<Trabajo> coleccionRevisiones;

    public Revisiones() {
        coleccionRevisiones = new ArrayList<>();
    }

    public ArrayList<Trabajo> get() {
        return new ArrayList<>(coleccionRevisiones);
    }

    public List<Trabajo> get(Cliente cliente) {
        List<Trabajo> revisionesCliente = new ArrayList<>();
        for (Trabajo r : coleccionRevisiones) {
            if (r.getCliente().equals(cliente)) {
                revisionesCliente.add(r);
            }
        }
        return revisionesCliente;
    }

    public List<Trabajo> get(Vehiculo vehiculo) {
        List<Trabajo> revisionesVehiculo = new ArrayList<>();
        for (Trabajo r : coleccionRevisiones) {
            if (r.getVehiculo().equals(vehiculo)) {
                revisionesVehiculo.add(r);
            }
        }
        return revisionesVehiculo;
    }

    public void insertar(Trabajo revision) throws TallerMecanicoExcepcion {
        if (revision == null) {
            throw new NullPointerException("No se puede insertar una revisión nula.");
        }
        comprobarRevision(revision.getCliente(), revision.getVehiculo(), revision.getFechaInicio());
        Trabajo revisionExistente = buscar(revision);
        if (revisionExistente != null) {
            if (!revisionExistente.estaCerrada()) {
                throw new TallerMecanicoExcepcion("Ya existe una revisión igual.");
            } else {
                coleccionRevisiones.remove(revisionExistente);
            }
        }
        coleccionRevisiones.add(revision);
    }

    private void comprobarRevision(Cliente cliente, Vehiculo vehiculo, LocalDate fechaRevision) throws TallerMecanicoExcepcion {
        for (Trabajo r : coleccionRevisiones) {
            if (!r.estaCerrada()) {
                if (r.getCliente().equals(cliente)) {
                    throw new TallerMecanicoExcepcion("El cliente tiene otra revisión en curso.");
                }
                if (r.getVehiculo().equals(vehiculo)) {
                    throw new TallerMecanicoExcepcion("El vehículo está actualmente en revisión.");
                }
            }
            if (r.estaCerrada() && !r.getFechaFin().isBefore(fechaRevision)) {
                if (r.getCliente().equals(cliente)) {
                    throw new TallerMecanicoExcepcion("El cliente tiene una revisión posterior.");
                }
                if (r.getVehiculo().equals(vehiculo)) {
                    throw new TallerMecanicoExcepcion("El vehículo tiene una revisión posterior.");
                }
            }
        }
    }

    private Trabajo getRevision(Trabajo revision) throws TallerMecanicoExcepcion {
        if (revision == null) {
            throw new NullPointerException("No puedo operar sobre una revisión nula.");
        }
        Trabajo revisionExistente = buscar(revision);
        if (revisionExistente == null) {
            throw new TallerMecanicoExcepcion("No existe ninguna revisión igual.");
        }
        return revisionExistente;
    }

    public Trabajo anadirHoras(Trabajo revision, int horas) throws TallerMecanicoExcepcion {
        Trabajo revision1 = getRevision(revision);
        revision1.anadirHoras(horas);
        return revision1;
    }

    public Trabajo anadirPrecioMaterial(Trabajo revision, float precioMaterial) throws TallerMecanicoExcepcion {
        Trabajo revision1 = getRevision(revision);
        revision1.anadirPrecioMaterial(precioMaterial);
        return revision1;
    }

    public Trabajo cerrar(Trabajo revision, LocalDate fechaFin) throws TallerMecanicoExcepcion {
        Trabajo revision1 = getRevision(revision);
        revision1.cerrar(fechaFin);
        return revision1;
    }

    public Trabajo buscar(Trabajo revision) {
        if (revision == null) {
            throw new NullPointerException("No se puede buscar una revisión nula.");
        }
        for (Trabajo r : coleccionRevisiones) {
            if (r.equals(revision)) {
                return r;
            }
        }
        return null;
    }

    public void borrar(Trabajo revision) throws TallerMecanicoExcepcion {
        if (revision == null) {
            throw new NullPointerException("No se puede borrar una revisión nula.");
        }
        Trabajo revisionExistente = buscar(revision);
        if (revisionExistente == null) {
            throw new TallerMecanicoExcepcion("No existe ninguna revisión igual.");
        }
        coleccionRevisiones.remove(revisionExistente);
    }
}

