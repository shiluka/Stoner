import com.softzone.stoner.bill.*;
import com.softzone.stoner.login.*;
import com.softzone.stoner.proxy.*;
import com.softzone.stoner.state.*;


public class SystemFacade {
	
	private LoginWindow loginWindow;
	
	public SystemFacade(LoginWindow loginWindow){
		this.loginWindow = loginWindow;
	}
	
	public void start(){
		loginWindow = LoginWindow.getUniqueLoginInstance();
		loginWindow.init();
		loginWindow.buildWindow();
	}

}
