package com.inventory.dto;

import lombok.Data;

@Data
public class AssetDto {

	private Integer assetId;
	private String assetName;
	private Integer installDate;
	private Boolean isAvailable;


}
