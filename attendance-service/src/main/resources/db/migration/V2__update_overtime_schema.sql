ALTER TABLE overtime_clock_events
    ADD date date;

ALTER TABLE overtime_clock_events
    ALTER COLUMN date SET NOT NULL;