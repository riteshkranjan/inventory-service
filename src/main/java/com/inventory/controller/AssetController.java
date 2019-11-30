package com.inventory.controller;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.inventory.dto.AssetDto;
import com.inventory.entity.Asset;
import com.inventory.service.AssetService;

@RestController
@RequestMapping("/asset")
@Validated
public class AssetController {

	@Autowired
	private AssetService service;

	@PostMapping
	public int registerAsset(@Valid @RequestBody Asset a) {
		return service.registerAsset(a);
	}

	@PutMapping("/{assetId}")
	public void editAsset(@PathVariable int assetId, @Valid @RequestBody AssetDto a) {
		service.editAsset(assetId, a);
	}

	@DeleteMapping("/{assetId}")
	public void deleteAsset(@PathVariable int assetId) {
		service.deleteAsset(assetId);
	}

	@GetMapping("/{assetName}")
	public List<AssetDto> getAsset(@PathVariable String assetName) {
		return service.getAsset(assetName);
	}

	@ExceptionHandler(MethodArgumentNotValidException.class)
	@ResponseStatus(value = HttpStatus.OK)
	public String handleIOException(MethodArgumentNotValidException ex, HttpServletRequest request) {
		StringBuilder sb = new StringBuilder("Invalid request : ");
		if (ex.getBindingResult() != null && ex.getBindingResult().getFieldError() != null) {
			sb.append(ex.getBindingResult().getFieldError().getField()).append(" : ")
					.append(ex.getBindingResult().getFieldError().getDefaultMessage());
		}
		return sb.toString();
	}

}
