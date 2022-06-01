package com.example.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.example.Model.Account;
import com.example.client.InfoManagementClient;

import net.minidev.json.JSONObject;

@Service
public class AccountService {
	
	@Autowired
	InfoManagementClient infoManagementClient;

	public String createAccount() {
		String htmlContent = createAccountHtml();
		return htmlContent;
	}
	
	@RequestMapping("/success")
	public ResponseEntity<JSONObject> registered(@RequestParam(value = "username") String username,
			@RequestParam(value = "desigination") String desigination,
			@RequestParam(value = "location") String location) {
		String successHtml = "Falied. Please try again";
		Account account = new Account();
		account.setName(username);
		account.setDesigination(desigination);
		account.setLocation(location);
		account.setCreatedDate();
		ResponseEntity<JSONObject> reponse = infoManagementClient.uploadDoc(account);
		if(reponse.getStatusCode().is2xxSuccessful()) {
			System.out.println("Success");
			reponse.getBody().put("successHtml", successHtml);
			successHtml = successHtml();
		}
		return reponse;
	}

	private String createAccountHtml() {
		String html = "<!DOCTYPE html>\n"
				+ "<html lang=\"en\">\n"
				+ "  <head>\n"
				+ "    <meta charset=\"utf-8\">\n"
				+ "    <meta name=\"viewport\" content=\"width=device-width, initial-scale=1, shrink-to-fit=no\">\n"
				+ "    <meta name=\"description\" content=\"\">\n"
				+ "    <meta name=\"author\" content=\"\">\n"
				+ "    <title>Please sign in</title>\n"
				+ "    <link href=\"https://maxcdn.bootstrapcdn.com/bootstrap/4.0.0-beta/css/bootstrap.min.css\" rel=\"stylesheet\" integrity=\"sha384-/Y6pD6FV/Vv2HJnA6t+vslU6fwYXjCFtcEpHbNJ0lyAFsXTsjBbfaDjzALeQsN6M\" crossorigin=\"anonymous\">\n"
				+ "    <link href=\"https://getbootstrap.com/docs/4.0/examples/signin/signin.css\" rel=\"stylesheet\" crossorigin=\"anonymous\"/>\n"
				+ "  </head>\n"
				+ "  <body>\n"
				+ "     <div class=\"container\">\n"
				+ "      <form class=\"form-signin\" method=\"check()\" action=\"/api/v1/success\">\n"
				+ "        <h2 class=\"form-signin-heading\">Please Register...</h2>\n"
				+ "        <p>\n"
				+ "          <label for=\"name\" class=\"sr-only\">Name</label>\n"
				+ "          <input type=\"text\" id=\"username\" name=\"username\" class=\"form-control\" placeholder=\"Name\" required autofocus>\n"
				+ "        </p>\n"
				+ "        <p>\n"
				+ "          <label for=\"desigination\" class=\"sr-only\">Desigination</label>\n"
				+ "          <input type=\"text\" id=\"desigination\" name=\"desigination\" class=\"form-control\" placeholder=\"Desigination\" required>\n"
				+ "        </p>\n"
				+ "        <p>\n"
				+ "          <label for=\"location\" class=\"sr-only\">Location</label>\n"
				+ "          <input type=\"text\" id=\"location\" name=\"location\" class=\"form-control\" placeholder=\"Location\" required>\n"
				+ "        </p>\n"
				+ "<input name=\"_csrf\" type=\"hidden\" value=\"5fbfe94c-6a72-4a89-ae93-74d9c1169214\" />\n"
				+ "        <button class=\"btn btn-lg btn-primary btn-block\" type=\"submit\" onclick=\"check()\">Register</button>\n"
				+ "      </form>\n"
				+ "</div>\n"
				+ "<script>"
				+ "function check() {        \n"
				+ "    document.getElementById('message').innerHTML = \"checking\";\n"
				+ "    const url = \"https://localhost//api/v1/success\";\n"
				+ "    const data = {\n"
				+ "        'username' : document.getElementById('username').value,\n"
				+ "        'desigination' : document.getElementById('desigination').value,\n"
				+ " 	   'location' : document.getElementById('location').value\n"
				+ "    };\n"
				+ "\n"
				+ "    const other_params = {\n"
				+ "        headers : { \"content-type\" : \"application/json; charset=UTF-8\" },\n"
				+ "        body : data,\n"
				+ "        method : \"POST\",\n"
				+ "        mode : \"cors\"\n"
				+ "    };"
				+ "</script>"
				+ "</body></html>";
		return html;
	}
	
	private String successHtml() {
		String html = "<!DOCTYPE html>\n" + "<html>\n" + "<head>\n"
				+ "<meta name=\"viewport\" content=\"width=device-width, initial-scale=1\">\n" + "<style>\n"
				+ ".alert {\n" + "  padding: 20px;\n" + "  background-color: #f44336;\n" + "  color: white;\n"
				+ "  opacity: 1;\n" + "  transition: opacity 0.6s;\n" + "  margin-bottom: 15px;\n" + "}\n" + "\n"
				+ ".alert.success {background-color: #04AA6D;}\n" + "\n" + ".closebtn {\n" + "  margin-left: 15px;\n"
				+ "  color: white;\n" + "  font-weight: bold;\n" + "  float: right;\n" + "  font-size: 22px;\n"
				+ "  line-height: 20px;\n" + "  cursor: pointer;\n" + "  transition: 0.3s;\n" + "}\n" + "\n"
				+ ".closebtn:hover {\n" + "  color: black;\n" + "}\n" + "</style>\n" + "</head>\n" + "<body>\n" + "\n"
				+ "<h2>Thank You</h2>\n" + "<div class=\"alert success\">\n"
				+ "  <span class=\"closebtn\">&times;</span>  \n"
				+ "  <strong>Success!</strong> User Registered Successfully...\n" + "</div>\n" + "\n" + "<script>\n"
				+ "var close = document.getElementsByClassName(\"closebtn\");\n" + "var i;\n" + "\n"
				+ "for (i = 0; i < close.length; i++) {\n" + "  close[i].onclick = function(){\n"
				+ "    var div = this.parentElement;\n" + "    div.style.opacity = \"0\";\n"
				+ "    setTimeout(function(){ div.style.display = \"none\"; }, 600);\n" + "  }\n" + "}\n"
				+ "</script>\n" + "\n" + "</body>\n" + "</html>\n" + "";
		return html;
	}
}