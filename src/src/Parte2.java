package src;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.Queue;
import java.util.Set;

public class Parte2 {

	public static void main(String[] args) throws IOException {
		// TODO Auto-generated method stub
		Parte2 instance = new Parte2();
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
		
		startTime = System.currentTimeMillis();
		
		ArrayList bfs = this.BFS(grafo);
		
		endTime = System.currentTimeMillis();

		for(int i = 0; i < bfs.size(); i ++)
		{
			for(int j = 0; j < ((ArrayList) bfs.get(i)).size(); j++)
			{
				System.out.print(((ArrayList) bfs.get(i)).get(j) + "\t");
			}
			System.out.println();
		}

		System.out.println("Tiempo usado para BFS (en milisegundos): " + (endTime-startTime));
		
	}
	
	public ArrayList BFS(int[][] grafo) 
	{
		int start = 0;
		ArrayList answer = new ArrayList();
		ArrayList tmp = new ArrayList();
		Set visited = new HashSet<Integer>();
		while(visited.size() < grafo.length)
		{
			Queue<Integer> queue = new LinkedList<Integer>();
			queue.add(start);
			visited.add(start);
			while(queue.size()>0) {
				int next = queue.remove();
				tmp.add(next);
				for(int i=0;i<grafo.length;i++) {
					if(grafo[next][i] != -1 && !visited.contains(i)) {
						queue.add(i);
						visited.add(i);
					}
				}
			}
			answer.add(tmp);
			tmp = new ArrayList<>();
			if(visited.size() < grafo.length)
			{
				for(int i = 0; i < grafo.length; i++)
				{
					if(!visited.contains(i))
					{
						start = i;
						break;
					}
				}
			}
		}
		return answer;

	}

}
