package net.thumbtack.school.hiring.database;

import com.google.gson.Gson;
import com.google.gson.JsonParser;
import com.google.gson.reflect.TypeToken;
import net.thumbtack.school.hiring.data.*;
import org.apache.commons.collections4.MapIterator;
import org.apache.commons.collections4.map.HashedMap;
import org.apache.commons.collections4.multimap.ArrayListValuedHashMap;

import java.io.*;
import java.util.*;

public class DataBase {
    private static HashedMap<String, String> userIds;
    private static HashedMap<String, String> authorizedUsers;
    private static HashedMap<String, String> passwords;
    private static HashedMap<String, Employee> employees;
    private static HashedMap<String, Employer> employers;
    private static HashedMap<String, Visibility> employeesVisibility;
    private static ArrayListValuedHashMap<String, String> employerVacancies;
    private static HashedMap<String, Vacancy> vacancies;
    private static HashSet<String> skills;

    public static String insert(Employee employee) throws DataBaseException {
        String id = UUID.randomUUID().toString();
        insertToUserIds(employee.getLogin(), id);
        passwords.put(employee.getLogin(), employee.getPassword());
        employees.put(id, employee);
        String token = UUID.randomUUID().toString();
        authorizedUsers.put(token, id);
        employeesVisibility.put(id, Visibility.PUBLIC);
        return token;
    }

    public static String insert(Employer employer) throws DataBaseException {
        String id = UUID.randomUUID().toString();
        insertToUserIds(employer.getLogin(), id);
        passwords.put(employer.getLogin(), employer.getPassword());
        employers.put(id, employer);
        String token = UUID.randomUUID().toString();
        authorizedUsers.put(token, id);
        return token;
    }

    public static String insert(String token, Vacancy vacancy) throws DataBaseException {
        String employerId = getId(token);
        updateSkills(vacancy.getRequiredSkills());
        updateSkills(vacancy.getExtraSkills());
        String vacancyID = UUID.randomUUID().toString();
        employerVacancies.put(employerId, vacancyID);
        vacancies.put(vacancyID, vacancy);
        return vacancyID;
    }

    public static void insert(String token, Skill skill) throws DataBaseException {
        String employeeId = getId(token);
        Employee employee = getEmployee(employeeId);
        if (!employee.addSkill(skill)) {
            throw new DataBaseException(DataBaseErrorCode.SKILL_ALREADY_EXISTS);
        }
    }

    public static void insert(String token, Visibility visibility) throws DataBaseException {
        String employeeId = getId(token);
        employeesVisibility.put(employeeId, visibility);
    }

    private static void insertToUserIds(String login, String id) throws DataBaseException {
        if (userIds.putIfAbsent(login, id) != null) {
            throw new DataBaseException(DataBaseErrorCode.LOGIN_ALREADY_EXISTS);
        }
    }

    public static void change(String token, Employee employeeUpd) throws DataBaseException{
        String employeeId = getId(token);
        Employee employee = getEmployee(employeeId);
        if (!employeeUpd.getLogin().equals(employee.getLogin())) {
            throw new DataBaseException(DataBaseErrorCode.LOGIN_DOES_NOT_MATCH);
        }
        passwords.replace(employeeUpd.getLogin(), employeeUpd.getPassword());
        employee.copy(employeeUpd);
    }

    public static void change(String token, Employer employerUpd) throws DataBaseException{
        String employerId = getId(token);
        Employer employer = getEmployer(employerId);
        if (!employerUpd.getLogin().equals(employer.getLogin())) {
            throw new DataBaseException(DataBaseErrorCode.LOGIN_DOES_NOT_MATCH);
        }
        passwords.replace(employerUpd.getLogin(), employerUpd.getPassword());
        employer.copy(employerUpd);
    }

