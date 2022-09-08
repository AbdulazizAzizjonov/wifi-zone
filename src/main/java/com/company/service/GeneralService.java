package com.company.service;

import org.modelmapper.ModelMapper;
import org.springframework.data.jpa.repository.JpaRepository;

public abstract class GeneralService<R extends JpaRepository> {
    R repository;

    ModelMapper modelMapper = new ModelMapper();

    public GeneralService(R repository) {
        this.repository = repository;
    }
}
