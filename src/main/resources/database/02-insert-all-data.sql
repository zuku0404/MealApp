--liquibase formatted sql
--changeset zuku:1

INSERT INTO cuisine (name) VALUES ('ITALIAN');
INSERT INTO cuisine (name) VALUES ('POLISH');

INSERT INTO meal (cuisine_id, name, description) VALUES (1, 'Margherita Pizza', 'Classic Italian pizza with tomato and cheese');
INSERT INTO meal (cuisine_id, name, description) VALUES (1, 'Spaghetti Carbonara', 'Pasta with eggs, cheese, and pancetta');
INSERT INTO meal (cuisine_id, name, description) VALUES (2, 'Pierogi', 'Polish dumplings with various fillings');
INSERT INTO meal (cuisine_id, name, description) VALUES (2, 'Bigos', 'Traditional Polish hunter’s stew');
INSERT INTO meal (cuisine_id, name, description) VALUES (1, 'Lasagna', 'Layers of pasta, cheese, and meat sauce');

INSERT INTO ingredient (name, ingredient_category) VALUES ('Tomato', 'VEGETARIAN');
INSERT INTO ingredient (name, ingredient_category) VALUES ('Mozzarella', 'VEGETARIAN');
INSERT INTO ingredient (name, ingredient_category) VALUES ('Flour', 'VEGAN');
INSERT INTO ingredient (name, ingredient_category) VALUES ('Yeast', 'VEGAN');
INSERT INTO ingredient (name, ingredient_category) VALUES ('Olive oil', 'VEGAN');
INSERT INTO ingredient (name, ingredient_category) VALUES ('Salt', 'VEGAN');
INSERT INTO ingredient (name, ingredient_category) VALUES ('Water', 'VEGAN');
INSERT INTO ingredient (name, ingredient_category) VALUES ('Spaghetti', 'VEGAN');
INSERT INTO ingredient (name, ingredient_category) VALUES ('Bacon (pancetta)', 'MEAT');
INSERT INTO ingredient (name, ingredient_category) VALUES ('Pecorino/Parmesan cheese', 'VEGETARIAN');
INSERT INTO ingredient (name, ingredient_category) VALUES ('Eggs', 'VEGETARIAN');
INSERT INTO ingredient (name, ingredient_category) VALUES ('Garlic', 'VEGAN');
INSERT INTO ingredient (name, ingredient_category) VALUES ('Black pepper', 'VEGAN');
INSERT INTO ingredient (name, ingredient_category) VALUES ('Potatoes', 'VEGAN');
INSERT INTO ingredient (name, ingredient_category) VALUES ('Cottage cheese', 'VEGETARIAN');
INSERT INTO ingredient (name, ingredient_category) VALUES ('Onion', 'VEGAN');
INSERT INTO ingredient (name, ingredient_category) VALUES ('Butter', 'VEGETARIAN');
INSERT INTO ingredient (name, ingredient_category) VALUES ('Sauerkraut', 'VEGAN');
INSERT INTO ingredient (name, ingredient_category) VALUES ('White cabbage', 'VEGAN');
INSERT INTO ingredient (name, ingredient_category) VALUES ('Sausage', 'MEAT');
INSERT INTO ingredient (name, ingredient_category) VALUES ('Bacon', 'MEAT');
INSERT INTO ingredient (name, ingredient_category) VALUES ('Dried mushrooms', 'VEGAN');
INSERT INTO ingredient (name, ingredient_category) VALUES ('Dried plums', 'VEGAN');
INSERT INTO ingredient (name, ingredient_category) VALUES ('Bay leaves', 'VEGAN');
INSERT INTO ingredient (name, ingredient_category) VALUES ('Allspice', 'VEGAN');
INSERT INTO ingredient (name, ingredient_category) VALUES ('Red wine', 'VEGAN');
INSERT INTO ingredient (name, ingredient_category) VALUES ('Lasagna sheets', 'VEGAN');
INSERT INTO ingredient (name, ingredient_category) VALUES ('Ground meat (beef/pork)', 'MEAT');
INSERT INTO ingredient (name, ingredient_category) VALUES ('Canned tomatoes', 'VEGAN');
INSERT INTO ingredient (name, ingredient_category) VALUES ('Milk', 'VEGETARIAN');
INSERT INTO ingredient (name, ingredient_category) VALUES ('Butter (for Béchamel)', 'VEGETARIAN');
INSERT INTO ingredient (name, ingredient_category) VALUES ('Flour (for Béchamel)', 'VEGAN');

