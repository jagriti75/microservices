package validation.user.controller;


import java.util.List;


import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import validation.user.data.UserRepository;
import validation.user.entity.UserEntity;
import validation.user.error.root;
import validation.user.exception.ApiRequestException;
import validation.user.exception.UnauthorizedException;



@RestController
public class UserController{
	Logger log = LoggerFactory.getLogger(this.getClass());
	
	@Autowired
	UserRepository repo;

	
	//to get details by username
	@GetMapping(value="/{name}",consumes= {MediaType.APPLICATION_JSON_VALUE , MediaType.APPLICATION_XML_VALUE} ,
	produces = {MediaType.APPLICATION_JSON_VALUE , MediaType.APPLICATION_XML_VALUE})
	public ResponseEntity<?> getValue(HttpServletRequest req , @PathVariable("name") String name){
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
			throw new UnauthorizedException("id entered is wrong");
		}else {
			UserEntity user = repo.findByName(name);
			if(user == null) {
				throw new ApiRequestException("Could not find the requested user");
			}
			return ResponseEntity.status(HttpStatus.OK).headers(headers).body(user);
		}
		
	}
	
	//to register a user name and age
	@PostMapping(value ="/register" , consumes= {MediaType.APPLICATION_JSON_VALUE , MediaType.APPLICATION_XML_VALUE} ,
	produces = {MediaType.APPLICATION_JSON_VALUE , MediaType.APPLICATION_XML_VALUE})
	public ResponseEntity<?> register(@RequestBody @Valid UserEntity user , BindingResult result ,HttpServletRequest req){	
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
			throw new UnauthorizedException("id entered is wrong");			
		}else{
			if(result.hasErrors()){			
				//Map<String,String> maps = new HashMap<>() ;
				String msg = null;
				List<FieldError> errors = result.getFieldErrors();
				for(FieldError error:errors) {					
					//maps.put("error"+error.getField().substring(0,1).toUpperCase()+error.getField().substring(1), error.getDefaultMessage());
					msg = error.getDefaultMessage();
				}
	
				root error = new root(msg);		
				return  ResponseEntity.status(HttpStatus.BAD_REQUEST).headers(headers).body(error);
				}						
		    }		
		repo.save(user);
		return ResponseEntity.status(HttpStatus.OK).headers(headers).body(user);		


	}
}

