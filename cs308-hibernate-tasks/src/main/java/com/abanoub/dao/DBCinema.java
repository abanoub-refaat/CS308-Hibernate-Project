package com.abanoub.dao;

import com.abanoub.models.Actor;
import com.abanoub.models.Address;
import com.abanoub.models.City;
import com.abanoub.models.Movie;
import java.util.List;
import org.hibernate.Session;

public class DBCinema {

    @SuppressWarnings("unchecked")
    public List<Address> getAddresses() {

        try (Session session = DBConfig.SESSION_FACTORY.openSession()) {

            return session.createQuery("FROM Address").list();

        } catch (Exception ex) {
            System.err.println(ex.getMessage());
        }

        return null;
    }

    @SuppressWarnings("unchecked")
    public List<City> getCities() {

        try (Session session = DBConfig.SESSION_FACTORY.openSession()) {

            return session.createQuery("FROM City").list();

        } catch (Exception ex) {
            System.err.println(ex.getMessage());
        }

        return null;
    }

    @SuppressWarnings("unchecked")
    public List<Actor> getActors() {

        try (Session session = DBConfig.SESSION_FACTORY.openSession()) {

            return session.createQuery("FROM Actor").list();

        } catch (Exception ex) {
            System.err.println(ex.getMessage());
        }

        return null;
    }

    @SuppressWarnings("unchecked")
    public List<Movie> getMovies() {

        try (Session session = DBConfig.SESSION_FACTORY.openSession()) {

            return session.createQuery("FROM Movie").list();

        } catch (Exception ex) {
            System.err.println(ex.getMessage());
        }

        return null;
    }

    public static void main(String[] args) {
        DBCinema dbCinema = new DBCinema();

        List<Actor> actors = dbCinema.getActors();

        for (Actor actor : actors) {
            System.out.println(actor.getName());
            System.out.println(actor.getAddress());
            System.out.println(actor.getCity());
        }

        List<Address> addresses = dbCinema.getAddresses();

        for (Address address : addresses) {
            System.out.println(address.getStreet());
        }

        System.out.println("-----------------------");

        List<City> cities = dbCinema.getCities();

        for (City city : cities) {
            System.out.println(city.getName());
            System.out.println(city.getActors());
        }

        System.out.println("-----------------------");

        List<Movie> movies = dbCinema.getMovies();

        for (Movie movie : movies) {
            System.out.println(movie.getName());
            System.out.println(movie.getActors());
        }

    }
}