INSERT INTO meal_ingredient (ingredient_id, meal_id, `count`, unit) VALUES (1, 1, 3.0, 'PIECE');  -- Tomato
INSERT INTO meal_ingredient (ingredient_id, meal_id, `count`, unit) VALUES (3, 1, 250.0, 'GRAM'); -- Mozzarella
INSERT INTO meal_ingredient (ingredient_id, meal_id, `count`, unit) VALUES (4, 1, 500.0, 'GRAM'); -- Flour
INSERT INTO meal_ingredient (ingredient_id, meal_id, `count`, unit) VALUES (5, 1, 1.0, 'PIECE');  -- Yeast (1 packet)
INSERT INTO meal_ingredient (ingredient_id, meal_id, `count`, unit) VALUES (6, 1, 1.0, 'TABLESPOON'); -- Olive oil
INSERT INTO meal_ingredient (ingredient_id, meal_id, `count`, unit) VALUES (7, 1, 1.0, 'TEASPOON'); -- Salt
INSERT INTO meal_ingredient (ingredient_id, meal_id, `count`, unit) VALUES (8, 1, 200.0, 'MILLILITER'); -- Water

INSERT INTO meal_ingredient (ingredient_id, meal_id, `count`, unit) VALUES (9, 2, 400.0, 'GRAM');  -- Spaghetti
INSERT INTO meal_ingredient (ingredient_id, meal_id, `count`, unit) VALUES (10, 2, 150.0, 'GRAM'); -- Bacon (pancetta)
INSERT INTO meal_ingredient (ingredient_id, meal_id, `count`, unit) VALUES (11, 2, 100.0, 'GRAM'); -- Pecorino/Parmesan cheese
INSERT INTO meal_ingredient (ingredient_id, meal_id, `count`, unit) VALUES (5, 2, 4.0, 'PIECE');   -- Eggs
INSERT INTO meal_ingredient (ingredient_id, meal_id, `count`, unit) VALUES (12, 2, 2.0, 'CLOVE');  -- Garlic
INSERT INTO meal_ingredient (ingredient_id, meal_id, `count`, unit) VALUES (13, 2, 1.0, 'TEASPOON'); -- Black pepper
INSERT INTO meal_ingredient (ingredient_id, meal_id, `count`, unit) VALUES (6, 2, 2.0, 'TABLESPOON'); -- Olive oil

INSERT INTO meal_ingredient (ingredient_id, meal_id, `count`, unit) VALUES (4, 3, 500.0, 'GRAM'); -- Flour
INSERT INTO meal_ingredient (ingredient_id, meal_id, `count`, unit) VALUES (8, 3, 250.0, 'MILLILITER'); -- Water
INSERT INTO meal_ingredient (ingredient_id, meal_id, `count`, unit) VALUES (5, 3, 1.0, 'PIECE');  -- Egg
INSERT INTO meal_ingredient (ingredient_id, meal_id, `count`, unit) VALUES (7, 3, 1.0, 'TEASPOON'); -- Salt
INSERT INTO meal_ingredient (ingredient_id, meal_id, `count`, unit) VALUES (14, 3, 500.0, 'GRAM'); -- Potatoes
INSERT INTO meal_ingredient (ingredient_id, meal_id, `count`, unit) VALUES (15, 3, 300.0, 'GRAM'); -- Cottage cheese
INSERT INTO meal_ingredient (ingredient_id, meal_id, `count`, unit) VALUES (16, 3, 1.0, 'PIECE');  -- Onion
INSERT INTO meal_ingredient (ingredient_id, meal_id, `count`, unit) VALUES (17, 3, 2.0, 'TABLESPOON'); -- Butter

