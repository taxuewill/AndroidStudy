// IBookManager.aidl
package com.example.common.bookmanager;

// Declare any non-default types here with import statements
import com.example.common.bookmanager.Book;

interface IBookManager {
    void addBook(in Book book);

    List<Book> getBookList();
}