package com.project.voitures.entities;

import java.util.Date;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
@Entity
public class Voiture {

	@Id
	@GeneratedValue(strategy= GenerationType.IDENTITY)
	private Long idVoiture;
	private String modele;
	private Double prixVoiture;
	private Date dateCreation;
	
	
	public Voiture() {
		super();
		// TODO Auto-generated constructor stub
	}


	public Voiture( String modele, Double prixVoiture, Date dateCreation) {
		super();
		
		this.modele = modele;
		this.prixVoiture = prixVoiture;
		this.dateCreation = dateCreation;
	}


	public Long getIdVoiture() {
		return idVoiture;
	}


	public void setIdVoiture(Long idVoiture) {
		this.idVoiture = idVoiture;
	}


	public String getModele() {
		return modele;
	}


	public void setModele(String modele) {
		this.modele = modele;
	}


	public Double getPrixVoiture() {
		return prixVoiture;
	}


	public void setPrixVoiture(Double prixVoiture) {
		this.prixVoiture = prixVoiture;
	}


	public Date getDateCreation() {
		return dateCreation;
	}


	public void setDateCreation(Date dateCreation) {
		this.dateCreation = dateCreation;
	}


	@Override
	public String toString() {
		return "Voiture [idVoiture=" + idVoiture + ", modele=" + modele + ", prixVoiture=" + prixVoiture
				+ ", dateCreation=" + dateCreation + "]";
	}
	
	
	
}
