package com.abanoub.dao;

import org.hibernate.Session;
import org.hibernate.Transaction;

import com.abanoub.models.Blog;

public class DBBlog {

    public Integer insert(Blog blog) {

        Transaction transaction = null;
        int blogId = 0;

        try (Session session = DBConfig.SESSION_FACTORY.openSession()) {

            transaction = session.beginTransaction();

            blogId = (Integer) session.save(blog);

            transaction.commit();

        } catch (Exception ex) {
            if (transaction != null) {
                transaction.rollback();
            }
            System.err.println(ex.getMessage());
        }

        return blogId;
    }
}
