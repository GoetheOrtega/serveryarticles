package com.example.spring_11200.controllers;

import com.example.spring_11200.dto.ServiceDto;
import com.example.spring_11200.dto.ServiceForm;
import com.example.spring_11200.services.ServiceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;


import org.springframework.data.domain.Sort;


@Controller
public class ServiceController {

    @Autowired
    private ServiceService servicesService;

    @GetMapping("/allservices")
    @ResponseBody
    public ResponseEntity<Page<ServiceDto>> getAllServices(@RequestParam(value = "page", defaultValue = "0") Integer page,
                                                           @RequestParam(value = "size", defaultValue = "10") Integer size,
                                                           @RequestParam(value = "q", required = false) String query) {
        PageRequest pageRequest = PageRequest.of(page, size);
        Page<ServiceDto> servicesPage = servicesService.getAllServices(pageRequest, query);
        return ResponseEntity.ok(servicesPage);
    }

    @GetMapping("/service")
    public String getSericePage(Model model) {
        PageRequest pageRequest = PageRequest.of(0, 10); // Página inicial con 10 elementos por página
        Page<ServiceDto> servicesPage = servicesService.getAllServices(pageRequest, null); // Obtener la primera página de servicios
        model.addAttribute("servicePage", servicesPage);
        return "service";
    }

    @PostMapping("/services")
    @ResponseBody
    public ResponseEntity<ServiceDto> addService(@RequestBody ServiceForm serviceForm) {
        return ResponseEntity.ok(servicesService.addService(serviceForm));
    }

    // localhost:8080/papers/service/search?page=0
    @GetMapping("/papers/service/search")
    @ResponseBody
    public ResponseEntity<Page<ServiceDto>> search(@RequestParam("page") Integer page,
                                                   @RequestParam("size") Integer size,
                                                   @RequestParam(value = "q", required = false) String query,
                                                   @RequestParam(value = "sort", required = false) String sort,
                                                   @RequestParam(value = "direction", required = false) String direction) {
        Sort.Direction sortDirection = direction != null ? Sort.Direction.fromString(direction) : Sort.Direction.ASC;
        Sort sortObject = Sort.by(sortDirection, sort != null ? sort : "id");
        PageRequest pageRequest = PageRequest.of(page, size, sortObject);
        Page<ServiceDto> servicesPage = servicesService.search(pageRequest, query);
        return ResponseEntity.ok(servicesPage);
    }
}
