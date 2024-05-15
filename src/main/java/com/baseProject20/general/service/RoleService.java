package com.baseProject20.general.service;

import com.baseProject20.general.domain.Role;

import javax.management.relation.RoleNotFoundException;

public interface RoleService {
    Role getRoleByName(String roleName) throws RoleNotFoundException;
    Role getRoleById(Long id) throws RoleNotFoundException;
    void deleteRole(Long id);
}
