package com.example.common.bookmanager;

import android.os.RemoteException;

import java.util.ArrayList;
import java.util.List;

public class BookManager extends IBookManager.Stub {

    List<Book> mBooks = new ArrayList<>(4);

    @Override
    public void addBook(Book book) throws RemoteException {
        mBooks.add(book);
    }

    @Override
    public List<Book> getBookList() throws RemoteException {
        return mBooks;
    }


}
