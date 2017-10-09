import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;
import javax.servlet.http.HttpSession;
@ManagedBean(name="userController")
@SessionScoped
public class userController {
	
	private String name;
	private String email;
	private String userName;
	private String password;
	
	
	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public userController()
	{
		
	}
	public String insertData()
	{ 
		   System.out.println("in method....");
		try {
			boolean valid =userDao.insertData(name, email, userName, password);
			System.out.println("after valid line....");
			if(valid)
			{
			//FacesContext.getCurrentInstance().addMessage(null,new FacesMessage(FacesMessage.SEVERITY_WARN,
			//				" Registration Successful","login now"));
				
				//FacesContext.getCurrentInstance().addMessage("myForm:newPassword1", new FacesMessage(PASSWORDS_DONT_MATCH, PASSWORDS_DONT_MATCH));	
				String message = "Registration information submitted successfully !!"; 
			    FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(message));
				
				return "login";
			}
			else
			{
				String message = "Registration information couldn't be submitted!!Try Again!"; 
			    FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(message));
				return "registration";
			}
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return "registration";
		}
		
	}
	public String checkData()
	{    System.out.println("method called");
		try {
			boolean valid=userDao.checkData(userName, password);
			if(valid)
			{
				System.out.println("in valid case");
				HttpSession session =Util.getSession();
				session.setAttribute("userName", userName);
				 return "mainpage";
			}
			else
			{
				String msg = "Either username or password is wrong.Try it again!!!"; 
				FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(msg));
				return "login";
				
			}
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			//String msg = "Either username or password is wrong.Try it again!!!"; 
		   // FacesContext.getCurrentInstance()().addMessage(null, new FacesMessage(message));
			//FacesContext.getCurrentInstance().addMessage("clientId", 
				//    new FacesMessage(FacesMessage.SEVERITY_INFO, msg, msg));
			
			// FacesContext.getCurrentInstance().addMessage("Requests:Certificate1:downloadButton", new FacesMessage(FacesMessage.SEVERITY_ERROR, "Error!", "Please enter the Reference No."));
			
			return "login";
		}
		
	}
	public String logOut() {
		HttpSession session = Util.getSession();
		session.invalidate();
		String message = "You have successfully logged out!!"; 
	    FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(message));
		return "login";

}
}
