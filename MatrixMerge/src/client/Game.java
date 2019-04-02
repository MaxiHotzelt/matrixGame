package client;

import java.awt.Graphics2D;

import javax.swing.JButton;

public class Game {
	
	private JButton backBtn = new JButton("Menu");
	private Container container;
	private String soughtWord;
	
	private MatrixString matrixString;
	private SolutionString solutionString;
	
	//getter
	public Container getContainer() { return this.container; }
	public String getSoughtWord() { return this.soughtWord; }
	public SolutionString getSolutionString() { return this.solutionString; }
	
	public Game(Container container) {
		this.container = container;
		
		
		backBtn.addActionListener(container);
	}
	
	public void start() {
		soughtWord = container.getFileUtils().getRandomWord();
		System.out.println(soughtWord);
		

		this.solutionString = new SolutionString(this);
		this.matrixString = new MatrixString(this);
		
		
		this.matrixString.setRunning(true);
		this.matrixString.start();
		
		this.solutionString.start();
	}
	
	public void stop() {
		this.matrixString.setRunning(false);
		this.matrixString.interrupt();
		
		this.solutionString.interrupt();
	}
	
	public void addComponents() {
		container.add(backBtn);
	}
	
	public void removeComponents() {
		container.remove(backBtn);
	}
	
	public void paintComponent(Graphics2D g) {
		matrixString.render(g);
		solutionString.render(g);
	}
}