package com.example.Resource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.example.client.InfoManagementClient;
import com.example.service.AccountService;

import net.minidev.json.JSONObject;

@Validated
@RestController
@RequestMapping("/api/v1")
public class AccountResource {

	@Autowired
	InfoManagementClient infoManagementClient;
	
	@Autowired
	AccountService accountService;
	
	@RequestMapping("/home")
	public String createAccount() {
		return accountService.createAccount();
	}
	
	@RequestMapping("/success")
	public ResponseEntity<JSONObject> registered(@RequestParam(value = "username") String username,
			@RequestParam(value = "desigination") String desigination,
			@RequestParam(value = "location") String location) {
		return accountService.registered(username, desigination, location);
	}
}