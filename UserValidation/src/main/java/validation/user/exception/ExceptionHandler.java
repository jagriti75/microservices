
package validation.user.exception;


import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;


import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.transaction.CannotCreateTransactionException;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import validation.user.error.error;


@RestControllerAdvice
public class ExceptionHandler{
	


	LocalDateTime date =  LocalDateTime.now();
	
	//to handle request body errors
	@org.springframework.web.bind.annotation.ExceptionHandler(HttpMessageNotReadableException.class)
	@ResponseStatus(HttpStatus.BAD_REQUEST)
	@ResponseBody
	public ResponseEntity<?> handleInvalidArgument(Exception ex , HttpServletRequest req){
		String message = null;
		Map<String , String> errors = new HashMap<>();
		HttpHeaders headers = new HttpHeaders();
		if(req.getHeader("CONTENT-TYPE").contains("application/json")) {
			if(req.getHeader("json").contains("true")) {
			headers.set("CONTENT-TYPE", "application/json");	
			}
			else {
				headers.set("CONTENT-TYPE", "application/xml");
			}
		}else {
			if(req.getHeader("xml").contains("true")) {
			headers.set("CONTENT-TYPE","application/xml");
			}else {
				headers.set("CONTENT-TYPE","application/json");
			}
		}
		
		if(!(req.getHeader("id").contains("abc"))) {
			Map<String,String> maps = new HashMap<>() ;
			maps.put("invalidCredentials", "id entered is wrong ");
			//errorsClass.setError(maps);
			message = "id entered is wrong";
			error error = new error(401 , date , maps);
			return ResponseEntity.status(HttpStatus.UNAUTHORIZED).headers(headers).body(error);
		}else if(req.getHeader("id") == null) {
			Map<String,String> maps = new HashMap<>() ;
			maps.put("invalidRequest", "id not found");
			error error = new error(500 , date , maps);
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).headers(headers).body(error);
		}
		else {
			 String error = ex.getMessage();
			 if(error.contains("int")){
				 message = "Age must be a number";
				 errors.put("invalidAge",message);
				
			 }
			 if(error.contains("Unexpected character ('}' (code 125)): expected a value;")) {
				 message="Age should not be empty";
				 errors.put("invalidAge", message);
			
			 }
			 if(error.contains("Unexpected character (',' (code 44)): expected a value;")) {
				 message="Name should not be empty";
				 errors.put("invalidName", message);
			
			 }
			 if(error.contains("JSON parse error: Unexpected character (',' (code 44)): expected a value; nested exception is com.fasterxml.jackson.core.JsonParseException: Unexpected character (',' (code 44)): expected a value\n at [Source: (org.springframework.util.StreamUtils$NonClosingInputStream); line: 2, column: 15]")) {
				 message="Name should not be empty";
				 errors.put("invalidName", message);
			 }
		}
	
		
		error error = new error(400 , date , errors);
		return ResponseEntity.status(HttpStatus.BAD_REQUEST).headers(headers).body(error);
		
	}	
	
	//
	@org.springframework.web.bind.annotation.ExceptionHandler(java.lang.NullPointerException.class)
	@ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
	@ResponseBody
	public ResponseEntity<?> handleInvalidAuthorization(Exception ex , HttpServletRequest req){
	
		String msg = ex.getMessage();
		HttpHeaders headers = new HttpHeaders();
		if(req.getHeader("CONTENT-TYPE").contains("application/json")) {
			if(req.getHeader("json").contains("true")) {
			headers.set("CONTENT-TYPE", "application/json");	
			}
			else {
				headers.set("CONTENT-TYPE", "application/xml");
			}
		}else {
			if(req.getHeader("xml").contains("true")) {
			headers.set("CONTENT-TYPE","application/xml");
			}else {
				headers.set("CONTENT-TYPE","application/json");
			}
		}
	
		if(msg.contains("Cannot invoke \"String.contains(java.lang.CharSequence)\" because the return value of \"javax.servlet.http.HttpServletRequest.getHeader(String)\" is null")){
			Map<String,String> maps = new HashMap<>() ;
			maps.put("invalidRequest", "id not found");
			error error = new error(500 , date , maps);
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).headers(headers).body(error);
				}
		if(req.getHeader("id") == null) {
			return ResponseEntity.status(HttpStatus.UNAUTHORIZED).headers(headers).body("error");
		}
		return ResponseEntity.status(HttpStatus.UNAUTHORIZED).headers(headers).body("error");
	}
	
	//to handle non-existing user error
	@org.springframework.web.bind.annotation.ExceptionHandler(ApiRequestException.class)
	@ResponseStatus(HttpStatus.NOT_FOUND)
	@ResponseBody
	public ResponseEntity<?> handleNotFound(ApiRequestException ex ,HttpServletRequest req){
		HttpHeaders headers = new HttpHeaders();
		if(req.getHeader("CONTENT-TYPE").contains("application/json")) {
			if(req.getHeader("json").contains("true")) {
			headers.set("CONTENT-TYPE", "application/json");	
			}
			else {
				headers.set("CONTENT-TYPE", "application/xml");
			}
		}else {
			if(req.getHeader("xml").contains("true")) {
			headers.set("CONTENT-TYPE","application/xml");
			}else {
				headers.set("CONTENT-TYPE","application/json");
			}
		}
	
			Map<String,String> maps = new HashMap<>() ;
			maps.put("invalid", ex.getMessage());
			error error = new error(404 , date , maps);
		
			  HttpStatus badRequestStatus = HttpStatus.NOT_FOUND;
			    return ResponseEntity.status(badRequestStatus).headers(headers)
			                         .body(error);
		}
	
	//to handle internal server error 
	@org.springframework.web.bind.annotation.ExceptionHandler(CannotCreateTransactionException.class)
	@ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
	@ResponseBody
	public ResponseEntity<?> handleInvalidArgument1(Exception ex , HttpServletRequest req){
		HttpHeaders headers = new HttpHeaders();
		if(req.getHeader("CONTENT-TYPE").contains("application/json")) {
			if(req.getHeader("json").contains("true")) {
			headers.set("CONTENT-TYPE", "application/json");	
			}
			else {
				headers.set("CONTENT-TYPE", "application/xml");
			}
		}else {
			if(req.getHeader("xml").contains("true")) {
			headers.set("CONTENT-TYPE","application/xml");
			}else {
				headers.set("CONTENT-TYPE","application/json");
			}
		}
		Map<String,String> maps = new HashMap<>() ;
		maps.put("invalid", "could not connect to database");
		
		error error = new error(500 , date , maps);
	
		  HttpStatus badRequestStatus = HttpStatus.INTERNAL_SERVER_ERROR;
		    return ResponseEntity.status(badRequestStatus).headers(headers)
		                         .body(error);
		
	}

	//to handle unauthorization
	@org.springframework.web.bind.annotation.ExceptionHandler(UnauthorizedException.class)
	@ResponseStatus(HttpStatus.NOT_FOUND)
	@ResponseBody
	public ResponseEntity<?> handleNotFound(UnauthorizedException ex ,HttpServletRequest req){
		HttpHeaders headers = new HttpHeaders();
		if(req.getHeader("CONTENT-TYPE").contains("application/json")) {
			if(req.getHeader("json").contains("true")) {
			headers.set("CONTENT-TYPE", "application/json");	
			}
			else {
				headers.set("CONTENT-TYPE", "application/xml");
			}
		}else {
			if(req.getHeader("xml").contains("true")) {
			headers.set("CONTENT-TYPE","application/xml");
			}else {
				headers.set("CONTENT-TYPE","application/json");
			}
		}
	
			Map<String,String> maps = new HashMap<>() ;
			maps.put("invalidCredentials", ex.getMessage());
			error error = new error(401 , date , maps);
		
			  HttpStatus badRequestStatus = HttpStatus.UNAUTHORIZED;
			    return ResponseEntity.status(badRequestStatus).headers(headers)
			                         .body(error);
		}
	
	
}
	
	



