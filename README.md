# Automação do site "Tricentis Vehicle Insurance"
Implementação da automação do site "Tricentis Vehicle Insurance"

### Pré-Requisitos para a execução:
- Eclipse IDE for Java Developers ou Intellij IDEA
- Plugin do Cucumber específico para a IDE, no caso de execução diretamente pela feature

### Instalação

##### Importação pelo Eclipse
- Dentro da IDE, escolher opção de importar um projeto Git com "*smart import*"
- Informar a URI a ser clonada
- Se já não estiver selecionado, selecionar o branch "*main*"
- Continuar o processo usando as configurações padrão. O projeto deve ser criado como um "*Maven Project*"

### Execução
 
##### Diretamente da feature
- Abrir a feature ``src/test/resources/features/quote.feature`` e clicar no botão de execução

##### Maven
- Executar o comando ``mvn clean test``
- O teste é executado por padrão no browser Chrome mas pode ser executado no Firefox ou Edge
- (Opcional) Para executar em outro browser, acrescentar o VM argument ``-Dbrowser.type=<nome>`` (valores válidos: ``CHROME``, ``EDGE`` ou ``FIREFOX``)