    public static void change(String token, String vacancyID, Vacancy vacancyUpd) throws DataBaseException {
        String employerId = getId(token);
        getEmployer(employerId);
        updateSkills(vacancyUpd.getRequiredSkills());
        updateSkills(vacancyUpd.getExtraSkills());
        Vacancy vacancy = vacancies.get(vacancyID);
        if (vacancy == null) {
            throw new DataBaseException(DataBaseErrorCode.VACANCY_NOT_FOUND);
        }
        if (!employerVacancies.get(employerId).contains(vacancyID)) {
            throw new DataBaseException(DataBaseErrorCode.VACANCY_ACCESS_DENIED);
        }
        vacancy.copy(vacancyUpd);
    }

    public static void change(String token, Skill skill) throws DataBaseException {
        String employeeId = getId(token);
        Employee employee = getEmployee(employeeId);
        if (!employee.changeSkill(skill)) {
            throw new DataBaseException(DataBaseErrorCode.SKILL_NOT_FOUND);
        }
    }

    public static void remove(String token) throws DataBaseException{
        String userId = authorizedUsers.remove(token);
        if (userId == null) {
            throw new DataBaseException(DataBaseErrorCode.USER_NOT_FOUND);
        }
        Employee employee = employees.remove(userId);
        if (employee != null) {
            employeesVisibility.remove(userId);
            userIds.remove(employee.getLogin());
            return;
        }
        Employer employer = employers.remove(userId);
        if (employer != null) {
            for (String vacancyId : employerVacancies.remove(userId)) {
                vacancies.remove(vacancyId);
            }
        }
    }

    public static void remove(String token, String vacancyID) throws DataBaseException {
        String employerId = getId(token);
        Vacancy vacancy = vacancies.get(vacancyID);
        if (vacancy == null) {
            throw new DataBaseException(DataBaseErrorCode.VACANCY_NOT_FOUND);
        }
        if (!employerVacancies.get(employerId).contains(vacancyID)) {
            throw new DataBaseException(DataBaseErrorCode.VACANCY_ACCESS_DENIED);
        }
        vacancies.remove(vacancyID);
        employerVacancies.removeMapping(employerId, vacancyID);
    }

    public static void remove(String token, Skill skill) throws DataBaseException {
        String employeeId = getId(token);
        Employee employee = getEmployee(employeeId);
        if (!employee.removeSkill(skill)) {
            throw new DataBaseException(DataBaseErrorCode.SKILL_NOT_FOUND);
        }
    }

    public static String insertAuthorized(String login, String password) throws DataBaseException {
        String userId = userIds.get(login);
        if (userId == null || !password.equals(passwords.get(login))) {
            throw new DataBaseException(DataBaseErrorCode.USER_NOT_FOUND);
        }
        if (authorizedUsers.containsValue(userId)) {
            throw new DataBaseException(DataBaseErrorCode.USER_ALREADY_LOGGED_IN);
        }
        String token = UUID.randomUUID().toString();
        authorizedUsers.put(token, userId);
        return token;
    }

    public static void removeAuthorized(String token) throws DataBaseException {
        getId(token);
        authorizedUsers.remove(token);
    }

    public static ArrayList<String> getEmployees(String token, String vacancyID, SearchMode searchMode) throws DataBaseException {
        String employerId = getId(token);
        Vacancy vacancy = vacancies.get(vacancyID);
        if (vacancy == null) {
            throw new DataBaseException(DataBaseErrorCode.VACANCY_NOT_FOUND);
        }
        if (!employerVacancies.get(employerId).contains(vacancyID)) {
            throw new DataBaseException(DataBaseErrorCode.VACANCY_ACCESS_DENIED);
        }
        MapIterator iterator = employeesVisibility.mapIterator();
        ArrayList<String> employeesList = new ArrayList<>();
        while (iterator.hasNext()) {
            String employeeId = (String) iterator.next();
            if (employeesVisibility.get(employeeId) == Visibility.PRIVATE) {
                continue;
            }
            if (checkEmployee(getEmployee(employeeId), vacancy, searchMode)) {
                employeesList.add(employeeId);
            }
        }
        return employeesList;
    }

