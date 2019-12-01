package com.inventory.service;

import java.util.List;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.inventory.dto.AssetDto;

@SpringBootTest
class AssetServiceTest {

	@Autowired
	private AssetService service;
	
	@Test
	void testAssetService() {
		AssetDto a = new AssetDto();
		a.setAssetName("someAsset");
		a.setInstallDate(1234);
		a.setIsAvailable(true);
		service.registerAsset(a);

		List<AssetDto> assets = service.getAsset("someAsset");
		Assertions.assertEquals(1, assets.size());
		Assertions.assertTrue(assets.get(0).getIsAvailable());
		Assertions.assertEquals(1234, assets.get(0).getInstallDate());
		Assertions.assertEquals("someAsset", assets.get(0).getAssetName());
		
		a.setAssetName("newName");
		service.editAsset(assets.get(0).getAssetId(), a);
		
		assets = service.getAsset("someAsset");
		Assertions.assertEquals(0, assets.size());
		
		assets = service.getAsset("newName");
		Assertions.assertEquals(1, assets.size());
		Assertions.assertTrue(assets.get(0).getIsAvailable());
		Assertions.assertEquals(1234, assets.get(0).getInstallDate());
		Assertions.assertEquals("newName", assets.get(0).getAssetName());
		
		service.deleteAsset(assets.get(0).getAssetId());
		assets = service.getAsset("newName");
		Assertions.assertEquals(0, assets.size());
		
	}

}
