package edu.baylor.ecs.csi3471.hotelReservationSystem.backend;

public enum CleanStatus {
  CLEAN, //ready to place next occupant
  DIRTY, //occupant has checked out
  OCCUPIED;
}
