import com.softzone.stoner.bill.*;
import com.softzone.stoner.login.*;
import com.softzone.stoner.proxy.*;
import com.softzone.stoner.state.*;



public class Test {
	
	public static void main(String[] args){
		
		LoginWindow loginWindow=null;
		SystemFacade system = new SystemFacade(loginWindow);
		system.start();
		
	}

}
