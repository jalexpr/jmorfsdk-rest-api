# tawt-rest-api
Ссылка на Swagger сервиса: http://boberpul2.asuscomm.com:8093/tawt-rest-api/swagger-ui.html

## Описание библиотеки-клиента
В библиотеке реализовано 3 класса, которые соответствуют инструментам фреймворка TAWT:
1) JMorfSdkRemoteService
2) GraphematicParserRemoteService
3) GamaRemoteService

Для использования библиотеки-клиента, необходимо подключить ее к
своему проекту. Для этого можно воспользоваться сборщиком пакетов Maven, с
помощью которого необходимо подключить пакет «tawt-rest-client» к своему
проекту.
В файле конфигурации application.properties необходимо указать адрес и
порт сервиса. В случае с локальным сервисом по умолчанию адрес:
http://localhost, порт: 30002, - которые могут быть указаны любые в соответствии
с конфигурацией системы, в которой сервис используется. В случае с
глобальным веб-сервисом это будет – адрес: http://boberpul2.asuscomm.com,
порт: 8093. После этого, необходимо создать объект класса самого инструмента
и вызвать необходимый метод с передачей исходных параметров.

Пример работы с инструментом GraphematicParserRemoteService,
используя веб-сервис:
```
RemoteServiceFactory factory = new RemoteServiceFactory("http://boberpul2.asuscomm.com", 8093);
GraphematicParserRemoteService graphematicParserRemoteService = factory.getGraphematicParserRemoteService();
System.out.println(graphematicParserRemoteService.parserSentence("Я ходил сегодня ночью в магазин.").getResult());
```
Пример выходных данных:
```
[[Я, ходил, сегодня, ночью, в, магазин]]
```
## Подключение библиотеки-клиента
Для того, чтобы подключить библиотеку-клиент необходмо перейти по ссылке:
https://jitpack.io/#profAlik/tawt-rest-api
Затем в разделе Commits выбрать последний коммит и нажать на кнопку «Get it», далее следовать инструкциям на сайте.
Для подключения только библиотеки без остальных модулей необходимо в тэге "artifactId" указать: tawt-rest-client
