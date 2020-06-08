Запуск тестов в терминале Linux:
  1. git clone https://github.com/BaturinIgor/PhoneBookTest.git
  2. cd PhoneBookTest
  3. Запустить приложение phonebook.jar двойным нажатием ЛКМ
  4. mvn clean install -U (для загрузки недостающих плагинов)
  5. mvn allure:serve (для запуска сервера с отчетом о тестировании)
