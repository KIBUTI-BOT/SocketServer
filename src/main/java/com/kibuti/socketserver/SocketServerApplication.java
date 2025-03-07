package com.kibuti.socketserver;

import com.kibuti.socketserver.GlobeAuthentication.Entity.Roles;
import com.kibuti.socketserver.GlobeAuthentication.Repository.RolesRepository;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class SocketServerApplication implements CommandLineRunner {

    @Autowired
    private RolesRepository roleRepository;

    @Bean
    public ModelMapper modelMapper() {
        return new ModelMapper();
    }

    public static void main(String[] args) {
        SpringApplication.run(SocketServerApplication.class, args);
    }

    private void createRoleIfNotExists(String roleName) {
        Roles existingRole = roleRepository.findByRoleName(roleName).orElse(null);

        if (existingRole == null) {
            Roles newRole = new Roles();
            newRole.setRoleName(roleName);
            roleRepository.save(newRole);
        }
    }

    @Override
    public void run(String... args) throws Exception {
        createRoleIfNotExists("ROLE_ADMIN");
        createRoleIfNotExists("ROLE_KITCHEN_ADMIN");
    }
}
