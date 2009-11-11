package persistence.util;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;


public class NeighborComparator {
	private final String FILE_PATH = "neighbors.txt";
	private List<String> neighborIndexes;
	private Integer[][] distances;
	
	private static NeighborComparator uniqueInstance;
	
	public static NeighborComparator getInstance(){
		if(uniqueInstance != null){
			uniqueInstance = new NeighborComparator();
		}
		return uniqueInstance;
	}
	
	private NeighborComparator(){
		this.processFile();
		this.floydWarshall();
	}

	private void processFile() {
		try {
			this.neighborIndexes = new ArrayList<String>();
			Scanner reader = new Scanner(new File(FILE_PATH));
			String line = reader.nextLine();
			while(reader.hasNextLine() && !line.equals("#")){
				this.neighborIndexes.add(line);
				line = reader.nextLine();
			}
			this.distances = new Integer[this.neighborIndexes.size()][this.neighborIndexes.size()];
			this.fillInitially();
			while(reader.hasNextLine()){
				line = reader.nextLine();
				String[] pieces = line.split("#");
				try{
				int firstIndex = getIndex(pieces[0]);
				int secondIndex = getIndex(pieces[1]);
				this.distances[firstIndex][secondIndex] = new Integer(pieces[2]);
				this.distances[secondIndex][firstIndex] = new Integer(pieces[2]);
				}catch(ArrayIndexOutOfBoundsException exc){
					exc.printStackTrace();
				}
			}
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	private void fillInitially() {
		for (int i = 0; i < this.distances.length; i++) {
			for (int j = 0; j < this.distances.length; j++) {
				if(i == j){
					this.distances[i][j] = 1;
				}else{
					this.distances[i][j] = 1000000000; //adotando como infinito
				}
			}
		}
	}

	private int getIndex(String neighbor){
		return this.neighborIndexes.indexOf(neighbor);
	}
	
	private void floydWarshall(){
		Integer[][] predecessor = new Integer[this.distances.length][this.distances.length];
		for (int i = 0; i < this.distances.length; i++) {
			for (int j = 0; j < this.distances.length; j++) {
				if(this.distances[i][j] < Integer.MAX_VALUE){
					predecessor[i][j] = i;
				}
			}
		}
		for (int k = 0; k < this.distances.length; k++) {
			for (int i = 0; i < this.distances.length; i++) {
				for (int j = 0; j < this.distances.length; j++) {
					if(this.distances[i][j] > this.distances[i][k] + this.distances[k][j]){
						this.distances[i][j] = this.distances[i][k] + this.distances[k][j];
						predecessor[i][j] = predecessor[k][j];
					}
				}
			}
		}
	}
	
	public int getDistance(String neighbor1, String neighbor2){
		return this.distances[getIndex(neighbor1)][getIndex(neighbor2)];
	}
	
	public List<String> getNeigborList(){
		return this.neighborIndexes;
	}

	public boolean isANeighbor(String caseValue) {
		return this.neighborIndexes.contains(caseValue);
	}
}
