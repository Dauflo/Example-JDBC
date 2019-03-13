package com.dauflo.dao;

import com.dauflo.entity.Trip;

import java.util.List;

public interface TripDao {
    void create(Trip trip);

    List<Trip> getTrips();

    boolean update(Trip trip);

    boolean delete(Trip trip);

    Trip findById(long id);

    List<Trip> getTripsByDepAndDesId(long departureId, long destinationId);
}
