## ⚔️ Game Batalha - Projeto POO

🧝‍♀️ Vitoria Barbosa  
Trabalho final da matéria de **Programação Orientada a Objetos**.   
Vídeo que demonstra o funcionamento do projeto: [clique aqui](https://drive.google.com/file/d/1VL_AhXvWy8GooZExTBPHze--it4Tu6PF/view?usp=sharing)

### ☑️ Sobre o projeto  
Um jogo de batalha por turnos rodando no terminal, onde você cria personagens e duela até a morte! Desenvolvido em **Java** aplicando conceitos de Orientação a Objetos (Herança, Polimorfismo, Encapsulamento).

### 🎮 Como Funciona  
O jogo permite cadastrar guerreiros, magos, arqueiros ou monges. Eles lutam em duelos 1x1 até sobrar apenas um vencedor. O sistema gera logs detalhados de cada ataque e permite salvar os dados da batalha.

### 🛡️ Classes e Habilidades 
Cada personagem tem uma lógica única de combate:  

* **🏹 Arqueiro:** Tem 50% de chance de realizar um **Ataque Múltiplo** (dano multiplicado). 
* **⚔️ Guerreiro:** Possui defesa física. Se a vida cair abaixo de 30%, entra em **Fúria** (dano bônus). 
* **🧙‍♂️ Mago:** Ignora a defesa do Guerreiro e dá o dobro de dano no Arqueiro, mas sofre **10 de autodano** a cada ataque. 
* **🧘‍♂️ Monge:** Tem 25% de chance de **esquivar** totalmente de um ataque.  

### 🚀 Funcionalidades 
* ✅ **Criação de Personagens:** Defina nomes e atributos. 
* ✅ **Sistema de Duelos:** Combate automático com relatórios turno a turno. 
* ✅ **Persistência:** Opção de salvar o histórico da batalha em arquivo **JSON**. 
* ✅ **Replay:** Visualize todas as ações que ocorreram durante o jogo. 

### 🛠️ Tecnologias 
* **Java** (Linguagem principal) 
* **Lombok** (Para simplificação de código) 
* **Jackson** (Para salvar arquivos JSON) 
* **MVC** (Arquitetura do projeto) 

### 📂 Estrutura do Projeto   
O código está organizado nos seguintes pacotes:  

```text
src/
├── controller/      # Lógica de controle (Batalha, Personagem)
├── exceptions/      # Exceções personalizadas 
├── models/          # Classes modelos (Guerreiro, Mago, Batalha, Acao)
├── services/        # Serviços externos (DataProvider)
├── utils/           # Utilitários de entrada, validação e menus
└── view/            # Interação com o usuário (Menus e prints)
```
