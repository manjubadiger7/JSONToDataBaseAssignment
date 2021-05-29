package com.midtree.Files.JsonDataTodatabase.Service;

import java.util.Set;

import com.midtree.Files.JsonDataTodatabase.Model.Book;

public interface JsonTodatabase {

	void storeJsonData(Set<Book> books) throws Exception;

	void getBooks() throws Exception;

}
