package gereFormation.modele;

public class Module {
	private int id;
	private String name;
	
	public Module() {
	}
	public Module(int id, String name) {
		super();
		this.id = id;
		this.name = name;
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

	public String toString() {
		return "le module " + name + ".";
	}
}
