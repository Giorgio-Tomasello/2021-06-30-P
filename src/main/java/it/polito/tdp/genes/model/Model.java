package it.polito.tdp.genes.model;

import java.util.ArrayList;
import java.util.List;

import org.jgrapht.Graph;
import org.jgrapht.Graphs;
import org.jgrapht.graph.DefaultWeightedEdge;
import org.jgrapht.graph.SimpleWeightedGraph;

import it.polito.tdp.genes.db.GenesDao;


public class Model {
	
	private GenesDao dao;
	private Graph<String, DefaultWeightedEdge> grafo;
	List<Coppie> coppie; 
	List<String> vertici;
	
	public Model() {
		
		dao = new GenesDao();
		
	}
	
	
	
	public List<String> getVertici() {
		return vertici;
	}



	public String creaGrafo() {

		this.grafo = new SimpleWeightedGraph<String,DefaultWeightedEdge>(DefaultWeightedEdge.class);
		vertici = new ArrayList<String>(dao.getLocalization());
		coppie = new ArrayList<Coppie>(dao.getArchi()); 
		

		Graphs.addAllVertices(this.grafo, vertici);
		
		for(Coppie c : coppie)
			Graphs.addEdge(this.grafo, c.getL1(), c.getL2(), c.getPeso());
		

		String output = "GRAFO CREATO" + "\n" + "Numero vertici: " + this.grafo.vertexSet().size() + 
				"\nNumero Archi: " + this.grafo.edgeSet().size();

		return output;
		
}
	
}