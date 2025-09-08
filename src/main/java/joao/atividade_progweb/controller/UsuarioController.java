package joao.atividade_progweb.controller;

import joao.atividade_progweb.dto.response.UsuarioResponseDTO;
import joao.atividade_progweb.dto.request.UsuarioRequestDTO;
import joao.atividade_progweb.entity.Usuario;
import joao.atividade_progweb.service.UsuarioService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import jakarta.validation.Valid;

import java.util.List;

@RestController
@RequestMapping("/api/usuarios")
public class UsuarioController {

    private final UsuarioService usuarioService;

    public UsuarioController(UsuarioService usuarioService) {
        this.usuarioService = usuarioService;
    }

    @GetMapping("/listar")
    public List<Usuario> listarTodos() {
        return usuarioService.listarTodos();
    }

    @GetMapping("/{id}")
    public Usuario listarPorId(@PathVariable int id) {
        return usuarioService.listarUsuarioPorId(id);
    }

    @PostMapping("/criar")
    public UsuarioResponseDTO criar(@Valid @RequestBody UsuarioRequestDTO usuarioRequestDTO) {
        return usuarioService.salvar(usuarioRequestDTO);
    }

    @PutMapping("/atualizar/{id}")
    public UsuarioResponseDTO atualizar(@PathVariable int id, @RequestBody UsuarioRequestDTO usuarioRequestDTO) {
        return usuarioService.atualizar(id, usuarioRequestDTO);
    }

    @DeleteMapping("/deletar/{id}")
    public ResponseEntity<Void> deletar(@PathVariable int id) {
        usuarioService.deletar(id);
        return ResponseEntity.noContent().build();
    }
}