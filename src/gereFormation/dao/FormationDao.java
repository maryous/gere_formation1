package gereFormation.dao;

import java.sql.Connection;
import java.util.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import gereFormation.modele.Formation;
import gereFormation.modele.Stagiaire;


public class FormationDao {
	
	
	public static void update(Formation f) throws Exception {
        Connection c = DBConnect.getConnection();
        PreparedStatement stm;
        try {

            stm = c.prepareStatement("UPDATE formation SET name = ? WHERE id = ?");
            stm.setString(1, f.getName());
            stm.setInt(2, f.getId());

            stm.executeUpdate();

        } catch (SQLException e) {
            //pb if here
            throw new Exception("pb lors de la mise a jour de Formation:" + e.getMessage());
        }
    }

    public static void delete(Formation f) throws Exception {
        Connection c = DBConnect.getConnection();
        PreparedStatement stm;
        try {

            stm = c.prepareStatement("DELETE FROM formation WHERE id = ?");
            stm.setInt(1, f.getId());

            stm.executeUpdate();

        } catch (SQLException e) {

            throw new Exception("pb lors de la suppression de Formation:" + e.getMessage());
        }
    }

    public static void save(Formation s) throws Exception {

        Connection c = DBConnect.getConnection();
        PreparedStatement stm;

        stm = c.prepareStatement("INSERT INTO formation (name,duration,date_debut,lieu) VALUES (?,?,?,?)", Statement.RETURN_GENERATED_KEYS);
        stm.setString(1, s.getName());
        stm.setInt(2, s.getDuration());
        stm.setDate(3, new java.sql.Date(s.getDate_debut().getTime()));
        stm.setString(4, s.getLieu());

        stm.execute();
        ResultSet rs = stm.getGeneratedKeys();

        if (rs.next()) {
            s.setId(rs.getInt(1));
        }
        stm.close();

    }

    /**
     * retourne la liste des Formation
     *
     * @return
     */
    public static List<Formation> findAll() {

        Connection c = DBConnect.getConnection();

        List<Formation> ps = new ArrayList<>();
        // test avec select
        Statement stm;
        try {
            stm = c.createStatement();

            String sql = "select * from formation" /*left join formation on Formation.idformation = formation.id left join ecf on Formation.id = ecf.idFormation*/;
            ResultSet rs = stm.executeQuery(sql);

            while (rs.next()) {
                int id = rs.getInt("formation.id");
                String name = rs.getString("formation.name");
                int duration= rs.getInt("formation.duration");
                Date date_debut =rs.getDate("formation.date_debut");
                String lieu = rs.getString("formation.lieu");
                
                //List<Stagiaire> stagiaire = getStagiaires(id);
                
                
                
                Formation f = new Formation(id, name, duration,date_debut,lieu);
                
               /* int villeId = rs.getInt("ville.id");
                if (!rs.wasNull()){ 
                    String nameVille = rs.getString("ville.name");
                    int nbhab = rs.getInt("ville.nbhabitant");
                    Capitale cap = new Capitale(nameVille, s, nbhab);
                    cap.setId(villeId);
                    s.setCapitale(cap);
                }*/
                

                ps.add(f);
            }
            rs.close();

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

        return ps;
    }
    /*
    private Formation getFormation(ResultSet rs){
    	int id = rs.getInt("id");
        
        String name = rs.getString("name");
        int duration = rs.getInt("duration");
        Date date_debut = rs.getDate("date_debut");
        String lieu = rs.getString("lieu");
        
        
        return new Formation(id, name,duration,date_debut,lieu);
    }*/

    public static Formation findById(int numFormation) {
        Formation f = null;
        Connection c = DBConnect.getConnection();
        Statement stm;
        try {
            stm = c.createStatement();

            String sql = "select * from formation WHERE formation.id=" + numFormation;
            ResultSet rs = stm.executeQuery(sql);

            if (rs.next()) {
                int id = rs.getInt("id");
                
                String name = rs.getString("name");
                int duration = rs.getInt("duration");
                Date date_debut = rs.getDate("date_debut");
                String lieu = rs.getString("lieu");
                
                
                f = new Formation(id, name,duration,date_debut,lieu);

            }
            rs.close();

        } catch (SQLException e) {
            throw new RuntimeException();
        }

        return f;
    }

    public static List<Stagiaire> getStagiaires(Formation f) {
    	List<Stagiaire> stagiaires = new ArrayList<>();
    	
    	Connection c = DBConnect.getConnection();
        Statement stm;
        try {
            stm = c.createStatement();

            String sql = "select * from stagiaire left join gestion_stagiaire on stagiaire.id = gestion_stagiaire.idstagiaire left join formation on formation.id = gestion_stagiaire.idformation";
            //String sql = "select * from gestion_stagiaire WHERE idformation=" + f.getId();
            ResultSet rs = stm.executeQuery(sql);

            if (rs.next()) {
            	int idStagiaire = rs.getInt("stagiaire.id");
                String name = rs.getString("stagiaire.name");
                String firstname = rs.getString("stagiaire.firstname");
                String adresse = rs.getString("stagiaire.adresse");
                String code_postal = rs.getString("stagiaire.code_postal");
                String ville = rs.getString("stagiaire.ville");
                String email = rs.getString("stagiaire.email");
                String telephone = rs.getString("stagiaire.telephone");
                Date date = rs.getDate("stagiaire.date_naissance");
                
                
                
                Stagiaire s = new Stagiaire(idStagiaire, name, firstname, adresse, code_postal, ville, email, telephone, date, f);
                stagiaires.add(s);
            }
            rs.close();

        } catch (SQLException e) {
        	
            throw new RuntimeException();
        }

        return stagiaires;
    }
}
