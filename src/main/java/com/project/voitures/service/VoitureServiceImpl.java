package com.project.voitures.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import com.project.voitures.entities.Voiture;
import com.project.voitures.repos.VoitureRepository;
@Service
public class VoitureServiceImpl implements VoitureService {

	@Autowired
	VoitureRepository voitureRepository;
	@Override
	public Voiture saveVoiture(Voiture v) {
	return voitureRepository.save(v);
	}
	@Override
	public Voiture updateVoiture(Voiture v) {
		return voitureRepository.save(v);
		
	}
	@Override
	public void deleteVoiture(Voiture v) {
		voitureRepository.delete(v);
		
	}
	@Override
	public void deleteVoitureById(Long id) {
		voitureRepository.deleteById(id);
		
	}
	@Override
	public Voiture getVoiture(Long id) {
		return voitureRepository.findById(id).get();
		}
	@Override
	public List<Voiture> getAllVoitures() {
		return voitureRepository.findAll();
	}
	@Override
	public Page<Voiture> getAllVoituresParPage(int page, int size) {
		return voitureRepository.findAll(PageRequest.of(page, size));

	}
	
}
