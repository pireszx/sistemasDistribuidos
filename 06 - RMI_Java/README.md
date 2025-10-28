# 🛰️ RMI em Java

## 💡 O que é RMI

**RMI (Remote Method Invocation)** é uma tecnologia do Java que permite que um programa execute métodos de objetos que estão em outra **JVM (Java Virtual Machine)** — inclusive em outro computador.  

Em outras palavras, o RMI possibilita **chamar métodos remotamente como se fossem locais**, tornando a comunicação entre aplicações Java simples e transparente.

---

## ⚙️ Como funciona

O RMI segue uma **arquitetura cliente-servidor**, composta por três partes principais:

- **Interface Remota:** define os métodos que podem ser acessados remotamente.  
- **Servidor:** implementa a interface e registra o objeto no **RMI Registry**.  
- **Cliente:** localiza o objeto remoto e invoca seus métodos como se estivessem na mesma JVM.

---

## 🧩 Fluxo resumido

1. O servidor cria e registra o objeto remoto.  
2. O cliente encontra o objeto utilizando o **RMI Registry**.  
3. O cliente chama o método remoto, e o RMI cuida automaticamente da comunicação pela rede.

---

## 🧩 Componentes Principais

| **Componente**     | **Função** |
|--------------------|------------|
| **Remote Interface** | Define os métodos que podem ser chamados remotamente. |
| **Stub** | Representa o objeto remoto no cliente (funciona como um “proxy”). |
| **Skeleton** | Recebe chamadas e repassa para o objeto real (gerado automaticamente nas versões atuais do Java). |
| **RMI Registry** | Serviço responsável por registrar e localizar objetos remotos. |

---

## 💻 Estrutura do Projeto

A aplicação é dividida em três partes principais:

- **Interface Remota:** define os métodos que o cliente pode chamar.  
- **Servidor:** registra o objeto remoto e o disponibiliza na rede.  
- **Cliente:** acessa o objeto remoto e executa operações como se estivesse localmente.

---

## ⚖️ Vantagens e Limitações

### ✅ Vantagens
- Comunicação simples entre objetos distribuídos.  
- Totalmente implementado em Java, sem dependências externas.  
- Permite modularização e reutilização de código entre aplicações.  

### ❌ Limitações
- Funciona apenas entre aplicações Java.  
- Exige configuração de rede e execução do **RMI Registry**.  
- É uma tecnologia mais antiga, substituída por soluções modernas como **REST**, **gRPC** e **WebSockets**.

---

## 🧠 Conclusão

O **RMI** foi uma das primeiras formas de **computação distribuída em Java**.  
Apesar de ter perdido espaço para tecnologias mais modernas, ele continua sendo uma ferramenta valiosa para compreender **conceitos de redes, sistemas distribuídos e comunicação entre processos**.

