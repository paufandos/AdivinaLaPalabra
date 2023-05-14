# Adivina la palabra

Guess the Word is a game similar to Wordle, in which the player has to guess a secret five-letter word in five attempts. The game gives the player clues about whether the letters they have entered are correct, incorrect, or in the wrong position.

This repository contains the game code, developed using Java and Spring Boot for the server side, and HTML, SASS, TypeScipt and Angular for the client side. The back-end is responsible for generating the secret words, validating player inputs, and sending responses to the front-end, which is responsible for making requests to the server, interpreting the responses it receives and displaying the content visually and dynamically.

![Java](https://img.shields.io/badge/Java-ED8B00?style=for-the-badge&logo=java&logoColor=white)
![Spring Boot](https://img.shields.io/badge/Spring_Boot-F2F4F9?style=for-the-badge&logo=spring-boot)
![Maven](https://img.shields.io/badge/Maven-000000?style=for-the-badge&logo=apache-maven&logoColor=white)
![Hibernate](https://img.shields.io/badge/Hibernate-59666C?style=for-the-badge&logo=Hibernate&logoColor=white)
![Apache Tomcat](https://img.shields.io/badge/apache%20tomcat-%23F8DC75.svg?style=for-the-badge&logo=apache-tomcat&logoColor=black)
![MySQL](https://img.shields.io/badge/mysql-%2300f.svg?style=for-the-badge&logo=mysql&logoColor=white)
![HTML5](https://img.shields.io/badge/HTML5-E34F26?style=for-the-badge&logo=html5&logoColor=white)
![CSS3](https://img.shields.io/badge/CSS3-1572B6?style=for-the-badge&logo=css3&logoColor=white)
![Bootstrap](https://img.shields.io/badge/bootstrap-%23563D7C.svg?style=for-the-badge&logo=bootstrap&logoColor=white)
![JavaScript](https://img.shields.io/badge/JavaScript-F7DF1E?style=for-the-badge&logo=javascript&logoColor=black)
![TypeScript](https://img.shields.io/badge/typescript-%23007ACC.svg?style=for-the-badge&logo=typescript&logoColor=white)
![Angular](https://img.shields.io/badge/angular-%23DD0031.svg?style=for-the-badge&logo=angular&logoColor=white)
![Jasmine](https://img.shields.io/badge/jasmine-%238A4182.svg?style=for-the-badge&logo=jasmine&logoColor=white)
![Visual Studio Code](https://img.shields.io/badge/Visual%20Studio%20Code-0078d7.svg?style=for-the-badge&logo=visual-studio-code&logoColor=white)
![Git](https://img.shields.io/badge/git-%23F05033.svg?style=for-the-badge&logo=git&logoColor=white)
[![GitHub](https://img.shields.io/badge/GitHub-100000?style=for-the-badge&logo=github&logoColor=white)](https://github.com/)
![Windows 11](https://img.shields.io/badge/Windows%2011-%230079d5.svg?style=for-the-badge&logo=Windows%2011&logoColor=white)

![LICENSE](https://licensebuttons.net/l/by-nc-sa/3.0/88x31.png)

## Index

- [Developers](#developers)
- [Requirements](#requirements)
- [Installation](#installation)
- [Documentation](#documentation)
- [Contribution](#contribution)
- [Contact](#contact)

## Developers

This repository has been developed by Pau Fandos.

## Requirements

To run the project, you need:

- Java 11 or higher
- Maven 3.6 or higher
- Angular CLI 15.2.6 or higher
- An IDE compatible with Spring Boot, and Angular (for example Visual Studio Code)

## Installation

To install the project, follow these steps:

- Clone the repository to your local machine with the command `git clone https://github.com/paufandos/AdivinaLaPalabra.git`
- Open the project with your IDE and wait for Maven dependencies to be resolved
- Install front-end dependencies using npm install command
- Run the class `AdivinaLaPalabraApplication.java` as a Spring Boot application
- Run ng serve for a dev server. Navigate to http://localhost:4200/. The application will automatically reload if you change any of the source files.

## Documentation

The back-end code documentation can be generated with the command `mvn javadoc:javadoc` and can be found in the folder `target/site/apidocs`.

## Contribution

This project is private and does not accept external contributions. If you are one of the developers and want to make changes to the code, follow these recommendations:

- Create a new branch with the name of the change you want to make (for example, `feature/new-word`)
- Make your modifications on the branch and check that they work correctly
- Commit with a descriptive message of your change (for example, `Added the option to choose the game difficulty`)
- Push your branch to the remote repository with the command `git push origin feature/new-word`
- Create a pull request from your branch to the main branch and wait for it to be reviewed and approved by another developer
- Once approved, merge your branch to the main branch and delete the remote and local branch


## Contact

If you have any questions or suggestions about the project, you can contact the developers via their email addresses:
- Pau Fandos Gorris: fandosgorrispau@gmail.com
