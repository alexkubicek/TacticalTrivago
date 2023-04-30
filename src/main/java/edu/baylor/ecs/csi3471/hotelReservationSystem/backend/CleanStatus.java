/*
 * An enum that indicates the status of a room in terms of
 * cleanliness and occupation
 */
package edu.baylor.ecs.csi3471.hotelReservationSystem.backend;

public enum CleanStatus {
  CLEAN, //ready to place next occupant
  DIRTY, //occupant has checked out
  OCCUPIED;
}
