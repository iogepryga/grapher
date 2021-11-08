/* grapher.ui.Main
 * (c) blanch@imag.fr 2021–                                                */

package grapher.ui;

import java.awt.BorderLayout;

import javax.swing.DefaultListModel;
import javax.swing.JFrame;
import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.JSplitPane;
import javax.swing.SwingUtilities;

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

		Grapher grapher = new Grapher(listmodel);
		for (String expression : expressions) {
			grapher.add(expression);
		}

		InteractionGrapher i = new InteractionGrapher(grapher);
		grapher.addInteractionGrapher(i);

		JPanel panel = new JPanel(new BorderLayout());
		panel.add(list, BorderLayout.CENTER);

		JSplitPane splitpane = new JSplitPane(JSplitPane.HORIZONTAL_SPLIT, panel, grapher);

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
