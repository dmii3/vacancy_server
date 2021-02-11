package net.thumbtack.school.hiring.dto.request;

import net.thumbtack.school.hiring.utils.DataTransferException;
import net.thumbtack.school.hiring.utils.ValidationUtils;

public class AddSkillDtoRequest {
    private String token;
    private String skillName;
    private int skillLevel;

    public AddSkillDtoRequest(String token, String skillName, int skillLevel) {
        this.token = token;
        this.skillName = skillName;
        this.skillLevel = skillLevel;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
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

    public void validate() throws DataTransferException {
        ValidationUtils.isNoneBlank(token, skillName);
        ValidationUtils.validateSkillLevel(skillLevel);
    }
}
