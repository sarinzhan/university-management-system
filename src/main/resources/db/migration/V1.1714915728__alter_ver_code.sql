alter table verification_code
drop column created_date,
drop column owner_personal_number,
add column applicant_application_id bigint references applicant_application(id);

alter table applicant_application
drop column applicant_application_status_id,
add column is_activated bool default false,
add column is_checked bool default false,
add column is_accepted bool default false,
add column is_declined bool default false;