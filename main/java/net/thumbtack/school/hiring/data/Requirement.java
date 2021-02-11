package net.thumbtack.school.hiring.data;

import java.util.Objects;

public class Requirement extends Skill {
    private boolean necessary;

    public Requirement(String skillName, int skillLevel, boolean necessary) {
        super(skillName, skillLevel);
        this.necessary = necessary;
    }

    public boolean isNecessary() {
        return necessary;
    }

    public void setNecessary(boolean necessary) {
        this.necessary = necessary;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        if (!super.equals(o)) return false;
        Requirement that = (Requirement) o;
        return necessary == that.necessary;
    }

    @Override
    public int hashCode() {

        return Objects.hash(super.hashCode(), necessary);
    }
}
