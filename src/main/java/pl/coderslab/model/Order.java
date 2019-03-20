package pl.coderslab.model;

import java.sql.Timestamp;

public class Order {

    private int id;
    private Timestamp admissioned;
    private Timestamp plannedStart;
    private String problem;
    private String scope;
    private String repairStatus;
    private int revenue;
    private int partsCost;
    private int manHourWage;
    private int manHours;

    public Order() {
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Timestamp getAdmissioned() {
        return admissioned;
    }

    public void setAdmissioned(Timestamp admissioned) {
        this.admissioned = admissioned;
    }

    public Timestamp getPlannedStart() {
        return plannedStart;
    }

    public void setPlannedStart(Timestamp plannedStart) {
        this.plannedStart = plannedStart;
    }

    public String getProblem() {
        return problem;
    }

    public void setProblem(String problem) {
        this.problem = problem;
    }

    public String getScope() {
        return scope;
    }

    public void setScope(String scope) {
        this.scope = scope;
    }

    public String getRepairStatus() {
        return repairStatus;
    }

    public void setRepairStatus(String repairStatus) {
        this.repairStatus = repairStatus;
    }

    public int getRevenue() {
        return revenue;
    }

    public void setRevenue(int revenue) {
        this.revenue = revenue;
    }

    public int getPartsCost() {
        return partsCost;
    }

    public void setPartsCost(int partsCost) {
        this.partsCost = partsCost;
    }

    public int getManHourWage() {
        return manHourWage;
    }

    public void setManHourWage(int manHourWage) {
        this.manHourWage = manHourWage;
    }

    public int getManHours() {
        return manHours;
    }

    public void setManHours(int manHours) {
        this.manHours = manHours;
    }
}
