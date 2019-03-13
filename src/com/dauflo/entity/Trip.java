package com.dauflo.entity;

import java.io.Serializable;

public class Trip implements Serializable {
    private long id;
    private Place departure;
    private Place destination;
    private long price;

    public Trip() {
    }

    public Trip(Place departure, Place destination, long price) {
        this.departure = departure;
        this.destination = destination;
        this.price = price;
    }

    public Trip(long id, Place departure, Place destination, long price) {
        this.id = id;
        this.departure = departure;
        this.destination = destination;
        this.price = price;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public Place getDeparture() {
        return departure;
    }

    public void setDeparture(Place departure) {
        this.departure = departure;
    }

    public Place getDestination() {
        return destination;
    }

    public void setDestination(Place destination) {
        this.destination = destination;
    }

    public long getPrice() {
        return price;
    }

    public void setPrice(long price) {
        this.price = price;
    }
}
