package com.cliniquems.userservice.service;

import com.cliniquems.userservice.Utils.Response;
import com.cliniquems.userservice.dto.UserMapper;
import com.cliniquems.userservice.dto.UserRequest;
import com.cliniquems.userservice.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
@Slf4j
@RequiredArgsConstructor
public class UserServiceImpl implements UserService{
    private final UserRepository repository;
    private final UserMapper userMapper;
    @Override
    public Response add(UserRequest request) {

        return null;
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
}
