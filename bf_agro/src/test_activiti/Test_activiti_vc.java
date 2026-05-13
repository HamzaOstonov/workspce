package test_activiti;

import org.zkoss.zk.ui.Component;
import org.zkoss.zk.ui.util.GenericForwardComposer;

public class Test_activiti_vc extends GenericForwardComposer
{
	public void doAfterCompose(Component comp) throws Exception
	{
        super.doAfterCompose(comp);
	}
	
	public void onClick$start_btn()
	{
/*		alert("clicked!");
		ProcessEngine processEngine = ProcessEngines.getDefaultProcessEngine();
		RepositoryService repositoryService = processEngine.getRepositoryService();
		repositoryService.createDeployment()
		  .addClasspathResource("org/activiti/test/VacationRequest.bpmn20.xml")
		  .deploy();

		Log.info("Number of process definitions: " + repositoryService.createProcessDefinitionQuery().count());
	*/
	}
}
