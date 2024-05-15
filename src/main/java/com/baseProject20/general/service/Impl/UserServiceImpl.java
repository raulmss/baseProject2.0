package com.baseProject20.general.service.Impl;

import com.baseProject20.general.domain.AppUser;
import com.baseProject20.general.domain.Role;
import com.baseProject20.general.exception.UserNotFoundException;
import com.baseProject20.general.repo.RoleRepo;
import com.baseProject20.general.repo.UserRepo;
import com.baseProject20.general.service.UserService;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import javax.management.relation.RoleNotFoundException;
import java.util.List;

@Service
@RequiredArgsConstructor
@Transactional
@Slf4j
public class UserServiceImpl implements UserService {

    private final UserRepo userRepo;
    private final RoleRepo roleRepo;

    @Override
    public AppUser saveUser(AppUser user) {
        log.info("Saving new user {} into database", user.getEmail());
        return userRepo.save(user);
    }

    @Override
    public Role saveRole(Role role) {
        log.info("Saving new role {} into database", role.getName());
        return roleRepo.save(role);
    }

    @Override
    public void addRoleToUser(String email, String roleName) throws RoleNotFoundException {
        var user = userRepo.findByEmail(email)
                .orElseThrow(() -> new UserNotFoundException("No user found with the email: "+email));
        var role = roleRepo.findByName(roleName)
                .orElseThrow(() -> new RoleNotFoundException("No role found with name: "+roleName));

        //Since this service class is transactional, it will save all roles into the database by doing this.
        log.info("Adding role {} to user {} and saving user", roleName, email);
        user.getRoles().add(role);
    }

    @Override
    public AppUser getUser(String email) {
        log.info("Fetching user {} by email", email);
        return userRepo.findByEmail(email).orElseThrow(() -> new UserNotFoundException("No user found with the email: "+email));
    }

    @Override
    public AppUser getUserById(Long id) {
        log.info("Fetching user {} by id", id);
        return userRepo.findById(id).orElseThrow(() -> new UserNotFoundException("No user found with the id: "+id));
    }


    @Override
    public List<AppUser> getUsers() {
        log.info("Fetching all users");
        return userRepo.findAll();
    }


    @Override
    public void deleteUser(Long id) {
        log.info("Deleting user with id {}", id);
        userRepo.deleteById(id);
    }


}
