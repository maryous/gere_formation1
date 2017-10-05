package gereFormation.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import gereFormation.dao.ModuleDao;
import gereFormation.dao.StagiaireDao;

import gereFormation.modele.Module;
import gereFormation.modele.Stagiaire;

public class ModuleDao {
	public static void update(Module m) throws Exception {
        Connection c = DBConnect.getConnection();
        PreparedStatement stm;
        try {

            stm = c.prepareStatement("UPDATE module SET name = ? WHERE id = ?");
            stm.setString(1, m.getName());
            stm.setInt(2, m.getId());

            stm.executeUpdate();

        } catch (SQLException e) {
            //pb if here
            throw new Exception("pb lors de la mise a jour de Module:" + e.getMessage());
        }
    }

    public static void delete(Module m) throws Exception {
        Connection c = DBConnect.getConnection();
        PreparedStatement stm;
        try {

            stm = c.prepareStatement("DELETE FROM module WHERE id = ?");
            stm.setInt(1, m.getId());

            stm.executeUpdate();

        } catch (SQLException e) {

            throw new Exception("pb lors de la suppression de Module:" + e.getMessage());
        }
    }

    public static void save(Module m) throws Exception {

        Connection c = DBConnect.getConnection();
        PreparedStatement stm;

        stm = c.prepareStatement("INSERT INTO module (name,) VALUES (?)", Statement.RETURN_GENERATED_KEYS);
        stm.setString(1, m.getName());

        stm.execute();
        ResultSet rs = stm.getGeneratedKeys();

        if (rs.next()) {
            m.setId(rs.getInt(1));
        }
        stm.close();

    }

    /**
     * retourne la liste des Module
     *
     * @return
     */
    public static List<Module> findAll() {

        Connection c = DBConnect.getConnection();

        List<Module> ps = new ArrayList<>();
        // test avec select
        Statement stm;
        try {
            stm = c.createStatement();

            String sql = "select * from module" /*left join formation on Module.idformation = formation.id left join ecf on Module.id = ecf.idModule*/;
            ResultSet rs = stm.executeQuery(sql);

            while (rs.next()) {
                int id = rs.getInt("module.id");
                String name = rs.getString("module.name");
               
                
                Module m = new Module(id, name);
                
               /* int villeId = rs.getInt("ville.id");
                if (!rs.wasNull()){ 
                    String nameVille = rs.getString("ville.name");
                    int nbhab = rs.getInt("ville.nbhabitant");
                    Capitale cap = new Capitale(nameVille, s, nbhab);
                    cap.setId(villeId);
                    s.setCapitale(cap);
                }*/
                

                ps.add(m);
            }
            rs.close();

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

        return ps;
    }

    public static Module findById(int numModule) {
        Module m = null;
        Connection c = DBConnect.getConnection();
        Statement stm;
        try {
            stm = c.createStatement();

            String sql = "select * from Module WHERE Module.id=" + numModule;
            ResultSet rs = stm.executeQuery(sql);

            if (rs.next()) {
                int id = rs.getInt("id");
                String name = rs.getString("name");
                
                m = new Module(id, name);

            }
            rs.close();

        } catch (SQLException e) {
            throw new RuntimeException();
        }

        return m;
    }

}

