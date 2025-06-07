# 🏦 Santander Dev Week 2025 API

![Java](https://img.shields.io/badge/java-%23ED8B00.svg?style=for-the-badge&logo=openjdk&logoColor=white)
![Spring](https://img.shields.io/badge/spring-%236DB33F.svg?style=for-the-badge&logo=spring&logoColor=white)
![Gradle](https://img.shields.io/badge/Gradle-02303A.svg?style=for-the-badge&logo=Gradle&logoColor=white)
![Railway](https://img.shields.io/badge/Railway-131415?style=for-the-badge&logo=railway&logoColor=white)

> API RESTful para sistema bancário digital desenvolvida para treinar criação de CRUD/API.

## ⚡ Funcionalidades

✅ **CRUD completo** de usuários bancários  
✅ **Relacionamentos JPA** (conta, cartão, features, notícias)  
✅ **Validação robusta** com Bean Validation  
✅ **Documentação automática** com Swagger/OpenAPI  
✅ **Deploy automatizado** no Railway

## 🛠️ Stack

- **Java 17** + Spring Boot 3 + Spring Data JPA
- **H2** (desenvolvimento) + **PostgreSQL** (produção)
- **Docker** + Railway para deploy
- **OpenAPI 3** + Swagger UI para documentação


**Acesse:**
- API: http://localhost:8080
- Swagger: http://localhost:8080/swagger-ui.html
- H2 Console: http://localhost:8080/h2-console

## 📋 Endpoints

| Método | Endpoint | Descrição |
|--------|----------|-----------|
| `GET` | `/users` | Lista usuários |
| `GET` | `/users/{id}` | Busca por ID |
| `POST` | `/users` | Cria usuário |
| `PUT` | `/users/{id}` | Atualiza usuário |
| `DELETE` | `/users/{id}` | Remove usuário |

## 📊 Modelo de Dados

```mermaid
classDiagram
    class User {
        +Long id
        +String name
        +Account account
        +Card card
        +List~Feature~ features
        +List~News~ news
    }
    
    class Account {
        +String number
        +String agency
        +BigDecimal balance
        +BigDecimal limit
    }
    
    class Card {
        +String number
        +BigDecimal limit
    }
    
    User ||--|| Account
    User ||--|| Card
    User ||--o{ Feature
    User ||--o{ News
```

## 🐋 Docker

```bash
# Build e execute
docker-compose up --build

# Ou execute a imagem
docker build -t santander-api .
docker run -p 8080:8080 santander-api
```

## 🚀 Deploy no Railway

1. Conecte seu repositório no Railway
2. Configure `SPRING_PROFILES_ACTIVE=prd`
3. Deploy automático configurado!

## 🧪 Testes

```bash
./gradlew test
```

---

💡 **Projeto desenvolvido durante o Santander Dev Week 2023 da DIO**


