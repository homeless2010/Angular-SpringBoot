package com.piedpiper;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.RequestBuilder;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import com.piedpiper.system.controller.LoginController;

@RunWith(SpringRunner.class)
@SpringBootTest
public class PiedpiperApplicationTests {
	private MockMvc mvc;

	@Before
	public void before() {
		mvc = MockMvcBuilders.standaloneSetup(new LoginController()).build();
	}

	@Test
	public void contextLoads() throws Exception {
		RequestBuilder req = get("/login");
		mvc.perform(req).andExpect(status().isOk()).andExpect(content().string("hello world!"));
	}

}
