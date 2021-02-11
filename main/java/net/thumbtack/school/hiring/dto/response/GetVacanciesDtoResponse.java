package net.thumbtack.school.hiring.dto.response;

import net.thumbtack.school.hiring.data.Vacancy;

import java.util.ArrayList;

public class GetVacanciesDtoResponse {
    private ArrayList<Vacancy> vacancies;

    public GetVacanciesDtoResponse(ArrayList<Vacancy> vacancies) {
        this.vacancies = vacancies;
    }

    public ArrayList<Vacancy> getVacancies() {
        return vacancies;
    }

    public void setVacancies(ArrayList<Vacancy> vacancies) {
        this.vacancies = vacancies;
    }
}
