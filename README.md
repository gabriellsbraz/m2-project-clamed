## DEVinPHARMACY - Medicine Inventory Management System

DEVinPHARMACY is a pharmacy and medication registration system, which allows the user to view and update their stores and products in an efficient and intuitive way.

## :rocket: Technologies
The following tools were used in this project:


- [Java](https://www.java.com/en/download/help/whatis_java.html)
- [Spring Framework](https://spring.io/projects/spring-framework)
- [Spring Boot](https://spring.io/projects/spring-boot)
- [Spring Data JPA](https://spring.io/projects/spring-data-jpa)
- [Postman](https://www.postman.com/)
- [PostgreSQL](https://www.postgresql.org/)
  
## :white_check_mark: Requirements

Before starting 🏁, you need to have Java installed on your machine, some API development and testing platform, and some database.
## How to Run
 Clone this project 
$ git clone https://github.com/gabriellsbraz/m2-project-clamed
#
 Configure the database in the application.properties or application.yml file
#
 The server will initialize in the http://localhost:5432/pharmacy-api
#
 Run the Spring Boot application: mvn spring-boot:run or run the Application.java main class
 #



## EndPoints
#### Farmacia
- PostMapping("/farmacia")
- @GetMapping("/farmacias") -> getAllFarmacias
- @GetMapping("/farmacias/{cnpj}") 
- @PutMapping("/farmacias/{cnpj}")
- @DeleteMapping("/farmacias/{cnpj}")
  
#### Request body example
  {
    "cnpj": 3423532652353253,
    "razaoSocial": "MedHouse Ltda",
    "nomeFantasia": "Farmácia MedHouse",
    "email": "medhouse@farmacia.com",
    "telefone": "(55)5555-5555",
    "celular": "(45)95555-5555",
    "endereco": {
        "cep": 8877799,
        "logradouro": "Rua Madrid",
        "numero": 76,
        "bairro": "Winterfell",
        "cidade": "Estocolmo",
        "estado": "SC",
        "complemento": "",
        "latitude": 19.254356,
        "longitude": 3.8987687
    }
}

#### Medicamentos
- @PostMapping("/medicamentos")
- @GetMapping("/medicamentos") -> getAllMedicamentos
- @GetMapping("medicamentos/{nroRegistro}")
- @PutMapping("medicamentos/{nroRegistro}")
- @DeleteMapping("medicamentos/{nroRegistro}")

#### Request body example
{
   "nroRegistro": 9876,
   "nome": "Medicine",
   "laboratorio": "Matrix",
   "dosagem": "2x ao dia",
   "descricao": "Descricao",
   "preco": 11.0,
   "tipoMedicamento": "COMUM"
}

#### Estoque
- @PostMapping("/estoque")
- @GetMapping("/estoque") -> getAllEstoques
- @GetMapping("estoque/{cnpj}")
- @PutMapping("estoque")
- @DeleteMapping("estoque")

#### Request body example
{
    "cnpj": 90561736000121,
    "nroRegistro": 1010,
    "nome": "Programapan",
    "quantidade": 8

}


