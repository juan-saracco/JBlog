package com.jblog.demo.service;

import com.jblog.demo.model.Publicacion;
import com.jblog.demo.model.Usuario;
import com.jblog.demo.repository.PublicacionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class PublicacionService {
    @Autowired
    private PublicacionRepository publicacionRepository;

    public Publicacion guardarPublicacion(Publicacion publicacion) {
        return publicacionRepository.save(publicacion);
    }

    public Optional<Publicacion> buscarPorId(Long id) {
        return publicacionRepository.findById(id);
    }

    public List<Publicacion> obtenerTodas() {
        return publicacionRepository.findAll();
    }

    public Publicacion actualizarPublicacion(Publicacion publicacion, Long id) {
        Publicacion publicacionActualizada = publicacionRepository.getById(id);

        publicacionActualizada.setTitulo(publicacion.getTitulo());
        publicacionActualizada.setContenido(publicacion.getContenido());
        publicacionActualizada.setFechaCreacion(publicacion.getFechaCreacion());

        return publicacionActualizada;
    }

    public Optional<Publicacion> obtenerPublicacionesPorAutor(Usuario autor) {
        return publicacionRepository.findById(autor.getId());
    }

    public boolean eliminarPublicacion(Long id) {
        try {
            publicacionRepository.deleteById(id);
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }
}
