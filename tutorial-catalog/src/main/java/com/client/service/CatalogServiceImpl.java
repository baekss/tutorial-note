package com.client.service;

import static java.util.stream.Collectors.toList;

import com.client.domain.Catalog;
import com.client.domain.CatalogRepository;
import com.client.vo.ResponseCatalog;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class CatalogServiceImpl implements CatalogService {

	private final CatalogRepository catalogRepository;
	private final ModelMapper mapper;

	@Override
	public List<ResponseCatalog> findAll() {
		return catalogRepository.findAll()
			.stream()
			.map(this::getResponseCatalog)
			.collect(toList());
	}

	private ResponseCatalog getResponseCatalog(Catalog catalog) {
		return mapper.map(catalog, ResponseCatalog.class);
	}
}
