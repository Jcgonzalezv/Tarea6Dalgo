package src;

import java.awt.List;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.Queue;
import java.util.Set;

import javax.management.Query;

public class Dijkstra implements PathFinding
{

	@Override
	public int[][] costoMinimo(int[][] grafo) 
	{
		int[][] rta = new int[grafo.length][grafo[0].length];
		
		for(int i = 0; i < grafo.length; i++)
		{
			rta[i] = costoMinimo(grafo, i);
		}
		
		return rta;
	}
	
	public int[] costoMinimo(int[][] grafo, int f)
	{
		int[] rta = new int[grafo.length];
		
		Set a = new HashSet<Integer>();
		a.add(f);
		int v = f;
		
		for(int j = 0; j < grafo.length; j++)
		{
			if(grafo[v][j] != -1 && v != j)
			{				
				rta[j] = grafo[v][j];
			}
			else if(grafo[v][j] == -1 && rta[j] == 0)
			{
				rta[j] = Integer.MAX_VALUE;
			}
		}
							
		while(a.size() < grafo.length)	
		{
			int min = Integer.MAX_VALUE;
			for(int i = 0; i < rta.length; i++)
			{
				if(rta[i] < min && !a.contains(i))
				{
					min = rta[i];
					v = i;
				}
			}
			a.add(v);
			for(int i = 0; i < grafo.length; i++)
			{
				if(grafo[v][i] != -1 && !a.contains(i))
				{
					rta[i] = Math.min(rta[i], rta[v] + grafo[v][i]);
				}
			}
		}
		
		return rta;
	}

	
}
