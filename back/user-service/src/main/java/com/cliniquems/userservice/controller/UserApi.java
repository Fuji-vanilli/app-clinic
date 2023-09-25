package com.cliniquems.userservice.controller;

import com.cliniquems.userservice.Utils.Response;
import com.cliniquems.userservice.dto.UserRequest;
import com.cliniquems.userservice.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import static com.cliniquems.userservice.Utils.Root.APP_ROOT;

@RestController
@RequestMapping(APP_ROOT)
@RequiredArgsConstructor
public class UserApi implements UserController{
    private final UserService userService;
    @Override
    public ResponseEntity<Response> add(UserRequest request) {
        return ResponseEntity.ok(userService.add(request));
    }

    @Override
    public ResponseEntity<Response> update(UserRequest request) {
        return ResponseEntity.ok(userService.update(request));
    }

    @Override
    public ResponseEntity<Response> get(String email) {
        return ResponseEntity.ok(userService.get(email));
    }

    @Override
    public ResponseEntity<Response> all() {
        return ResponseEntity.ok(userService.all());
    }

    @Override
    public ResponseEntity<Response> delete(String email) {
        return ResponseEntity.ok(userService.delete(email));
    }
}
