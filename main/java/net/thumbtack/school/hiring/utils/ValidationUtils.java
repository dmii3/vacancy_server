package net.thumbtack.school.hiring.utils;

import net.thumbtack.school.hiring.data.Skill;
import org.apache.commons.lang3.StringUtils;

import java.util.ArrayList;

public class ValidationUtils {
    public static void isNoneBlank(String... strings) throws DataTransferException {
        if (!StringUtils.isNoneBlank(strings)) {
            throw new DataTransferException(DataTransferErrorCode.NOT_ENOUGH_DATA);
        }
    }

    public static void validateEmail(String eMail) throws DataTransferException {
        if (!eMail.matches(".+@.+\\..+$")) {
            throw new DataTransferException(DataTransferErrorCode.INVALID_EMAIL);
        }
    }

    public static void validateLogin(String login) throws DataTransferException {
        if (login.length() < 5) {
            throw new DataTransferException(DataTransferErrorCode.LOGIN_TOO_SHORT);
        }
        if (!login.matches("([a-zA-Z0-9]+[._]?[a-zA-Z0-9]+)+$")) {
            throw new DataTransferException(DataTransferErrorCode.INVALID_LOGIN);
        }
    }

    public static void validatePassword(String password) throws DataTransferException {
        if (password.length() < 6) {
            throw new DataTransferException(DataTransferErrorCode.PASSWORD_TOO_SHORT);
        }
    }

    public static void validateRequirements(ArrayList<Skill> requiredSkills, ArrayList<Skill> extraSkills) throws DataTransferException {
        checkEachRequirement(requiredSkills);
        checkEachRequirement(extraSkills);
        checkDuplicates(requiredSkills);
        checkDuplicates(extraSkills);

        for (Skill requiredSkill : requiredSkills) {
            for (Skill extraSkill : extraSkills) {
                if (requiredSkill.getSkillName().equals(extraSkill.getSkillName()) &&
                        requiredSkill.getSkillLevel() >= extraSkill.getSkillLevel()) {

                    throw new DataTransferException(DataTransferErrorCode.INVALID_REQUIREMENTS);
                }
            }
        }
    }

    private static void checkEachRequirement(ArrayList<Skill> skills) throws DataTransferException {
        for (Skill requiredSkill : skills) {
            if (StringUtils.isBlank(requiredSkill.getSkillName()) || requiredSkill.getSkillLevel() < 1 || requiredSkill.getSkillLevel() > 5) {
                throw new DataTransferException(DataTransferErrorCode.INVALID_REQUIREMENT);
            }
        }
    }

    private static void checkDuplicates(ArrayList<Skill> skills) throws DataTransferException {
        for (int i = 0; i < skills.size(); i++) {
            for (int j = skills.size() - 1; j > i; j--) {
                if (skills.get(j).getSkillName().equals(skills.get(i).getSkillName())) {
                    throw new DataTransferException(DataTransferErrorCode.DUPLICATE_SKILL);
                }
            }
        }
    }

    public static void validateSkillLevel(int level) throws DataTransferException {
        if(level < 1 || level > 5) {
            throw new DataTransferException(DataTransferErrorCode.INVALID_SKILL_LEVEL);
        }
    }
}
