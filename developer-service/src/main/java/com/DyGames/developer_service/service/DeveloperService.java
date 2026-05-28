package com.DyGames.developer_service.service;

import com.DyGames.developer_service.dto.DeveloperRespuesta;
import com.DyGames.developer_service.mapper.DeveloperMapper;
import com.DyGames.developer_service.model.Developer;
import com.DyGames.developer_service.repository.DeveloperRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class DeveloperService {

    @Autowired
    private DeveloperRepository developerRepository;

    @Autowired
    private DeveloperMapper developerMapper;

    public List<Developer> findAll() {
        return developerRepository.findAll();
    }

    public Developer findById(Long id) {
        return developerRepository.findById(id).orElse(null);
    }

    public Developer save(Developer developer) {
        return developerRepository.save(developer);
    }

    public Developer update(Long id, Developer developer) {
        Developer d = developerRepository.findById(id).orElse(null);
        if (d == null) return null;
        d.setNombre(developer.getNombre());
        d.setSitioWeb(developer.getSitioWeb());
        return developerRepository.save(d);
    }

    public void delete(Long id) {
        developerRepository.deleteById(id);
    }

    // Metodos DTO
    public DeveloperRespuesta findDTO(Long id) {
        return developerMapper.toDTO(findById(id));
    }

    public List<DeveloperRespuesta> findDTOList() {
        return developerMapper.toDTOList(findAll());
    }
}
