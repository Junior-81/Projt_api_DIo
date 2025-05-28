# 🚀 Guia de Inicialização - Santander Dev Week 2023 API

## 📋 Pré-requisitos

### 1. Instalar Java 17 (JDK)

**Opção 1: Download Direto**
1. Acesse: https://adoptium.net/temurin/releases/?version=17
2. Baixe o **OpenJDK 17 LTS** para Windows x64
3. Execute o instalador (.msi)
4. Durante a instalação, marque a opção "Set JAVA_HOME variable"

**Opção 2: Via Chocolatey (se tiver instalado)**
```powershell
choco install openjdk17
```

**Opção 3: Via Winget**
```powershell
winget install EclipseAdoptium.Temurin.17.JDK
```

### 2. Verificar Instalação

Após instalar, abra um novo terminal PowerShell e teste:

```powershell
java -version
javac -version
echo $env:JAVA_HOME
```

Você deve ver algo como:
```
openjdk version "17.0.x" 2023-xx-xx LTS
```

## 🎯 Iniciar o Projeto

### 1. Navegar para o Diretório

```powershell
cd "c:\Users\ailto\OneDrive\Documentos\java_dio\Projt-API"
```

### 2. Verificar Estrutura do Projeto

```powershell
dir
```

### 3. Executar a Aplicação

**Opção 1: Via Gradle Wrapper (Recomendado)**
```powershell
.\gradlew.bat bootRun
```

**Opção 2: Via arquivo de lote criado**
```powershell
.\run.bat
```

**Opção 3: Build e execução manual**
```powershell
.\gradlew.bat build
java -jar build\libs\santander-dev-week-api-0.0.1-SNAPSHOT.jar
```

## 🌐 Acessar a Aplicação

Após iniciar com sucesso, você verá no terminal:
```
Started SantanderDevWeekApiApplication in X.XXX seconds
```

### URLs Importantes:

| URL | Descrição |
|-----|-----------|
| http://localhost:8080 | API base |
| http://localhost:8080/swagger-ui.html | Documentação interativa |
| http://localhost:8080/h2-console | Console do banco H2 |
| http://localhost:8080/users | Endpoint de usuários |

### Configurações do H2 Console:
- **JDBC URL**: `jdbc:h2:mem:sdw2023`
- **User Name**: `sdw2023`
- **Password**: (deixe em branco)

## 🧪 Testar a API

### 1. Via Swagger UI
1. Acesse: http://localhost:8080/swagger-ui.html
2. Explore os endpoints disponíveis
3. Use o botão "Try it out" para testar

### 2. Via cURL

**Listar usuários:**
```bash
curl -X GET http://localhost:8080/users
```

**Criar usuário:**
```bash
curl -X POST http://localhost:8080/users \
  -H "Content-Type: application/json" \
  -d '{
    "name": "João Silva",
    "account": {
      "number": "12345-6",
      "agency": "0001",
      "balance": 1500.00,
      "limit": 500.00
    },
    "card": {
      "number": "**** **** **** 1234",
      "limit": 2000.00
    },
    "features": [
      {
        "icon": "💳",
        "description": "Cartão de Crédito"
      }
    ],
    "news": [
      {
        "icon": "📱",
        "description": "Novo app disponível"
      }
    ]
  }'
```

## 🔧 Solução de Problemas

### Erro: "java não é reconhecido"
- Reinstale o Java 17
- Verifique se JAVA_HOME está configurado
- Reinicie o terminal

### Erro: "Port 8080 already in use"
- Pare outros serviços na porta 8080
- Ou altere a porta em `src/main/resources/application.yml`:
  ```yaml
  server:
    port: 8081
  ```

### Erro: "gradlew não é reconhecido"
- Use: `.\gradlew.bat` (com .\ no início)
- Ou execute no PowerShell com permissões de administrador

### Erro: "Access Denied"
```powershell
Set-ExecutionPolicy -ExecutionPolicy RemoteSigned -Scope CurrentUser
```

## 📚 Próximos Passos

1. **Explore a API** via Swagger UI
2. **Teste todos os endpoints** CRUD
3. **Verifique o banco H2** pelo console
4. **Modifique o código** e veja as mudanças
5. **Execute os testes** com `.\gradlew.bat test`

## 🆘 Precisa de Ajuda?

- Confira a documentação no README.md
- Veja os logs no terminal para identificar erros
- Use o Swagger UI para entender a API
- Consulte o código fonte para detalhes de implementação

---

💡 **Dica**: Mantenha o terminal aberto para ver os logs em tempo real!
