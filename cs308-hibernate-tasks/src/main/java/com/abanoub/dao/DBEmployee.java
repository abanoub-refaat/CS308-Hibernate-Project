package com.abanoub.dao;

import com.abanoub.models.Employee;
import java.util.List;
import org.hibernate.Session;
import org.hibernate.Transaction;
import java.util.ArrayList;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
import org.hibernate.query.Query;
import com.abanoub.criteria.FilterQuery;
import com.abanoub.criteria.Operator;

public class DBEmployee {

    public List<Employee> get() {

        try (Session session = DBConfig.SESSION_FACTORY.openSession()) {

            return session.createQuery("FROM Employee", Employee.class).getResultList();

        } catch (Exception ex) {
            System.err.println(ex.getMessage());
        }

        return null;
    }

    public Employee get(Integer employeeId) {

        try (Session session = DBConfig.SESSION_FACTORY.openSession()) {

            return session.get(Employee.class, employeeId);

        } catch (Exception ex) {
            System.err.println(ex.getMessage());
        }

        return null;
    }

    public Integer insert(Employee employee) {

        Transaction transaction = null;
        int employeeId = 0;

        try (Session session = DBConfig.SESSION_FACTORY.openSession()) {

            transaction = session.beginTransaction();

            employeeId = (Integer) session.save(employee);

            transaction.commit();

        } catch (Exception ex) {
            if (transaction != null) {
                transaction.rollback();
            }
            System.err.println(ex.getMessage());
        }

        return employeeId;
    }

    public void update(Employee employee) {

        Transaction transaction = null;

        try (Session session = DBConfig.SESSION_FACTORY.openSession()) {

            transaction = session.beginTransaction();

            session.update(employee);

            transaction.commit();

        } catch (Exception ex) {
            if (transaction != null) {
                transaction.rollback();
            }
            System.err.println(ex.getMessage());
        }
    }

    public void delete(Integer employeeId) {

        Transaction transaction = null;

        try (Session session = DBConfig.SESSION_FACTORY.openSession()) {

            transaction = session.beginTransaction();

            Employee employee = get(employeeId);

            session.delete(employee);

            transaction.commit();

        } catch (Exception ex) {
            if (transaction != null) {
                transaction.rollback();
            }
            System.err.println(ex.getMessage());
        }
    }

    public List<Employee> getByFilter(List<FilterQuery> filterQueries) {
        try (Session session = DBConfig.SESSION_FACTORY.openSession()) {

            CriteriaBuilder cb = session.getCriteriaBuilder();
            CriteriaQuery<Employee> cr = cb.createQuery(Employee.class);
            Root<Employee> root = cr.from(Employee.class);

            List<Predicate> predicates = new ArrayList<>();

            for (FilterQuery fq : filterQueries) {
                String attributeName = fq.getAttributeName();
                Object value = fq.getAttributeValue();
                Operator op = fq.getOp();

                if (attributeName == null || value == null || op == null)
                    continue;

                switch (op) {
                    case EQ:
                        predicates.add(cb.equal(root.get(attributeName), value));
                        break;
                    case GT:
                        if (value instanceof Number) {
                            predicates.add(cb.gt(root.get(attributeName), (Number) value));
                        }
                        break;
                    case LT:
                        if (value instanceof Number) {
                            predicates.add(cb.lt(root.get(attributeName), (Number) value));
                        }
                        break;
                    // case GTE:
                    // if (value instanceof Number) {
                    // predicates.add(cb.ge(root.get(attributeName), (Number) value));
                    // }
                    // break;
                    case LTE:
                        if (value instanceof Number) {
                            predicates.add(cb.le(root.get(attributeName), (Number) value));
                        }
                        break;
                    // case LIKE:
                    // if (value instanceof String) {
                    // predicates.add(cb.like(root.get(attributeName), "%" + value + "%"));
                    // }
                    // break;
                    default:
                        break;
                }
            }

            cr.select(root).where(cb.and(predicates.toArray(new Predicate[0])));
            Query<Employee> query = session.createQuery(cr);
            return query.getResultList();

        } catch (Exception ex) {
            ex.printStackTrace();
        }

        return new ArrayList<>();
    }

}
