package me.pjones.skills.skills;

public enum SkillType {
    ENDURANCE("Endurance", Endurance.class),
    AGILITY("Agility", Agility.class),
    COMBAT("Combat", Combat.class),
    MAGIC("Magic", Magic.class);

    private String name;
    private Class<? extends SkillBase> implementation;

    public Class<? extends SkillBase> getImplementation() {
        return implementation;
    }

    public String getName() {
        return name;
    }

    SkillType(String name, Class<? extends SkillBase> implementation) {
        this.name = name;
        this.implementation = implementation;
    }
}
