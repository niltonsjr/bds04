package com.devsuperior.bds04.services;

import com.devsuperior.bds04.dto.CityDTO;
import com.devsuperior.bds04.entities.City;
import com.devsuperior.bds04.repositories.CityRepository;
import java.util.List;
import java.util.stream.Collectors;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

@Service
public class CityService {

  @Autowired
  private CityRepository repository;

  @Transactional(readOnly = true)
  public List<CityDTO> findAll() {
    List<City> list = repository.findAll(Sort.by("name"));
    return list.stream().map(CityDTO::new).collect(Collectors.toList());
  }

  @Transactional(propagation = Propagation.REQUIRED, readOnly = false)
  public CityDTO insert(CityDTO dto) {
    City entity = new City();
    entity.setName(dto.getName());
    entity = repository.save(entity);

    return new CityDTO(entity);
  }
}
