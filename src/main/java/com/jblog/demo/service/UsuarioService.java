package com.jblog.demo.service;

import com.jblog.demo.model.Usuario;
import com.jblog.demo.repository.UsuarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class UsuarioService {
    @Autowired
    private  UsuarioRepository usuarioRepository;

    public Usuario guardarUsuario(Usuario usuario){
        return  usuarioRepository.save(usuario);
    }

    public Optional<Usuario> buscarPorId(Long id){
        return usuarioRepository.findById(id);
    }

    public List<Usuario> obtenerTodos(){
        return usuarioRepository.findAll();
    }

    //COOREGIR
    public Usuario actualizarUsuario(Usuario usuario, Long id){
        Usuario usuarioActualizado = usuarioRepository.findById(id).get();

            usuarioActualizado.setUserName(usuario.getUserName());
            usuarioActualizado.setEmail(usuario.getEmail());
            usuarioActualizado.setPublicaciones(usuario.getPublicaciones());


            return usuarioRepository.save(usuarioActualizado);
    }


    public boolean eliminarUsuario(Long id){
        try {
            usuarioRepository.deleteById(id);
            return true;
        }catch (Exception e){
            e.printStackTrace();
            return false;
        }
    }

}
