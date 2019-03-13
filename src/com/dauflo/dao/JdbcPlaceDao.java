package com.dauflo.dao;

import com.dauflo.entity.Place;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class JdbcPlaceDao extends AbstractJdbc implements PlaceDao {

    public JdbcPlaceDao() {
        try {
            Class.forName(JDBC_DRIVER);
            connection = DriverManager.getConnection(DB_URL, USER, PASS);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public void create(Place place) {
        try {
            PreparedStatement preparedStatement = connection.prepareStatement("INSERT INTO place (name) VALUE (?)");
            preparedStatement.setString(1, place.getName());

            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public List<Place> getPlaces() {
        List<Place> places = new ArrayList<>();
        String query = "SELECT * FROM place";

        try {
            Statement statement = connection.createStatement();
            ResultSet rs = statement.executeQuery(query);
            while(rs.next()) {
                places.add(new Place(rs.getLong("id"), rs.getString("name")));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return places;
    }

    @Override
    public boolean update(Place place) {
        try {
            PreparedStatement preparedStatement = connection.prepareStatement("UPDATE place SET name = ? WHERE id = ?");
            preparedStatement.setString(1, place.getName());
            preparedStatement.setLong(2, place.getId());

            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
            return  false;
        }
        return true;
    }

    @Override
    public boolean delete(Place place) {
        try {
            PreparedStatement preparedStatement = connection.prepareStatement("DELETE FROM place WHERE id = ?");
            preparedStatement.setLong(1, place.getId());

            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
        return true;
    }

    @Override
    public Place findById(long id) {
        try {
            PreparedStatement preparedStatement = connection.prepareStatement("SELECT * FROM place WHERE id = ?");
            preparedStatement.setLong(1, id);

            ResultSet rs = preparedStatement.executeQuery();
            while(rs.next()) {
                return new Place(rs.getLong("id"), rs.getString("name"));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }
}
