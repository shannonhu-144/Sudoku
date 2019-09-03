import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;

import javax.swing.Timer;

import java.util.ArrayList;

import javax.swing.JButton;
import javax.swing.JComponent;
import javax.swing.JTextArea;
import javax.swing.JTextField;

// Note that the JComponent is set up to listen for mouse clicks
// and mouse movement.  To achieve this, the MouseListener and
// MousMotionListener interfaces are implemented and there is additional
// code in init() to attach those interfaces to the JComponent.


public class Display extends JComponent implements MouseListener, MouseMotionListener {
	public static final int ROWS = 9;
	public static final int COLS = 9;
	public static Cell[][] cell = new Cell[ROWS][COLS];
	public static int[][] solution;
	public static int[][] guess = new int[ROWS][COLS];
	private final int X_GRID_OFFSET = 25; // 25 pixels from left
	private final int Y_GRID_OFFSET = 40; // 40 pixels from top
	private final int CELL_WIDTH = 40;
	private final int CELL_HEIGHT = 40;
	public static int givens;
	private static int timeElapsed;
	private static JTextField[][] fields;
	private static JTextField victory;
	private static JTextArea setGivens;
	private static JTextField Givens;
	private static JTextArea Possibilities;
	private static JTextField getPossibilities;
	private static JTextArea Time;
	private static JTextField Replot_Time;

	// Note that a final field can be initialized in constructor
	private final int DISPLAY_WIDTH;   
	private final int DISPLAY_HEIGHT;
	private boolean paintloop = false;
	private boolean possibilities = false;
	private static boolean givensModeOn = false;
	private int TIME_BETWEEN_REPLOTS;
	private static CompareButton compare;
	private NewGameButton newgame;
	private GenerateButton generate;
	private SuperHardButton impossible;
	private HardButton hard;
	private MediumButton medium;
	private EasyButton easy;
	private JTextArea stopwatch;
	private static Timer timer;
	private PauseButton pause;
	private QuitButton quit;
	private HintButton hint;
	private SudokuSolver solve;
	private SolveButton start;
	private TogglePossibilitiesButton toggle;
	private FastButton fast;
	private NormalButton normal;
	private SlowButton slow;
	private ClearButton clear;


