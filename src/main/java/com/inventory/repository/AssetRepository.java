package com.inventory.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.inventory.entity.Asset;

public interface AssetRepository extends JpaRepository<Asset, Integer> {

	List<Asset> findByAssetName(String assetName);

}
