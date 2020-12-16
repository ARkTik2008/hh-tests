
##### Запуск через Gradle 

При запуске обязательно указать токен
```
gradle clean test allureReport -Papi_token="<token>"
```

#### Просмотр результатов
Результаты для пострения Allure отчетов сохраняются в папку allure-results откуда их можно сгенерировать 
используя команду `gradle allureServe`, либо allure command line, подробнее об allure тут 
https://docs.qameta.io/allure/