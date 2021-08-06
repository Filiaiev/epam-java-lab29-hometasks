-- Persons init

    INSERT INTO persons(first_name, middle_name, last_name, birth_date)
    VALUES ('Vladyslav', 'Dmytrovych', 'Filiaiev', '2002-02-20'),
           ('Olexandra', 'Andriivna', 'Panchuk', '2002-11-27'),
           ('Dmytro', 'Oleksiyovych', 'Komarov', '1987-12-03'),
           ('Maksym', 'Andriyovych', 'Rabotyuk', '2001-06-26');

-- Users init

    INSERT INTO users(email, login, password, person_id)
    VALUES ('furnace@gmail.com', 'susundron', '2002vlad', 1),
           ('sandora@gmail.com', 'sandora', 'sasha222', 2),
           ('komar@gmail.com', 'koma', 'koma111', 3),
           ('rab@gmail.com', 'rab', 'rab222', 4);

-- Clients init

    INSERT INTO clients(user_id)
    VALUES (1), (2);

-- Employees init

    INSERT INTO employees(user_id)
    VALUES(3), (4);

-- Orders init

    INSERT INTO order_headers(client_id, worker_id, complete_date,
                          cost, comment, description, status)
    VALUES
            (1, 1, NULL, 11500, NULL, 'Test order', 'CREATED'),
            (2, NULL, NULL, 5000, NULL, 'Test order 2', 'IN_WORK');


-- Roles insert