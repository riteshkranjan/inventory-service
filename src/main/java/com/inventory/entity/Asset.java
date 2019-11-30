package com.inventory.entity;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.validation.constraints.NotBlank;

import lombok.Data;

@Data
@Entity
public class Asset {
	@Id
	@GeneratedValue
	private Integer assetId;
	@NotBlank(message = "asset name can not be blank")
	private String assetName;
	private Integer installDate;

}
