package com.app.groupprojectapplication.dao.impl;

import com.app.groupprojectapplication.dao.ITestDao;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

//don't forget to add @Repository here.
@Repository
public class TestDaoImpl implements ITestDao {

    @Autowired
    protected SessionFactory sessionFactory;

    @Override
    public void test() {
        int i = 1;
        System.out.println(i);
        for(int j=0; j<5; j++){
            System.out.println();
        }
        //do some database operation here.
        Session session = sessionFactory.getCurrentSession();
//        Book currentBook = session.get(Book.class, book.getBookId());
//        currentBook.setName(book.getName());
//        currentBook.setAuthor(book.getAuthor());
//        currentBook.setPublishDate(book.getPublishDate());
//        session.update(currentBook);
    }
}
