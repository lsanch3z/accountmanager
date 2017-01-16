package account.view;

import account.controller.Controller;
import account.model.Model;

public interface View {
	Controller getController();
	public void setController(Controller aController);
	public Model getModel();
	public void setModel(Model aModel);
}