INSERT INTO free_time ( freetime_id,
                       doctor_email,
                        cron_expression)
VALUES (nextval('ehealth_sequence'),
        'catalin.schiopu@isa.utm.md',
        '0 0-23 * * 0');