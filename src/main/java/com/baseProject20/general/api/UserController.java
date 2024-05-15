package com.baseProject20.general.api;

import com.baseProject20.general.domain.AppUser;
import com.baseProject20.general.domain.dto.AppUserDTO;
import com.baseProject20.general.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/users")
public class UserController {
    private final UserService userService;

    @GetMapping("/")
    public ResponseEntity<List<AppUser>>getUsers(){
        return ResponseEntity.ok().body(userService.getUsers());
    }

    @PostMapping("/save")
    public ResponseEntity<AppUser>saveUser(@RequestBody AppUserDTO appUserDTO){
        var appUser = AppUser.builder()
                .name(appUserDTO.getName())
                .email(appUserDTO.getEmail())
                .password(appUserDTO.getPassword())
                .build();
        URI uri = URI.create(ServletUriComponentsBuilder.fromCurrentContextPath().path("/api/users/save").toUriString());

        return ResponseEntity.created(uri).body(userService.saveUser(appUser));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteUser(@PathVariable Long id){
        userService.deleteUser(id);
        return ResponseEntity.ok().build();
    }

    @GetMapping("/{id}")
    public ResponseEntity<AppUserDTO> getUserById(@PathVariable Long id){
        var user = userService.getUserById(id);
        var userDTO = AppUserDTO.builder()
                .name(user.getName())
                .email(user.getEmail())
                .password(user.getPassword())
                .build();

        return ResponseEntity.ok().body(userDTO);
    }
}
