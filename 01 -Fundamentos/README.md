29/07 - Processos e Threads

# Threads vs Processos em Sistemas Distribuídos

Este documento explica o que são **threads** e **processos**, destaca as **diferenças entre eles**, e como eles se comportam em **sistemas distribuídos**.

---

## 🔧 Conceitos Fundamentais

### 🧠 Processo

Um **processo** é uma instância independente de um programa em execução.

- Possui **memória própria**, arquivos, variáveis de ambiente, e recursos.
- É **isolado** de outros processos.
- Criar um processo é relativamente **custoso**.
- Pode conter **uma ou mais threads**.

### 🔁 Thread

Uma **thread** é a menor unidade de execução dentro de um processo.

- Todas as threads de um mesmo processo **compartilham o mesmo espaço de memória** (heap).
- Criar uma thread é mais **leve e rápido** do que criar um processo.
- Cada thread possui sua **própria pilha (stack)**.

---

## ⚖️ Diferenças Entre Processo e Thread

| Característica         | Processo                             | Thread                                  |
|------------------------|--------------------------------------|-----------------------------------------|
| Memória                | Isolada                              | Compartilhada                           |
| Custo de criação       | Alto                                 | Baixo                                   |
| Comunicação            | Complexa (IPC, sockets, etc.)        | Simples (variáveis, memória compartilhada) |
| Estabilidade           | Falha afeta só o processo            | Falha pode afetar todo o processo       |
| Independência          | Sim                                   | Não                                     |
| Exemplo                | Dois editores de texto abertos       | Várias abas no mesmo navegador          |

---

## 🌐 Em Sistemas Distribuídos

### 📦 Processos Distribuídos

Em sistemas distribuídos, os processos:

- São executados em **máquinas diferentes** (nós).
- Não compartilham memória diretamente.
- Se comunicam via **rede** (HTTP, gRPC, mensagens, etc).
- Exemplo: microserviços em containers diferentes (Docker).

### 🔄 Threads em Sistemas Distribuídos

Dentro de cada processo (mesmo distribuído), as threads:

- Executam tarefas concorrentes localmente.
- Compartilham memória entre si.
- Melhoram o desempenho e a capacidade de lidar com múltiplas tarefas simultaneamente.
- Exemplo: servidor web com uma thread por requisição.

---

## 🔍 Comparação Focada em Sistemas Distribuídos

| Característica             | Processo (Distribuído)                     | Thread (Local ao processo)                       |
|----------------------------|--------------------------------------------|--------------------------------------------------|
| Localização                | Pode estar em máquinas diferentes          | Sempre na mesma máquina/processo                |
| Comunicação                | Pela rede (TCP, HTTP, etc)                 | Por memória compartilhada                       |
| Compartilhamento de dados | Não compartilham memória                   | Compartilham heap                               |
| Exemplo                    | Microserviços distintos                    | Threads de um servidor HTTP                     |
| Falhas                     | Isoladas entre processos                   | Uma falha pode encerrar o processo inteiro      |

---

## 🛠️ Quando Usar Cada Um?

### ✅ Use **processos** quando:
- Precisa de **isolamento forte** (ex: segurança, confiabilidade).
- Quer escalar serviços de forma **independente**.
- Está trabalhando com **microserviços**, **containers**, ou **Kubernetes**.

### ✅ Use **threads** quando:
- Precisa de **concorrência leve** dentro de um serviço.
- Está lidando com **I/O concorrente**, como múltiplas requisições HTTP.
- Precisa aproveitar **núcleos de CPU** com paralelismo local.

---

## 📌 Exemplos Práticos

- Um sistema de e-commerce distribuído:
  - Microserviços (processos) para autenticação, carrinho, pagamentos.
  - Cada microserviço usa múltiplas threads para processar requisições simultâneas.

- Um servidor web (Spring Boot, Express, etc):
  - Um processo por instância da aplicação.
  - Threads para lidar com múltiplos usuários ao mesmo tempo.

---

## 📚 Referências

- Sistemas Operacionais Modernos – Andrew S. Tanenbaum
- Designing Data-Intensive Applications – Martin Kleppmann
- Documentação oficial de Docker, Kubernetes e Java Threads

