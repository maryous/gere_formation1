package gereFormation.modele;

import java.util.Date;
import java.util.List;

public class Formation {
	
	private int id;
	private String name;
	private int duration;
	private Date date_debut;
	private String lieu;
	private List<Module> modules;
	private List<Stagiaire> stagiaires;
	
	public Formation() {
	}
	
	public Formation(int id, String name, int duration, Date date_debut,
			String lieu) {
		super();
		this.id = id;
		this.name = name;
		this.duration = duration;
		this.date_debut = date_debut;
		this.lieu = lieu;
	}
	
	public Formation(int id, String name, int duration, Date date_debut,
			String lieu, List<Module> modules, List<Stagiaire> stagiaires) {
		super();
		this.id = id;
		this.name = name;
		this.duration = duration;
		this.date_debut = date_debut;
		this.lieu = lieu;
		this.modules = modules;
		this.stagiaires = stagiaires;
	}

    public Formation(String name, int duration, Date date_debut, String lieu) {
        this.name = name;
        this.duration = duration;
        this.date_debut = date_debut;
        this.lieu = lieu;
    }



	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public int getDuration() {
		return duration;
	}
	public void setDuration(int duration) {
		this.duration = duration;
	}
	public Date getDate_debut() {
		return date_debut;
	}
	public void setDate_debut(Date date_debut) {
		this.date_debut = date_debut;
	}
	public String getLieu() {
		return lieu;
	}
	public void setLieu(String lieu) {
		this.lieu = lieu;
	}	
	public List<Module> getModules() {
		return modules;
	}
	public void setModules(List<Module> modules) {
		this.modules = modules;
	}
	public List<Stagiaire> getStagiaires() {
		return stagiaires;
	}
	public void setStagiaires(List<Stagiaire> stagiaires) {
		this.stagiaires = stagiaires;
	}
	
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + id;
		return result;
	}
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Formation other = (Formation) obj;
		if (id != other.id)
			return false;
		return true;
	}
	
	public String toString() {
		return  name ;
	}
}
