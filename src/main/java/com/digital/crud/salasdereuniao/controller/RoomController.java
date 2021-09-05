package com.digital.crud.salasdereuniao.controller;

import com.digital.crud.salasdereuniao.exceptions.ResourceNotFoundException;
import com.digital.crud.salasdereuniao.model.Room;
import com.digital.crud.salasdereuniao.repository.RoomRepository;

import com.digital.crud.salasdereuniao.service.RoomService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@CrossOrigin(origins = "http://localhost:4200")
@RequestMapping("/api/v1/rooms")
public class RoomController {

  private RoomService roomService;

  @Autowired
  public RoomController(RoomService roomService) {
    this.roomService = roomService;
  }

  @GetMapping
  public ResponseEntity<?> getAllRooms() {
    List<Room> list = roomService.listAll();
    if (list.isEmpty()) {
      return ResponseEntity.noContent().build();
    }
    return ResponseEntity.ok(list);
  }

  @GetMapping("/{id}")
  public ResponseEntity<Room> getRoomById(@PathVariable(value = "id") Long roomId)
      throws ResourceNotFoundException {
    Room room = roomService.getById(roomId);
    return ResponseEntity.ok().body(room);
  }

  @PostMapping
  public ResponseEntity<Room> createRoom(@Valid @RequestBody Room room) {
    Room created = roomService.create(room);
    return ResponseEntity.ok(created);
  }

  @PutMapping("/{id}")
  public ResponseEntity<Room> updateRoom(
      @PathVariable(value = "id") Long roomId, @Valid @RequestBody Room roomDetails) {
    Room updated = roomService.update(roomDetails, roomId);
    return ResponseEntity.ok(updated);
  }

  @DeleteMapping("/{id}")
  public Map<String, Boolean> deleteRoom(@PathVariable(value = "id") Long roomId) {
    Map<String, Boolean> response = roomService.delete(roomId);
    return response;
  }
}
