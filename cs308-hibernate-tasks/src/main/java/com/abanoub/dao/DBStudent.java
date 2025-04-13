package com.abanoub.dao;

import com.abanoub.models.Student;
import java.util.List;
import org.hibernate.Session;
import org.hibernate.Transaction;

public class DBStudent {
    public List<Student> get() {

        try (Session session = DBConfig.SESSION_FACTORY.openSession()) {

            return session.createQuery("FROM Student", Student.class).getResultList();

        } catch (Exception ex) {
            System.err.println("COULDN'T FETCH STUDENTS: " + ex.getMessage());
        }

        return null;
    }

    public Student get(Integer studentId) {

        try (Session session = DBConfig.SESSION_FACTORY.openSession()) {

            return session.get(Student.class, studentId);

        } catch (Exception ex) {
            System.err.println("COUDN'T FETCH STUDENT: " + ex.getMessage());
        }

        return null;
    }

    public Integer insert(Student student) {
        Transaction transaction = null;
        int studentId = 0;

        try (Session session = DBConfig.SESSION_FACTORY.openSession()) {
            transaction = session.beginTransaction();

            studentId = (Integer) session.save(student);

            transaction.commit();
        } catch (Exception ex) {
            if (transaction != null) {
                transaction.rollback();
            }
            System.err.println("COUDN'T INSERT STUDENT: " + ex.getMessage());
        }

        return studentId;
    }

    public void update(Student student) {

        Transaction transaction = null;

        try (Session session = DBConfig.SESSION_FACTORY.openSession()) {
            transaction = session.beginTransaction();

            session.update(student);

            transaction.commit();
        } catch (Exception ex) {
            if (transaction != null) {
                transaction.rollback();
            }
            System.err.println("COUDN'T UPDATE STUDENT: " + ex.getMessage());
        }
    }

    public void delete(Integer studentId) {
        Transaction transaction = null;

        try (Session session = DBConfig.SESSION_FACTORY.openSession()) {
            transaction = session.beginTransaction();

            Student student = get(studentId);
            session.delete(student);

            transaction.commit();
        } catch (Exception ex) {
            if (transaction != null) {
                transaction.rollback();
            }
            System.err.println("COUDN'T DELETE STUDENT: " + ex.getMessage());
        }
    }
}
