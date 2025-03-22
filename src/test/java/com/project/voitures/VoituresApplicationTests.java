package com.project.voitures;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.Page;

import java.util.Date;
import java.util.List;

import com.project.voitures.entities.Voiture;
import com.project.voitures.repos.VoitureRepository;
import com.project.voitures.service.VoitureService;

@SpringBootTest
class VoituresApplicationTests {

	@Autowired
	private VoitureRepository voitureRepository;
	@Autowired
	private VoitureService voitureService;
	@Test
	public void testCreateVoiture() {
	Voiture voit = new Voiture("clio 5 ",50000.0,new Date());
	voitureRepository.save(voit);
	}
	
	@Test
	public void testFindVoiture()
	{
	Voiture v = voitureRepository.findById(1L).get();
	System.out.println(v);
	}
	
	@Test
	public void testUpdateVoiture()
	{
	Voiture v = voitureRepository.findById(1L).get();
	v.setPrixVoiture(150000.0);
	voitureRepository.save(v);
	}
	
	@Test
	public void testListerTousVoitures()
	{
	List<Voiture> voits = voitureRepository.findAll();
	for (Voiture v : voits)
	{
	System.out.println(v);
	}
	}
	
	public void testDeleteProduit()
	{
	voitureRepository.deleteById(1L);;
	}
	@Test
	public void testFindByModeleContains()
	{
	Page<Voiture> voits = voitureService.getAllVoituresParPage(0,2);
	System.out.println(voits.getSize());
	System.out.println(voits.getTotalElements());
	System.out.println(voits.getTotalPages());
	voits.getContent().forEach(v -> {System.out.println(v.toString());
	 });
	/*ou bien
	for (Produit p : prods)
	{
	System.out.println(p);
	} */
	}
	
}
