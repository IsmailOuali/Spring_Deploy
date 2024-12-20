package com.example.demo.repository;

import com.example.demo.entity.Utilisateur;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UtilisateurRepository extends JpaRepository<Utilisateur, Long> {
    Utilisateur findByUsernameAndMotDePasse(String username, String motDePasse);
    Optional<Utilisateur> findByUsername(String username);
    Optional<Utilisateur> findByPigeons_Id(Long pigeonId);
}
