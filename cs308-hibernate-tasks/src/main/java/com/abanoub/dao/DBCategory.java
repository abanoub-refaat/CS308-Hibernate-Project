package com.abanoub.dao;

import java.util.List;

import org.hibernate.Session;
import org.hibernate.Transaction;

import com.abanoub.models.Category;

public class DBCategory {
    public Integer insertCategory(Category category) {
        Transaction transaction = null;
        int categoryId = 0;

        try (Session session = DBConfig.SESSION_FACTORY.openSession()) {
            transaction = session.beginTransaction();

            categoryId = (Integer) session.save(category);

            transaction.commit();
        } catch (Exception ex) {
            if (transaction != null) {
                transaction.rollback();
            }
            System.err.println("COUDN'T INSERT CATEGORY: " + ex.getMessage());
        }

        return categoryId;
    }

    public List<Category> getAllProducts() {

        try (Session session = DBConfig.SESSION_FACTORY.openSession()) {

            return session.createQuery("FROM Category", Category.class).getResultList();

        } catch (Exception ex) {
            System.err.println("COULDN'T FETCH CATEGORIES: " + ex.getMessage());
        }

        return null;
    }
}
