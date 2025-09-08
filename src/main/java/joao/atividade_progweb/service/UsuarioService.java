package joao.atividade_progweb.service;

import joao.atividade_progweb.dto.response.UsuarioResponseDTO;
import joao.atividade_progweb.dto.request.UsuarioRequestDTO;
import joao.atividade_progweb.entity.Usuario;
import joao.atividade_progweb.repository.UsuarioRepository;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
public class UsuarioService {

    private final UsuarioRepository usuarioRepository;
    private final ModelMapper modelMapper;

    public UsuarioService(UsuarioRepository usuarioRepository, ModelMapper modelMapper) {
        this.usuarioRepository = usuarioRepository;
        this.modelMapper = modelMapper;
    }

    public List<Usuario> listarTodos() {
        return usuarioRepository.findAll();
    }

    public Usuario listarUsuarioPorId(int idUsuario){
        return this.usuarioRepository.findById(idUsuario).orElse(null);
    }

    public UsuarioResponseDTO salvar(UsuarioRequestDTO usuarioRequestDTO) {
        Usuario usuario = modelMapper.map(usuarioRequestDTO, Usuario.class);

        usuario.setStatus(1);
        usuario.setLogDataCriacao(LocalDateTime.now());
        usuario.setLogResponsavelId(1);

        Usuario usuarioSalvo = usuarioRepository.save(usuario);

        return modelMapper.map(usuarioSalvo, UsuarioResponseDTO.class);
    }
}