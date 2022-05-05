INSERT INTO free_time ( freetime_id,
                       doctor_email,
                        cron_expression)
VALUES (nextval('ehealth_sequence'),
        'tommy@email.com',
        '0 0/30 8-10 * * *');