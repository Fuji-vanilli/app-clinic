package com.cliniquems.userservice.controller;

import com.cliniquems.userservice.Utils.Response;
import com.cliniquems.userservice.dto.UserRequest;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

public interface UserController {
    @PostMapping("add")
    ResponseEntity<Response> add(@RequestBody UserRequest request);
    @PutMapping("update")
    ResponseEntity<Response> update(@RequestBody UserRequest request);
    @GetMapping("get/{email}")
    ResponseEntity<Response> get(@PathVariable String email);
    @GetMapping("all")
    ResponseEntity<Response> all();
    @DeleteMapping("delete/{email}")
    ResponseEntity<Response> delete(@PathVariable String email);
}
