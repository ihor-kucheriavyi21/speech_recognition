# Speech Learning

## About

- Web-based interactive application for learning the correct pronunciation of sounds using Spring technologies.
- Application created using Java, Python, JavaScript. For User Interface I use Bootstrap, HTML/CSS, Thymeleaf.

## Used frameworks and libraries

- spring-boot
- spring-orm
- gson
- commons-collections4
- hibernate-core
- tomcat-dbcp
- lombok
- postgresqlл

## Implemented methods during application development

- Methods of database design using ORM system;
- Row hashing methods;
- Methods of recognizing pronunciation from an audio file;
- Methods of localization of the web application;
- Methods of saving files in the database;
- Methods of developing adaptive pages;
- Methods of testing applications.

## Author

- [@KucheriavyiIhor](https://github.com/Ascomos21)

#### Український опис

## Про додаток

- Інтерактивний веб-додаток для навчання правильної вимови звуків за технологіями Spring.
- Додаток, створений за допомогою Java 11, Python, PostgreSql, JavaScript. Для інтерфейсу користувача я використовую
  Bootstrap, HTML/CSS, Thymeleaf.

## Реалізовані методи під час розробки програми

- Методи проектування бази даних за допомогою системи ORM;
- Методи хешування рядків;
- Методи розпізнавання вимови з аудіофайлу;
- Методи локалізації веб-додатка;
- Методи збереження файлів у базі даних;
- Методи розробки адаптивних сторінок;
- Методи перевірки додатків.

## Як запустити додаток

1. Потрібно мати встановлені Java 11+, PostgreSql та Python 3.9+
2. Налаштувати змінні середовища (якщо користувач Windows)
3. Встановити наступні бібліотеки Python: google-cloud-speech, PyAudio, pyttsx3, SpeechRecognition
4. Для успішної локалізації мовою яка використовує кирилю налаштувати файл properties на charset - UTF-8
5. В класі ihorko/work/speech_recognition/db/util/HibernateConfig.java в методі DataSource ввести назву своєї бази
   даних, пароль та ім'я користувача

```sh
 dataSource.setUrl("jdbc:postgresql://localhost:5432/{db-name}");
        dataSource.setUsername("{username}");
        dataSource.setPassword("{password}");
```

##

<img src="BasePage.png" alt="Logo" width="80" height="80">

## Автор

- [@KucheriavyiIhor](https://github.com/Ascomos21)
