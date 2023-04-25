package validation.user.error;




import org.springframework.stereotype.Component;

@Component
public class root{

 //private int statusCode;
 //private LocalDateTime timestamp;
 private String errorMessage;
 

public root( String errorMessage) {
	//super();
	//this.statusCode = statusCode;
	//this.timestamp = timestamp;
	this.errorMessage = errorMessage;
}
public root() {
	super();
	// TODO Auto-generated constructor stub
}

/*public int getStatusCode() {
	return statusCode;
}
public void setStatusCode(int statusCode) {
	this.statusCode = statusCode;
}
public LocalDateTime getTimestamp() {
	return timestamp;
}
public void setTimestamp(LocalDateTime timestamp) {
	this.timestamp = timestamp;
}*/

public String getMessage() {
	return errorMessage;
}
public void setMessage(String errorMessage) {
	this.errorMessage = errorMessage;
}


}

