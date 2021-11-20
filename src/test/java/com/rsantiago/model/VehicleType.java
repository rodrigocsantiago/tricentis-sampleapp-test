package com.rsantiago.model;

public enum VehicleType {
    AUTOMOBILE("Automobile"),
    TRUCK("Truck"),
    MOTORCYCLE("Motorcycle"),
    CAMPER("Camper");

    private final String typeName;

    public String getTypeName() {
        return this.typeName;
    }

    public boolean isEqualTo(String typeName) {
        return this.getTypeName().equalsIgnoreCase(typeName);
    }

    VehicleType(String typeName) {
        this.typeName = typeName;
    }
}
