package it.polito.tdp.genes.db;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import it.polito.tdp.genes.model.Coppie;
import it.polito.tdp.genes.model.Genes;
import it.polito.tdp.genes.model.Interactions;


public class GenesDao {
	
	public List<Genes> getAllGenes(){
		String sql = "SELECT DISTINCT GeneID, Essential, Chromosome FROM Genes";
		List<Genes> result = new ArrayList<Genes>();
		Connection conn = DBConnect.getConnection();

		try {
			PreparedStatement st = conn.prepareStatement(sql);
			ResultSet res = st.executeQuery();
			while (res.next()) {

				Genes genes = new Genes(res.getString("GeneID"), 
						res.getString("Essential"), 
						res.getInt("Chromosome"));
				result.add(genes);
			}
			res.close();
			st.close();
			conn.close();
			return result;
			
		} catch (SQLException e) {
			throw new RuntimeException("Database error", e) ;
		}
	}
	
	public List<String> getLocalization(){
		String sql = "SELECT DISTINCT Localization "
					+ "FROM classification "
					+ "ORDER BY Localization ASC";
		List<String> result = new ArrayList<String>();
		Connection conn = DBConnect.getConnection();

		try {
			PreparedStatement st = conn.prepareStatement(sql);
			ResultSet res = st.executeQuery();
			while (res.next()) {

				
				result.add(res.getString("Localization"));
			}
			res.close();
			st.close();
			conn.close();
			return result;
			
		} catch (SQLException e) {
			throw new RuntimeException("Database error", e) ;
		
	

	
}}
	
	public List<Coppie> getArchi(){
		String sql = "SELECT l1.Localization as l1, l2.Localization as l2,  COUNT(DISTINCT i.Type) as peso "
				+ "FROM interactions i, classification l1, classification l2 "
				+ "WHERE ((i.GeneID1 = l1.GeneID AND i.GeneID2 = l2.GeneID) OR (i.GeneID2 = l1.GeneID AND i.GeneID1 = l2.GeneID)) "
				+ "AND l1.Localization > l2.Localization "
				+ "GROUP BY l1.Localization, l2.Localization";
		
		List<Coppie> result = new ArrayList<Coppie>();
		Connection conn = DBConnect.getConnection();

		try {
			PreparedStatement st = conn.prepareStatement(sql);
			ResultSet res = st.executeQuery();
			while (res.next()) {

				
				result.add(new Coppie(res.getString("l1"), res.getString("l2"), res.getInt("peso")));
				
			}
			res.close();
			st.close();
			conn.close();
			return result;
			
		} catch (SQLException e) {
			throw new RuntimeException("Database error", e) ;
		
	

	
}}
	
	
	

}
