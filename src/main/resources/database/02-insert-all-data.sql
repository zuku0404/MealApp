--liquibase formatted sql
--changeset zuku:1

INSERT INTO cuisine (name) VALUES
('ITALIAN'),
('POLISH');

INSERT INTO meal (cuisine_id, name, description, image_url, source) VALUES
(1, 'Margherita Pizza', 'Classic Italian pizza with tomato and cheese', 'http://localhost:8080/images/margarita.jpg','GLOBAL'),
(1, 'Spaghetti Carbonara', 'Pasta with eggs, cheese, and pancetta', 'http://localhost:8080/images/spaghetti.jpg','GLOBAL'),
(2, 'Pierogi', 'Polish dumplings with various fillings','http://localhost:8080/images/pierogi.jpg','GLOBAL'),
(2, 'Bigos', 'Traditional Polish hunter’s stew','http://localhost:8080/images/bigos.jpg','GLOBAL'),
(1, 'Lasagna', 'Layers of pasta, cheese, and meat sauce','http://localhost:8080/images/lasagna.jpg','GLOBAL');

INSERT INTO ingredient (name, ingredient_category) VALUES
('Tomato', 'VEGETARIAN'),
('Mozzarella', 'VEGETARIAN'),
('Flour', 'VEGAN'),
('Yeast', 'VEGAN'),
('Olive oil', 'VEGAN'),
('Salt', 'VEGAN'),
('Water', 'VEGAN'),
('Spaghetti', 'VEGAN'),
('Bacon (pancetta)', 'MEAT'),
('Pecorino/Parmesan cheese', 'VEGETARIAN'),
('Eggs', 'VEGETARIAN'),
('Garlic', 'VEGAN'),
('Black pepper', 'VEGAN'),
('Potatoes', 'VEGAN'),
('Cottage cheese', 'VEGETARIAN'),
('Onion', 'VEGAN'),
('Butter', 'VEGETARIAN'),
('Sauerkraut', 'VEGAN'),
('White cabbage', 'VEGAN'),
('Sausage', 'MEAT'),
('Bacon', 'MEAT'),
('Dried mushrooms', 'VEGAN'),
('Dried plums', 'VEGAN'),
('Bay leaves', 'VEGAN'),
('Allspice', 'VEGAN'),
('Red wine', 'VEGAN'),
('Lasagna sheets', 'VEGAN'),
('Ground meat (beef/pork)', 'MEAT'),
('Canned tomatoes', 'VEGAN'),
('Milk', 'VEGETARIAN'),
('Butter (for Béchamel)', 'VEGETARIAN'),
('Flour (for Béchamel)', 'VEGAN');

INSERT INTO meal_composition (ingredient_id, meal_id, `count`, unit) VALUES
(1, 1, 3.0, 'PIECE'),  -- Tomato
(3, 1, 250.0, 'GRAM'), -- Mozzarella
(4, 1, 500.0, 'GRAM'), -- Flour
(5, 1, 1.0, 'PIECE'),  -- Yeast (1 packet)
(6, 1, 1.0, 'TABLESPOON'), -- Olive oil
(7, 1, 1.0, 'TEASPOON'), -- Salt
(8, 1, 200.0, 'MILLILITER'); -- Water

INSERT INTO meal_composition (ingredient_id, meal_id, `count`, unit) VALUES
(9, 2, 400.0, 'GRAM'),  -- Spaghetti
(10, 2, 150.0, 'GRAM'), -- Bacon (pancetta)
(11, 2, 100.0, 'GRAM'), -- Pecorino/Parmesan cheese
(5, 2, 4.0, 'PIECE'),   -- Eggs
(12, 2, 2.0, 'CLOVE'),  -- Garlic
(13, 2, 1.0, 'TEASPOON'), -- Black pepper
(6, 2, 2.0, 'TABLESPOON'); -- Olive oil

INSERT INTO meal_composition (ingredient_id, meal_id, `count`, unit) VALUES
(4, 3, 500.0, 'GRAM'), -- Flour
(8, 3, 250.0, 'MILLILITER'), -- Water
(5, 3, 1.0, 'PIECE'),  -- Egg
(7, 3, 1.0, 'TEASPOON'), -- Salt
(14, 3, 500.0, 'GRAM'), -- Potatoes
(15, 3, 300.0, 'GRAM'), -- Cottage cheese
(16, 3, 1.0, 'PIECE'),  -- Onion
(17, 3, 2.0, 'TABLESPOON'); -- Butter

INSERT INTO meal_composition (ingredient_id, meal_id, `count`, unit) VALUES
(18, 4, 1.0, 'KILOGRAM'), -- Sauerkraut
(19, 4, 500.0, 'GRAM'), -- White cabbage
(10, 4, 300.0, 'GRAM'), -- Sausage
(20, 4, 200.0, 'GRAM'), -- Bacon
(21, 4, 50.0, 'GRAM'), -- Dried mushrooms
(22, 4, 100.0, 'GRAM'), -- Dried plums
(16, 4, 2.0, 'PIECE'), -- Onion
(23, 4, 2.0, 'PIECE'), -- Bay leaves
(24, 4, 5.0, 'PIECE'), -- Allspice
(13, 4, 1.0, 'TEASPOON'), -- Pepper
(7, 4, 1.0, 'TEASPOON'), -- Salt
(25, 4, 200.0, 'MILLILITER'); -- Red wine (optional)

