package com.retry_connection;

import com.retry_connection.controller.Controller;
import com.retry_connection.service.RetryConnectionService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;

import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(Controller.class)
public class RetryConnectionApplicationTests {

	@Autowired
	private MockMvc mockMvc;

	@MockBean
	private RetryConnectionService service;

	@Test
	public void shouldReturnTrueFromService() throws Exception {
		for (int i = 0; i < 6; i++) {
			when(service.someMethod1("")).thenReturn(true);
			this.mockMvc.perform(get("/ip")).andDo(print()).andExpect(status().isOk());
		}
	}
}
