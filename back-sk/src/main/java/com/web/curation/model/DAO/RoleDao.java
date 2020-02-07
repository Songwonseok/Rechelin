package com.web.curation.model.DAO;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.web.curation.model.DTO.Role;
import com.web.curation.model.DTO.RoleName;

@Repository
public interface RoleDao extends JpaRepository<Role, Long> {
    Role findByName(RoleName roleName);
}