INSERT INTO meal_composition (ingredient_id, meal_id, `count`, unit) VALUES
(26, 5, 400.0, 'GRAM'), -- Lasagna sheets
(27, 5, 500.0, 'GRAM'), -- Ground meat (beef/pork)
(28, 5, 400.0, 'GRAM'), -- Canned tomatoes
(3, 5, 300.0, 'GRAM'), -- Mozzarella
(11, 5, 100.0, 'GRAM'), -- Parmesan
(12, 5, 3.0, 'CLOVE'),  -- Garlic
(16, 5, 1.0, 'PIECE'),  -- Onion
(6, 5, 2.0, 'TABLESPOON'), -- Olive oil
(7, 5, 1.0, 'TEASPOON'), -- Salt
(13, 5, 1.0, 'TEASPOON'), -- Pepper
(29, 5, 500.0, 'MILLILITER'), -- Milk (for Béchamel)
(17, 5, 50.0, 'GRAM'), -- Butter (for Béchamel)
(4, 5, 50.0, 'GRAM');  -- Flour (for Béchamel)

INSERT INTO account (login, password) VALUES
('admin', '$2a$10$BxVBlwD0Q4iwyjTOvx8Om.QvqiG7IoQqR4w2rYyxpPolwQaylUqzy'),
('user1', '$2a$10$MjAQCxCa7bv0zZtRNIfZ6eeAtIqGfeHyXxck1.lg.XVDg97KISY7m'),
('user2', '$2a$10$Q3XaWvnDOmzcM7vkEhlDkegQK2fqYDuZ2IvHDdUy9H5m5jg.tpvBi'),
('user3', '$2a$10$f847O/RhszF4tXodQ.5Ce.NbSzN1nO8zfO4zHAROv1hrzil95U.pm');

INSERT INTO family_preference (id) VALUES
(1),
(2);

INSERT INTO family(name, preference_id) VALUES
('family_1',1),
('family_2',2);

INSERT INTO user (account_id,name,role, current_family_id) VALUES
(1,'admin','ROLE_ADMIN', null),
(2,'user1','ROLE_USER',1),
(3,'user2','ROLE_USER',1),
(4,'user3','ROLE_USER',2);

INSERT INTO user_family(family_id, user_id) VALUES
(1,2),
(2,2),
(1,3),
(2,4);

INSERT INTO family_ingredient (family_id, name, ingredient_category) VALUES
(2, 'Tofu', 'VEGAN'),
(2, 'Chili Pepper', 'VEGAN');

INSERT INTO family_meal (family_id, cuisine_id, name, description, image_url, source) VALUES
(2, 1, 'Tofu Stir-Fry', 'Stir-fried tofu with vegetables and soy sauce','http://localhost:8080/images/default.jpg','CUSTOM'),
(2, 1, 'Spicy Veggie Wrap', 'A wrap with vegetables and spicy tofu','http://localhost:8080/images/default.jpg','CUSTOM'),
(2, 1, 'Chili Tofu Salad', 'Fresh salad with chili tofu and greens','http://localhost:8080/images/default.jpg','CUSTOM');

INSERT INTO family_meal_composition (family_meal_id, ingredient_id, family_ingredient_id, `count`, unit) VALUES
(1, NULL,   1,      200.0,  'GRAM'), -- Tofu (user ingredient)
(1, 12,     NULL,   2.0,    'CLOVE'), -- Garlic
(1, 6,      NULL,   2.0,    'TABLESPOON'), -- Olive oil
(1, 16,     NULL,   1.0,    'PIECE'), -- Onion
(1, NULL,   2,      50.0,   'MILLILITER'); -- Soy sauce (user ingredient)

INSERT INTO family_meal_composition (family_meal_id, ingredient_id, family_ingredient_id, `count`, unit) VALUES
(2,  NULL,   1,      150.0,  'GRAM'), -- Tofu (user ingredient)
(2,  NULL,   2,      1.0,    'PIECE'), -- Chili Pepper (user ingredient)
(2,  1,      NULL,   1.0,    'PIECE'), -- Tomato
(2,  3,      NULL,   50.0,   'GRAM'), -- Lettuce (user ingredient)
(2,  4,      NULL,   1.0,    'PIECE'), -- Tortilla (user ingredient)
(2,  6,      NULL,   1.0,    'TABLESPOON'); -- Olive oil(20, 4, 200.0, 'GRAM')

INSERT INTO family_preference_cuisine (family_preference_id, cuisine_id) VALUES
(1,1),
(1,2),
(2,1);

INSERT INTO family_preference_without_ingredient (family_preference_id, ingredient_id) VALUES
(2,5);

INSERT INTO meal_frequency(family_id, meal_id, source, frequency) VALUES
(2,1,'CUSTOM','ONCE_WEEK'),
(2,2,'CUSTOM','TWICE_WEEK'),
(2,1,'GLOBAL','ONCE_WEEK'),
(2,2,'GLOBAL','TWICE_MONTH'),
(1,1,'GLOBAL','TWICE_MONTH'),
(1,2,'GLOBAL','ONCE_WEEK');