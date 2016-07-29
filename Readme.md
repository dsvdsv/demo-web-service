#demo-web-service

###Технологии

- Для упращения использован spring-boot, для демонстрации чего либо он подходит больше всего, так как не нужно кудато чтото диплоить и т.д.
- Обработка входящих реквестов занимается [apache camel](http://camel.apache.org/), через компонент [netty4-http](http://camel.apache.org/netty-http.html)
Camel выбрал, так как в нем много компонентов, и он поддерживает [EIP](http://camel.apache.org/enterprise-integration-patterns.html), в этом проекте 
использован [Content Based Router](http://camel.apache.org/content-based-router.html)
- gradle как система сборки, по моему опыту наиболее подходит для создания каких либо нестандартных сборок, в данному случае интеграция с nodejs и npm


###Краткое описание
 Для сборки нужно установить gradle или воспользоваться враппером (gradlew/gradlew.bat)
####Сборка
  ```
  gradle clean build
  ```
или
  ```
  gradlew.bat clean build
  ```
####Запуск 
После сборки проекта можно запустить demo-web-service-0.0.1-SHAPSHOT.jar файл из директории build/libs/
запросы можно отправлять на [http://localhost:8080/endpoint](http://localhost:8080/endpoint)

####Тестирование
В com.example.ApplicationTests реализованны e2e тесты, проверить их выполнение можно командой 
 ```
  gradle clean test
 ```
или
 ```
  gradlew.bat clean test
 ```