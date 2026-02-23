package com.forohub.domain.topico;

import jakarta.persistence.*;
import lombok.Setter;

import java.time.LocalDateTime;

@Entity
@Table(name = "topicos")
public class Topico {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Setter
    @Column(nullable = false, length = 120)
    private String titulo;

    @Setter
    @Column(nullable = false, length = 5000)
    private String mensaje;

    @Setter
    @Column(name = "fecha_creacion", nullable = false, updatable = false)
    private LocalDateTime fechaCreacion;

    @Setter
    @Column(nullable = false, length = 30)
    private String status;

    @Setter
    @Column(nullable = false, length = 120)
    private String autor;

    @Setter
    @Column(nullable = false, length = 120)
    private String curso;

    // Constructor vacío requerido por JPA
    public Topico() {
    }

    // Constructor opcional (más profesional)
    public Topico(String titulo, String mensaje, String autor, String curso, String status) {
        this.titulo = titulo;
        this.mensaje = mensaje;
        this.autor = autor;
        this.curso = curso;
        this.status = status;
    }

    @PrePersist
    protected void onCreate() {
        if (fechaCreacion == null) {
            fechaCreacion = LocalDateTime.now();
        }
        if (status == null || status.isBlank()) {
            status = "ABIERTO";
        }
    }

    // ===== Getters & Setters =====

    public Long getId() {
        return id;
    }

    public String getTitulo() {
        return titulo;
    }

    public String getMensaje() {
        return mensaje;
    }

    public LocalDateTime getFechaCreacion() {
        return fechaCreacion;
    }

    public String getStatus() {
        return status;
    }

    public String getAutor() {
        return autor;
    }

    public String getCurso() {
        return curso;
    }

}