/* grapher.ui.Main
 * (c) blanch@imag.fr 2021–                                                */

package grapher.ui;

import java.awt.BorderLayout;

import javax.swing.DefaultListModel;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JList;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JPanel;
import javax.swing.JSplitPane;
import javax.swing.JToolBar;
import javax.swing.ListSelectionModel;
import javax.swing.SwingUtilities;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;

import grapher.fc.Function;

// main that launch a grapher.ui.Grapher

public class Main extends JFrame {
	Main(String title, String[] expressions) {
		super(title);
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		expressions = new String[] { "cos(x)", "x", "sin(x)" };

		JList<Function> list = new JList<Function>();
		DefaultListModel<Function> listmodel = new DefaultListModel<Function>();
		list.setModel(listmodel);
		ListSelectionModel listselectionmodel = list.getSelectionModel();

		Grapher grapher = new Grapher(listmodel, listselectionmodel);
		for (String expression : expressions) {
			grapher.add(expression);
		}

		InteractionGrapher i = new InteractionGrapher(grapher);
		grapher.addInteractionGrapher(i);
		list.addListSelectionListener(new ListSelectionListener() {
			Grapher grapher;

			@Override
			public void valueChanged(ListSelectionEvent arg0) {
				grapher.repaint();
			}

			public ListSelectionListener addgrapher(Grapher grapher) {
				this.grapher = grapher;
				return this;
			}
		}.addgrapher(grapher));
		
		DelFunctionAction delaction = new DelFunctionAction(grapher,listmodel,listselectionmodel);
		AddFunctionAction addaction = new AddFunctionAction(grapher,listmodel);
		JButton addbutton = new JButton("+ Add");
		JButton delbutton = new JButton("- Del");
		addbutton.setAction(addaction);
		delbutton.setAction(delaction);
		JToolBar toolbar = new JToolBar();
		toolbar.add(addbutton);
		toolbar.add(delbutton);
		
		JPanel panel = new JPanel(new BorderLayout());
		panel.add(list, BorderLayout.CENTER);
		panel.add(toolbar,BorderLayout.SOUTH);

		JSplitPane splitpane = new JSplitPane(JSplitPane.HORIZONTAL_SPLIT, panel, grapher);

		JMenuBar menubar = new JMenuBar();
		setJMenuBar(menubar);
		JMenu expressionmenu = new JMenu("Expression");
		menubar.add(expressionmenu);
		JMenuItem additem = new JMenuItem("Add expression");
		additem.addActionListener(addaction);
		expressionmenu.add(additem);
		JMenuItem delitem = new JMenuItem("Remove expression");
		delitem.addActionListener(delaction);
		expressionmenu.add(delitem);
		
		add(splitpane);
		pack();
	}

	public static void main(String[] argv) {
		final String[] expressions = argv;
		SwingUtilities.invokeLater(new Runnable() {
			public void run() {
				new Main("grapher", expressions).setVisible(true);
			}
		});
	}
}
