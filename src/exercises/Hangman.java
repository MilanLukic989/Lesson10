package exercises;

import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.io.File;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.Stack;

import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

import examples.FileHelper;

/*Finish the Hangman game so that it checks for correct answers and loads a new puzzle if it was correct.
 * Use the full list of words instead of hard-coded puzzles for Hangman.
 * Modify your Hangman program from Lesson 10 so that an exception is thrown when a special character is found.
 * If a word loaded from the list contains special characters, print a warning to the console and present the user with the subsequent puzzle instead.*/

public class Hangman extends KeyAdapter {

//	Stack<String> puzzles = new Stack<String>();
	private List<String> puzzles;
	ArrayList<JLabel> boxes = new ArrayList<JLabel>();
	int lives = 9;
	JLabel livesLabel = new JLabel("" + lives);
	JFrame frame = new JFrame("June's Hangman");

	public static void main(String[] args) {
		Hangman hangman = new Hangman();
		hangman.addPuzzles();
		hangman.createUI();
	}

//	private void addPuzzles() {
//		puzzles.push("defenestrate");
//		puzzles.push("fancypants");
//		puzzles.push("elements");
//	}

	public void addPuzzles() {
		puzzles = FileHelper.loadFileContentsIntoArrayList("resource/words.txt");
	}

	JPanel panel = new JPanel();
	private String puzzle;

	private void createUI() {
//		playDeathKnell();
//		JFrame frame = new JFrame("June's Hangman");
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		panel.add(livesLabel);
		loadNextPuzzle();
		frame.add(panel);
		frame.setVisible(true);
		frame.pack();
		frame.addKeyListener(this);
	}

	private void loadNextPuzzle() {
		removeBoxes();
		frame.repaint();
		lives = 9;
		livesLabel.setText("" + lives);
		Random random = new Random();
		int num = random.nextInt(puzzles.size());
		puzzle = puzzles.get(num);
		try {
			if (!puzzle.matches("[A-Za-z]+")) {
				throw new Exception("Word " + puzzle + " contains special characters!");
			}
			System.out.println("puzzle is now " + puzzle);
			createBoxes();
		} catch (Exception ex) {
			System.out.println(ex.getMessage());
			loadNextPuzzle();
		}
	}

	public void keyTyped(KeyEvent arg0) {
		System.out.println(arg0.getKeyChar());
		if (lives == 0) {
			playDeathKnell();
//			loadNextPuzzle();
		} else {
			updateBoxesWithUserInput(arg0.getKeyChar());
		}
	}

	private void updateBoxesWithUserInput(char keyChar) {
		boolean gotOne = false;

		for (int i = 0; i < puzzle.length(); i++) {
			if (puzzle.charAt(i) == keyChar) {
				boxes.get(i).setText("" + keyChar);
				gotOne = true;
			}
		}
		if (!gotOne) {
			livesLabel.setText("" + --lives);
		} else {
			boolean wordFinished = true;
			for (int i = 0; i < boxes.size(); i++) {
				if (boxes.get(i).getText() == "_") {
					wordFinished = false;
				}
			}
			if (wordFinished) {
				loadNextPuzzle();
			}
		}
	}

	void createBoxes() {
		for (int i = 0; i < puzzle.length(); i++) {
			JLabel textField = new JLabel("_");
			boxes.add(textField);
			panel.add(textField);
		}
	}

	void removeBoxes() {
		for (JLabel box : boxes) {
			panel.remove(box);
		}
		boxes.clear();
	}

	public void playDeathKnell() {
		try {
			AudioInputStream audioInputStream = AudioSystem.getAudioInputStream(new File("resource/funeral-march.wav"));
			Clip clip = AudioSystem.getClip();
			clip.open(audioInputStream);
			clip.start();
			Thread.sleep(8400);
		} catch (Exception ex) {
			ex.printStackTrace();
		}
	}

}
