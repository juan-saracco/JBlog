package com.jblog.demo.controller;

import com.jblog.demo.model.Publicacion;
import com.jblog.demo.model.Usuario;
import com.jblog.demo.repository.UsuarioRepository;
import com.jblog.demo.service.PublicacionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/publicaciones")
public class PublicacionController {
    @Autowired
    private PublicacionService publicacionService;

    @Autowired
    private UsuarioRepository usuarioRepository;
    
    //bien
    @GetMapping
    public ResponseEntity<List<Publicacion>> obtenerTodas() {
        List<Publicacion> publicaciones = publicacionService.obtenerTodas();
        return ResponseEntity.ok(publicaciones);
    }
    //bien
    @GetMapping("/{id}")
    public ResponseEntity<Publicacion> obtenerPorId(@PathVariable Long id) {
        Optional<Publicacion> publicacion = publicacionService.buscarPorId(id);
        return publicacion.map(ResponseEntity::ok).orElse(ResponseEntity.notFound().build());
    }

    //CORREGIR
    @PutMapping("/{id}")
    public ResponseEntity<Publicacion> actualizarPublicacion(@RequestBody Publicacion publicacion, @PathVariable Long id) {
        Publicacion publicacionActualizada = publicacionService.actualizarPublicacion(publicacion, id);
        return ResponseEntity.ok(publicacionActualizada);
    }
    //bien
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> eliminarPublicacion(@PathVariable Long id) {
        boolean eliminado = publicacionService.eliminarPublicacion(id);
        if (eliminado) {
            return ResponseEntity.noContent().build();
        } else {
            return ResponseEntity.notFound().build();
        }
    }
}

