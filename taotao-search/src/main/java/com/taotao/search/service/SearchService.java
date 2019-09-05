package com.taotao.search.service;

import javax.naming.directory.SearchResult;

public interface SearchService {

	SearchResult search(String queryString, int page, int rows);
}
