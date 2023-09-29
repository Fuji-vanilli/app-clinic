package com.cliniquems.userservice.service;

import com.cliniquems.userservice.Utils.Response;
import com.cliniquems.userservice.dto.UserMapper;
import com.cliniquems.userservice.dto.UserRequest;
import com.cliniquems.userservice.model.User;
import com.cliniquems.userservice.repository.UserRepository;
import com.cliniquems.userservice.validator.UserValidator;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.time.LocalDateTime;
import java.util.Date;
import java.util.List;
import java.util.Map;

@Service
@Transactional
@Slf4j
@RequiredArgsConstructor
public class UserServiceImpl implements UserService{
    private final UserRepository repository;
    private final UserMapper userMapper;
    @Override
    public Response add(UserRequest request) {
        List<String> errors= UserValidator.validate(request);
        if(!errors.isEmpty()) {
            log.error("some request field not match");
            return generateResponse(
                    HttpStatus.BAD_REQUEST,
                    null,
                    Map.of(
                            "errors", errors
                    ),
                    "some request field not valid"
            );
        }
        if(repository.existsByEmail(request.getEmail())) {
            log.error("user with the email {} already exist on the database", request.getEmail());
            return generateResponse(
                    HttpStatus.BAD_REQUEST,
                    null,
                    null,
                    "user with the email: "+request.getEmail()+" already exist on the database"
            );
        }

        User user= userMapper.mapToUser(request);
        log.info("new user added successfully!");

        user.setCreationDate(new Date());
        user.setLastUpdate(new Date());

        URI location= ServletUriComponentsBuilder.fromCurrentContextPath()
                .path("/{code}")
                .buildAndExpand("api/user/get/",request.getEmail())
                .toUri();

        return generateResponse(
                HttpStatus.OK,
                location,
                Map.of(
                        "user", userMapper.mapToUserResponse(user)
                ),
                "new user added successfully!"
        );
    }

    @Override
    public Response update(UserRequest request) {
        return null;
    }

    @Override
    public Response get(String email) {
        return null;
    }

    @Override
    public Response all() {
        return null;
    }

    @Override
    public Response delete(String email) {
        return null;
    }
    private Response generateResponse(HttpStatus status, URI location, Map<?, ?> data, String message){
        return Response.builder()
                .timeStamp(LocalDateTime.now())
                .status(status)
                .statusCode(status.value())
                .location(location)
                .data(data)
                .message(message)
                .build();
    }
}
