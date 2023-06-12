package src;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

public class Parte1 {

	public static void main(String[] args) throws IOException {
		// TODO Auto-generated method stub
		Parte1 instance = new Parte1();
		instance.run("./data/distances100.txt");
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
		
		
		Dijkstra djk = new Dijkstra();
		
		startTime = System.currentTimeMillis();
		
		int[][] dijkstraMin = djk.costoMinimo(grafo);
		
		endTime = System.currentTimeMillis();
		/*
		for(int i = 0; i < dijkstraMin.length; i ++)
		{
			for(int j = 0; j < dijkstraMin[i].length; j++)
			{
				System.out.print(dijkstraMin[i][j] + "\t");
			}
			System.out.println();
		}
		*/
		System.out.println("Tiempo usado por Dijkstra (en milisegundos): " + (endTime-startTime));
		
		System.out.println("--------------------------------------------");
		
		BellmanFord bell = new BellmanFord();
		
		startTime = System.currentTimeMillis();
		
		int[][] bellMin = bell.costoMinimo(grafo);
		
		endTime = System.currentTimeMillis();
		/*
		for(int i = 0; i < bellMin.length; i ++)
		{
			for(int j = 0; j < bellMin[i].length; j++)
			{
				System.out.print(bellMin[i][j] + "\t");
			}
			System.out.println();
		}
		*/
		System.out.println("Tiempo usado por Bellman Ford (en milisegundos): " + (endTime-startTime));
		
		
		
		System.out.println("--------------------------------------------");
		
		FloydWarschall floyd = new FloydWarschall();
		
		startTime = System.currentTimeMillis();
		
		int[][] floydMin = floyd.costoMinimo(grafo);
		
		endTime = System.currentTimeMillis();
		/*
		for(int i = 0; i < floydMin.length; i ++)
		{
			for(int j = 0; j < floydMin[i].length; j++)
			{
				System.out.print(floydMin[i][j] + "\t");
			}
			System.out.println();
		}
		*/
		System.out.println("Tiempo usado por Floyd Warschall (en milisegundos): " + (endTime-startTime));
		
		
		if(filename.equalsIgnoreCase("./data/distances1000.txt")) {
			System.out.println("el tamaño del grafo es muy grande para mostrar la matriz de costos minimos,\n por favor elija otro archivo de fuente");
		}else {
			System.out.println("matriz costos minimos\n");
			
			for(int i = 0; i < dijkstraMin.length; i ++)
			{
				for(int j = 0; j < dijkstraMin[i].length; j++)
				{
					System.out.print(dijkstraMin[i][j] + "\t");
				}
				System.out.println();
			}
		}		
	}
	
	

}
