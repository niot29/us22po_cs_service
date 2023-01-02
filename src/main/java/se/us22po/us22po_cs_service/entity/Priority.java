package se.us22po.us22po_cs_service.entity;

public enum Priority {
    LOW(1),
    MEDIUM(2),
    HIGH(3);

    Priority(int priorityValue) {
        this.priorityValue = priorityValue;
    }

    final int priorityValue;


}
