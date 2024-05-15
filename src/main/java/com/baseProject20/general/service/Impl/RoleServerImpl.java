package com.baseProject20.general.service.Impl;

import com.baseProject20.general.domain.Role;
import com.baseProject20.general.repo.RoleRepo;
import com.baseProject20.general.service.RoleService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import javax.management.relation.RoleNotFoundException;

@Service
@RequiredArgsConstructor
public class RoleServerImpl implements RoleService {

    private final RoleRepo roleRepo;
    @Override
    public Role getRoleByName(String roleName) throws RoleNotFoundException {
        return roleRepo.findByName(roleName)
                .orElseThrow(() -> new RoleNotFoundException("No role found with name: "+roleName));
    }

    @Override
    public Role getRoleById(Long id) throws RoleNotFoundException {
        return roleRepo.findById(id)
                .orElseThrow(() -> new RoleNotFoundException("No role found with id: "+id));
    }

    @Override
    public void deleteRole(Long id) {
        roleRepo.deleteById(id);
    }
}
