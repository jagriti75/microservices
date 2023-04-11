package validation.user.error;

import java.time.LocalDateTime;
import java.util.Map;

import org.springframework.stereotype.Component;

@Component
public class error{

 private int statusCode;
 private LocalDateTime timestamp;
 private Map<String,String> message;
 

public error(int statusCode, LocalDateTime timestamp, Map<String,String> message) {
	super();
	this.statusCode = statusCode;
	this.timestamp = timestamp;
	this.message = message;
}
public error() {
	super();
	// TODO Auto-generated constructor stub
}
public int getStatusCode() {
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
}
public Map<String, String> getMessage() {
	return message;
}
public void setMessage(Map<String, String> message) {
	this.message = message;
}


}

