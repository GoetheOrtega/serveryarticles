package com.example.spring_11200.services;

import com.example.spring_11200.dto.ServiceDto;
import com.example.spring_11200.dto.ServiceForm;
import com.example.spring_11200.models.Service;
import com.example.spring_11200.repositores.ServiceRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Component;



@Component
public class ServiceServiceImpl implements ServiceService {

    private final ServiceRepository serviceRepository;

    public ServiceServiceImpl(ServiceRepository serviceRepository) {
        this.serviceRepository = serviceRepository;
    }

    @Override
    public Page<ServiceDto> getAllServices(Pageable pageable, String query) {
        if (query != null && !query.isEmpty()) {
            return serviceRepository.search(query, pageable).map(ServiceDto::of);
        } else {
            return serviceRepository.findAll(pageable).map(ServiceDto::of);
        }
    }

    @Override
    public ServiceDto addService(ServiceForm serviceForm) {
        Service service = Service.builder()
                .name(serviceForm.getName())
                .cost(serviceForm.getCost())
                .date(serviceForm.getDate())
                .build();
        serviceRepository.save(service);
        return ServiceDto.of(service);
    }

    @Override
    public Page<ServiceDto> search(Pageable pageable, String query) {
        if (query == null) {
            query = ""; // Si query es null, establecerlo como una cadena vacía
        }
        return serviceRepository.search(query, pageable).map(ServiceDto::of);
    }
}


