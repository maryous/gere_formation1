package gereFormation.modele;

import java.io.Serializable;


public class Ecf implements Serializable {
	private int id;
	private int idstagiaire;
	private int idmodule;
	private int validate;
	
	public Ecf() {
		
	}
	
	
	public Ecf(int id, int idstagiaire, int idmodule, int validate) {
		super();
		this.id = id;
		this.idstagiaire = idstagiaire;
		this.idmodule = idmodule;
		this.validate = validate;
	}
	
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public int getIdstagiaire() {
		return idstagiaire;
	}
	public void setIdstagiaire(int idstagiaire) {
		this.idstagiaire = idstagiaire;
	}
	public int getIdmodule() {
		return idmodule;
	}
	public void setIdmodule(int idmodule) {
		this.idmodule = idmodule;
	}
	public int getValidate() {
		return validate;
	}
	public void setValidate(int validate) {
		this.validate = validate;
	}
	
	public String toString() {
		return "" + idstagiaire + " " + idmodule + " " + validate + "" ;
	}
	
	
}
