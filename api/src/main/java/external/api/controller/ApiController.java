package external.api.controller;

import java.io.IOException;
import java.time.LocalDateTime;
import java.util.UUID;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.json.JSONArray;
import org.json.JSONObject;
import org.json.JSONTokener;
import org.json.XML;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import external.api.data.LogDataRepository;
import external.api.entity.LogData;
import external.api.entity.postEntity;

@RestController
public class ApiController {
	
	Logger logger = LoggerFactory.getLogger(this.getClass());
	LocalDateTime date = LocalDateTime.now();
	String id = UUID.randomUUID().toString();
	HttpHeaders headers = new HttpHeaders();
	
	@Autowired
	LogDataRepository repo;
	
	//to consume and external api
	@GetMapping(value = "/ips"  )
	private String getAll(HttpServletResponse response) throws IOException{
		String uri = "https://ipinfo.io/161.185.160.93/geo";
		RestTemplate restTemplate = new RestTemplate();	
		String ipList = restTemplate.getForObject(uri, String.class);
		LocalDateTime date = LocalDateTime.now();
		String id = UUID.randomUUID().toString();
		response.setHeader("DateTime Logged in",date.toString());
		response.setHeader("id",id);
		logger.info("DateTime Logged in : "+ date);
		logger.info("Id: ",id);
		return ipList;
	}
	
	//to set headers 
	@GetMapping(value ="/all")
	private String getBpi() throws IOException {
		String url = "https://api.us-east-a.apiconnect.ibmappdomain.cloud/v10-0/sandbox/bitcoin/coin";
		headers.set("X-IBM-Client-Id", "f7c6f8cc5a0c982a915c9b1b68590c7a");
		HttpEntity<String> entity = new HttpEntity<String>(headers);
		RestTemplate restTemplate = new RestTemplate();
		ResponseEntity<String> response= restTemplate.exchange(url,HttpMethod.GET,entity,String.class);
		LocalDateTime date =  LocalDateTime.now();
		String id = UUID.randomUUID().toString();
		logger.info("DateTime Logged in : "+ date);
		logger.info("Id: "+ id);
		return response.getBody();
	}

	@PostMapping(value="/post")
	private String getList1(@RequestBody postEntity entity) {
		String url1 = "https://jsonplaceholder.typicode.com/posts";
		RestTemplate restTemplate = new RestTemplate();
		String response1 = restTemplate.postForEntity(url1, entity , String.class).getBody();
		return response1;
	}

	//to convert formats (json to json , xml ... xml to json,xml)
	@PostMapping(value="/conversion")
	private ResponseEntity<String> convert(@RequestBody String value ,HttpServletRequest req ) {
		LogData data = new LogData();
		Object jsonformat = new JSONTokener(value).nextValue();
		String isJson = "no";
		if(jsonformat instanceof JSONObject || jsonformat instanceof JSONArray) {
			isJson = "yes";
		}
		
		if(isJson == "yes" && req.getHeader("json").contains("true")) {
			data = new LogData(id,"json to json",date.toString());
			repo.save(data);
			headers.setContentType(MediaType.APPLICATION_JSON);
			return ResponseEntity.ok()
					.headers(headers)
					.body(value);
		}
		else if(isJson == "yes" && req.getHeader("xml").contains("true")) {
			data = new LogData(id,"json to xml",date.toString());
			repo.save(data);
			headers.set("Content-Type","application/xml");
			JSONObject jsonValue = new JSONObject(value  );
			return ResponseEntity.ok()
					.headers(headers)
					.body(XML.toString(jsonValue));
		}
		else if(isJson == "no" && req.getHeader("json").contains("true")) {
			data = new LogData(id,"xml to json",date.toString());
			repo.save(data);
			headers.setContentType(MediaType.APPLICATION_JSON);
			JSONObject jsonValue = XML.toJSONObject(value);
			return ResponseEntity.ok()
					.headers(headers)
					.body(jsonValue.toString());
		}
		else {
			data = new LogData(id,"xml to xml",date.toString());
			repo.save(data);
			headers.setContentType(MediaType.APPLICATION_XML);
			return ResponseEntity.ok()
					.headers(headers).
					body(value);
		}

	}
}
