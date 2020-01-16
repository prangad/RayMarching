import java.awt.Dimension;
import javax.swing.*;

public class MainFrame extends JFrame {
	private static final long serialVersionUID = 1L;

	public MainFrame()
	{
		this.setTitle("Ray Marching Example");
		this.setResizable(false);
		this.setDefaultCloseOperation(EXIT_ON_CLOSE);
		
		MainPanel mainPanel = new MainPanel(1440, 900);
		mainPanel.setPreferredSize(new Dimension(1440, 900));
		this.setContentPane(mainPanel);
		this.addKeyListener(mainPanel);
		mainPanel.requestFocusInWindow();

		this.pack();
		this.setLocationRelativeTo(null);
		this.setVisible(true);
	}
}