INSERT INTO meal_ingredient (ingredient_id, meal_id, `count`, unit) VALUES (18, 4, 1.0, 'KILOGRAM'); -- Sauerkraut
INSERT INTO meal_ingredient (ingredient_id, meal_id, `count`, unit) VALUES (19, 4, 500.0, 'GRAM'); -- White cabbage
INSERT INTO meal_ingredient (ingredient_id, meal_id, `count`, unit) VALUES (10, 4, 300.0, 'GRAM'); -- Sausage
INSERT INTO meal_ingredient (ingredient_id, meal_id, `count`, unit) VALUES (20, 4, 200.0, 'GRAM'); -- Bacon
INSERT INTO meal_ingredient (ingredient_id, meal_id, `count`, unit) VALUES (21, 4, 50.0, 'GRAM'); -- Dried mushrooms
INSERT INTO meal_ingredient (ingredient_id, meal_id, `count`, unit) VALUES (22, 4, 100.0, 'GRAM'); -- Dried plums
INSERT INTO meal_ingredient (ingredient_id, meal_id, `count`, unit) VALUES (16, 4, 2.0, 'PIECE'); -- Onion
INSERT INTO meal_ingredient (ingredient_id, meal_id, `count`, unit) VALUES (23, 4, 2.0, 'PIECE'); -- Bay leaves
INSERT INTO meal_ingredient (ingredient_id, meal_id, `count`, unit) VALUES (24, 4, 5.0, 'PIECE'); -- Allspice
INSERT INTO meal_ingredient (ingredient_id, meal_id, `count`, unit) VALUES (13, 4, 1.0, 'TEASPOON'); -- Pepper
INSERT INTO meal_ingredient (ingredient_id, meal_id, `count`, unit) VALUES (7, 4, 1.0, 'TEASPOON'); -- Salt
INSERT INTO meal_ingredient (ingredient_id, meal_id, `count`, unit) VALUES (25, 4, 200.0, 'MILLILITER'); -- Red wine (optional)

INSERT INTO meal_ingredient (ingredient_id, meal_id, `count`, unit) VALUES (26, 5, 400.0, 'GRAM'); -- Lasagna sheets
INSERT INTO meal_ingredient (ingredient_id, meal_id, `count`, unit) VALUES (27, 5, 500.0, 'GRAM'); -- Ground meat (beef/pork)
INSERT INTO meal_ingredient (ingredient_id, meal_id, `count`, unit) VALUES (28, 5, 400.0, 'GRAM'); -- Canned tomatoes
INSERT INTO meal_ingredient (ingredient_id, meal_id, `count`, unit) VALUES (3, 5, 300.0, 'GRAM'); -- Mozzarella
INSERT INTO meal_ingredient (ingredient_id, meal_id, `count`, unit) VALUES (11, 5, 100.0, 'GRAM'); -- Parmesan
INSERT INTO meal_ingredient (ingredient_id, meal_id, `count`, unit) VALUES (12, 5, 3.0, 'CLOVE');  -- Garlic
INSERT INTO meal_ingredient (ingredient_id, meal_id, `count`, unit) VALUES (16, 5, 1.0, 'PIECE');  -- Onion
INSERT INTO meal_ingredient (ingredient_id, meal_id, `count`, unit) VALUES (6, 5, 2.0, 'TABLESPOON'); -- Olive oil
INSERT INTO meal_ingredient (ingredient_id, meal_id, `count`, unit) VALUES (7, 5, 1.0, 'TEASPOON'); -- Salt
INSERT INTO meal_ingredient (ingredient_id, meal_id, `count`, unit) VALUES (13, 5, 1.0, 'TEASPOON'); -- Pepper
INSERT INTO meal_ingredient (ingredient_id, meal_id, `count`, unit) VALUES (29, 5, 500.0, 'MILLILITER'); -- Milk (for Béchamel)
INSERT INTO meal_ingredient (ingredient_id, meal_id, `count`, unit) VALUES (17, 5, 50.0, 'GRAM'); -- Butter (for Béchamel)
INSERT INTO meal_ingredient (ingredient_id, meal_id, `count`, unit) VALUES (4, 5, 50.0, 'GRAM');  -- Flour (for Béchamel)

