package com.inventory.dto;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

import lombok.Data;

@Data
public class AssetDto {

	private Integer assetId;
	@NotEmpty
	private String assetName;
	@NotNull
	private Integer installDate;
	private Boolean isAvailable;


}
