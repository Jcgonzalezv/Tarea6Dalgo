package src;

import java.util.ArrayList;

public class FloydWarschall implements PathFinding
{

	@Override
	public int[][] costoMinimo(int[][] grafo) {
		int [][] rta = new int[grafo.length][grafo[0].length];
		
		int i = 0, j = 0, k = 0;
		int n = grafo.length;
		
		while(k < n)
		{
			if(k == 0)
			{
				if(grafo[i][j] == -1)
					rta[i][j] = 10000;
				else
					rta[i][j] = grafo[i][j];
			}
			else if(k > 0 && i != k && j != k)
				rta[i][j] = Math.min(rta[i][j], rta[i][k] + rta[k][j]);
			if(j < n-1)
				j++;
			else if(j == n-1 && i == n-1)
			{
				i = 0; 
				j = 0;
				k++;
			}
			else if(j == n-1)
			{
				i++;
				j = 0;
			}
			
				
		}
		
		return rta;
	}
	
	public int m(int i, int j, int k, int[][] grafo)
	{
		if(k == 0)
			return grafo[i][j];
		else
		{
			return Math.min(m(i, j, k-1, grafo), m(i, k, k-1, grafo) + m(k, j, k-1, grafo));
		}
	}

}
