# 📚 Capgemini Bootcamp: Java + Angular

This repository contains the projects developed during the Capgemini Bootcamp, focused on **Java** and **Angular**. It includes a variety of projects ranging from backend to frontend applications, as well as practical exercises and labs.

## 📂 Repository Structure

- **`catalogo/`**: Main project acting as a backend for the catalogue application, developed with **Spring Boot**.
- **`GildedRose/`**: Exercise based on the classic ‘Gilded Rose’ refactoring problem. [More information](https://github.com/emilybache/GildedRose-Refactoring-Kata).
- **`demo/`**, **`demo-maven/`**, **`demo-ng/`**: Demonstration projects used to explore and practice specific concepts in Java and Angular.
- **`project-batch/`**: Project that implements batch processes using **Spring Batch**.
- **`ws-soap/`** and **`ws-soap-consume/`**: Projects related to SOAP web services; the former for the creation of a SOAP service and the latter for its consumption.
- **`catalogo-frontend/`**: Main project acting as a frontend for the catalogue application, developed with **Angular**.

## 🛠️ Installation and Configuration

### Prerequisites

Make sure you have the following components installed:

- **Java Development Kit (JDK) 17 or higher**.
- **Maven** for dependency management and building Java projects.
- **Node.js** and **npm** for Angular projects.
- **Angular CLI** if you work with Angular projects.

## :computer: Get Started:

- Clone this repository:

```
git clone <https://github.com/claudiaglez/BootcampCapgemini.git>
```

- Navigate to the directory:

```
cd BootcampCapgemini
```

Project Configuration

Backend (catalogo/)

- Go to the backend folder:

```
cd catalogo
```

- Build and run the application:

```
mvn spring-boot:run
```

The application will be available in: [http://localhost:8080](http://localhost:8080/).

Frontend (demo-ng/)

- Go to the frontend folder:

```
cd demo-ng
```

- Install dependencies

```
npm install
```

Run the application:

```
ng serve
```

The application will be available in: [http://localhost:4200](http://localhost:4200/).

## ⚙️ Technologies

Java 21

Spring Boot

Spring Batch

SOAP Web Services

Angular

Maven

Node.js 

npm

## 📄 API Documentation

The API documentation is available on Swagger. To access it, follow these steps:

1. Make sure the backend (`catalogo/`) and database are running.
    - The **MySQL** database is located in a Podman container. To get it up, run the following command:
        
        ```bash
        podman run -d --name container_name -e MYSQL_ROOT_PASSWORD=password -e MYSQL_DATABASE=base_name -p 3306:3306 mysql:latest
        ```
        
        (Set `container_name`, `password` and `base_name` according to the configuration provided).
        
    - If a `podman-compose.yml` file is used, the container can be raised with:
        
        ```bash
        podman-compose up -d
        ```
        
2. Make sure the backend is running:
    
    ```bash
    cd catalog
    mvn spring-boot:run
    ```
    

Open your browser and visit:

🔗 http://localhost:8080/swagger-ui.html

From this interface you can test the endpoints directly.

🗄 Database

Database: MySQL

Credentials:

User: root

Password: password (set according to configuration)

URL: jdbc:mysql://localhost:3306/base_name

Make sure the database container is properly configured and running before interacting with the API.

## 📂 Project Structure

```bash
📦 BootcampCapgemini
├── 📂 catalogo/                  # Principal Backend in Spring Boot
│   ├── 📂 src/                    # Source code
│   ├── 📄 pom.xml                 # Maven Configuration
│   ├── 📄 application.properties   #Backend configuration
├── 📂 catalogo-frontend/          # Principal frontend in Angular
│   ├── 📂 src/                    #  Angular source code
│   ├── 📄 package.json            # npm dependencies
├── 📂 GildedRose/                 # Gilded Rose Refactoring Exercise
├── 📂 demo/                       # Java demonstration project
├── 📂 demo-maven/                 # Maven demonstration project
├── 📂 demo-ng/                    # Angular demonstration project
│   ├── 📂 src/                    #  Angular source code
│   ├── 📄 package.json            # npm dependencies
│   └── ...
├── 📂 project-batch/              # Spring Batch project
├── 📂 ws-soap/                    #  SOAP web service in Spring
├── 📂 ws-soap-consume/            # Client consuming the SOAP service
├── 📄 [README.md](http://readme.md/)                   # Repository documentation
└── 📄 .gitignore                   # Files ignored by Git
```
## :open_hands: Contributions

Do you have any suggestion? Please, feel free to contact me or open an issue or pull request :star_struck:

## 👩‍💻 Author

🧡 [Claudia González](https://www.linkedin.com/in/claudiaglezgarcia/)<br>
