package com.paparising.receiptmanager.domain.enumeration;

/**
 * The receiving patient type enumeration.
 */
public enum ReceivingType {
    ABULMANCE (1, "呼救（120或其它）出车"),
    TRANSFER(2, "转院（包含任何医疗机构"),
    SELF(3, "自行来院 "),
    INHOUSE(4, "院内发病");
    
    private final int index;
    private final String name;
    
    ReceivingType(int index, String name) {
        this.index = index;
        this.name = name;
    }

    public int getIndex() {
        return index;
    }

    public String getName() {
        return name;
    }
}
