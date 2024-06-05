alter table curriculum
rename column name to identifier_name;

alter table intermediate_result
rename column scope to score;

alter table report
rename column scope to score;