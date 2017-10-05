package gereFormation.modele;

import java.io.Serializable;
import java.util.Date;


public class Stagiaire implements Serializable {
	private int id;
	private String name;
	private String firstname;
	private String adresse;
	private String code_postal;
	private String ville;
	private String email;
	private String telephone;
	private Date date;
    private Formation formation;
	
	public Stagiaire() {
		
	}
	public Stagiaire(String name) {
		super();
		this.name = name;
	}	
	public Stagiaire(int id, String name) {
		super();
		this.id = id;
		this.name = name;
	}
	public Stagiaire( String name, String firstname, String adresse,
			String code_postal, String ville, String email, String telephone,
			Date date) {
		super();
		this.name = name;
		this.firstname = firstname;
		this.adresse = adresse;
		this.code_postal = code_postal;
		this.ville = ville;
		this.email = email;
		this.telephone = telephone;
		this.date = date;

	}
	public Stagiaire(int id, String name, String firstname, String adresse,
			String code_postal, String ville, String email, String telephone,
			Date date, Formation formation) {
		super();
		this.id = id;
		this.name = name;
		this.firstname = firstname;
		this.adresse = adresse;
		this.code_postal = code_postal;
		this.ville = ville;
		this.email = email;
		this.telephone = telephone;
		this.date = date;
		this.formation = formation;
	}

    public Stagiaire(String name, String firstname, String adresse) {
        this.name = name;
		this.firstname = firstname;
		this.adresse = adresse;
        
    }
	
	public String getName() {
		return name;
	}
	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public void setName(String name) {
		this.name = name;
	}
	public String getFirstname() {
		return firstname;
	}
	public void setFirstname(String firstname) {
		this.firstname = firstname;
	}
	public String getAdresse() {
		return adresse;
	}
	public void setAdresse(String adresse) {
		this.adresse = adresse;
	}
	public String getCode_postal() {
		return code_postal;
	}
	public void setCode_postal(String code_postal) {
		this.code_postal = code_postal;
	}
	public String getVille() {
		return ville;
	}
	public void setVille(String ville) {
		this.ville = ville;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getTelephone() {
		return telephone;
	}
	public void setTelephone(String telephone) {
		this.telephone = telephone;
	}
	public Date getDate() {
		return date;
	}
	public void setDate(Date date) {
		this.date = date;
	}
	public Formation getFormation() {
		return formation;
	}
	public void setFormation(Formation formation) {
		this.formation = formation;
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
		Stagiaire other = (Stagiaire) obj;
		if (id != other.id)
			return false;
		return true;
	}
	
	public String toString() {
		return "Je m'appelle " + name + " " + firstname;
	}
}
