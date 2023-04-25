package com.devsuperior.dslearnbds.services;

import javax.persistence.EntityNotFoundException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.devsuperior.dslearnbds.dto.DeliverRevisionDTO;
import com.devsuperior.dslearnbds.entities.Deliver;
import com.devsuperior.dslearnbds.repositories.DeliverRepository;
import com.devsuperior.dslearnbds.services.exceptions.ControllerNotFoundException;

@Service
public class DeliverService {
	
	@Autowired
	private DeliverRepository deliverRepository;
	
	@Transactional
	public void deliverRevision(Long id, DeliverRevisionDTO deliverRevisionDTO) {
		try {
			Deliver deliver = deliverRepository.getOne(id);
			deliver.setStatus(deliverRevisionDTO.getStatus());
			deliver.setFeedback(deliverRevisionDTO.getFeedback());
			deliver.setCorrectCount(deliverRevisionDTO.getCorrectCount());
			deliverRepository.save(deliver);
		}
		catch (EntityNotFoundException e) {
			throw new ControllerNotFoundException("Id not found " + id);
		}
	}
}
