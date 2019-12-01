package com.inventory.controller;

import static org.mockito.Mockito.when;
import static org.springframework.http.MediaType.APPLICATION_JSON;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.util.ArrayList;
import java.util.List;

import org.json.JSONArray;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;

import com.inventory.dto.AssetDto;
import com.inventory.service.AssetService;

@WebMvcTest(AssetController.class)
class AssetControllerTest {

	@Autowired
	private MockMvc mvc;

	@MockBean
	private AssetService service;

	@Test
	void testRegisterAsset() throws Exception {
		List<AssetDto> list = new ArrayList<>();
		AssetDto dto = new AssetDto();
		dto.setAssetName("someAsset");
		dto.setInstallDate(1234);
		dto.setIsAvailable(true);
		list.add(dto);
		when(service.getAsset("someAsset")).thenReturn(list);
		MvcResult response = mvc.perform(get("/asset/someAsset").contentType(APPLICATION_JSON))
				.andExpect(status().isOk()).andReturn();
		JSONArray jsonArr = new JSONArray(response.getResponse().getContentAsString());
		Assertions.assertEquals(1, jsonArr.length());
		Assertions.assertTrue(jsonArr.getJSONObject(0).getBoolean("isAvailable"));
		Assertions.assertEquals(1234, jsonArr.getJSONObject(0).getInt("installDate"));
		Assertions.assertEquals("someAsset", jsonArr.getJSONObject(0).getString("assetName"));

	}

}