    public static ArrayList<Vacancy> getVacancies(String token, SearchMode searchMode) throws DataBaseException {
        String employeeId = getId(token);
        Employee employee = getEmployee(employeeId);
        MapIterator iterator = vacancies.mapIterator();
        String vacancyId;

        ArrayList<Vacancy> vacanciesList = new ArrayList<>();
        while (iterator.hasNext()) {
            vacancyId = (String) iterator.next();
            Vacancy vacancy = vacancies.get(vacancyId);
            if (vacancy.getVisibility() == Visibility.PRIVATE) {
                continue;
            }
            if (checkEmployee(getEmployee(employeeId), vacancy, searchMode)) {
                vacanciesList.add(vacancy);
            }
        }
        if(searchMode.equals(SearchMode.AT_LEAST_ONE_NECESSARY_SKILL)) {

            vacanciesList.sort((v1, v2) -> {
                int quantity1 = 0;
                int quantity2 = 0;

                for (Skill requirement : v1.getRequiredSkills()) {
                    int index = Collections.binarySearch(employee.getSkills(), requirement);
                    if(index >= 0) {
                        quantity1++;
                    }
                }

                for (Skill requirement : v2.getRequiredSkills()) {
                    int index = Collections.binarySearch(employee.getSkills(), requirement);
                    if (index >= 0) {
                        quantity2++;
                    }
                }
                return quantity2 - quantity1;
            });
        }
        return vacanciesList;
    }

    public static void recruit(String token, String vacancyID, String employeeId) throws DataBaseException {
        String employerId = getId(token);
        Vacancy vacancy = vacancies.get(vacancyID);
        if (vacancy == null) {
            throw new DataBaseException(DataBaseErrorCode.VACANCY_NOT_FOUND);
        }
        if (!employerVacancies.get(employerId).contains(vacancyID)) {
            throw new DataBaseException(DataBaseErrorCode.VACANCY_ACCESS_DENIED);
        }
        vacancy.setVisibility(Visibility.PRIVATE);
        employeesVisibility.replace(employeeId, Visibility.PRIVATE);
    }

    private static boolean checkEmployee(Employee employee, Vacancy vacancy, SearchMode searchMode) {
        switch (searchMode) {
            case ALL_SKILLS:
                return compare(employee.getSkills(), vacancy.getRequiredSkills()) &&
                        compare(employee.getSkills(), vacancy.getExtraSkills());

            case ALL_NECESSARY_SKILLS:
                return compare(employee.getSkills(), vacancy.getRequiredSkills());

            case ALL_SKILLS_ANY_LEVEL:
                return compareIgnoreLevel(employee.getSkills(), vacancy.getRequiredSkills()) &&
                        compareIgnoreLevel(employee.getSkills(), vacancy.getExtraSkills());

            case AT_LEAST_ONE_NECESSARY_SKILL:
                for (Skill requirement : vacancy.getRequiredSkills()) {
                    int index = Collections.binarySearch(employee.getSkills(), requirement);
                    if (index >= 0) {
                        return true;
                    }
                }
                return false;
        }
        return false;
    }

    private static boolean compare(ArrayList<Skill> skills, ArrayList<Skill> requirements) {
        for (Skill requirement : requirements) {
            int index = Collections.binarySearch(skills, requirement);
            if (index < 0) {
                return false;
            }
        }
        return true;
    }

    private static boolean compareIgnoreLevel(ArrayList<Skill> skills, ArrayList<Skill> requirements) {
        for (Skill requirement : requirements) {
            int index = Collections.binarySearch(skills, requirement, Comparator.comparing(Skill::getSkillName));
            if (index < 0) {
                return false;
            }
        }
        return true;
    }

    private static Employee getEmployee(String id) throws DataBaseException {
        Employee employee = employees.get(id);
        if (employee == null) {
            throw new DataBaseException(DataBaseErrorCode.USER_NOT_FOUND);
        }
        return employee;
    }

    private static Employer getEmployer(String id) throws DataBaseException {
        Employer employer = employers.get(id);
        if (employer == null) {
            throw new DataBaseException(DataBaseErrorCode.USER_NOT_FOUND);
        }
        return employer;
    }

