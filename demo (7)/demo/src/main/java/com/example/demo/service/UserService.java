package com.example.demo.service;

import com.example.demo.dto.UtilisateurDto;
import com.example.demo.entity.Role;
import com.example.demo.entity.Utilisateur;

import java.util.List;
import java.util.Optional;

public interface UserService {
    Utilisateur findByUsernameAndMotDePasse(String username, String motDePasse);
    UtilisateurDto registerUtilisateur(UtilisateurDto user);
    List<Utilisateur> getAllUtilisateurs();
    UtilisateurDto changeUserRole(Long userId, Role newRole);
    Optional<Utilisateur> findById(Long userId);
}
