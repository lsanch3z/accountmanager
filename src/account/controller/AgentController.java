package account.controller;

import account.model.Agent;
import account.view.AgentView;
import account.view.BankAccountView;


public class AgentController extends AbstractController{

	public void operation(String option) {
		if(option.equals(AgentView.StartAgent)){
			double amountin = AgentView.getAmountin();
			@SuppressWarnings("static-access")
			int ops = (((AgentView)getView()).getOpS());
			String id = AgentView.getIDAgent();
			((Agent)getModel()).setAgentID(id);
			((Agent)getModel()).setAmountin$(amountin);
			((Agent)getModel()).setOPS(ops);
			((Agent)getModel()).setFields();
			((AgentView)getView()).startingAgent();
		}else if(option.equals(AgentView.Dismiss)){
			AgentView views = (AgentView)getView();
			Agent currentAgent = (Agent)getModel();
			currentAgent.finishThread();
			views.dispose();
		}else if(option.equals(AgentView.DismissAgent)){
			AgentView views = (AgentView)getView();
			Agent currentAgent = (Agent)getModel();
			currentAgent.finishThread();
			views.dispose();
			((AgentView)getView()).closeworkingAgent();
		}else if(option.equals(AgentView.StopAgent)){
			((Agent)getModel()).pauseAgent();
			((AgentView)getView()).enableDismissAgent();
		}
		
	}

}
