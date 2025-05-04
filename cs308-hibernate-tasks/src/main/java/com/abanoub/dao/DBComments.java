package com.abanoub.dao;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.query.Query;

import com.abanoub.criteria.FilterQuery;
import com.abanoub.criteria.Operator;
import com.abanoub.models.Comment;

public class DBComments {
    public List<Comment> get() {

        try (Session session = DBConfig.SESSION_FACTORY.openSession()) {

            return session.createQuery("FROM Comment", Comment.class).getResultList();

        } catch (Exception ex) {
            System.err.println(ex.getMessage());
        }

        return null;
    }

    public Comment get(Integer commentId) {

        try (Session session = DBConfig.SESSION_FACTORY.openSession()) {

            return session.get(Comment.class, commentId);

        } catch (Exception ex) {
            System.err.println(ex.getMessage());
        }

        return null;
    }

    public Integer insert(Comment comment) {

        Transaction transaction = null;
        int commentId = 0;

        try (Session session = DBConfig.SESSION_FACTORY.openSession()) {

            transaction = session.beginTransaction();

            commentId = (Integer) session.save(comment);

            transaction.commit();

        } catch (Exception ex) {
            if (transaction != null) {
                transaction.rollback();
            }
            System.err.println(ex.getMessage());
        }

        return commentId;
    }

    public List<Comment> getByFilter(List<FilterQuery> filterQueries) {
        try (Session session = DBConfig.SESSION_FACTORY.openSession()) {

            CriteriaBuilder cb = session.getCriteriaBuilder();
            CriteriaQuery<Comment> cr = cb.createQuery(Comment.class);
            Root<Comment> root = cr.from(Comment.class);

            List<Predicate> predicates = new ArrayList<>();

            for (FilterQuery fq : filterQueries) {
                String attributeName = fq.getAttributeName();
                Object value = fq.getAttributeValue();
                Operator op = fq.getOp();

                if (attributeName == null || value == null || op == null)
                    continue;

                switch (op) {
                    case EQ:
                        predicates.add(cb.equal(root.get(attributeName), value)); // Comment.get(lastname) == "Fady"
                        break;
                    case GT:
                        if (value instanceof Number) {
                            predicates.add(cb.gt(root.get(attributeName), (Number) value));
                        }
                        break;
                    default:
                        break;
                }
            }

            cr.select(root).where(cb.and(predicates.toArray(new Predicate[0])));
            Query<Comment> query = session.createQuery(cr);
            return query.getResultList();

        } catch (Exception ex) {
            ex.printStackTrace();
        }

        return new ArrayList<>();
    }
}
