package com.forohub.controller;

import com.forohub.domain.topico.Topico;
import com.forohub.repository.TopicoRepository;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotBlank;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.net.URI;
import java.time.LocalDateTime;
import java.util.List;

import static org.springframework.http.HttpStatus.CONFLICT;

@RestController
@RequestMapping("/topicos")
public class TopicoController {

    private final TopicoRepository repository;

    public TopicoController(TopicoRepository repository) {
        this.repository = repository;
    }

    // POST - Crear tópico
    @PostMapping
    public ResponseEntity<TopicoResponse> crearTopico(@RequestBody @Valid CreateTopicoRequest req) {

        if (repository.existsByTituloAndMensaje(req.titulo(), req.mensaje())) {
            throw new ResponseStatusException(CONFLICT, "Ya existe un tópico con el mismo título y mensaje");
        }

        Topico nuevo = new Topico();
        nuevo.setTitulo(req.titulo());
        nuevo.setMensaje(req.mensaje());
        nuevo.setAutor(req.autor());
        nuevo.setCurso(req.curso());
        nuevo.setStatus(req.status() == null || req.status().isBlank() ? "ABIERTO" : req.status());

        Topico guardado = repository.save(nuevo);

        return ResponseEntity
                .created(URI.create("/topicos/" + guardado.getId()))
                .body(TopicoResponse.from(guardado));
    }

    // GET - Listar todos
    @GetMapping
    public ResponseEntity<List<TopicoResponse>> listarTopicos() {
        List<TopicoResponse> lista = repository.findAll()
                .stream()
                .map(TopicoResponse::from)
                .toList();

        return ResponseEntity.ok(lista);
    }

    // GET - Buscar por id
    @GetMapping("/{id}")
    public ResponseEntity<TopicoResponse> buscarPorId(@PathVariable Long id) {
        return repository.findById(id)
                .map(t -> ResponseEntity.ok(TopicoResponse.from(t)))
                .orElse(ResponseEntity.notFound().build());
    }

    // PUT - Actualizar tópico
    @PutMapping("/{id}")
    public ResponseEntity<TopicoResponse> actualizarTopico(
            @PathVariable Long id,
            @RequestBody @Valid UpdateTopicoRequest req) {

        return repository.findById(id)
                .map(topico -> {
                    topico.setTitulo(req.titulo());
                    topico.setMensaje(req.mensaje());
                    topico.setStatus(req.status());
                    topico.setAutor(req.autor());
                    topico.setCurso(req.curso());

                    Topico actualizado = repository.save(topico);
                    return ResponseEntity.ok(TopicoResponse.from(actualizado));
                })
                .orElse(ResponseEntity.notFound().build());
    }

    // DELETE - Eliminar tópico
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> eliminarTopico(@PathVariable Long id) {
        if (!repository.existsById(id)) {
            return ResponseEntity.notFound().build();
        }
        repository.deleteById(id);
        return ResponseEntity.noContent().build();
    }

    // ===== DTOs internos (para no crear más archivos por ahora) =====

    public record CreateTopicoRequest(
            @NotBlank String titulo,
            @NotBlank String mensaje,
            @NotBlank String autor,
            @NotBlank String curso,
            String status
    ) {}

    public record UpdateTopicoRequest(
            @NotBlank String titulo,
            @NotBlank String mensaje,
            @NotBlank String status,
            @NotBlank String autor,
            @NotBlank String curso
    ) {}

    public record TopicoResponse(
            Long id,
            String titulo,
            String mensaje,
            LocalDateTime fechaCreacion,
            String status,
            String autor,
            String curso
    ) {
        static TopicoResponse from(Topico t) {
            return new TopicoResponse(
                    t.getId(),
                    t.getTitulo(),
                    t.getMensaje(),
                    t.getFechaCreacion(),
                    t.getStatus(),
                    t.getAutor(),
                    t.getCurso()
            );
        }
    }
}