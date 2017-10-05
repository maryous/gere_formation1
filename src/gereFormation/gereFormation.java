package gereFormation;

import java.util.Date;
import java.util.List;

import gereFormation.dao.EcfDao;
import gereFormation.dao.FormationDao;
import gereFormation.dao.ModuleDao;
import gereFormation.dao.SequenceDao;
import gereFormation.dao.StagiaireDao;
import gereFormation.modele.Ecf;
import gereFormation.modele.Formation;
import gereFormation.modele.Module;
import gereFormation.modele.Sequence;
import gereFormation.modele.Stagiaire;


public class gereFormation {

	/**
	 * @param args
	 * @throws Exception 
	 */
	public static void main(String[] args) throws Exception {
		
		
		
		List<Stagiaire> stagiaires = StagiaireDao.findAll();
		List<Sequence> sequences = SequenceDao.findAll();
		List<Formation> formations = FormationDao.findAll();
		List<Module> modules = ModuleDao.findAll();
		List<Ecf> ecfs = EcfDao.findAll();
		// TODO Auto-generated method stub
		Stagiaire stagi = new Stagiaire("golgot","henry","15 rue fdsfs","25242","mon cul","hasnae@claquetachatte.com","3615hulla", new Date());
                //creer une nouvelle formation
                Formation form = new Formation("concepteur",8,new Date(),"PAris");
		try {
			StagiaireDao.save(stagi);
		} catch(Exception e) {
			System.out.println(e.getMessage());
		}
                try {
			FormationDao.save(form);
		} catch(Exception e) {
			System.out.println(e.getMessage());
		}
		
		for (Stagiaire stagiaire : stagiaires) {
			System.out.println(stagiaire);
		}
		
		for (Sequence sequence : sequences) {
			System.out.println(sequence);
		}
		
		for (Formation formation : formations) {
			System.out.println(formation);
		}
		
		for (Module module : modules) {
			System.out.println(module);
		}
	}

}
