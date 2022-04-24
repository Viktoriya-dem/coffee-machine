# coffee-machine

Доступные методы:
- POST http://localhost:8080/api/add/milk для добавления молока.
- POST http://localhost:8080/api/add/coffee для добавления кофе.
- POST http://localhost:8080/api/make/{coffee} для приготовления кофе, доступные напитки: ESPRESSO, CAPPUCCINO, LATTE.
- GET http://localhost:8080/api/info для получения информации о количестве кофе и молока в кофе-машине.

Для запуска приложения необходимо:
- Скопировать проект с помощью команды "git clone https://github.com/Viktoriya-dem/coffee-machine.git"
- Перейти в папку с проектом в командной строке
- Выполнить команду docker-compose up -d  

После запуска приложения информацию о методах и проверку работы можно выполнить по адресу http://localhost:8080/swagger-ui.html

