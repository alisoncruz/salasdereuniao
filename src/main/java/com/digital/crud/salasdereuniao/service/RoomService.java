package com.digital.crud.salasdereuniao.service;

import com.digital.crud.salasdereuniao.exceptions.ResourceNotFoundException;
import com.digital.crud.salasdereuniao.model.Room;
import com.digital.crud.salasdereuniao.repository.RoomRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class RoomService {

  RoomRepository roomRepository;

  @Autowired
  RoomService(RoomRepository roomRepository) {
    this.roomRepository = roomRepository;
  }

  public List<Room> listAll() {
    return this.roomRepository.findAll();
  }

  public Room getById(Long roomId) {
    Room room =
        roomRepository
            .findById(roomId)
            .orElseThrow(() -> new ResourceNotFoundException("Room not found: " + roomId));
    return room;
  }

  public Room create(Room room) {
    Room created = roomRepository.save(room);
    return created;
  }

  public Room update(Room roomDetails, Long roomId) {
    Room room =
        roomRepository
            .findById(roomId)
            .orElseThrow(
                () -> new ResourceNotFoundException("Room not found for this id: " + roomId));
    room.setDate(roomDetails.getDate());
    room.setName(roomDetails.getName());
    room.setStartHour(roomDetails.getStartHour());
    room.setEndHour(roomDetails.getEndHour());
    Room updated = roomRepository.save(room);
    return updated;
  }

  public Map<String, Boolean> delete(Long roomId) {
    Room room =
        roomRepository
            .findById(roomId)
            .orElseThrow(
                () -> new ResourceNotFoundException("Room not found for this id: " + roomId));

    roomRepository.delete(room);
    Map<String, Boolean> response = new HashMap<>();
    response.put("deleted", true);
    return response;
  }
}
