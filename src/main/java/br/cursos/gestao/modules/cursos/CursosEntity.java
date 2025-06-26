package br.cursos.gestao.modules.cursos;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import org.hibernate.annotations.GenericGenerator;

import java.util.UUID;

@Entity
@Table(name = "cursos")
public class CursosEntity {

    @Id
    @GeneratedValue(generator = "UUID")
    @GenericGenerator(name = "UUID", strategy = "org.hibernate.id.UUIDGenerator")
    @Column(name = "id", updatable = false, nullable = false)
    private UUID id;

    @NotBlank(message = "O nome do curso é obrigatório")
    @Column(name = "name", nullable = false)
    private String name;

    @NotBlank(message = "category não pode ser nulo")
    @Column(name = "category", nullable = false)
    private String category;

    @NotNull(message = "defina active ele é true ou false?")
    @Column(name = "active", nullable = false)
    private boolean active;

    // Construtores
    public CursosEntity() {
    }

    public CursosEntity(String name, String category, boolean active) {
        this.name = name;
        this.category = category;
        this.active = active;
    }

    // Getters e Setters
    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public boolean isActive() {
        return active;
    }

    public void setActive(boolean active) {
        this.active = active;
    }

    // toString, equals e hashCode (opcional, mas recomendado)

    @Override
    public String toString() {
        return "CursosEntity{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", category='" + category + '\'' +
                ", active=" + active +
                '}';
    }
}
