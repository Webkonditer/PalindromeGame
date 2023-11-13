plugins {
    id("java")
    id ("war") // Плагин для создания веб-приложения WAR
}

group = "ru.webkonditer"
version = "1.0-SNAPSHOT"

repositories {
    mavenCentral()
}

dependencies {
    // Зависимость для сервлетов
    implementation ("javax.servlet:javax.servlet-api:4.0.1")

    // Зависимость для веб-сервера (Jetty)
    implementation ("org.eclipse.jetty:jetty-server:11.0.14")
    implementation ("org.eclipse.jetty:jetty-servlet:11.0.14")

    // Подключение логгера
    implementation ("org.slf4j:slf4j-api:1.4.11")
    implementation ("ch.qos.logback:logback-classic:1.4.11")

    // Подключение sqlite
    implementation("org.xerial:sqlite-jdbc:3.43.2.2")
}


