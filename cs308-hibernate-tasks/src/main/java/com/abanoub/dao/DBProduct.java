package com.abanoub.dao;

import java.util.List;

import org.hibernate.Session;
import org.hibernate.Transaction;

import com.abanoub.models.Product;

public class DBProduct {
    public Integer insertProduct(Product product) {
        Transaction transaction = null;
        int productId = 0;

        try (Session session = DBConfig.SESSION_FACTORY.openSession()) {
            transaction = session.beginTransaction();

            productId = (Integer) session.save(product);

            transaction.commit();
        } catch (Exception ex) {
            if (transaction != null) {
                transaction.rollback();
            }
            System.err.println("COUDN'T INSERT PRODUCT: " + ex.getMessage());
        }

        return productId;
    }

    public List<Product> getAllProducts() {

        try (Session session = DBConfig.SESSION_FACTORY.openSession()) {

            return session.createQuery("FROM Product", Product.class).getResultList();

        } catch (Exception ex) {
            System.err.println("COULDN'T FETCH PRODUCTS: " + ex.getMessage());
        }

        return null;
    }
}
