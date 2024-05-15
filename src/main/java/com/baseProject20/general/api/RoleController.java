package com.baseProject20.general.api;

import com.baseProject20.general.domain.Role;
import com.baseProject20.general.domain.dto.RoleDTO;
import com.baseProject20.general.service.RoleService;
import com.baseProject20.general.service.UserService;
import lombok.Data;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import javax.management.relation.RoleNotFoundException;
import java.net.URI;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/roles")

public class RoleController {
    private final UserService userService;
    private final RoleService roleService;

    @PostMapping("/save")
    public ResponseEntity<Role> saveRole(RoleDTO roleDTO){
        var role = Role.builder()
                .name(roleDTO.getName())
                .build();

        URI uri = URI.create(ServletUriComponentsBuilder.fromCurrentContextPath().path("/api/roles/save").toUriString());
        return ResponseEntity.created(uri).body(userService.saveRole(role));
    }

    @PostMapping("/addRoleToUser")
    public ResponseEntity<?> addRoleToUser(@RequestBody RoleToUserForm form) throws RoleNotFoundException {
        userService.addRoleToUser(form.getEmail(), form.getRoleName());
        return ResponseEntity.ok().build();
    }

    @GetMapping("/{name}")
    public ResponseEntity<RoleDTO> getRoleByName(@PathVariable String name) throws RoleNotFoundException {
        var role = roleService.getRoleByName(name);
        var roleDTO = RoleDTO.builder()
                .name(role.getName())
                .build();

        return ResponseEntity.ok().body(roleDTO);
    }

    @GetMapping("/id/{id}")
    public ResponseEntity<RoleDTO> getRoleById(@PathVariable Long id) throws RoleNotFoundException {
        var role = roleService.getRoleById(id);
        var roleDTO = RoleDTO.builder()
                .id(role.getId())
                .name(role.getName())
                .build();

        return ResponseEntity.ok().body(roleDTO);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteRole(@PathVariable Long id){
        roleService.deleteRole(id);
        return ResponseEntity.ok().build();
    }


}
@Data
class RoleToUserForm {
    private String email;
    private String roleName;
}
