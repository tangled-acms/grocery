package com.acms.grocery;

import static org.assertj.core.api.Assertions.assertThat;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.web.servlet.MockMvc;

import com.acms.controllers.ProductController;

@RunWith( SpringJUnit4ClassRunner.class )
@SpringBootTest
@AutoConfigureMockMvc
public class GroceryApplicationTests {
	@Autowired
	MockMvc mockMvc;
	
	@Autowired
	ProductController productController;
	
	@Test
	public void contextLoads() throws Exception {
		assertThat(productController).isNotNull();
		
	}
	
	
	@Test
	public void unknownRoute() throws Exception {
		this.mockMvc.perform(get("/RIP")).andExpect(status().is4xxClientError());
	}
	
	@Test
	public void testHome() throws Exception {
		this.mockMvc.perform(get("/product/getAll")).andExpect(status().isOk()); 
	}


}