INSERT INTO account (login, password) VALUES
('admin', '$2a$10$BxVBlwD0Q4iwyjTOvx8Om.QvqiG7IoQqR4w2rYyxpPolwQaylUqzy'),
('user1', '$2a$10$MjAQCxCa7bv0zZtRNIfZ6eeAtIqGfeHyXxck1.lg.XVDg97KISY7m'),
('user2', '$2a$10$Q3XaWvnDOmzcM7vkEhlDkegQK2fqYDuZ2IvHDdUy9H5m5jg.tpvBi'),
('user3', '$2a$10$f847O/RhszF4tXodQ.5Ce.NbSzN1nO8zfO4zHAROv1hrzil95U.pm');

INSERT INTO user_preference (id) VALUES
(1),
(2),
(3),
(4);

INSERT INTO user (account_id,name,role,preference_id) VALUES
(1,'adminek','ROLE_ADMIN', 1),
(2,'user1','ROLE_USER', 2),
(3,'user2','ROLE_USER', 3),
(4,'user3','ROLE_USER', 4);

INSERT INTO user_ingredient (user_id, name, ingredient_category) VALUES
(2, 'Tofu', 'VEGAN'),
(2, 'Chili Pepper', 'VEGAN');

INSERT INTO user_meal (user_id, cuisine_id, name, description) VALUES (2, 1, 'Tofu Stir-Fry', 'Stir-fried tofu with vegetables and soy sauce');
INSERT INTO user_meal (user_id, cuisine_id, name, description) VALUES (2, 1, 'Spicy Veggie Wrap', 'A wrap with vegetables and spicy tofu');
INSERT INTO user_meal (user_id, cuisine_id, name, description) VALUES (2, 1, 'Chili Tofu Salad', 'Fresh salad with chili tofu and greens');

INSERT INTO user_meal_ingredient (user_meal_id, ingredient_id, user_ingredient_id, `count`, unit) VALUES
(1, NULL,   1,      200.0,  'GRAM'), -- Tofu (user ingredient)
(1, 12,     NULL,   2.0,    'CLOVE'), -- Garlic
(1, 6,      NULL,   2.0,    'TABLESPOON'), -- Olive oil
(1, 16,     NULL,   1.0,    'PIECE'), -- Onion
(1, NULL,   2,      50.0,   'MILLILITER'); -- Soy sauce (user ingredient)

INSERT INTO user_meal_ingredient (user_meal_id, ingredient_id, user_ingredient_id, `count`, unit) VALUES
(2,  NULL,   1,      150.0,  'GRAM'), -- Tofu (user ingredient)
(2,  NULL,   2,      1.0,    'PIECE'), -- Chili Pepper (user ingredient)
(2,  1,      NULL,   1.0,    'PIECE'), -- Tomato
(2,  3,      NULL,   50.0,   'GRAM'), -- Lettuce (user ingredient)
(2,  4,      NULL,   1.0,    'PIECE'), -- Tortilla (user ingredient)
(2,  6,      NULL,   1.0,    'TABLESPOON'); -- Olive oil

INSERT INTO user_preference_cuisine (user_preference_id, cuisine_id) VALUES
(1,1),
(1,2),
(2,1);

INSERT INTO user_preference_without_ingredient (user_preference_id, ingredient_id) VALUES
(2,5);


