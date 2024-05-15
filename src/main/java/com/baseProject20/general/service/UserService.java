package com.baseProject20.general.service;

import com.baseProject20.general.domain.AppUser;
import com.baseProject20.general.domain.Role;

import javax.management.relation.RoleNotFoundException;
import java.util.List;

public interface UserService {
    AppUser saveUser(AppUser user);
    Role saveRole(Role role);
    void addRoleToUser(String email, String roleName) throws RoleNotFoundException;
    AppUser getUser(String email);
    AppUser getUserById(Long id);
    List<AppUser>getUsers();
    void deleteUser(Long id);


}
