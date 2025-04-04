package com.kibuti.socketserver.GlobeAuthentication.Repository;


import com.kibuti.socketserver.GlobeAuthentication.Entity.Roles;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;
import java.util.UUID;
public interface RolesRepository extends JpaRepository<Roles, UUID> {
    Optional<Roles> findByRoleName(String name);
}
