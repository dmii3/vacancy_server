package net.thumbtack.school.hiring.data;

import java.util.Objects;

public class Skill implements Comparable<Skill> {
    private String skillName;
    private int skillLevel;

    public Skill(String skillName, int skillLevel) {
        this.skillName = skillName;
        this.skillLevel = skillLevel;
    }

    public String getSkillName() {
        return skillName;
    }

    public void setSkillName(String skillName) {
        this.skillName = skillName;
    }

    public int getSkillLevel() {
        return skillLevel;
    }

    public void setSkillLevel(int skillLevel) {
        this.skillLevel = skillLevel;
    }

    @Override
    public int compareTo(Skill o) {
        if (this.skillName.equals(o.skillName)) {
            return this.skillLevel >= o.skillLevel ? 0 : -1;
        }
        return this.skillName.compareTo(o.skillName);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Skill skill = (Skill) o;
        return skillLevel == skill.skillLevel &&
                Objects.equals(skillName, skill.skillName);
    }

    @Override
    public int hashCode() {

        return Objects.hash(skillName, skillLevel);
    }
}
