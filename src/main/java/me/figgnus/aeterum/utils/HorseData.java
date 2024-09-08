package me.figgnus.aeterum.utils;

import org.bukkit.Location;

import java.util.UUID;

public class HorseData {
    private UUID horseUUID;
    private String horseWorld;
    private Location horseLocation;
    private boolean horseAlive;

    public HorseData(UUID horseUUID, String horseWorld, Location horseLocation, boolean horseAlive) {
        this.horseUUID = horseUUID;
        this.horseWorld = horseWorld;
        this.horseLocation = horseLocation;
        this.horseAlive = horseAlive;
    }
    public boolean isHorseAlive() {
        return horseAlive;
    }
    public void setHorseAlive(boolean horseAlive) {
        this.horseAlive = horseAlive;
    }
    public UUID getHorseUUID() {
        return horseUUID;
    }

    public String getHorseWorld() {
        return horseWorld;
    }

    public Location getHorseLocation() {
        return horseLocation;
    }

    public void setHorseUUID(UUID horseUUID) {
        this.horseUUID = horseUUID;
    }

    public void setHorseWorld(String horseWorld) {
        this.horseWorld = horseWorld;
    }

    public void setHorseLocation(Location horseLocation) {
        this.horseLocation = horseLocation;
    }
}
