package com.college.model.infrastructure;

public class Classroom {
    private int    roomId;
    private String building;
    private int    capacity;

    public Classroom() {}
    public Classroom(int id, String building, int capacity) {
        this.roomId = id; this.building = building; this.capacity = capacity;
    }

    public int    getRoomId()   { return roomId; }
    public String getBuilding() { return building; }
    public int    getCapacity() { return capacity; }

    public void setRoomId(int v)    { roomId    = v; }
    public void setBuilding(String v){ building = v; }
    public void setCapacity(int v)  { capacity  = v; }
}