	public Display(int width, int height) {
		DISPLAY_WIDTH = width;
		DISPLAY_HEIGHT = height;
		solution = randomSolutionSelector();
		timeElapsed = 0;
		givens = 45;
		init();
		setPaintLoop(false);
		TIME_BETWEEN_REPLOTS = 500;
	}
	public static int[][] randomSolutionSelector()
	{
		Sudoku sudoku = new Sudoku();
		sudoku.solutionGenerator();
		return sudoku.getSolution();
	}
	public void init() {
		setSize(DISPLAY_WIDTH, DISPLAY_HEIGHT);
		initFields();
		initCells();
		TIME_BETWEEN_REPLOTS = 500;
		addMouseListener(this);
		addMouseMotionListener(this);
		compare = new CompareButton();
		compare.setBounds(25, 535, 100, 36);
		add(compare);
		compare.setVisible(true);
		newgame = new NewGameButton();
		newgame.setBounds(125, 535, 100, 36);
		add(newgame);
		newgame.setVisible(true);
		generate = new GenerateButton();
		generate.setToolTipText("In generate mode, you can press g when in a square to toggle whether it is editable or not");
		generate.setBounds(675, 214, 100, 36);
		add(generate);
		generate.setVisible(true);
		setGivens = new JTextArea("Givens:");
		add(setGivens);
		setGivens.setBounds(520, 260, 65, 16);
		setGivens.setVisible(true);
		setGivens.setEditable(false);
		setGivens.setBackground(getBackground());
		Givens = new JTextField("45");
		add(Givens);
		Givens.setBounds(585, 250, 190, 36);
		Givens.setVisible(true);
		Givens.setHorizontalAlignment(JTextField.RIGHT);
		Givens.addKeyListener(new KeyListener() {
			public void keyPressed(KeyEvent e) {
				
			}
			public void keyTyped(KeyEvent e) {
				if ((!Character.isDigit(e.getKeyChar()) && !("" + e.getKeyChar()).equals("g")) && (e.getKeyChar() != KeyEvent.VK_BACK_SPACE))
					e.consume();
			}
			public void keyReleased(KeyEvent e) {
				
			}
		});
		Possibilities = new JTextArea("Possibilities:");
		add(Possibilities);
		Possibilities.setBounds(520, 294, 95, 16);
		Possibilities.setVisible(true);
		Possibilities.setEditable(false);
		Possibilities.setBackground(getBackground());
		getPossibilities = new JTextField("");
		add(getPossibilities);
		getPossibilities.setBounds(615, 284, 160, 36);
		getPossibilities.setVisible(false);
		getPossibilities.setHorizontalAlignment(JTextField.RIGHT);
		Time = new JTextArea("Replot Time (ms):");
		add(Time);
		Time.setBounds(520, 330, 125, 16);
		Time.setVisible(true);
		Time.setEditable(false);
		Time.setBackground(getBackground());
		Replot_Time = new JTextField("500");
		add(Replot_Time);
		Replot_Time.setBounds(645, 320, 130, 36);
		Replot_Time.setVisible(true);
		Replot_Time.setHorizontalAlignment(JTextField.RIGHT);
		Replot_Time.addKeyListener(new KeyListener() {
			public void keyPressed(KeyEvent e) {
				
			}
			public void keyTyped(KeyEvent e) {
				if ((!Character.isDigit(e.getKeyChar()) && !("" + e.getKeyChar()).equals("g")) && (e.getKeyChar() != KeyEvent.VK_BACK_SPACE))
					e.consume();
			}
			public void keyReleased(KeyEvent e) {
				
			}
		});
		impossible = new SuperHardButton();
		impossible.setBounds(675, 178, 100, 36);
		add(impossible);
		impossible.setVisible(true);
		hard = new HardButton();
		hard.setBounds(675, 142, 100, 36);
		add(hard);
		hard.setVisible(true);
		medium = new MediumButton();
		medium.setBounds(675, 106, 100, 36);
		add(medium);
		medium.setVisible(true);
		easy = new EasyButton();
		easy.setBounds(675, 70, 100, 36);
		add(easy);
		easy.setVisible(true);
		stopwatch = new JTextArea("Timer: " + timeElapsed);
		stopwatch.setBounds(450, 40, 100, 36);
		add(stopwatch);
		stopwatch.setVisible(true);
		stopwatch.setEditable(false);
		stopwatch.setBackground(getBackground());
		pause = new PauseButton();
		pause.setBounds(225, 535, 100, 36);
		add(pause);
		pause.setVisible(true);
		JTextArea credits = new JTextArea("Credits: Created by Shannon Hu");
		credits.setBounds(25, 420, 700, 36);
		add(credits);
		credits.setVisible(true);
		credits.setEditable(false);
		credits.setBackground(getBackground());
		victory = new JTextField("You Win!");
		victory.setBounds(25, 465, 700, 60);
		victory.setHorizontalAlignment(JTextField.CENTER);
		Font font1 = new Font("SansSerif", Font.BOLD, 50);
		victory.setFont(font1);
		add(victory);
		victory.setVisible(false);
		victory.setEditable(false);
		toggle = new TogglePossibilitiesButton();
		toggle.setBounds(25, 586, 300, 36);
		add(toggle);
		toggle.setVisible(true);
		quit = new QuitButton();
		quit.setBounds(625, 535, 100, 36);
		add(quit);
		quit.setVisible(true);
		clear = new ClearButton();
		clear.setBounds(525, 535, 100, 36);
		add(clear);
		clear.setVisible(true);
		hint = new HintButton();
		hint.setBounds(325, 535, 100, 36);
		add(hint);
		hint.setVisible(true);
		start = new SolveButton();
		start.setBounds(425, 535, 100, 36);
		add(start);
		start.setVisible(true);
		fast = new FastButton();
		fast.setBounds(425, 571, 100, 36);
		add(fast);
		fast.setVisible(true);
		normal = new NormalButton();
		normal.setBounds(425, 607, 100, 36);
		add(normal);
		normal.setVisible(true);
		slow = new SlowButton();
		slow.setBounds(425, 643, 100, 36);
		add(slow);
		slow.setVisible(true);
		timer = new Timer(1000, new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				SudokuSolver test = new SudokuSolver(guess);
				if (!test.done())
				timeElapsed++;
				stopwatch.setText("Timer: " + timeElapsed);
			}
		});
		timer.start();
	}


	private void initFields() {
		fields = new JTextField[ROWS][COLS];
		for(int i = 0; i < ROWS; i++)
		{
			for(int j = 0; j < COLS; j++)
			{
				final int row = i;
				final int col = j;
				fields[i][j] = new JTextField();
				fields[i][j].setLocation(X_GRID_OFFSET + 1 + (j * (CELL_WIDTH + 1)), Y_GRID_OFFSET + 1 + (i * (CELL_HEIGHT + 1)));
				fields[i][j].setSize(CELL_WIDTH, CELL_HEIGHT);
				fields[i][j].setHorizontalAlignment(JTextField.CENTER);
				fields[i][j].addMouseListener(new MouseAdapter() {
					public void mouseClicked(MouseEvent e) {
						for (int i = 0; i < ROWS; i++)
							for (int j = 0; j < COLS; j++)
							{
								if (!(fields[i][j].getText().equals("")))
									guess[i][j] = Integer.parseInt(fields[i][j].getText());
								else guess[i][j] = 0;
							}
						getPossibilities.setText("");
						solve = new SudokuSolver(guess);
						solve.evaluatePossibilities();
						int[][][] possibilities = solve.getPossibilities();
						for (int k = 0; k < possibilities[0][0].length; k++)
						{
							if (possibilities[row][col][k] != 0) {
								getPossibilities.setText(getPossibilities.getText() + possibilities[row][col][k] + ", ");
							}}
					}
				});
				fields[i][j].addKeyListener(new KeyListener() {
					public void keyPressed(KeyEvent e)
					{
						if (((!Character.isDigit(e.getKeyChar()) && !("" + e.getKeyChar()).equals("g"))) && (e.getKeyChar() != KeyEvent.VK_BACK_SPACE))
							e.consume();
						else if (e.getKeyChar() == KeyEvent.VK_BACK_SPACE)
						{
							
						}
						else if (Character.isDigit(e.getKeyChar()) && fields[row][col].getText().length() > 0)
							{
							e.consume();
							}
						else e.consume();
					}
					public void keyReleased(KeyEvent e)
					{
					
					}
					public void keyTyped(KeyEvent e)
					{
						if ((!Character.isDigit(e.getKeyChar()) && !("" + e.getKeyChar()).equals("g")) && (e.getKeyChar() != KeyEvent.VK_BACK_SPACE))
							e.consume();
						else if (Character.isDigit(e.getKeyChar()) && fields[row][col].getText().length() > 0)
							{
							e.consume();
							}
						else if (("" +e.getKeyChar()).equals("g") && givensModeOn)
						{
							fields[row][col].setEditable(!fields[row][col].isEditable());
							e.consume();
						}
						else if (Character.isDigit(e.getKeyChar()))
						{
							
						}
						else e.consume();
					}
				});
				if (Math.random() <= givens / (ROWS * COLS * 1.0))
				{
					fields[i][j].setText("" + solution[i][j]);
					fields[i][j].setBackground(Color.CYAN);
					fields[i][j].setEditable(false);
				}
				else {
					fields[i][j].setText("");
					fields[i][j].setBackground(Color.white);
					fields[i][j].setEditable(true);
				}
				add(fields[i][j]);
			}
		}
	}
	public void paintComponent(Graphics g) {
		// change to your liking
		if (Replot_Time.getText().equals(""))
			TIME_BETWEEN_REPLOTS = 500;
		else TIME_BETWEEN_REPLOTS = Integer.parseInt(Replot_Time.getText());
		g.setColor(Color.BLACK);
		Graphics2D g2 = (Graphics2D) g;
		drawGrid(g2);
		drawCells(g2);

		if (paintloop) {
			try {
				Thread.sleep(TIME_BETWEEN_REPLOTS);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
			for (int i = 0; i < ROWS; i++)
				for (int j = 0; j < COLS; j++)
				{
					if (!(fields[i][j].getText().equals("")))
						guess[i][j] = Integer.parseInt(fields[i][j].getText());
					else guess[i][j] = 0;
				}
			solve = new SudokuSolver(guess);
			solve.Solve();
			repaint();
		}
	}


	public void initCells() {
		for (int row = 0; row < ROWS; row++) {
			for (int col = 0; col < COLS; col++) {
				cell[row][col] = new Cell(row, col, solution[row][col]);
			}
		}
	}


	public void togglePaintLoop() {
		paintloop = !paintloop;
		repaint();
	}


	public void setPaintLoop(boolean value) {
		paintloop = value;
	}
	public void togglePossibilities() {
		possibilities = !possibilities;
		if (possibilities)
			getPossibilities.setVisible(true);
		else getPossibilities.setVisible(false);

	}

	void drawGrid(Graphics2D g2) {
		for (int row = 0; row <= ROWS; row++) {
			if (row == 0 || row == 3 || row == 6 || row == 9)
				g2.setStroke(new BasicStroke(10));
			else g2.setStroke(new BasicStroke(3));
			g2.drawLine(X_GRID_OFFSET,
					Y_GRID_OFFSET + (row * (CELL_HEIGHT + 1)), X_GRID_OFFSET
					+ COLS * (CELL_WIDTH + 1), Y_GRID_OFFSET
					+ (row * (CELL_HEIGHT + 1)));
		}
		for (int col = 0; col <= COLS; col++) {
			if (col == 0 || col == 3 || col == 6 || col == 9)
				g2.setStroke(new BasicStroke(10));
			else g2.setStroke(new BasicStroke(5));
			g2.drawLine(X_GRID_OFFSET + (col * (CELL_WIDTH + 1)), Y_GRID_OFFSET,
					X_GRID_OFFSET + (col * (CELL_WIDTH + 1)), Y_GRID_OFFSET
					+ ROWS * (CELL_HEIGHT + 1));
		}
	}


	void drawCells(Graphics2D g) {
		// Have each cell draw itself
		g.setStroke(new BasicStroke(3));
		for (int row = 0; row < ROWS; row++) {
			for (int col = 0; col < COLS; col++) {
				// The cell cannot know for certain the offsets nor the height
				// and width; it has been set up to know its own position, so
				// that need not be passed as an argument to the draw method
				cell[row][col].draw(X_GRID_OFFSET, Y_GRID_OFFSET, CELL_WIDTH,
						CELL_HEIGHT, g);
			}
		}
	}
	public boolean compareTo(int[][] arr1, int[][] arr2)
	{
		boolean same = false;
		int[][] attempt = arr1;
		for(int i = 0; i < ROWS; i++)
		{
			for (int j = 0; j < COLS; j++)
			{
				if(attempt[i][j] != 0)
				{
					int[] col = new int[ROWS];
					for (int k = 0; k < ROWS; k++)
						col[k] = attempt[k][j];
					int[] square = new int[9];
					int urow = 0;
					int drow = 0;
					int lcol = 0;
					int rcol = 0;
					if (i % 3 == 0)
					{
						urow = i + 1;
						drow = i + 2;
					}
					else if (i % 3 == 1)
					{
						urow = i - 1;
						drow = i + 1;
					}
					else {
						urow = i - 2;
						drow = i - 1;
					}
					if (j % 3 == 0)
					{
						lcol = j + 1;
						rcol = j + 2;
					}
					else if (j % 3 == 1)
					{
						lcol = j - 1;
						rcol = j + 1;
					}
					else {
						lcol = j - 2;
						rcol = j - 1;
					}
					square[0] = attempt[urow][lcol];
					square[1] = attempt[urow][j];
					square[2] = attempt[urow][rcol];
					square[3] = attempt[i][lcol];
					square[4] = attempt[i][j];
					square[5] = attempt[i][rcol];
					square[6] = attempt[drow][lcol];
					square[7] = attempt[drow][j];
					square[8] = attempt[drow][rcol];
					if (attempt[i][j] == 0)
						fields[i][j].setBackground(Color.white);
					else if (appearances(attempt[i][j], attempt[i]) > 1 || appearances(attempt[i][j], col) > 1 || appearances(guess[i][j], square) > 1)
					{
						if (!(fields[i][j].getBackground().equals(Color.CYAN)))
							fields[i][j].setBackground(Color.red);
					}
					else if (!(fields[i][j].getBackground().equals(Color.CYAN)))
						fields[i][j].setBackground(Color.white);
				}
			}
		}
		SudokuSolver sudoku = new SudokuSolver(arr1);
		if (sudoku.valid() && sudoku.done())
			same = true;
		return same;
	}
	public int appearances(int a, int[] arr)
	{
		int counter = 0;
		for (int i = 0; i < arr.length; i++)
			if (a == arr[i])
				counter += 1;
		return counter;
	}
	public static void showOne()
	{
		int a = (int)(Math.random() * 9);
		int b = (int)(Math.random()* 9);
		if (fields[a][b].getText().equals(""))
		{
			fields[a][b].setText(solution[a][b] + "");
			fields[a][b].setBackground(Color.CYAN);
			fields[a][b].setEditable(false);
		}
		else {
			boolean done = true;
			for (int i = 0; i < ROWS; i++)
				for (int j = 0; j < COLS; j++)
					{
					if (fields[i][j].getText().equals(""))
					done = false;
					}
			if (!done)
				showOne();
			
		}
	}
	public static void newGame()
	{
		givens = Integer.parseInt(Givens.getText());
		solution = randomSolutionSelector();
		for (int i = 0; i < ROWS; i++)
		{
			for (int j = 0; j < COLS; j++)
			{
				if (Math.random() <= givens / (ROWS * COLS * 1.0))
				{
					fields[i][j].setText("" + solution[i][j]);
					fields[i][j].setBackground(Color.cyan);
					fields[i][j].setEditable(false);
				}
				else {
					fields[i][j].setText("");
					fields[i][j].setBackground(Color.white);
					fields[i][j].setEditable(true);
				}
			}
			System.out.println("");
		}
		givensModeOn = false;
		victory.setVisible(false);
		timeElapsed = 0;
		timer.start();
	}
	public static void printInts(int[] a)
	{
		for (int i = 0; i < a.length; i++)
		{
			if (i == 0)
				System.out.print("{" + a[i] + " ");
			else if (i == a.length - 1)
				System.out.print(a[i] + "}");
			else System.out.print(a[i] + " ");
		}
	}
	public void mouseClicked(MouseEvent arg0) {
		int x= arg0.getX();
		int y = arg0.getY();
		if (x >= X_GRID_OFFSET && x <= (CELL_WIDTH + 1) * COLS + X_GRID_OFFSET)
			if (y >= Y_GRID_OFFSET && y <= (CELL_HEIGHT +1) * ROWS + Y_GRID_OFFSET)
			{
				for (int i = 0; i < ROWS; i++)
					for (int j = 0; j < COLS; j++)
					{
						if (!(fields[i][j].getText().equals("")))
							guess[i][j] = Integer.parseInt(fields[i][j].getText());
						else guess[i][j] = 0;
					}
				getPossibilities.setText("");
				solve = new SudokuSolver(guess);
				solve.evaluatePossibilities();
				int[][][] possibilities = solve.getPossibilities();
				for (int k = 0; k < possibilities[0][0].length; k++)
				{
					if (possibilities[(int) (y - Y_GRID_OFFSET - 1) / (CELL_HEIGHT+1)][(int) (x - X_GRID_OFFSET - 1) / (CELL_WIDTH + 1)][k] != 0)
						getPossibilities.setText(getPossibilities.getText() + possibilities[(int) (y - Y_GRID_OFFSET - 1) / (CELL_HEIGHT+1)][(int) (x - X_GRID_OFFSET - 1) / (CELL_WIDTH + 1)][k] + ", ");
				}
			}
	}


	public void mouseEntered(MouseEvent arg0) {

	}


	public void mouseExited(MouseEvent arg0) {

	}


	public void mousePressed(MouseEvent arg0) {

	}


	public void mouseReleased(MouseEvent arg0) {

	}


	public void mouseDragged(MouseEvent arg0) {

	}


	public void mouseMoved(MouseEvent arg0) {

	}
	private class ClearButton extends JButton implements ActionListener {
		ClearButton() {
			super("Clear");
			addActionListener(this);
		}
		public void actionPerformed(ActionEvent arg0)
		{
			for (int i = 0; i < ROWS; i++)
				for (int j = 0; j < COLS; j++)
				{
					if (!fields[i][j].getBackground().equals(Color.cyan) || fields[i][j].isEditable())
						fields[i][j].setText("");
					if (!fields[i][j].getBackground().equals(Color.cyan))
						fields[i][j].setEditable(true);
				}
		}
	}
	private class CompareButton extends JButton implements ActionListener {
		CompareButton() {
			super("Compare");
			addActionListener(this);
		}
		public void actionPerformed(ActionEvent arg0) {
			for(int i = 0; i < ROWS; i++)
			{
				for (int j = 0; j < COLS; j++)
				{
					if (fields[i][j].getText().equals(""))
						guess[i][j] = 0;
					else guess[i][j] = Integer.parseInt(fields[i][j].getText());
				}
			}
			boolean same = compareTo(guess, solution);
			if (same)
			{
				victory.setVisible(true);
				timer.stop();
				for (int i = 0; i < ROWS; i++)
					for (int j = 0; j < COLS; j++)
						fields[i][j].setEditable(false);
			}
		}
	}
	private class NewGameButton extends JButton implements ActionListener{
		NewGameButton() {
			super("New Game");
			addActionListener(this);
		}
		public void actionPerformed(ActionEvent arg0) {
			newGame();
			repaint();
		}
	}
	private class SuperHardButton extends JButton implements ActionListener{
		SuperHardButton() {
			super("Impossible");
			addActionListener(this);
		}
		public void actionPerformed(ActionEvent arg0) {
			Givens.setText("10");
			newGame();
			repaint();
		}
	}private class GenerateButton extends JButton implements ActionListener{
		GenerateButton() {
			super("Generate");
			addActionListener(this);
		}
		public void actionPerformed(ActionEvent arg0) {
			Givens.setText("0");
			newGame();
			givensModeOn = true;
			repaint();
		}
	}
	private class HardButton extends JButton implements ActionListener{
		HardButton() {
			super("Hard");
			addActionListener(this);
		}
		public void actionPerformed(ActionEvent arg0) {
			Givens.setText("35");
			newGame();
			repaint();
		}
	}
	private class MediumButton extends JButton implements ActionListener{
		MediumButton() {
			super("Medium");
			addActionListener(this);
		}
		public void actionPerformed(ActionEvent arg0) {
			Givens.setText("40");
			newGame();
			repaint();
		}
	}
	private class EasyButton extends JButton implements ActionListener{
		EasyButton() {
			super("Easy");
			addActionListener(this);
		}
		public void actionPerformed(ActionEvent arg0) {
			Givens.setText("45");
			newGame();
			repaint();
		}
	}
	private class PauseButton extends JButton implements ActionListener{
		PauseButton() {
			super("Pause");
			addActionListener(this);
		}
		public void actionPerformed(ActionEvent arg0) {
			if (getText().equals("Pause"))
			{
				for(int i = 0; i < fields.length; i++)
				{
					for (int j = 0; j < fields[0].length; j++)
					{
						fields[i][j].setVisible(false);
					}
				}
				setText("Resume");
				timer.stop();
				repaint();
			}
			else {
				for(int i = 0; i < fields.length; i++)
				{
					for (int j = 0; j < fields[0].length; j++)
					{
						fields[i][j].setVisible(true);
					}
				}
				setText("Pause");
				timer.start();
				repaint();
			}
		}
	}
	private class QuitButton extends JButton implements ActionListener {
		QuitButton() {
			super("Quit");
			addActionListener(this);
		}
		public void actionPerformed(ActionEvent arg0) {
			System.exit(0);
		}
	}
	private class HintButton extends JButton implements ActionListener {
		HintButton() {
			super("Hint");
			addActionListener(this);
		}
		public void actionPerformed(ActionEvent arg0) {
			showOne();
		}
	}
	private class SolveButton extends JButton implements ActionListener {
		SolveButton() {
			super("Start");
			addActionListener(this);
		}
		public void actionPerformed(ActionEvent arg0) {
			// nextGeneration(); // test the start button
			if (this.getText().equals("Start")) {
				togglePaintLoop();
				setText("Stop");
			} else {
				togglePaintLoop();
				setText("Start");
			}
			repaint();
		}
	}
	private class TogglePossibilitiesButton extends JButton implements ActionListener {
		TogglePossibilitiesButton() {
			super("Show Possibilities");
			addActionListener(this);
		}
		public void actionPerformed(ActionEvent arg0) {
			if (this.getText().equals("Do Not Show Possibilities")) {
				togglePossibilities();
				setText("Show Possibilities");
			} else {
				togglePossibilities();
				setText("Do Not Show Possibilities");
			}
			repaint();
		}
	}
	private class FastButton extends JButton implements ActionListener{
		FastButton() {
			super("Fast");
			addActionListener(this);
		}
		public void actionPerformed(ActionEvent arg0) {
			Replot_Time.setText("100");
			repaint();
		}
	}
	private class NormalButton extends JButton implements ActionListener{
		NormalButton() {
			super("Normal");
			addActionListener(this);
		}
		public void actionPerformed(ActionEvent arg0) {
			Replot_Time.setText("500");
			repaint();
		}
	}
	private class SlowButton extends JButton implements ActionListener{
		SlowButton() {
			super("Slow");
			addActionListener(this);
		}
		public void actionPerformed(ActionEvent arg0) {
			Replot_Time.setText("1000");
			repaint();
		}
	}
	
	private class SudokuSolver {
		private int[][] attempt;
		private int[][][] possibilities;
		private static final int ROWS = 9;
		private static final int COLS = 9;
		private int selectedRow;
		private int selectedCol;
		private int selectedPossibility;
		private int[] col;	//with the way this matrix is set up, you can just use attempt[i] or something to find the row, but you have to set up a column matrix separately
		private int[] square;
		private ArrayList<String> arrList;
		public SudokuSolver(Sudoku sudoku)
		{
			attempt = sudoku.getSolution();
			possibilities = new int[ROWS][COLS][9];
			col = new int[ROWS];
			square = new int[9];
			arrList = new ArrayList<String>();
		}
		public SudokuSolver(int[][] givens)
		{
			attempt = givens;
			possibilities = new int[ROWS][COLS][9];
			col = new int[ROWS];
			square = new int[9];
			arrList = new ArrayList<String>();
		}
		public void Solve()
		{
			if (!done())
			{
				evaluatePossibilities();
				boolean workdone = false;	// determined by whether or not a space with only one possible number was filled in, if there wasn't such a space then it allows the program to continue to guessing
				boolean secondworkdone = false;
				if (!valid() || !validMoves())	//if the board is unsolvable, then the program resets the board to its default state <-- edit this later to reset it to previous save, and make an adjustment for if there are no possibilities
				{
					for (int i = 0; i < ROWS; i++)
						for (int j = 0; j < COLS; j++)
						{
							if (fields[i][j].isEditable())
								fields[i][j].setText("");
							
						}
				}
				else {
					for(int i = 0; i < ROWS; i++)	// fills in all of the spaces with only one possible number
					{
						for (int j = 0; j < COLS; j++)
						{
							if (numberOfPossibilities(possibilities[i][j]) == 1)
								for (int k = 0; k < possibilities[i][j].length; k++)
									if (possibilities[i][j][k] != 0)
									{
										fields[i][j].setText("" + possibilities[i][j][k]);
										workdone = true;
									}
						}
					}
				}
				if (!workdone)	//if a space with only one possible number doesn't exist
				{
					while (!secondworkdone)	//as long as it hasn't made a guess
					{
						selectRandomCell();	// find a random empty cell
						if (!validMoves())	//none of the possible numbers can be placed legally, probably just a redundant error check just to make sure nothing crashes
						{
							for (int i = 0; i < ROWS; i++)
								for (int j = 0; j < COLS; j++)
								{
									if (fields[i][j].isEditable())
										fields[i][j].setText("");
								}
							workdone = true;
							secondworkdone = true;
						}
						else if (numberOfPossibilities(possibilities[selectedRow][selectedCol]) >= 2)
						{
							selectRandomPossibility();	//select a random possible number that can fit in the square
							fields[selectedRow][selectedCol].setText("" + possibilities[selectedRow][selectedCol][selectedPossibility]);
							repaint();
							workdone = true;
							secondworkdone = true;	//ends the guessing loop
						}
					}
				}
			}
			else if (!valid())	//probably yet another redundant error check
			{
				for (int i = 0; i < ROWS; i++)
					for (int j = 0; j < COLS; j++)
					{
						if (fields[i][j].isEditable())
							fields[i][j].setText("");
					}
			}
		}
		public void selectRandomPossibility()
		{
			int k = (int) (Math.random() * 9);
			if (possibilities[selectedRow][selectedCol][k] != 0)
				selectedPossibility = k;
			else selectRandomPossibility();
		}
		public boolean validMoves()
		{
			boolean validMoves = false;
			for (int i = 0; i < ROWS; i++)
				for (int j = 0; j < COLS; j++)
					if (attempt[i][j] == 0)	// if no number is in the space
					{
						for (int k = 0; k < possibilities[i][j].length; k++)
						{
							for (int l = 0; l < ROWS; l++)
								col[l] = attempt[l][j];
							int urow = 0;
							int drow = 0;
							int lcol = 0;
							int rcol = 0;
							if (i % 3 == 0)	//sets the numbers for the other two rows in a Sudoku square, this one is for the top row of each square
							{
								urow = i + 1;
								drow = i + 2;
							}
							else if (i % 3 == 1) //the middle row of each square
							{
								urow = i - 1;
								drow = i + 1;
							}
							else {	//the bottom row of each square
								urow = i - 2;
								drow = i - 1;
							}
							if (j % 3 == 0)	//sets the numbers for the other two columns in a Sudoku square, this one is for the left column of each square
							{
								lcol = j + 1;
								rcol = j + 2;
							}
							else if (j % 3 == 1)	//the middle column of each square
							{
								lcol = j - 1;
								rcol = j + 1;
							}
							else {	//the right column of each square
								lcol = j - 2;
								rcol = j - 1;
							}
							square[0] = attempt[urow][lcol];
							square[1] = attempt[urow][j];
							square[2] = attempt[urow][rcol];
							square[3] = attempt[i][lcol];
							square[4] = attempt[i][j];
							square[5] = attempt[i][rcol];
							square[6] = attempt[drow][lcol];
							square[7] = attempt[drow][j];
							square[8] = attempt[drow][rcol];	//fills in the square matrix with all the numbers in it
							if (appearances(possibilities[i][j][k], attempt[i]) < 1 && appearances(possibilities[i][j][k], col) < 1 && appearances(possibilities[i][j][k], square) < 1)	// tests if the possible number is already present or not in the square, column, and row of the space
								validMoves = true;
						}
					}
			return validMoves;
		}
		public void selectRandomCell()
		{
			for (int i = 0; i < ROWS; i++)
				for (int j = 0; j < ROWS; j++)
					if (attempt[i][j] == 0)
						arrList.add("" + i + j);
			String selection = arrList.get((int)(Math.random() * arrList.size()));	//select a random possibility from the empty cell array list
			selectedRow = Integer.parseInt(selection.substring(0, 1));	//substring just returns an Int? I think so need to Integer.parseInt to change to integer, selects the first thing in the selection string, which is the selected row
			selectedCol = Integer.parseInt(selection.substring(1));	//takes the selected column
		}
		public void evaluatePossibilities()
		{
			for (int i = 0; i < ROWS; i++)
			{
				for (int j = 0; j < COLS; j++)
				{
					for (int k = 0; k < possibilities[i][j].length; k++)
					{
						if (guess[i][j] != 0)
							possibilities[i][j][k] = 0;
						else { 
							for (int l = 0; l < ROWS; l++)
								col[l] = attempt[l][j];
							int urow = 0;
							int drow = 0;
							int lcol = 0;
							int rcol = 0;
							if (i % 3 == 0)
							{
								urow = i + 1;
								drow = i + 2;
							}
							else if (i % 3 == 1)
							{
								urow = i - 1;
								drow = i + 1;
							}
							else {
								urow = i - 2;
								drow = i - 1;
							}
							if (j % 3 == 0)
							{
								lcol = j + 1;
								rcol = j + 2;
							}
							else if (j % 3 == 1)
							{
								lcol = j - 1;
								rcol = j + 1;
							}
							else {
								lcol = j - 2;
								rcol = j - 1;
							}
							square[0] = attempt[urow][lcol];
							square[1] = attempt[urow][j];
							square[2] = attempt[urow][rcol];
							square[3] = attempt[i][lcol];
							square[4] = attempt[i][j];
							square[5] = attempt[i][rcol];
							square[6] = attempt[drow][lcol];
							square[7] = attempt[drow][j];
							square[8] = attempt[drow][rcol];
							if (appearances(k + 1, attempt[i]) < 1 && appearances(k + 1, col) < 1 && appearances(k + 1, square) < 1)
								possibilities[i][j][k] = k + 1;
							else possibilities[i][j][k] = 0; //default value for not possible
						}
					}
				}
			}
		}
		public int appearances(int num, int[][] arr)	//function for appearances in a possibilities matrix?
		{
			int counter = 0;
			for (int i = 0; i < arr.length; i++)
			{
				if (num == arr[i][num - 1])
					counter += 1;
			}
			return counter;
		}
		public boolean valid()
		{
			boolean valid = true;
			for(int i = 0; i < ROWS; i++)
			{
				for (int j = 0; j < COLS; j++)
				{
					if(attempt[i][j] != 0)
					{
						for (int k = 0; k < ROWS; k++)
							col[k] = attempt[k][j];
						int urow = 0;
						int drow = 0;
						int lcol = 0;
						int rcol = 0;
						if (i % 3 == 0)
						{
							urow = i + 1;
							drow = i + 2;
						}
						else if (i % 3 == 1)
						{
							urow = i - 1;
							drow = i + 1;
						}
						else {
							urow = i - 2;
							drow = i - 1;
						}
						if (j % 3 == 0)
						{
							lcol = j + 1;
							rcol = j + 2;
						}
						else if (j % 3 == 1)
						{
							lcol = j - 1;
							rcol = j + 1;
						}
						else {
							lcol = j - 2;
							rcol = j - 1;
						}
						square[0] = attempt[urow][lcol];
						square[1] = attempt[urow][j];
						square[2] = attempt[urow][rcol];
						square[3] = attempt[i][lcol];
						square[4] = attempt[i][j];
						square[5] = attempt[i][rcol];
						square[6] = attempt[drow][lcol];
						square[7] = attempt[drow][j];
						square[8] = attempt[drow][rcol];
						if (appearances(attempt[i][j], attempt[i]) > 1 || appearances(attempt[i][j], col) > 1 || appearances(guess[i][j], square) > 1)
							valid = false;
					}
				}
			}
			return valid;
		}
		public int appearances(int a, int[] arr)
		{
			int counter = 0;
			for (int i = 0; i < arr.length; i++)
				if (a == arr[i])
					counter += 1;
			return counter;
		}
		public int numberOfPossibilities(int[] arr)
		{
			int counter = 0;
			for (int i = 0; i < arr.length; i++)
				if (arr[i] != 0)
					counter += 1;
			return counter;
		}
		public boolean done()
		{
			boolean done = true;
			for (int i = 0; i < ROWS; i++)
				for (int j = 0; j < COLS; j++)
					if(attempt[i][j] == 0)
						done = false;
			return done;
		}
		public int[][][] getPossibilities()
		{
			return possibilities;
		}
	}

}
