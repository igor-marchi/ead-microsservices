package com.ead.authuser.web.controller;

import com.ead.authuser.core.entity.User;
import com.ead.authuser.core.service.IUserService;
import com.ead.authuser.web.controller.dto.UserDto;
import com.fasterxml.jackson.annotation.JsonView;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.Optional;
import java.util.UUID;

@RestController
@CrossOrigin(origins = "*", maxAge = 3600)
@RequestMapping("/users")
public class UserController {

    @Autowired
    IUserService userService;

    @GetMapping
    public ResponseEntity<Page<User>> getAllUsers(
            @PageableDefault(page = 0, size = 10, sort = "id", direction = Sort.Direction.ASC) Pageable pageable
    ) {
        Page<User> userPage = userService.findAll(pageable);
        return ResponseEntity.status(HttpStatus.OK).body(userPage);
    }

    @GetMapping("/{userId}")
    public ResponseEntity<Object> getUser(@PathVariable(value = "userId") UUID userId) {
        Optional<User> userOptional = userService.findById(userId);
        if (userOptional.isEmpty()) return ResponseEntity.status(HttpStatus.NOT_FOUND).body("User not found.");
        return ResponseEntity.status(HttpStatus.OK).body(userOptional);
    }

    @DeleteMapping("/{userId}")
    public ResponseEntity<Object> deleteUser(@PathVariable(value = "userId") UUID userId) {
        Optional<User> userOptional = userService.findById(userId);
        if (userOptional.isEmpty()) return ResponseEntity.status(HttpStatus.NOT_FOUND).body("User not found.");
        userService.delete(userOptional.get());
        return ResponseEntity.status(HttpStatus.OK).body("User deleted success.");
    }

    @PutMapping("/{userId}")
    public ResponseEntity<Object> updateUser(
            @PathVariable(value = "userId") UUID userId,
            @RequestBody @Validated(UserDto.UserView.UserPut.class) @JsonView(UserDto.UserView.UserPut.class) UserDto userDto
    ) {
        Optional<User> userOptional = userService.findById(userId);
        if (userOptional.isEmpty()) return ResponseEntity.status(HttpStatus.NOT_FOUND).body("User not found.");
        var user = userOptional.get();
        user.setFullName(userDto.getFullName());
        user.setPhone(userDto.getPhone());
        user.setDocument(userDto.getDocument());
        user.setUpdatedAt(LocalDateTime.now(ZoneId.of("UTC")));
        userService.save(user);
        return ResponseEntity.status(HttpStatus.OK).body(user);
    }

    @PutMapping("/{userId}/password")
    public ResponseEntity<Object> updatePassword(
            @PathVariable(value = "userId") UUID userId,
            @RequestBody @Validated(UserDto.UserView.PasswordPut.class) @JsonView(UserDto.UserView.PasswordPut.class) UserDto userDto
    ) {
        Optional<User> userOptional = userService.findById(userId);
        if (userOptional.isEmpty()) return ResponseEntity.status(HttpStatus.NOT_FOUND).body("User not found.");

        var user = userOptional.get();
        if (!user.getPassword().equals(userDto.getOldPassword())) {
            return ResponseEntity.status(HttpStatus.CONFLICT).body("Error: Mismatched old password!");
        }
        user.setPassword(userDto.getPassword());
        user.setUpdatedAt(LocalDateTime.now(ZoneId.of("UTC")));
        userService.save(user);
        return ResponseEntity.status(HttpStatus.OK).body("Password updated successfully.");
    }

    @PutMapping("/{userId}/image")
    public ResponseEntity<Object> updateImage(
            @PathVariable(value = "userId") UUID userId,
            @RequestBody @Validated(UserDto.UserView.ImagePut.class) @JsonView(UserDto.UserView.ImagePut.class) UserDto userDto
    ) {
        Optional<User> userOptional = userService.findById(userId);
        if (userOptional.isEmpty()) return ResponseEntity.status(HttpStatus.NOT_FOUND).body("User not found.");

        var user = userOptional.get();
        user.setImageUrl(userDto.getImageUrl());
        user.setUpdatedAt(LocalDateTime.now(ZoneId.of("UTC")));
        userService.save(user);
        return ResponseEntity.status(HttpStatus.OK).body(user);
    }
}

// f27