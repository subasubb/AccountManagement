package com.example.client;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

import com.example.Model.Account;

import net.minidev.json.JSONObject;

@FeignClient(name = "info-management")
public interface InfoManagementClient {

	@PostMapping(value = "/api/v1/value/save")
	public ResponseEntity<JSONObject> uploadDoc(@RequestBody Account account);

	
	@Component
	public class InfoManagementClientFallback implements InfoManagementClient {

		private Throwable cause;

		public InfoManagementClientFallback() {
			this(null);
		}

		InfoManagementClientFallback(Throwable cause) {
			this.cause = cause;
		}

		@Override
		public ResponseEntity<JSONObject> uploadDoc(Account account) {
			System.out.println("Fallback initiated for DocumentManagementClient ");
			return null;
		}

	}
	@Component
	public class InfoManagementClientFallbackFactory
			implements org.springframework.cloud.openfeign.FallbackFactory<InfoManagementClient> {

		@Override
		public InfoManagementClient create(Throwable cause) {
			return new InfoManagementClientFallback(cause);
		}
	}

}
