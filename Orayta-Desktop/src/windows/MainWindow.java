package windows;

import java.awt.EventQueue;

import javax.swing.BoxLayout;
import javax.swing.JFrame;
import javax.swing.JScrollPane;
import javax.swing.JSplitPane;
import javax.swing.JTextPane;
import javax.swing.JTree;

import settings.SettingsManager;
import adapters.TreeAdapter;
import book.Book;
import bookTree.BookTree;
import bookTree.BookTreeBuilder;

public class MainWindow {

	private JFrame frame;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					MainWindow window = new MainWindow();
					window.frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 */
	public MainWindow() {
		initialize();
		startApp();
	}

	private void startApp() 
	{
		BookTreeBuilder tb = new BookTreeBuilder();
		BookTree bookTree = tb.buildTree(SettingsManager.getSettings().get_BOOKS_ROOT_DIR());
		
		tree.setModel(new TreeAdapter<Book>().genTreeModel(bookTree.getElementsTree()));
	}
	
	JTree tree = new JTree();
	private void initialize() {
		frame = new JFrame();
		frame.setBounds(100, 100, 590, 488);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(new BoxLayout(frame.getContentPane(), BoxLayout.X_AXIS));
		
		JSplitPane splitPane = new JSplitPane();
		frame.getContentPane().add(splitPane);
		
		JTextPane txtPanel = new JTextPane();
		txtPanel.setContentType("text/html");
		splitPane.setRightComponent(txtPanel);
		
		JScrollPane scrollPane = new JScrollPane();
		splitPane.setLeftComponent(scrollPane);
		
		scrollPane.setViewportView(tree);
	}

}
