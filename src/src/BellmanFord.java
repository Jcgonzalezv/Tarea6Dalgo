package src;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.Set;
import java.util.Stack;

public class BellmanFord implements PathFinding
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
		
		Set seen = new HashSet<Integer>();
		
		
		for(int i = 0; i < rta.length; i++)
			rta[i] = Integer.MAX_VALUE;
		rta[f] = 0;
		int v = f;
		Stack<Integer> s = new Stack<Integer>();
		for(int i = 0; i < (grafo.length-1); i++)
		{
			for(int j = 0; j < grafo.length; j++)
			{				
				if(grafo[v][j] > 0)
				{
					rta[j] = Math.min(rta[j], rta[v] + grafo[v][j]);
					if(!s.contains(j) && !seen.contains(j))
					{
						s.push(j);
					}
				}
			}
			seen.add(v);
			v = s.pop();
		}
		
		
		
		return rta;
	}
	
}
