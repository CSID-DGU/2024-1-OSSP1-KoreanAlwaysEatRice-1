package com.example.menuw.service;

import com.example.menuw.domain.Refrigerator;
import com.example.menuw.dto.RefrigeratorDto;
import com.example.menuw.repository.RefrigeratorRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class RefrigeratorService {
    private final RefrigeratorRepository refrigeratorRepository;

    @Transactional
    public void saveRefrigerator(Refrigerator refrigerator) { refrigeratorRepository.save(refrigerator);
    }

    public RefrigeratorDto findOne(Integer refrigeratorId) {
        return refrigeratorRepository.findRefrigeratorByUserId(refrigeratorId);
    }
}
