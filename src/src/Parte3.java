package src;

import java.awt.List;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;

public class Parte3 {

	public static void main(String[] args) throws IOException {
		// TODO Auto-generated method stub
		Parte3 instance = new Parte3();
		instance.run("./data/distances1000.txt");
	}
	
	public void run(String filename) throws IOException 
	{
		int[][] grafo; 
		try (FileReader reader = new FileReader(filename);
			BufferedReader in = new BufferedReader(reader)) {
			String line = in.readLine();
			String [] items = line.split("\t| ");
			grafo = new int[items.length][items.length];
			for(int i = 0; i < grafo.length && line != null; i++)
			{
				items = line.split("\t| ");
				for(int j = 0; j < grafo[i].length; j++)
				{
					grafo[i][j] = Integer.parseInt(items[j]);
				}
				line = in.readLine();
				
			}			
		}
		long startTime;
		long endTime;
		
		startTime = System.currentTimeMillis();
		
		boolean hayCiclo = this.search4Cycle(grafo);
		
		endTime = System.currentTimeMillis();

		System.out.println("hay un ciclo en el grafo? : " + hayCiclo);

		System.out.println("Tiempo usado para BFS (en milisegundos): " + (endTime-startTime));
		
	}
	
	private Boolean search4Cycle(int[][] grafo)
	{
		 boolean[] visitados = new boolean[grafo.length]; 
	     boolean[] stack = new boolean[grafo.length]; 
	     
	     for (int i = 0; i < grafo.length; i++) {
			if(hayCiclosHasta(i, visitados, stack, grafo)) {
				return true;
			}
	     }
	     
	     return false;
	     
	    
		
	}
	
	private boolean hayCiclosHasta(int i, boolean[] visitados, boolean[] stack, int[][] grafo) {
		if(stack[i]) return true;
		if(visitados[i]) return false;
		
		visitados[i] = true;
		stack[i] = true;
		
		ArrayList<Integer> hijos = new ArrayList<Integer>();
		for (int j = 0; j < grafo.length; j++) {
			if(j != i && grafo[i][j]!= -1) {
				hijos.add(j);
			}
		}
		
		for (Integer h: hijos) {
			if(hayCiclosHasta(h, visitados, stack, grafo)) return true;
		}
		
		stack[i] = false;
		
		return false;		
		
		
	}

}
