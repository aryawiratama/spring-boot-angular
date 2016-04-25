# Belajar Spring Boot dan Angular JS
Project untuk belajar Spring boot, dengan Angular JS pada bagian depan (front end). Project ini masih sangat sederhana, hanya berisikan proses CRUD data. Spring Boot hanya menghasilkan data dengan format JSON yang mana nantinya akan diakses (consume) oleh Angular JS. Project ini saya gunakan sebagai tempat untuk belajar Spring Boot dan AngularJS. Bagi yang mau belajar bisa langsung download project ini. Terima Kasih.

## Getting Started
### Aplikasi
Download dulu project ini, kemudian pastikan komputer anda telah terinstall `Java Versi 7`, `Maven` dan `Bower`. Jika pada komputer anda belum terinstall ketiga tools tersebut maka bisa di download pada link dibawah ini
- [Java](http://www.oracle.com/technetwork/java/javase/downloads/jdk7-downloads-1880260.html).
- [Maven](https://maven.apache.org/).
- [Bower](http://bower.io/).

### Database
Untuk konfigurasi database, anda bisa membuka file `application.properties` yang terletak pada folder `src\main\resources`. Silahkan diganti sesuai dengan konfigurasi database lokal anda.

## Cara Menjalankan
Buka Command Prompt arahkan ke root project kemudian jalankan perintah `mvn clean install`. Maven akan mendownload apa saja dependensi yang dibutuhkan untuk Spring. Kemudian jalankan perintah `bower install`, untuk mendownload dependensi angular js yang saya pakai pada project ini. Jika sudah selesai, jalankan project dengan perintah `mvn spring-boot:run`. Maka project sudah dapat diakses pada halaman `localhost:8080`.

## Teknologi
- Spring Boot
- Angular JS
- Bootstrap
- Thymeleaf
- Joda Time
