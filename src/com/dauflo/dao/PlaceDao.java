package com.dauflo.dao;

import com.dauflo.entity.Place;

import java.util.List;

public interface PlaceDao {
    void create(Place place);

    List<Place> getPlaces();

    boolean update(Place place);

    boolean delete(Place place);

    Place findById(long id);
}
