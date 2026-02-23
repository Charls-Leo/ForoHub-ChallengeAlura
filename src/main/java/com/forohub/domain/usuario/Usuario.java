package com.forohub.domain.usuario;

import jakarta.persistence.*;
import lombok.Setter;

@Entity
@Table(name = "usuarios")
public class Usuario {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Setter
    @Column(nullable = false, length = 120)
    private String nombre;

    @Setter
    @Column(nullable = false, unique = true, length = 160)
    private String email;

    @Setter
    @Column(nullable = false, length = 255)
    private String password;

    // Ej: "ROLE_USER", "ROLE_ADMIN"
    @Setter
    @Column(nullable = false, length = 50)
    private String role = "ROLE_USER";

    public Usuario() {}

    public Usuario(String nombre, String email, String password, String role) {
        this.nombre = nombre;
        this.email = email;
        this.password = password;
        this.role = role == null || role.isBlank() ? "ROLE_USER" : role;
    }

    // ===== Getters & Setters =====

    public Long getId() {
        return id;
    }

    public String getNombre() {
        return nombre;
    }

    public String getEmail() {
        return email;
    }

    public String getPassword() {
        return password;
    }

    public String getRole() {
        return role;
    }

}