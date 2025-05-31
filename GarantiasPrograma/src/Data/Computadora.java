package Data;

import java.time.LocalDate;

public class Computadora {
    private String serviceTag;
    private String descripcionProblema;
    private LocalDate fechaRecepcion;
    private String nombreCliente;
    private String correoCliente;
    private String telefonoCliente;

    public Computadora(String serviceTag, String descripcionProblema, LocalDate fechaRecepcion,
                       String nombreCliente, String correoCliente, String telefonoCliente) {
        this.serviceTag = serviceTag;
        this.descripcionProblema = descripcionProblema;
        this.fechaRecepcion = fechaRecepcion;
        this.nombreCliente = nombreCliente;
        this.correoCliente = correoCliente;
        this.telefonoCliente = telefonoCliente;
    }

    public String getServiceTag() {
        return serviceTag;
    }

    public String getDescripcionProblema() {
        return descripcionProblema;
    }

    public LocalDate getFechaRecepcion() {
        return fechaRecepcion;
    }

    public String getNombreCliente() {
        return nombreCliente;
    }

    public String getCorreoCliente() {
        return correoCliente;
    }

    public String getTelefonoCliente() {
        return telefonoCliente;
    }
}