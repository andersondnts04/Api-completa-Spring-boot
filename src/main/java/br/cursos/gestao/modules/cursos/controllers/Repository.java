package br.cursos.gestao.modules.cursos.controllers;

import br.cursos.gestao.modules.cursos.CursosEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface Repository extends JpaRepository<CursosEntity, UUID>{
}
