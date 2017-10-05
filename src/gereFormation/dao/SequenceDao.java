package gereFormation.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import gereFormation.modele.Sequence;

public class SequenceDao {
	
	public static void update(Sequence se) throws Exception {
        Connection c = DBConnect.getConnection();
        PreparedStatement stm;
        try {

            stm = c.prepareStatement("UPDATE sequence SET name = ? WHERE id = ?");
            stm.setString(1, se.getName());
            stm.setInt(2, se.getId());

            stm.executeUpdate();

        } catch (SQLException e) {
            //pb if here
            throw new Exception("pb lors de la mise a jour de Sequence:" + e.getMessage());
        }
    }

    public static void delete(Sequence se) throws Exception {
        Connection c = DBConnect.getConnection();
        PreparedStatement stm;
        try {

            stm = c.prepareStatement("DELETE FROM sequence WHERE id = ?");
            stm.setInt(1, se.getId());

            stm.executeUpdate();

        } catch (SQLException e) {

            throw new Exception("pb lors de la suppression de Sequence:" + e.getMessage());
        }
    }

    public static void save(Sequence se) throws Exception {

        Connection c = DBConnect.getConnection();
        PreparedStatement stm;

        stm = c.prepareStatement("INSERT INTO sequence (name) VALUES (?)", Statement.RETURN_GENERATED_KEYS);
        stm.setString(1, se.getName());

        stm.execute();
        ResultSet rs = stm.getGeneratedKeys();

        if (rs.next()) {
            se.setId(rs.getInt(1));
        }
        stm.close();

    }

    /**
     * retourne la liste des Sequence
     *
     * @return
     */
    public static List<Sequence> findAll() {

        Connection c = DBConnect.getConnection();

        List<Sequence> ps = new ArrayList<>();
        // test avec select
        Statement stm;
        try {
            stm = c.createStatement();

            String sql = "select * from sequence" /*left join formation on Sequence.idformation = formation.id left join ecf on Sequence.id = ecf.idSequence*/;
            ResultSet rs = stm.executeQuery(sql);

            while (rs.next()) {
                int id = rs.getInt("sequence.id");
                String name = rs.getString("sequence.name");
               
                
                Sequence se = new Sequence(id, name);
                
               /* int villeId = rs.getInt("ville.id");
                if (!rs.wasNull()){ 
                    String nameVille = rs.getString("ville.name");
                    int nbhab = rs.getInt("ville.nbhabitant");
                    Capitale cap = new Capitale(nameVille, s, nbhab);
                    cap.setId(villeId);
                    s.setCapitale(cap);
                }*/
                

                ps.add(se);
            }
            rs.close();

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

        return ps;
    }

    public static Sequence findById(int numSequence) {
        Sequence se = null;
        Connection c = DBConnect.getConnection();
        Statement stm;
        try {
            stm = c.createStatement();

            String sql = "select * from sequence WHERE sequence.id=" + numSequence;
            ResultSet rs = stm.executeQuery(sql);

            if (rs.next()) {
                int id = rs.getInt("id");
                String name = rs.getString("name");
                
                se = new Sequence(id, name);

            }
            rs.close();

        } catch (SQLException e) {
            throw new RuntimeException();
        }

        return se;
    }


}
