ЗАПУСК ТЕСТОВ В ТЕРМИНАЛЕ ДЛЯ ОС WINDOWS:

  1. git clone https://github.com/BaturinIgor/portfolio.git
  2. cd Java/local_servers/phoneBook
  3. Запустить приложение phonebook.jar (ссылка на проект https://yadi.sk/d/IjbO47CZ_Vh7ew)
  4. mvn clean install -U (для загрузки недостающих плагинов)
  5. mvn allure:serve (для запуска сервера с отчетом о тестировании)
P.S. Если ОС другая, то скачать драйвер по ссылке https://chromedriver.chromium.org/ и распаковать в папку проекта.
