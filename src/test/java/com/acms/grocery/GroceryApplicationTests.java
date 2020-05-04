package com.acms.grocery;

import java.util.Collections;

import org.junit.Test;
//import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import com.acms.repositories.ProductRepository;
import com.acms.services.ProductService;


@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest(classes = GroceryApplication.class)
public class GroceryApplicationTests {
	
	@Autowired
	MockMvc mockMvc;
	
	@MockBean
	ProductService productService;
	
	@Test
	public void contextLoads() throws Exception {
		
		Mockito.when(productService.getAllProductDetails()).thenReturn(Collections.emptyList());
		MvcResult mvcResult = mockMvc.perform(
				MockMvcRequestBuilders.get("/product/getAll")
				).andReturn();
		System.out.println(mvcResult.getResponse());
		Mockito.verify(productService).getAllProductDetails();
	}

}
