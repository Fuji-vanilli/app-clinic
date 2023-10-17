package com.cliniquems.medicamentservice.service;

import com.cliniquems.medicamentservice.MedicamentRepository;
import com.cliniquems.medicamentservice.Utils.Response;
import com.cliniquems.medicamentservice.dto.MedicamentMapper;
import com.cliniquems.medicamentservice.dto.MedicamentRequest;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
@RequiredArgsConstructor
@Slf4j
public class MedicamentServiceImpl implements MedicamentService{
    private final MedicamentRepository repository;
    private final MedicamentMapper medicamentMapper;
    @Override
    public Response add(MedicamentRequest request) {
        return null;
    }

    @Override
    public Response get(String code) {
        return null;
    }

    @Override
    public Response update(MedicamentRequest request) {
        return null;
    }

    @Override
    public Response all(int page, int size) {
        return null;
    }

    @Override
    public Response delete(String code) {
        return null;
    }
}
