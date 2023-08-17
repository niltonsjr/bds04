package com.devsuperior.bds04.services;

import com.devsuperior.bds04.dto.EventDTO;
import com.devsuperior.bds04.entities.City;
import com.devsuperior.bds04.entities.Event;
import com.devsuperior.bds04.repositories.EventRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

@Service
public class EventService {

  @Autowired
  private EventRepository repository;

  @Transactional(readOnly = true)
  public Page<EventDTO> findAllPaged(Pageable pageable) {
    Page<Event> list = repository.findAll(pageable);

    return list.map(EventDTO::new);
  }

  @Transactional(propagation = Propagation.REQUIRED, readOnly = false)
  public EventDTO insert(EventDTO dto) {
    Event entity = new Event();
    entity.setName(dto.getName());
    entity.setUrl(dto.getUrl());
    entity.setDate(dto.getDate());
    entity.setCity(new City(dto.getCityId(), null));
    entity = repository.save(entity);

    return new EventDTO(entity);
  }
}
