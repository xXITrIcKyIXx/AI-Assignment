package AIPRM;

import java.awt.Container;
import java.io.File;
import java.io.IOException;
import javax.swing.JFileChooser;
import javax.swing.JOptionPane;
import java.io.BufferedReader;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.List;

public class Main {

	private static Container container;
	private static File defaultPath;
	static ArrayList<Obstacle> obstacles;
	private static int asvCount;
	private static State initialState;
	private static State goalState;
	
	public static void randomSpawnPoint()
	{
		boolean collide;
		double[] coord;
		do
		{
			coord = new double[2]; 
			coord[0] = Math.random();
			coord[1] =  Math.random();
			collide = false;
			for(int i = 0; i < obstacles.size(); i++)
			{
				collide = obstacles.get(i).CheckCollision(coord);
				if(collide == true)
				{
					break;
				}
			}
		}while(collide == true);
		
		interpolatePoints(coord);
	}
	
	public static void interpolatePoints(double[] coords)
	{
		double xInterval = 
	}
	
	private static File askForFile() {
		defaultPath = null;
		JFileChooser fc = new JFileChooser(defaultPath);
		int returnVal = fc.showOpenDialog(container);
		if (returnVal != JFileChooser.APPROVE_OPTION) {
			return null;
		}
		return fc.getSelectedFile();
	}
	
	public static void loadProblem(String filename) throws IOException {
		BufferedReader input = new BufferedReader(new FileReader(filename));
		try {
			asvCount = Integer.valueOf(input.readLine().trim());
			initialState = new State(asvCount, input.readLine().trim());
			goalState = new State(asvCount, input.readLine().trim());
			
			int numObstacles = Integer.valueOf(input.readLine().trim());
			obstacles = new ArrayList<Obstacle>();
			for (int i = 0; i < numObstacles; i++) {
				obstacles.add(new Obstacle(input.readLine().trim()));
			}
			input.close();
		} catch (NumberFormatException e) {
			throw new IOException("Invalid number format.");
		} catch (IndexOutOfBoundsException e) {
			throw new IOException("Invalid format; not enough tokens in a line.");
		}
	}
	
	private static void showFileError(File f) {
		JOptionPane.showMessageDialog(container,
				"Error loading " + f.getName(),
				"File I/O Error",
				JOptionPane.ERROR_MESSAGE);
	}
	
	private static void loadProblem(File f) {
		try {
			loadProblem(f.getPath());
		} catch (IOException e1) {
			showFileError(f);
		}
	}
	
	private static void loadProblem() {
		File f = askForFile();
		if (f == null) {
			return;
		}
		loadProblem(f);	
	}
	
	public static void main(String[] args)
	{
		loadProblem();
		//File newFile = askForFile();
		randomSpawnPoint();
		//System.out.println(newFile.getPath());
	}
	
}
