package com.learning.mockito;

public class Seat {

    private Location location;
    private ClassOfSeat classOfSeat;
    private static Seat seat = new Seat();



    public static Seat near(Location location) {
        seat.setLocation(location);
        return seat;
    }

    private void setLocation(Location location) {
        this.location= location;
    }

    public Location getLocation() {
        return location;
    }


    public static Seat in(ClassOfSeat classOfSeat){
        seat.setClassOfSeat(classOfSeat);
        return seat;
    }

    private void setClassOfSeat(ClassOfSeat classOfSeat) {
        seat.classOfSeat = classOfSeat;
    }


}
