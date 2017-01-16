package account.model;

import account.model.ModelEvent;

public interface ModelListener {
	public void modelChanged(ModelEvent me);
}
