package com.client.service;

import com.client.vo.ResponseCatalog;
import java.util.List;

public interface CatalogService {
	List<ResponseCatalog> findAll();
}
