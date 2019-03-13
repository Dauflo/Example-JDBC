package com.dauflo.dao;

import com.dauflo.entity.Place;
import com.dauflo.entity.Trip;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class JdbcTripDao extends AbstractJdbc implements TripDao {

    public JdbcTripDao() {
        try {
            Class.forName(JDBC_DRIVER);
            connection = DriverManager.getConnection(DB_URL, USER, PASS);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public void create(Trip trip) {
        try {
            PreparedStatement preparedStatement = connection.prepareStatement("INSERT INTO trip (departure_id, destination_id, price) VALUE (?, ?, ?)");
            preparedStatement.setLong(1, trip.getDeparture().getId());
            preparedStatement.setLong(2, trip.getDestination().getId());
            preparedStatement.setLong(3, trip.getPrice());

            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public List<Trip> getTrips() {
        List<Trip> trips = new ArrayList<>();
        String query = "SELECT * FROM trip";

        try {
            Statement statement = connection.createStatement();
            ResultSet rs = statement.executeQuery(query);
            while(rs.next()) {
                JdbcPlaceDao placeDao = new JdbcPlaceDao();
                Place destination = placeDao.findById(rs.getLong("destination_id"));
                Place departure = placeDao.findById(rs.getLong("departure_id"));

                trips.add(new Trip(rs.getLong("id"), departure, destination, rs.getLong("price")));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return trips;
    }

    @Override
    public boolean update(Trip trip) {
        try {
            PreparedStatement preparedStatement = connection.prepareStatement("UPDATE trip SET departure_id = ?, destination_id = ?, price = ? WHERE id = ?");
            preparedStatement.setLong(1, trip.getDeparture().getId());
            preparedStatement.setLong(2, trip.getDestination().getId());
            preparedStatement.setLong(3, trip.getPrice());
            preparedStatement.setLong(4, trip.getId());

            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
            return  false;
        }
        return true;
    }

    @Override
    public boolean delete(Trip trip) {
        try {
            PreparedStatement preparedStatement = connection.prepareStatement("DELETE FROM trip WHERE id = ?");
            preparedStatement.setLong(1, trip.getId());

            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
        return true;
    }

    @Override
    public Trip findById(long id) {
        try {
            PreparedStatement preparedStatement = connection.prepareStatement("SELECT * FROM trip WHERE id = ?");
            preparedStatement.setLong(1, id);

            ResultSet rs = preparedStatement.executeQuery();
            while(rs.next()) {
                JdbcPlaceDao placeDao = new JdbcPlaceDao();
                Place destination = placeDao.findById(rs.getLong("destination_id"));
                Place departure = placeDao.findById(rs.getLong("departure_id"));

                return  new Trip(rs.getLong("id"), departure, destination, rs.getLong("price"));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public List<Trip> getTripsByDepAndDesId(long departureId, long destinationId) {
        List<Trip> trips = new ArrayList<>();

        try {
            PreparedStatement preparedStatement = connection.prepareStatement("SELECT * FROM trip WHERE departure_id = ? AND destination_id = ?");
            preparedStatement.setLong(1, departureId);
            preparedStatement.setLong(2, destinationId);

            ResultSet rs = preparedStatement.executeQuery();
            while(rs.next()) {
                JdbcPlaceDao placeDao = new JdbcPlaceDao();
                Place destination = placeDao.findById(rs.getLong("destination_id"));
                Place departure = placeDao.findById(rs.getLong("departure_id"));

                trips.add(new Trip(rs.getLong("id"), departure, destination, rs.getLong("price")));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return trips;
    }
}
