package se.us22po.us22po_cs_service.entity;

public enum Status {
    PENGINS("3"),
    INPROGRESS("2"),
    DONE("1");


    private Status(String statusValue) {
        this.statusValue = statusValue;
    }

    public final String statusValue;

}
