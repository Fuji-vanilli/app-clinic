package com.cliniquems.medecinservice.service;

import com.cliniquems.medecinservice.Utils.Response;
import com.cliniquems.medecinservice.dto.MedecinRequest;
import com.cliniquems.medecinservice.repository.MedecinRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
@Slf4j
@RequiredArgsConstructor
public class MedecinServiceImpl implements MedecinService {
    private final MedecinRepository repository;
    @Override
    public Response add(MedecinRequest request) {
        return null;
    }

    @Override
    public Response get(String code) {
        return null;
    }

    @Override
    public Response update(MedecinRequest request) {
        return null;
    }

    @Override
    public Response all() {
        return null;
    }

    @Override
    public Response delete(String code) {
        return null;
    }
}