    private static String getId(String token) throws DataBaseException {
        String userId = authorizedUsers.get(token);
        if (userId == null) {
            throw new DataBaseException(DataBaseErrorCode.USER_NOT_FOUND);
        }
        return userId;
    }

    public static String getIdByLogin(String login) {
        return userIds.get(login);
    }

    private static void updateSkills(ArrayList<Skill> skillList) {
        for (Skill skill : skillList) {
            skills.add(skill.getSkillName());
        }
    }

    public static void initDataBase(BufferedReader br) throws IOException {
        userIds = new Gson().fromJson(br.readLine(), new TypeToken<HashedMap<String, String>>(){}.getType());
        authorizedUsers = new Gson().fromJson(br.readLine(), new TypeToken<HashedMap<String, String>>(){}.getType());
        passwords = new Gson().fromJson(br.readLine(), new TypeToken<HashedMap<String, String>>(){}.getType());
        employees = new Gson().fromJson(br.readLine(), new TypeToken<HashedMap<String, Employee>>(){}.getType());
        employers = new Gson().fromJson(br.readLine(), new TypeToken<HashedMap<String, Employer>>(){}.getType());
        employeesVisibility = new Gson().fromJson(br.readLine(), new TypeToken<HashedMap<String, Visibility>>(){}.getType());
        HashedMap<String, Collection<String>> temp = new Gson().fromJson(br.readLine(), new TypeToken<HashedMap<String, Collection<String>>>(){}.getType());
        MapIterator iterator = temp.mapIterator();
        employerVacancies = new ArrayListValuedHashMap<>();
        while (iterator.hasNext()) {
            String employerId = (String) iterator.next();
            for (String vacancyId : temp.get(employerId)) {
                employerVacancies.put(employerId, vacancyId);
            }
        }
        vacancies = new Gson().fromJson(br.readLine(), new TypeToken<HashedMap<String, Vacancy>>(){}.getType());
        skills = new Gson().fromJson(br.readLine(), new TypeToken<HashSet<String>>(){}.getType());
    }

    public static void initDataBase() {
        userIds = new HashedMap<>();
        authorizedUsers = new HashedMap<>();
        passwords = new HashedMap<>();
        employees = new HashedMap<>();
        employers = new HashedMap<>();
        employeesVisibility = new HashedMap<>();
        employerVacancies = new ArrayListValuedHashMap<>();
        vacancies = new HashedMap<>();
        skills = new HashSet<>();
    }

    public static void saveDataBase(BufferedWriter bw) throws IOException {
        bw.write(new Gson().toJson(userIds));
        bw.newLine();
        bw.write(new Gson().toJson(authorizedUsers));
        bw.newLine();
        bw.write(new Gson().toJson(passwords));
        bw.newLine();
        bw.write(new Gson().toJson(employees));
        bw.newLine();
        bw.write(new Gson().toJson(employers));
        bw.newLine();
        bw.write(new Gson().toJson(employeesVisibility));
        bw.newLine();
        bw.write(new Gson().toJson(employerVacancies.asMap()));
        bw.newLine();
        bw.write(new Gson().toJson(vacancies));
        bw.newLine();
        bw.write(new Gson().toJson(skills));
    }

    //------------------------------------------------------------------------------------------
    // For testing
    //------------------------------------------------------------------------------------------


    public static HashedMap<String, String> getUserIds() {
        return userIds;
    }

    public static HashedMap<String, String> getAuthorizedUsers() {
        return authorizedUsers;
    }

    public static HashedMap<String, String> getPasswords() {
        return passwords;
    }

    public static HashedMap<String, Employee> getEmployees() {
        return employees;
    }

    public static HashedMap<String, Employer> getEmployers() {
        return employers;
    }

    public static HashedMap<String, Visibility> getEmployeesVisibility() {
        return employeesVisibility;
    }

    public static ArrayListValuedHashMap<String, String> getEmployerVacancies() {
        return employerVacancies;
    }

    public static HashedMap<String, Vacancy> getVacancies() {
        return vacancies;
    }

    public static HashSet<String> getSkills() {
        return skills;
    }
}
