package com.timecast.controller;

import com.timecast.model.Usuario;
import com.timecast.repository.UsuarioRepository;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/usuarios")
@CrossOrigin(origins = "*")
public class AuthController {

    private final UsuarioRepository repo;

    public AuthController(UsuarioRepository repo) {
        this.repo = repo;
    }

    @PostMapping("/login")
    public boolean login(@RequestBody Usuario usuario) {
        return repo.findByEmail(usuario.getEmail())
                .map(u -> u.getSenha().equals(usuario.getSenha()))
                .orElse(false);
    }

    @PostMapping("/registrar")
    public Usuario registrar(@RequestBody Usuario usuario) {
        return repo.save(usuario);
    }
}