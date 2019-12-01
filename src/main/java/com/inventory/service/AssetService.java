package com.inventory.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.inventory.dto.AssetDto;
import com.inventory.entity.Asset;
import com.inventory.repository.AssetRepository;

@Service
public class AssetService {

	@Autowired
	private AssetRepository repo;

	@Autowired
	private ModelMapper mapper;

	public int registerAsset(AssetDto dto) {
		Asset a = new Asset();
		mapper.map(dto, a);
		return repo.saveAndFlush(a).getAssetId();
	}

	public List<AssetDto> getAsset(String assetName) {
		List<Asset> assets = repo.findByAssetName(assetName);
		List<AssetDto> response = new ArrayList<>();
		for (Asset a : assets) {
			AssetDto dto = new AssetDto();
			mapper.map(a, dto);
			response.add(dto);
		}
		return response;
	}

	public void deleteAsset(int assetId) {
		Asset a = searchAsset(assetId);
		if (a != null)
			repo.delete(a);

	}

	public void editAsset(int assetId, AssetDto dto) {
		Asset a = searchAsset(assetId);
		if (a != null) {
			a.setAssetName(dto.getAssetName());
			a.setInstallDate(dto.getInstallDate());
			a.setIsAvailable(dto.getIsAvailable());
			repo.save(a);
		}
	}

	private Asset searchAsset(int assetId) {
		Optional<Asset> a = repo.findById(assetId);
		return a.isPresent() ? a.get() : null;
	}
}
