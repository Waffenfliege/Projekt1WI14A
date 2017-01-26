package view;

public class ViewHandler
{
	private MainFrame mainFrame;
	
	public ViewHandler()
	{
		mainFrame = new MainFrame();

	}
	
	public void start(){
		mainFrame.startFrame();
	}
}
