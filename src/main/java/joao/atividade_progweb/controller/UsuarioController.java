package joao.atividade_progweb.controller;

import joao.atividade_progweb.dto.response.UsuarioResponseDTO;
import joao.atividade_progweb.dto.request.UsuarioRequestDTO;
import joao.atividade_progweb.entity.Usuario;
import joao.atividade_progweb.service.UsuarioService;
import org.springframework.web.bind.annotation.*;

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

    // MÉTODO ATUALIZADO
    @PostMapping("/criar")
    public UsuarioResponseDTO criar(@RequestBody UsuarioRequestDTO usuarioRequestDTO) {
        return usuarioService.salvar(usuarioRequestDTO);
    }
}