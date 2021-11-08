package grapher.ui;

import java.awt.event.ActionEvent;

import javax.swing.AbstractAction;
import javax.swing.DefaultListModel;
import javax.swing.JOptionPane;

import grapher.fc.Function;
import grapher.fc.FunctionFactory;

public class AddFunctionAction extends AbstractAction{
	private DefaultListModel<Function> functions;
	private Grapher grapher;
	
	public AddFunctionAction(Grapher grapher, DefaultListModel<Function> functions) {
		this.grapher = grapher;
		this.functions = functions;
	}
	
	@Override
	public void actionPerformed(ActionEvent arg0) {
		String newfunction = JOptionPane.showInputDialog(grapher,"Entrez la nouvelle fonction","Nouvelle fonction",JOptionPane.QUESTION_MESSAGE);
		try {
			functions.addElement(FunctionFactory.createFunction(newfunction));
		} catch(RuntimeException ex) {
			JOptionPane.showInternalMessageDialog(null, new String("Erreur syntax"));
		}
		grapher.repaint();
	}
}
