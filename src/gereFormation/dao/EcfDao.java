package gereFormation.dao;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import gereFormation.modele.Ecf;
import gereFormation.modele.Formation;
import gereFormation.modele.Module;
import gereFormation.modele.Stagiaire;

public class EcfDao {
	public static void update(Ecf ec) throws Exception {
        Connection c = DBConnect.getConnection();
        PreparedStatement stm;
        try {

            stm = c.prepareStatement("UPDATE ecf SET validate = ? WHERE id = ?");
            stm.setInt(1, ec.getValidate());
            stm.setInt(2, ec.getId());

            stm.executeUpdate();

        } catch (SQLException e) {
            //pb if here
            throw new Exception("pb lors de la mise a jour de Formation:" + e.getMessage());
        }
    }

    public static void delete(Ecf ec) throws Exception {
        Connection c = DBConnect.getConnection();
        PreparedStatement stm;
        try {

            stm = c.prepareStatement("DELETE FROM ecf WHERE id = ?");
            stm.setInt(1, ec.getId());

            stm.executeUpdate();

        } catch (SQLException e) {

            throw new Exception("pb lors de la suppression de Formation:" + e.getMessage());
        }
    }

    public static void save(Ecf ec) throws Exception {

        Connection c = DBConnect.getConnection();
        PreparedStatement stm;

        stm = c.prepareStatement("INSERT INTO ecf (validate) VALUES (?)", Statement.RETURN_GENERATED_KEYS);
        stm.setInt(1, ec.getValidate());

        stm.execute();
        ResultSet rs = stm.getGeneratedKeys();

        if (rs.next()) {
            ec.setId(rs.getInt(1));
        }
        stm.close();

    }

    /**
     * retourne la liste des Formation
     *
     * @return
     */
    public static List<Ecf> findAll() {

        Connection c = DBConnect.getConnection();

        List<Ecf> ps = new ArrayList<>();
        Statement stm;
        try {
            stm = c.createStatement();

            String sql = "select * from ecf";
            ResultSet rs = stm.executeQuery(sql);

            while (rs.next()) {
                int id = rs.getInt("ecf.id");
                int validate = rs.getInt("ecf.validate");
                int id_stagiaire = rs.getInt("ecf.idstagiaire");
                int id_module = rs.getInt("ecf.idmodule");
                
                Ecf ec = new Ecf(id, id_stagiaire, id_module,validate);
                
               /* int villeId = rs.getInt("ville.id");
                if (!rs.wasNull()){ 
                    String nameVille = rs.getString("ville.name");
                    int nbhab = rs.getInt("ville.nbhabitant");
                    Capitale cap = new Capitale(nameVille, s, nbhab);
                    cap.setId(villeId);
                    s.setCapitale(cap);
                }*/
                

                ps.add(ec);
            }
            rs.close();

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

        return ps;
    }

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
}
