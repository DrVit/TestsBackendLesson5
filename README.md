# TestsBackendLesson5
Тестирование Category Controller:
а)GetPositiveCategoryTest - тестирование позитивного запроса;
б)GetNegativeCategoryIdIntTests:
  1.Тест на int значение id отсутствующие в базе - Должен выдавать код 400, "message" : "Unable to find category with id:" (значение 4294967295 - max INT UNSIGNED);
  2.Тест на int значение id <= 0 - Должен выдавать код 404, "error" : "Bad request" (полагаю, что при обращении к базе id не может быть меньше 1);
в)GetNegativeCategoryIdNotIntTests - Тест на не int значение id - Должен выдавать код 400, error "Bad request";
Тестирование Product Controller:
а)PositiveCreateProductTest - позитивное тестирование Create - Put - Get(by id) - Delete;
б)NegativeCreateDoubleProductTest - негативное тестирование на создание дублированных полей "title" (с точки зрения логики данное поле должно быть уникальным);
в)DeleteNegative500Test - негативное тестирование Delete на int значение id отсутствующие в базе должен выдавать код 500, "error" : "Internal Server Error";
г)DeleteNegative400Test - негативное тестирование Delete на не int значение id - Должен выдавать код 400,"error" : "Bad request";

Негативные тесты проходят не все поскольку, с моей точки зрения, страдает логика mini-market.
Повеселил swagger с полями id - int на 64 символа, принимающий Long. :)
