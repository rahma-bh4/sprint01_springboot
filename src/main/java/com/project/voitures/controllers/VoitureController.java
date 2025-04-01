package com.project.voitures.controllers;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.project.voitures.entities.Voiture;
import com.project.voitures.service.VoitureService;

@Controller
public class VoitureController {

	@Autowired
	VoitureService voitureService;

	/*@RequestMapping("/ListeVoitures")
	public String listeVoitures(ModelMap modelMap) {
		List<Voiture> voits = voitureService.getAllVoitures();
		modelMap.addAttribute("voitures", voits);
		return "listeVoitures";
	}*/
	@RequestMapping("/ListeVoitures")
	public String listeVoitures(ModelMap modelMap, @RequestParam(name = "page", defaultValue = "0") int page,
			@RequestParam(name = "size", defaultValue = "2") int size) {
		
		Page<Voiture> voits = voitureService.getAllVoituresParPage(page, size);
		modelMap.addAttribute("voitures", voits);
		modelMap.addAttribute("pages", new int[voits.getTotalPages()]);
		modelMap.addAttribute("currentPage", page);
		
		return "listevoitures";
	}

	@RequestMapping("/showCreate")
	public String showCreate(ModelMap modelMap) {
		modelMap.addAttribute("voiture", new Voiture());
		modelMap.addAttribute("mode", "new");
		return "formVoiture";
	}

	@RequestMapping("/saveVoiture")
	public String saveVoiture(@ModelAttribute("voiture") Voiture voiture, @RequestParam("dateCreation") String date,
			ModelMap modelMap,@RequestParam (name="page",defaultValue = "0") int page,
			@RequestParam (name="size", defaultValue = "2") int size) throws ParseException {
		int currentPage;
		if(voiture.getIdVoiture()==null) {
		SimpleDateFormat dateformat = new SimpleDateFormat("yyyy-MM-dd");
		Date dateCreation = dateformat.parse(String.valueOf(date));
		voiture.setDateCreation(dateCreation);

		Voiture saveVoiture = voitureService.saveVoiture(voiture);
		String msg = "Voiture enregistré avec Id " + saveVoiture.getIdVoiture();
		Page<Voiture> voits = voitureService.getAllVoituresParPage(page, size);
		currentPage = voits.getTotalPages()-1;
		modelMap.addAttribute("msg", msg);
		modelMap.addAttribute("voitures", voits);
		modelMap.addAttribute("pages", new int[voits.getTotalPages()]);
		modelMap.addAttribute("page", currentPage);
		modelMap.addAttribute("size", size);
		}else 
			{
			currentPage=page;
			}
		return ("redirect:/ListeVoitures?page="+currentPage+"&size="+size);
	}

	@RequestMapping("/supprimerVoiture")
	public String supprimerVoiture(@RequestParam("id") Long id, ModelMap modelMap,
			@RequestParam (name="page",defaultValue = "0") int page,
			@RequestParam (name="size", defaultValue = "2") int size) {
		voitureService.deleteVoitureById(id);
		Page<Voiture> voits = voitureService.getAllVoituresParPage(page,
				size);
				modelMap.addAttribute("voitures", voits);
				modelMap.addAttribute("pages", new int[voits.getTotalPages()]);
				modelMap.addAttribute("currentPage", page);
				modelMap.addAttribute("size", size);
				return "listevoitures";
	}

	@RequestMapping("/modifierVoiture")
	public String editerVoiture(@RequestParam("id") Long id, ModelMap modelMap,
			@RequestParam (name="page",defaultValue = "0") int page,
			@RequestParam (name="size", defaultValue = "2") int size) {
		Voiture v = voitureService.getVoiture(id);
		modelMap.addAttribute("voiture", v);
		modelMap.addAttribute("mode", "edit");
		modelMap.addAttribute("page", page);
		modelMap.addAttribute("size", size);
		return "formVoiture";
	}

	@RequestMapping("/updateVoiture")
	public String updateVoiture(@ModelAttribute("voiture") Voiture voiture, @RequestParam("dateCreation") String date, ModelMap modelMap,@RequestParam (name="page",defaultValue = "0") int page,
			@RequestParam (name="size", defaultValue = "2") int size) throws ParseException {
		// conversion de la date
		SimpleDateFormat dateformat = new SimpleDateFormat("yyyy-MM-dd");
		Date dateCreation = dateformat.parse(String.valueOf(date));
		voiture.setDateCreation(dateCreation);

		voitureService.updateVoiture(voiture);
		Page<Voiture> voits = voitureService.getAllVoituresParPage(page, size);
		modelMap.addAttribute("voitures", voits);
		return "listeVoitures";
	}
}
