package gereFormation.dao;

import java.sql.Connection;
import java.util.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import gereFormation.modele.Stagiaire;

public class StagiaireDao {
	
	public static void update(Stagiaire s) throws Exception {
        Connection c = DBConnect.getConnection();
        PreparedStatement stm;
        try {
        	
            stm = c.prepareStatement("UPDATE stagiaire SET name = ? ,firstname = ?, adresse = ? ,code_postal = ? ,ville = ?, email = ? ,telephone = ?, date_naissance = ? WHERE id = ?");
            stm.setString(1, s.getName());
            stm.setString(2, s.getFirstname());
            stm.setString(3, s.getAdresse());
            stm.setString(4, s.getCode_postal());
            stm.setString(5, s.getVille());
            stm.setString(6, s.getEmail());
            stm.setString(7, s.getTelephone());
            stm.setDate(8, new java.sql.Date(s.getDate().getTime()));
            stm.setInt(9, s.getId());

            stm.executeUpdate();

        } catch (SQLException e) {
            //pb if here
            throw new Exception("pb lors de la mise a jour de Stagiaire:" + e.getMessage());
        }
    }

    public static void delete(Stagiaire s) throws Exception {
        Connection c = DBConnect.getConnection();
        PreparedStatement stm;
        try {

            stm = c.prepareStatement("DELETE FROM stagiaire WHERE id = ?");
            stm.setInt(1, s.getId());

            stm.executeUpdate();

        } catch (SQLException e) {

            throw new Exception("pb lors de la suppression de Stagiaire:" + e.getMessage());
        }
    }

    public static void save(Stagiaire s) throws Exception {

        Connection c = DBConnect.getConnection();
        PreparedStatement stm;

        stm = c.prepareStatement("INSERT INTO stagiaire (name,firstname, adresse, code_postal, ville, email, telephone, date_naissance)  VALUES (?, ?, ? ,?, ?, ?, ?, ?)", Statement.RETURN_GENERATED_KEYS);
        stm.setString(1, s.getName());
        stm.setString(2, s.getFirstname());
        stm.setString(3, s.getAdresse());
        stm.setString(4, s.getCode_postal());
        stm.setString(5, s.getVille());
        stm.setString(6, s.getEmail());
        stm.setString(7, s.getTelephone());
        //si on rentre pas de date 
        if (s.getDate()==null){
            stm.setDate(8, new java.sql.Date(new Date().getTime()));
        }else{
            stm.setDate(8, new java.sql.Date(s.getDate().getTime()));
        }
        
        stm.execute();
        ResultSet rs = stm.getGeneratedKeys();

        if (rs.next()) {
            s.setId(rs.getInt(1));
        }
        
        
        
        stm.close();

    }

    /**
     * retourne la liste des Stagiaire
     *
     * @return
     */
    public static List<Stagiaire> findAll() {

        Connection c = DBConnect.getConnection();

        List<Stagiaire> ps = new ArrayList<>();
        // test avec select
        Statement stm;
        try {
            stm = c.createStatement();

            String sql = "select * from stagiaire"; /*left join gestion_stagiaire on stagiaire.id = gestion_stagiaire.idstagiaire left join" +
            		" formation on formation.id = gestion_stagiaire.idformation WHERE gestion_stagiaire.idformation= ? "; */
            ResultSet rs = stm.executeQuery(sql);

            while (rs.next()) {
                int id = rs.getInt("stagiaire.id");
                String name = rs.getString("stagiaire.name");
                String firstname = rs.getString("stagiaire.firstname");
                String adresse = rs.getString("stagiaire.adresse");
                String code_postal = rs.getString("stagiaire.code_postal");
                String ville = rs.getString("stagiaire.ville");
                String email = rs.getString("stagiaire.email");
                String telephone = rs.getString("stagiaire.telephone");
                Date date = rs.getDate("stagiaire.date_naissance");
                 //int idformation = rs.getInt("gestion_stagiaire.idformation");
                
               // Formation formation = FormationDao.findById(idformation);
                
                Stagiaire s = new Stagiaire(id, name, firstname, adresse, code_postal, ville, email, telephone, date, null);
                
               /* int villeId = rs.getInt("ville.id");
                if (!rs.wasNull()){ 
                    String nameVille = rs.getString("ville.name");
                    int nbhab = rs.getInt("ville.nbhabitant");
                    Capitale cap = new Capitale(nameVille, s, nbhab);
                    cap.setId(villeId);
                    s.setCapitale(cap);
                }*/
                

                ps.add(s);
            }
            rs.close();

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

        return ps;
    }

    public static Stagiaire findById(int numStagiaire) {
        Stagiaire s = null;
        Connection c = DBConnect.getConnection();
        Statement stm;
        try {
            stm = c.createStatement();

            String sql = "select * from stagiaire WHERE id=" + numStagiaire;
            ResultSet rs = stm.executeQuery(sql);

            if (rs.next()) {
            	int id = rs.getInt("stagiaire.id");
                String name = rs.getString("stagiaire.name");
                String firstname = rs.getString("stagiaire.firstname");
                String adresse = rs.getString("stagiaire.adresse");
                String code_postal = rs.getString("stagiaire.code_postal");
                String ville = rs.getString("stagiaire.ville");
                String email = rs.getString("stagiaire.email");
                String telephone = rs.getString("stagiaire.telephone");
                Date date = rs.getDate("stagiaire.date_naissance");
                
                s = new Stagiaire(name, firstname, adresse, code_postal, ville, email, telephone, date);

            }
            rs.close();

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

        return s;
    }

}
