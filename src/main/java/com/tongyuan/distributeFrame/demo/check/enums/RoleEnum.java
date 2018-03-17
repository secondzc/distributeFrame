package com.tongyuan.distributeFrame.demo.check.enums;

/**
 * Created by zhangcy on 2018/3/16
 */
public enum RoleEnum {
    MachineEngineer("machineEngineer"),
    ElectricEngineer("electricEngineer"),
    HydraulicEngineer("hydraulicEngineer"),
    PhsicalEngineer("pysicalEngineer"),
    DrawingEngineer("drawingEngineer");

    private String engineerRole;

    public String getEngineerRole() {
        return engineerRole;
    }

    public void setEngineerRole(String engineerRole) {
        this.engineerRole = engineerRole;
    }

    RoleEnum(String engineerRole) {
        this.engineerRole = engineerRole;
    }

}
