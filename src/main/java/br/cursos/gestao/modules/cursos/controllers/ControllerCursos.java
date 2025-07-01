package br.cursos.gestao.modules.cursos.controllers;

import jakarta.validation.Valid;
import br.cursos.gestao.modules.cursos.CursosEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.UUID;

@RestController
@RequestMapping("/")
public class ControllerCursos {
    @Autowired
    private Repository repository;

    @PostMapping("/curso")
    public CursosEntity create(@RequestBody @Valid CursosEntity cursosEntity ){
        System.out.println("curso: "+cursosEntity.getName() );
        return repository.save(cursosEntity);
    }

    @GetMapping("/curso")
        public List<CursosEntity> getAll(){
            return repository.findAll();
        }
    @DeleteMapping("/curso/{id}")
    public ResponseEntity<String> delet(@PathVariable UUID id){
       Optional<CursosEntity> curso = repository.findById(id);
       if(curso.isPresent()){
           repository.deleteById(id);
          return ResponseEntity.ok("curso deletado com sucesso");
       }else{
           return ResponseEntity.notFound().build();
       }
    }

    @PutMapping("/curso/{id}")
    public ResponseEntity<CursosEntity> update(@PathVariable UUID id, @RequestBody @Valid CursosEntity atualizado) {
        Optional<CursosEntity> existente = repository.findById(id);
        if (existente.isPresent()) {
            CursosEntity curso = existente.get();
            curso.setName(atualizado.getName());
            return ResponseEntity.ok(repository.save(curso));
        } else {
            return ResponseEntity.notFound().build();
        }
    }
    @PatchMapping("/curso/{id}")
    public ResponseEntity<CursosEntity> patchUpdate(@PathVariable UUID id, @RequestBody Map<String, Object> updates) {
        Optional<CursosEntity> existente = repository.findById(id);
        if (existente.isEmpty()) {
            return ResponseEntity.notFound().build();
        }

        CursosEntity curso = existente.get();

        updates.forEach((chave, valor) -> {
            switch (chave) {
                case "name":
                    curso.setName((String) valor);
                case "category":
                    curso.setCategory((String) valor);
                    break;
                default:
                    throw new IllegalArgumentException("Campo '" + chave + "' não pode ser atualizado.");
            }
        });

        CursosEntity atualizado = repository.save(curso);
        return ResponseEntity.ok(atualizado);
    }

}
