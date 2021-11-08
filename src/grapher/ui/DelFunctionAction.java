package grapher.ui;

import java.awt.event.ActionEvent;

import javax.swing.AbstractAction;
import javax.swing.DefaultListModel;
import javax.swing.ListSelectionModel;

import grapher.fc.Function;

public class DelFunctionAction extends AbstractAction {
	private Grapher grapher;
	private ListSelectionModel listselectionmodel;
	private DefaultListModel<Function> functions;
	
	public DelFunctionAction(Grapher grapher,DefaultListModel<Function> functions,ListSelectionModel listselectionmodel) {
		this.grapher = grapher;
		this.listselectionmodel = listselectionmodel;
		this.functions = functions;
	}
	
	@Override
	public void actionPerformed(ActionEvent e) {
		int i = listselectionmodel.getMinSelectionIndex();
		while (i != -1) {
			functions.removeElementAt(i);
			i = listselectionmodel.getMinSelectionIndex();
		}
	}

}
