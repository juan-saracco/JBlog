package com.jblog.demo.controller;

import com.jblog.demo.model.Publicacion;
import com.jblog.demo.model.Usuario;
import com.jblog.demo.repository.UsuarioRepository;
import com.jblog.demo.service.PublicacionService;
import com.jblog.demo.service.UsuarioService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/usuarios")
public class UsuarioController {

    @Autowired
    private UsuarioService usuarioService;
    @Autowired
    private PublicacionService publicacionService;
    @Autowired
    private UsuarioRepository usuarioRepository;

    // bien!
    @GetMapping
    public ResponseEntity<List<Usuario>> obenerTodos() {
        List<Usuario> usuarios = usuarioService.obtenerTodos();
        return ResponseEntity.ok(usuarios);
    }

    // bien!
    @GetMapping("/{id}/publicaciones")
    public ResponseEntity<Optional<Publicacion>> obtenerPublicacionesDeUsuario(@PathVariable Long id) {
        Optional<Usuario> usuario = usuarioService.buscarPorId(id);

        if (usuario.isPresent()) {
            Optional<Publicacion> publicaciones = publicacionService.obtenerPublicacionesPorAutor(usuario.get());
            return ResponseEntity.ok(publicaciones);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    // bien!
    @GetMapping("/{id}")
    public ResponseEntity<Usuario> obtenerPorId(@PathVariable Long id) {
        Optional<Usuario> usuario = usuarioService.buscarPorId(id);
        return usuario.map(ResponseEntity::ok).orElse(ResponseEntity.notFound().build());
    }

    // bien!
    @PostMapping
    public ResponseEntity<Usuario> crearUsuario(@RequestBody Usuario usuario) {
        Usuario nuevoUsuario = usuarioService.guardarUsuario(usuario);
        return ResponseEntity.ok(nuevoUsuario);
    }

    // bien!
    @PostMapping("/publicaciones/{id}")
    public ResponseEntity<Publicacion> crearPublicacionParaUsuario(@PathVariable Long id,
            @RequestBody Publicacion publicacion) {

        Usuario usuario = usuarioRepository.findById(id).get();

        publicacion.setAutor(usuario);
        Publicacion nuevaPublicacion = publicacionService.guardarPublicacion(publicacion);
        publicacion.setFechaCreacion(LocalDateTime.now());
        return ResponseEntity.ok(nuevaPublicacion);
    }

    // bien!
    @PutMapping("/{id}")
    public ResponseEntity<Usuario> actualizarUsuario(@RequestBody Usuario usuario, @PathVariable Long id) {
        Usuario usuarioActualizado = usuarioService.actualizarUsuario(usuario, id);
        return ResponseEntity.ok(usuarioActualizado);
    }

    // bien!
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> eliminarUsuario(@PathVariable Long id) {
        boolean eliminado = usuarioService.eliminarUsuario(id);
        if (eliminado) {
            return ResponseEntity.noContent().build();
        } else {
            return ResponseEntity.notFound().build();
        }
    }



}
