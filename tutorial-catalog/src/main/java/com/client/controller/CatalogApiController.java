package com.client.controller;

import com.client.service.CatalogService;
import com.client.vo.ResponseCatalog;
import java.util.List;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@RestController
@RequiredArgsConstructor
@RequestMapping("/api")
public class CatalogApiController {

	private final CatalogService catalogService;

	@GetMapping("/catalogs")
	public ResponseEntity<List<ResponseCatalog>> findAll() {
		return ResponseEntity.ok(catalogService.findAll());
	}
}
