package com.example.spring_11200.services;

import com.example.spring_11200.dto.ServiceDto;
import com.example.spring_11200.dto.ServiceForm;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface ServiceService {

    Page<ServiceDto> getAllServices(Pageable pageable, String query);

    ServiceDto addService(ServiceForm serviceForm);

    Page<ServiceDto> search(Pageable pageable, String query);
}
