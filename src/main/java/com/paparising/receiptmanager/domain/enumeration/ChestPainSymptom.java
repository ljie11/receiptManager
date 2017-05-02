package com.paparising.receiptmanager.domain.enumeration;

/**
 * The ChestPain Symptom enumeration.
 */
public enum ChestPainSymptom {
    PERSISTENT_CHEST_PAIN (1, "持续性胸闷/胸痛"),
    INTERMITTENT_CHEST_PAIN(2, "间断性胸闷/胸痛"),
    CHEST_PAIN_ALLEVIATED(3, "胸痛症状已缓解"),
    STOMACH_ACHE(4, "腹痛 "),
    DIFFICULTY_BREATHING(5, "呼吸困难"),
    SHOCK(6, "休克"),
    HEART_FAILURE(7, "心衰 "),
    MALIGNANT_ARRHYTHMIA(8, "恶性心律失常"),
    CPR(9, "心肺复苏"),
    COMBINED_BLEEDING(10, "合并出血");
    
 
    private final int index;
    private final String name;
    
    ChestPainSymptom(int index, String name) {
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
