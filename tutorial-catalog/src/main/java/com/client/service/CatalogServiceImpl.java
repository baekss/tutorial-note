package com.client.service;

import static java.util.stream.Collectors.toList;

import com.client.domain.Catalog;
import com.client.domain.CatalogRepository;
import com.client.vo.ResponseCatalog;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.modelmapper.convention.MatchingStrategies;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class CatalogServiceImpl implements CatalogService {

	private final CatalogRepository catalogRepository;

	@Override
	public List<ResponseCatalog> findAll() {
		ModelMapper mapper = new ModelMapper();
		mapper.getConfiguration().setMatchingStrategy(MatchingStrategies.STRICT);
		return catalogRepository.findAll()
			.stream()
			.map(catalog -> getResponseCatalog(mapper, catalog))
			.collect(toList());
	}

	private ResponseCatalog getResponseCatalog(ModelMapper mapper, Catalog catalog) {
		ResponseCatalog res = mapper.map(catalog, ResponseCatalog.class);
		return res;
	}
}
